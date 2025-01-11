package io.github.universityTycoon.Events;

import io.github.universityTycoon.*;
import java.util.Map;
import java.util.HashMap;

public class CelebrityGuestHandler extends GameEventHandler {
    // a map that holds the responses deascription and its effect to this event, can be accessed by the response ID
    private Map<Integer, Response> eventResponses = new HashMap<>(); 

    public CelebrityGuestHandler(GameModel gameModel){
        super(gameModel);
        initialiseEventResponses();
    }


    /**
     * Initialises the response pool for this event
     */
    @Override
    public void initialiseEventResponses(){
        eventResponses.clear(); // clear responses to old events that occured
        eventResponses.put(1, new Response(0,"Host an exclusive dinner with selected top-performing students"));
        eventResponses.put(2, new Response(8, "Have the celebrity pose for photos around campus."));
        eventResponses.put(3, new Response(-20, "Organize a panel discussion with the celebrity and students"));
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