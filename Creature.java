package org.swellington16.creature;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Creature extends AbstractCreature implements Runnable
{

    public Creature()
    {
        this.name = "TEST_CREATURE";
        this.feed_val = 0;
        this.grow_val = 0;
        this.isDead = false;
        this.hasEyes = true;
        this.hasMouth = true;
        this.hasTeeth = false;
        this.hasClaws = false;
        this.hasTongue = false;
    }

    public void feed()
    {
        String filename = "food_tray.foodtray";
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
            System.out.println("Food tray cannot be opened/found for reading...");
        }

        try
        {
            if(!(food.equals("")))
            {
                this.mouth = new BufferedWriter(new FileWriter(filename));
                this.mouth.write(""); //feed
                this.mouth.close(); //stop feeding
                this.feed_val = 0; //reset feed value
                this.excrete();
                this.grow(); 
            }
            else
            {
                System.out.println("...........");
            } 
        }
        catch(IOException e1)
        {
            System.out.println("Food tray cannot be opened/found for writing...");
        }   
    }

    public void grow()
    {
        this.grow_val += 1;
        if(grow_val == TEETH_VAL)
        {
            this.hasTeeth = true;
        }
        if(grow_val == CLAWS_VAL)
        {
            this.hasClaws = true;
        }
        if(grow_val == TONGUE_VAL)
        {
            this.hasTongue = true;
        }
        if(grow_val == DEATH_VAL)
        {
            this.isDead = true;
            System.out.println("The creature is dead.");
        }
    }

    public void excrete()
    {
        String filename = "waste/dump"+this.grow_val+".txt";
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write("creature faeces");
        }
        catch(IOException ex)
        {
            System.out.println("Creature cannot take a dump...");
        }
    }

    public void move()
    {
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
                System.out.println("The creature needs to feed...");
                timer.cancel();
                promptFeeding();
            }
        }
        
        public void promptFeeding()
        {
            if(!(this.creature.isDead))
            {
                this.creature.feed();
                this.creature.move();
            }
        }
    }

    
    public void run()
    {
        new Movement(this);
        System.out.println("The creature is moving...");
    }

    public static void main()
    {
        Creature creature = new Creature();
        creature.move();
    }
}
