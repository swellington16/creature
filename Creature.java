import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Scanner;
import java.util.Random;

public class Creature extends AbstractCreature implements Runnable
{

    public Creature()
    {
        this.name = "TEST_CREATURE";
        this.belly = "";
        this.tired_val = 0;
        this.feed_val = 0;
        this.grow_val = 0;
        this.isDead = false;
        this.hasEyes = true;
        this.hasMouth = true;
        this.hasTeeth = false;
        this.hasClaws = false;
        this.hasTongue = false;
    }
    
    public int getFeedVal()
    {
        return feed_val;
    }
    
    public int getGrowVal()
    {
        return grow_val;
    }
    
    public int getTiredVal()
    {
        return tired_val;
    }
    
    public boolean hasDied()
    {
        return isDead;
    }
    
    public void starve()
    {
        this.feed_val += 1;
        if(feed_val == STARVE_VAL)
        {
            this.isDead = true;
            System.out.println("[DEATH] The creature is dead.");
            System.out.println("[DEATH] Cause of death: Starvation");
        }
    }

    public void feed()
    {
        String filename = "food.foodtray";
        String food = "";
        try
        {
            this.nose = new BufferedReader(new FileReader(filename));
            String line = null;

            while((line = nose.readLine()) != null)
            {
                food = food + line;
            }

            this.nose.close(); //stop sniffing
        }
        catch(IOException e)
        {
            System.out.println("[ERR] Food tray cannot be opened/found for reading...");
        }

        try
        {
            if(!(food.equals("")))
            {
                int len = food.length() - 1;
                this.belly = this.belly + food.substring(0,len-1);
                this.mouth = new BufferedWriter(new FileWriter(filename));
                this.mouth.write(""); //feed
                this.mouth.close(); //stop feeding
                this.feed_val = 0; //reset feed value
                this.excrete(food.substring(len));
                this.grow(); 
            }
            else
            {
                System.out.println("...........");
            } 
        }
        catch(IOException e1)
        {
            System.out.println("[ERR] Food tray cannot be opened/found for writing...");
        }   
    }

    public void grow()
    {
        this.grow_val += 1;
        if(grow_val == TEETH_VAL)
        {
            this.hasTeeth = true;
            System.out.println("[DEV] The creature now has teeth...");
        }
        if(grow_val == CLAWS_VAL)
        {
            this.hasClaws = true;
            System.out.println("[DEV] The creature now has claws...");
        }
        if(grow_val == TONGUE_VAL)
        {
            this.hasTongue = true;
            System.out.println("[DEV] The creature now has a tongue...");
        }
        if(grow_val == DEATH_VAL)
        {
            this.isDead = true;
            System.out.println("[DEATH] The creature is dead.");
            System.out.println("[DEATH] Cause of death: Natural growth");
        }
    }

    public void excrete(String stuff)
    {
        String filename = "waste.txt";
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename,true));
            writer.write(stuff);
            writer.close();
        }
        catch(IOException ex)
        {
            System.out.println("[ERR] Creature cannot take a dump...");
        }
    }

    public void move()
    {
        this.tired_val += 1;
        this.run();
    }

    class Movement
    {
        Timer timer;
        Creature creature;
        boolean mustFeed;
        final int SECONDS = 1;

        public Movement(Creature c)
        {
            this.creature = c;
            this.timer = new Timer();
            this.timer.schedule(new MoveTask(), SECONDS*1000);
        }

        class MoveTask extends TimerTask
        {
            public void run()
            {
                System.out.println("[NEED] The creature needs to feed...");
                timer.cancel();
                promptFeeding();
            }
        }
        
        //"Fill the foodtray file for the creature to feed on
        public void fillTray()
        {
            try
            {
                BufferedWriter filler = new BufferedWriter(new FileWriter("food.foodtray"));
                filler.write("dghfskfsgfj");
                filler.close();
                System.out.println("[MAINTENANCE] Food tray is filled...");
            }
            catch(IOException ef)
            {
                System.out.println("[MAINTENANCE] The food tray cannot be filled...");
            }
        }
        
        public void promptFeeding()
        {
            this.creature.starve();
            if(!(this.creature.isDead))
            {
                this.fillTray(); //Fill the food tray for the creature
                this.creature.feed();
                if(!(this.creature.isDead))
                {
                    if(this.creature.getTiredVal() == this.creature.TIRED_VAL)
                    {
                        System.out.println("[NEED] The creature needs to sleep...");
                        int naptime = 5000; //5 second nap
                        System.out.println("[OCCURRENCE] The creature is sleeping...");
                        this.creature.rest(naptime);
                    }
                    this.creature.move();
                }
            }
        }
    }
    
    public void rest(int time)
    {
        try
        {
            Thread.sleep(time);
            this.tired_val = 0;
        }
        catch(InterruptedException ie)
        {
            System.out.println("[ERR] Error with creature sleeping: "+ie.getMessage());
        }
    }

    
    public void run()
    {
        new Movement(this);
        System.out.println("[OCCURRENCE] The creature is moving...");
    }

    public static void main()
    {
        Creature creature = new Creature();
        creature.move();
    }
}
