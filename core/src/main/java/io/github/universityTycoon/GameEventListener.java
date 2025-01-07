package io.github.universityTycoon;

import io.github.universityTycoon.Events.AnonymousGrantHandler;
import io.github.universityTycoon.Events.CafeMachineUpgradeHandler;
import io.github.universityTycoon.Events.CelebrityGuestHandler;
import io.github.universityTycoon.Events.CulturalFairHandler;
import io.github.universityTycoon.Events.FloodingHandler;
import io.github.universityTycoon.Events.FootballVictoryHandler;
import io.github.universityTycoon.Events.GameEventHandler;
import io.github.universityTycoon.Events.GoodWeatherHandler;
import io.github.universityTycoon.Events.HurricaneHandler;
import io.github.universityTycoon.Events.PowerOutageHandler;
import io.github.universityTycoon.Events.StudentProtestHandler;

import java.util.Map;
import java.util.HashMap;


/**
 * The GameEventListener "listens" for events triggered by the EventManager.
 * It then creates a handler to delegate the event handling to. It calls the handle method 
 * in GameEventHandler that deals with the event.
 *
 */
public class GameEventListener {

    GameEventHandler handler; // Each event raised -> 1 handler created for it
    private final Map<EventType, GameEventHandler> handlers = new HashMap<>();

    MapController 

    /**
     * Constructor - creates a Listener for each type and adds to the map of handlers
     * 
     * @param handler the handler to be notified when an event is raised
     */
    public GameEventListener() {
        
    }


    /**
     * Calls the appriopriate handler for the EventType passed to it.
     * To do that it retrieves the EventType to find the corresponding HanldlerType.
     * 
     * @param event triggered and passed from EventManager
     */
    public void raiseEvent(GameEvent event) throws Exception{

        EventType eventType = event.getEventType();

        if (!handlers.containsKey(eventType)){
            GameEventHandler handler = createHandler(eventType);
            if (handler != null){
                handlers.put(eventType, handler); // adds the handler to its map as it is created
            } else {
                throw new Exception("No handler was created, invalid eventType.");
                return;
            }
        }
        GameEventHandler handler = handlers.get(eventType);
        handler.handle(event);
    }


    // Creates and returns a corresponding Handler object based on EventType passed
    private GameEventHandler createHandler(EventType eventType){
        switch (eventType){
            // Negative Events
            case FLOODING: // 1
                return new FloodingHandler();
                break;
            case HURRICANE: // 2
                return new HurricaneHandler();
                break;
            case POWER_OUTAGE: // 3
                return new PowerOutageHandler();
                break;
            case STUDENT_PROTEST: // 4
                return new StudentProtestHandler();
                break;
            // Positive Events
            case CELEBRITY_GUEST: // 5
                return new CelebrityGuestHandler();
                break;
            case FOOTBALL_VICTORY: // 6
                return new FloodingHandler();
                break;
            case ANONYMOUS_GRANT: // 7
                return new AnonymousGrantHandler();
                break;
            case CULTURAL_FAIR: // 8
                return new CulturalFairHandler();
                break;
            // Neutral Events
            case GOOD_WEATHER: // 9
                return new GoodWeatherHandler();
                break;
            case CAFE_MACHINE_UPGRADE: //10
                return new CafeMachineUpgradeHandler();
                break;
            default:
                return null;
        }
    }
}
