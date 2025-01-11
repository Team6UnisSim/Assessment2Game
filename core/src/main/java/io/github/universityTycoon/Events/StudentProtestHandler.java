package io.github.universityTycoon.Events;

import io.github.universityTycoon.*;
import java.util.Map;

public class StudentProtestHandler extends GameEventHandler {
    
    public StudentProtestHandler(GameModel gameModel){
        super(gameModel);
        initialiseEventResponses();
    }


    /**
     * Initialises the response pool for this event
     */
    @Override
    public void initialiseEventResponses(){
        eventResponses.clear(); // clear responses to old events that occured
        eventResponses.put(1, new Response(0,"Hold a meeting with student representatives to understand their concerns and find solutions."));
        eventResponses.put(2, new Response(-20, "Disperse the protest and enforce stricter regulations on campus demonstrations."));
        eventResponses.put(3, new Response(-12, "Provide temporary solutions to address the immediate issues raised by the protestors."));
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