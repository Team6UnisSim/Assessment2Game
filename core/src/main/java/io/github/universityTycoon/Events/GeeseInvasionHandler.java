package io.github.universityTycoon.Events;

import io.github.universityTycoon.*;

public class GeeseInvasionHandler extends AbstractGameEvent implements GameEventHandler{

    public GeeseInvasionHandler(GameModel gameModel){
        super(gameModel);
    }
    
    /**
     * Handles Geese Invasion event
     */
    @Override
    public void handle(GameEvent event){

        modifyScore(event); // handled in the abstract class
         
        displayMessageToPlayer(event); // handled in the abstract class

        // add icon placement on map logic here
    }
}
