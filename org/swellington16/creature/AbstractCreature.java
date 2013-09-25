package org.swellington16.creature;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

public abstract class AbstractCreature
{
    protected String name; //name of creature
    protected int feed_val; //keeps track of when it should feed
    protected int grow_val; //keeps track of its growth
    protected int tired_val; //keeps track of how tired it is
    final protected int TIRED_VAL = 2; //value of move_val at which creature will get tired
    final protected int STARVE_VAL = 10; //value of feed_val at which creature will die
    final protected int DEATH_VAL = 10; //value of grow_val at which creature will die
    final protected int TEETH_VAL = 2; //value of grow_val at which creature will get teeth
    final protected int CLAWS_VAL = 5; //value of grow_val at which creature will get claws
    final protected int TONGUE_VAL = 7; //value of grow_val at which creature will get a tongue
    protected boolean isDead; //Is the creature dead?
    protected boolean isTired; //Is the creature tired?
    protected boolean hasEyes; //Does the creature have eyes?
    protected boolean hasMouth; //Does the creature have a mouth?
    protected boolean hasTeeth; //Does the creature have teeth?
    protected boolean hasClaws; //Does the creature have claws?
    protected boolean hasTongue; //Does the creature have a tongue?
    protected BufferedReader nose; //The creature's "nose"
    protected BufferedWriter mouth; //The creature's "mouth"
    
    public AbstractCreature()
    {
    }
    
    public void feed()
    {
    }
    
    public void starve()
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
