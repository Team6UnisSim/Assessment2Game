package io.github.universityTycoon.Events;

import io.github.universityTycoon.*;

public class CoffeeMachineBreakdownHandler extends AbstractGameEvent implements GameEventHandler{

    public CoffeeMachineBreakdownHandler(GameModel gameModel){
        super(gameModel);
    }

    /**
     * Handles Coffee Machine Breakdown event
     */
    @Override
    public void handle(GameEvent event){

        modifyScore(event); // handled in the abstract class
         
        displayMessageToPlayer(event); // handled in the abstract class

        // add icon placement on map logic here
    }
}
