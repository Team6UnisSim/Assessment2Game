package io.github.universityTycoon;

import io.github.universityTycoon.Events.*;
import java.util.Map;
import java.util.HashMap;


/**
 * The GameEventListener "listens" for events triggered by the EventManager.
 * It then creates a handler to delegate the event handling to. It calls the handle method 
 * in GameEventHandler that deals with the event.
 *
 */
public class GameEventListener {
    private GameModel gameModel; // reference to game model
    private Map<EventType, GameEventHandler> handlers = new HashMap<>();     // Links the event with their handler class


    /**
     * Constructor - GameEventListener
     * 
     * @param gameModel passes the current game model
     */
    public GameEventListener(GameModel gameModel) {
        this.gameModel = gameModel;
    }


    /**
     * Calls the appriopriate handler for the EventType passed to it.
     * To do that it retrieves the EventType to find the corresponding HanldlerType.
     * 
     * @param event triggered and passed from EventManager
     * @throws IllegalStateException if no handler is created or event type is invalid
     */
    public void raiseEvent(GameEvent event){
        EventType eventType = event.getEventType();

        // check if handler already exists in map, retreive it
        if (!handlers.containsKey(eventType)){ // 
            GameEventHandler handler = createHandler(eventType);
            if (handler != null){
                handlers.put(eventType, handler); // store the handler in the map
            } 
        }
        // Retrieve handler and invoke the appropriate Event-specific handler class
        GameEventHandler handler = handlers.get(eventType);
        if (handler == null){
            throw new IllegalStateException("handler for event type: " + eventType + "not found.");
        }
        // If eventType was flooding, a flooding handler object will be created and the corresponding class is invoked here
        handler.handle(event); 
    }


    // Creates and returns the corresponding handler based on EventType 
    private GameEventHandler createHandler(EventType eventType){
        
        switch (eventType){
            // Negative Events:
            case FLOODING: 
                return new FloodingHandler(gameModel);
            case HURRICANE: 
                return new HurricaneHandler(gameModel);
            case COFFEE_MACHINE_BREAKDOWN: 
                return new CoffeeMachineBreakdownHandler(gameModel);
            case STUDENT_PROTEST: 
                return new StudentProtestHandler(gameModel);
            // Positive Events:
            case CELEBRITY_GUEST: 
                return new CelebrityGuestHandler(gameModel);
            case FOOTBALL_VICTORY: 
                return new FootballVictoryHandler(gameModel);
            case ANONYMOUS_GRANT: 
                return new AnonymousGrantHandler(gameModel);
            case CULTURAL_FAIR:
                return new CulturalFairHandler(gameModel);
            // Neutral Events:
            case GOOD_WEATHER: 
                return new GoodWeatherHandler(gameModel);
            case GEESE_INVASION: 
                
            default:
                throw new IllegalArgumentException("Event type is invalid.");
        }
    }
}
