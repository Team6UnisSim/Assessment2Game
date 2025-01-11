package io.github.universityTycoon.Events;

import io.github.universityTycoon.*;
import java.util.*;

/**
 * Abstract method used for all event hanlders to access the methods modifyScore and displayMessageToPlayer
 */
public class GameEventHandler {
    GameModel gameModel;
    // holds the responses to this event
    private Map<Integer, Response> eventResponses = new HashMap<>();
    

    /**
     * Constructor - GameEventHandler 
     * @param gameModel
     */
    public GameEventHandler (GameModel gameModel){
        this.gameModel = gameModel;
    }


    public Response getResponse(int responseID){
        Response response = eventResponses.get(responseID);
        if (response == null){
            return null;
        }
        return response;
    }


    public void handleResponse(int responseID, GameEvent event){
        Response response = getResponse(responseID);
        event.setResponseEffect(response.getEffect());
        event.setResponseDescription(response.getDescription());
    }


    /**
     * Main method that delegates logic of event handling to following 3
     */
    public void handle(GameEvent event){
        displayMessageToPlayer(event);
        placeIconOnMap(event);  
    }

    /**
     * Passes the event description and calls the GameModel method to add the message
     * 
     * @param event event's description to retrieve
     */
    public void displayMessageToPlayer(GameEvent event){
        gameModel.addDescriptionMessage(event.getDescription());
    } 



    /**
     * Finds a free tile on the map for placing the event icon. 
     * 
     * @param event event to process
     * @return coordinates of the placed icon
     */
    public void placeIconOnMap(GameEvent event){
        // retrieve the tile the event was placed at in MapController 
        gameModel.getMapController().placeEvent(event);
    }   
}
