package io.github.universityTycoon.Events;

import java.util.HashMap;
import java.util.Map;

import io.github.universityTycoon.*;


/**
 * ADDED IN ASSESSMENT 2
 * A handler for the Football Victory event that allows the player to respond to this event type.
 * 
 * @param eventResponses A Map holding reponses for this event type, which contains a description and a score effect.
 */
public class FootballVictoryHandler extends GameEventHandler {
    
    private Map<Integer, Response> eventResponses = new HashMap<>(); 

    /**
     * Constructor taking the following parameters.
     * 
     * @param gameModel an instance of GameModel which is needed to access the array of MapObjects for placing icons
     */
    public FootballVictoryHandler(GameModel gameModel) {
        super(gameModel);
        initialiseEventResponses();
    }

    /**
     * Adds the 3 different responses to the map.
     */
    public void initialiseEventResponses(){
        eventResponses.put(1, new Response(3,"Sell the team's shirts in the university shop."));
        eventResponses.put(2, new Response(0, "Give money that would be allocated to football pitch maintenance to the team members."));
        eventResponses.put(3, new Response(8, "Improve football club facilities."));
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