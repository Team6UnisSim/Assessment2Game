package io.github.universityTycoon.Events;

import io.github.universityTycoon.*;

public class CulturalFairHandler extends AbstractGameEvent{

    public CulturalFairHandler(GameModel gameModel){
        super(gameModel);
    }

    /**
     * Right now the AbstractEventHandler is invoked 
     * Main method that delegates logic of event specific handling - in that case override and imlement
     * 
     */
    // @Override
    // public int[] handle(GameEvent event){
    //     modifyScore(event);
    //     displayMessageToPlayer(event);
    //     return placeIconOnMap(event); // Return coordinates of placed icon in case it's needed
        
    // }
    
    // @Override
    // public void modifyScore(GameEvent event){
    //     // implement if needed to be event specific
    // }

    // @Override
    // public void displayMessageToPlayer(GameEvent event){
    //     // implement if needed to be event specific
    // } 

    // @Override
    // public int[] placeIconOnMap(GameEvent event){
    //     // implement if needed to be event specific
    // }   

    public boolean isResolved(GameEvent event){
        // implement
        return true;
    }
}