
public abstract class AbstractCreature
{
    protected String name; //name of creature
    protected int feed_val; //keeps track of when it should feed
    protected int grow_val; //keeps track of its growth
    final protected int DEATH_VAL = 5; //value of feed_val at which creature will die
    protected boolean isDead; //Is the creature dead?
    protected boolean hasEyes; //Does the creature have eyes?
    protected boolean hasMouth; //Does the creature have a mouth?
    protected boolean hasTeeth; //Does the creature have teeth?
    protected boolean hasClaws; //Does the creature have claws?
    protected boolean hasTongue; //Does the creature have a tongue?
    
    public AbstractCreature()
    {
    }
    
    public void feed()
    {
    }
    
    public void grow()
    {
    }
    
    public void excrete()
    {
    }
    
    public void speak()
    {
    }
}
