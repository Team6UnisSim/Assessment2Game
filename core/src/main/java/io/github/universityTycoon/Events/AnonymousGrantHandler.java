package io.github.universityTycoon.Events;

import io.github.universityTycoon.*;
import java.util.Map;
import java.util.HashMap;


/**
 * ADDED IN ASSESSMENT 2
 * A handler for the Anonymous Grant event that allows the player to respond to this event type.
 * 
 * @param eventResponses A Map holding reponses for this event type, which contains a description and a score effect.
 */
public class AnonymousGrantHandler extends GameEventHandler {
    
    private Map<Integer, Response> eventResponses = new HashMap<>(); 

    /**
     * Constructor taking the following parameters.
     * 
     * @param gameModel an instance of GameModel which is needed to access the array of MapObjects for placing icons
     */
    public AnonymousGrantHandler(GameModel gameModel){
        super(gameModel);
        initialiseEventResponses();
    }

    /**
     * Adds the 3 different responses to the map.
     */
    public void initialiseEventResponses(){
        eventResponses.put(1, new Response(10,"Launch a sustainability initiative for the campus."));
        eventResponses.put(2, new Response(0, "Host a celebration to honor the donor."));
        eventResponses.put(3, new Response(15, "Establish a new scholarship fund with restrictive criteria."));
    }

    /**
     * Returns the repsonse instance associated with responseID.
     * 
     * @param responseID the key for this instance of Response in eventResponses.
     * @return the associated instance of Response. 
     */
    public Response getResponse(int responseID){
        if (responseID < 1 || responseID > 3){
            throw new IllegalArgumentException("Response ID must be 1, 2 or 3");
        }
        return eventResponses.get(responseID);
    }

    /**
     * Returns the map of event responses.
     * 
     * @return the Map eventResponses.
     */
    public Map<Integer, Response> getAllResponses(){
        return eventResponses;
    }
}