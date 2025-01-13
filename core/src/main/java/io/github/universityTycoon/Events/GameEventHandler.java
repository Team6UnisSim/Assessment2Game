package io.github.universityTycoon.Events;
import io.github.universityTycoon.*;
import java.util.*;

/**
 * ADDED IN ASSESSMENT 2
 * A handler for a generic event, which will be extended by each of the different event types.
 * 
 * @param gameModel an instance of GameModel which is needed to access the array of MapObjects for placing icons.
 * @param eventResponses A Map holding instances of Response, which contain a description and a score effect.
 */
public class GameEventHandler {

    GameModel gameModel;
    private Map<Integer, Response> eventResponses = new HashMap<>();
    

    /**
     * Constructor taking the following parameters. 
     * @param gameModel an instance of GameModel for accessing the map.
     */
    public GameEventHandler (GameModel gameModel){
        this.gameModel = gameModel;
    }

    /**
     * Returns the repsonse instance associated with responseID.
     * 
     * @param responseID the key for this instance of Response in eventResponses.
     * @return the associated instance of Response. 
     */
    public Response getResponse(int responseID){
        Response response = eventResponses.get(responseID);
        if (response == null){
            return null;
        }
        return response;
    }


    /**
     * Adds the event icon to the map.
     * 
     * @param event the instance of GameEvent currently being handled.
     */
    public void placeIconOnMap(GameEvent event){
        // retrieve the tile the event was placed at in MapController 
        gameModel.getMapController().placeEvent(event);
    }   
}
