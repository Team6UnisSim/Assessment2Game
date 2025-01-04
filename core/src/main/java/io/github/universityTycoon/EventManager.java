package io.github.universityTycoon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Map;


/**
 * Responsible for raising an event and dispatching it to the GameEventListener
 */
public class EventManager {
    private Map<GameEvent, Integer> eventMap = new HashMap<>();
    private GameEventListener listener;
    private float timeSinceLastEvent = 0.0f;
    private static final float EVENT_INTERVAL = 60.0f; // 60 sec between events

    /**
     * Assigns the GameEventListener
     * @param listener The listener that can process the event
     */
    public EventManager(GameEventListener listener) {
        this.listener = listener;
    }


    /**
     * Adds an event-rarity pair to eventMap
     * 
     * @param event event to be added
     * @param rarity event's rarity value
     */
    public void addEvent(GameEvent event, Integer rarity){

        if (rarity < 1 || rarity > 5){ // rarity scale from 1 (rare) to 5 (common)
            throw new IllegalArgumentException("Rarity value must be between 1 and 5.");
        }

        if (eventMap.containsKey(event)){
            throw new IllegalArgumentException("Event exists already.");
        }

        eventMap.put(event,rarity);
    }

    /**
     * Removes given event from  the map, checks if it exists first
     * 
     * @param event event to be removed
     * @param rarity event's rarity to be removed
     */
    public void removeEvent(GameEvent event){
        if (!eventMap.keySet().contains(event)){
            throw new IllegalArgumentException("Event does not exist.");
        }

        eventMap.remove(event); // removes pair
    }


    // ADDED THIS METHOD - HELPER METHOD FOR THE ONE BELOW

    /**
     * Picks event from eventMap based on their rarity value, higher >> more chance to be picked.
     * 
     * Example: (E1:1), (E2:2), (E3:3), (E4:4) -> (Event, rarity)
     * totalWeight = 10 (1+2+3+4), lowestRarity <= randomNumber <= totalWeight
     * E1 picked if randomNumber = 1 (10% chance)
     * E2 picked if randomNumber = 2,3 (20% chance)
     * E3 picked if randomNumber = 4,5,6 (30% chance)
     * E4 picked if randomNumber = 7,8,9,10 (40% chance)
     * 
     * @param eventMap map that contains all the events 
     * @return single GameEvent
     */
    public GameEvent pickRandomEvent(Map<GameEvent, Integer> eventMap) {
        int totalRarity = 0;

        // First, check if eventMap is empty
        if (eventMap.isEmpty()){
            throw new IllegalStateException("EventMap is empty");
        }

        for (int rarity : eventMap.values()){
            totalRarity += rarity;
        }
        // Generate random number between 1 and total rarity -> so 1 and 10
        int randomNumber = new Random().nextInt(totalRarity) + 1;

        for (Map.Entry<GameEvent, Integer> entry : eventMap.entrySet()){
            randomNumber -= entry.getValue();
            if (randomNumber <= 0){ // Once <= 0 we opick this event
                return entry.getKey();
            }
        }
        throw new IllegalStateException("Random weight calculation failed");
    }



    /**
     * Run this inside the main process loop (MainScreen.logic()). Raises events at random intervals determined by
     * the rarity of said event.
     * @param delta time in seconds since the last frame
     */
    public void processEvents(float delta) {

        // increment thiswith every frame delta time
        timeSinceLastEvent += delta;

        // when interval reached, pick an event to generate
        if (timeSinceLastEvent >= EVENT_INTERVAL){
            GameEvent currentEvent = pickRandomEvent(eventMap);
            timeSinceLastEvent -= EVENT_INTERVAL; // reset

            if (currentEvent != null){
                listener.raiseEvent(currentEvent);
            }
        }
    }
}
