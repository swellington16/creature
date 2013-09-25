creature
========

This is an extremly simple simulation of a living creature. 

The creature, represented by the Creature class, will "feed" by "consuming" (overwriting) the text it sniffs out
in the file, food.foodtray.  If this file has no text, the creature will starve, and each time the creature starves
as it tries to find food in the file, it will eventually die if it starves too often.  Additionally, each time the 
creature feeds, it "grows", and when it "grows" enough, it will die anyway, indicating natural death.

The creature "moves" by having a timer run.  When the timer runs out, the creature needs to feed, and so it reads the 
foodtray file.  If it reads the file and finds text, it will overwrite the text and "move again", and this is done in
a repetitive cycle until the creature's "grow value" becomes equal to its constant "death value", at which point it "dies",
and the creature's thread stops running. Also, each time the creature feeds, it "excretes" by taking the last character of
the text it reads in the foodtray file, and appends it ("passes it out") in the "waste.txt" file. Finally, each time 
the creature feeds successfully, its "feed value", the value that keeps track of how much it's starving, is reset to 0.

Each time the creature does not read any text, its feed value increments.  When the feed value equals its death value, the
creature "dies of starvation", and its thread halts. Furthermore when the creature moves a certain number of times, its 
thread sleeps (through the rest() method) for X seconds, and the creature's "tired value" (which increments each time
the creature "moves", and is compared with a constant of the same name to determine if the creature should sleep) is
reset to 0.

In this project's current version, the creature is automatically fed by the fillTray() method call, and if that line
is commented out or removed, the creature will starve if you run the main method.  
