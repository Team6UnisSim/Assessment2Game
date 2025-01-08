package io.github.universityTycoon.Events;

import io.github.universityTycoon.*;

public class FootballVictoryHandler extends AbstractGameEvent implements GameEventHandler{

    public FootballVictoryHandler(GameModel gameModel){
        super(gameModel);
    }
    
    /**
     * Handles Football Victory event
     */
    @Override
    public void handle(GameEvent event){

        modifyScore(event); // handled in the abstract class
         
        displayMessageToPlayer(event); // handled in the abstract class

        // add icon placement on map logic here
    }
}
