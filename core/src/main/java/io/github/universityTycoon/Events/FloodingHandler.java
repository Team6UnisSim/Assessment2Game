package io.github.universityTycoon.Events;

import io.github.universityTycoon.*;

/**
 * Deals with the logic of the event handling
 */
public class FloodingHandler extends AbstractGameEvent implements GameEventHandler {    

    /*
     * Constructor for EventHandler
     */
    public FloodingHandler(GameModel gameModel) {
        super(gameModel);
    }

    /**
     * Handles flooding event
     */
    @Override
    public void handle(GameEvent event){

        modifyScore(event); // handled in the abstract class
         
        displayMessageToPlayer(event); // handled in the abstract class
             
        // place icon on map using mapController
        String iconPath = event.getIconPath();

        MapController mapController = gameModel.getMapController();
        int[] freeTileCoordinates = mapController.findRandomFreeTile();
         
        if (freeTileCoordinates != null){
            int x = freeTileCoordinates[0];
            int y = freeTileCoordinates[1];
 
            boolean iconPlacedSuccesfully = mapController.addEvent(iconPath, x, y);
            if (!iconPlacedSuccesfully){
                throw new IllegalStateException("Flooding event handling failed, unable to place event icon on map");
            }
        }    
    }
}
