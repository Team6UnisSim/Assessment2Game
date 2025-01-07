package io.github.universityTycoon;

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
        initialiseEventMap(); 
    }

    // Iterates over enum class EventTypes and adds all types to eventMap as active events
    private void initialiseEventMap(){
        for (EventType eventType : EventType.values()){
            eventMap.put(new GameEvent(eventType), eventType.getRarity());
        }
    }


    // ADDED THIS HELPER METHOD FOR processEvents() METHOD

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
    public GameEvent pickRandomEvent() {
        int totalRarity = 0;

        // First, check if eventMap is empty
        if (eventMap.isEmpty()){
            throw new IllegalStateException("EventMap is empty");
        }

        // Calculate total rarity by adding all rarity values
        for (Map.Entry<GameEvent, Integer> entry : eventMap.entrySet()){
            if (entry.getKey().isActive()){
                totalRarity += entry.getValue();
            }
        }

        // Will only be 0 if  ther are no active events left
        if (totalRarity == 0){
            throw new IllegalStateException("No active events available.");
        }

        // Generate random number between 1 and total rarity -> so 1 - 10 in above example
        int randomNumber = new Random().nextInt(totalRarity) + 1;

        // iterate over map and select an event
        for (Map.Entry<GameEvent, Integer> entry : eventMap.entrySet()){
            GameEvent event = entry.getKey(); // Check if event has an active status
            if (event.isActive()){
                randomNumber -= entry.getValue(); // we keep subtracting the rarities until 0
                if (randomNumber <= 0){ // Once <= 0 we opick this event
                    event.disableEvent(); // disbale so it is not picked again
                    return event;
                }
            }
        } throw new IllegalStateException("Random event selection failed");
    }



    /**
     * Run this inside the main process loop (MainScreen.logic()). Raises events at random intervals determined by
     * the rarity of said event.
     * @param delta time in seconds since the last frame
     */
    public void processEvents(float delta) throws Exception {

        // increment thiswith every frame delta time
        timeSinceLastEvent += delta;

        // when interval reached, pick an event to generate
        if (timeSinceLastEvent >= EVENT_INTERVAL){
            GameEvent currentEvent = pickRandomEvent();
            timeSinceLastEvent -= EVENT_INTERVAL; // reset

            if (currentEvent != null){
                listener.raiseEvent(currentEvent);
            }
        }
    }
}
