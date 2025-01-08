package io.github.universityTycoon.Events;

import io.github.universityTycoon.*;

public class CelebrityGuestHandler extends AbstractGameEvent implements GameEventHandler{

    public CelebrityGuestHandler(GameModel gameModel){
        super(gameModel);
    }
    
    /**
     * Handles Celebrity Guest event
     */
    @Override
    public void handle(GameEvent event){

        modifyScore(event); // handled in the abstract class
         
        displayMessageToPlayer(event); // handled in the abstract class

        // add icon placement on map logic here
    }
}
