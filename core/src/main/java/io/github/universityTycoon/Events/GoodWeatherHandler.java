package io.github.universityTycoon.Events;

import io.github.universityTycoon.*;

public class GoodWeatherHandler extends AbstractGameEvent implements GameEventHandler{

    public GoodWeatherHandler(GameModel gameModel){
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
