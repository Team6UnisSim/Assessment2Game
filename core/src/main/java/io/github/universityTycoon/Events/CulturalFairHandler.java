package io.github.universityTycoon.Events;

import io.github.universityTycoon.*;

public class CulturalFairHandler extends AbstractGameEvent implements GameEventHandler{

    public CulturalFairHandler(GameModel gameModel){
        super(gameModel);
    }

    /**
     * Handles Cultural Fair event
     */
    @Override
    public void handle(GameEvent event){

        modifyScore(event); // handled in the abstract class
         
        displayMessageToPlayer(event); // handled in the abstract class

        // add icon placement on map logic here
    }
}
