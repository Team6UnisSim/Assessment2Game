package io.github.universityTycoon.Events;

import io.github.universityTycoon.*;

public class HurricaneHandler extends AbstractGameEvent implements GameEventHandler{

    public HurricaneHandler(GameModel gameModel){
        super(gameModel);
    }

    /**
     * Handles Hurricane event
     */
    @Override
    public void handle(GameEvent event){

        modifyScore(event); // handled in the abstract class
         
        displayMessageToPlayer(event); // handled in the abstract class

        // add icon placement on map logic here
    }
}
