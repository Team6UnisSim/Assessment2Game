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
        modifyScore(event);
        displayMessageToPlayer(event);
        placeIconOnMap(event);  
    }

    /**
     * Modifies satisfaction score based on the event-specific effect value
     * 
     * @param event to process its score effect
     */
    public void modifyScore(GameEvent event){
        ScoreCalculator scoreCalculator = gameModel.getScoreCalculator();
        float modifiedScore = scoreCalculator.calculateEventScoreChange(event);
        gameModel.setSatisfactionScore(modifiedScore);
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
