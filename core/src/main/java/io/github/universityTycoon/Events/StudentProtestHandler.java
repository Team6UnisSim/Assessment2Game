package io.github.universityTycoon.Events;

import io.github.universityTycoon.*;

public class StudentProtestHandler extends AbstractGameEvent implements GameEventHandler{

    public StudentProtestHandler(GameModel gameModel){
        super(gameModel);
    }
    
    /**
     * Handles Student Protest event
     */
    @Override
    public void handle(GameEvent event){

        modifyScore(event); // handled in the abstract class
         
        displayMessageToPlayer(event); // handled in the abstract class

        // add icon placement on map logic here
    }
}
