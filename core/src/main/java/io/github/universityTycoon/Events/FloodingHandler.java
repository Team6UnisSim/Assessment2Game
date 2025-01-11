package io.github.universityTycoon.Events;

import java.util.HashMap;
import java.util.Map;

import io.github.universityTycoon.*;

public class FloodingHandler extends GameEventHandler {  
    // a map that holds the responses deascription and its effect to this event, can be accessed by the response ID
    private Map<Integer, Response> eventResponses = new HashMap<>();   

    public FloodingHandler(GameModel gameModel) {
        super(gameModel);
        initialiseEventResponses();
    }

    public void initialiseEventResponses(){
        eventResponses.put(1, new Response(0,"Temporarily relocate affected classes to other parts of the campus."));
        eventResponses.put(2, new Response(-5, "Focus on immediate repair of only the most damaged buildings."));
        eventResponses.put(3, new Response(-20, "Commit to a long-term plan to repair all flood-affected areas so this doesn't happen again. This may delay immediate repairs."));
    }

    /**
     * Get specific response using response ID
     * @param responseID
     * @return
     */
    public Response getResponse(int responseID){
        if (responseID < 1 || responseID > 3){
            throw new IllegalArgumentException("Response ID must be 1, 2 or 3");
        }
        return eventResponses.get(responseID);
    }

    /**
     * Get all ther event responses as a map
     *
     */
    public Map<Integer, Response> getAllResponses(){
        return eventResponses;
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