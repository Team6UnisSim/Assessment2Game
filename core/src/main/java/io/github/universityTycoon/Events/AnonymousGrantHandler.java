package io.github.universityTycoon.Events;

import io.github.universityTycoon.*;

public class AnonymousGrantHandler extends AbstractGameEvent implements GameEventHandler{

    public AnonymousGrantHandler(GameModel gameModel){
        super(gameModel);
    }

    /**
     * Handles anonymouns grant event
     */
    @Override
    public void handle(GameEvent event){

        modifyScore(event); // handled in the abstract class
         
        displayMessageToPlayer(event); // handled in the abstract class

        // add icon placement on map logic here
    }
}