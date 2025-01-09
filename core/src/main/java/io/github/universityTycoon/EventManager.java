package io.github.universityTycoon;

import java.time.LocalDateTime;
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
    private float eventInterval = 60; // 60 sec before first event is triggered
    private GameEvent currentActiveEvent; // holds the current event taking place 

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


    /**
     * Returns the current event -> taking place now
     */
    public GameEvent getCurrentActiveEvent(){
        return currentActiveEvent;
    }
    

    /**
     * Removes the current event as active -> when event finishes
     */
    public void clearActiveCurrentEvent(){
        currentActiveEvent = null;
    }



    /**
     * Picks event from eventMap based on their rarity value, higher >> more chance to be picked (i know it should be the reverse)
     * 
     * Example: (E1:2), (E2:1), (E3:4), (E4:3) -> (Event, rarity)
     * totalWeight = 10 (1+2+3+4), lowestRarity <= randomNumber <= totalWeight
     * if randomNumber = 4, randomNumber - 2 = 2, randomNumber - 1 = 1, randomNumber - 4 = -3 (so event E3 is picked as it )
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
                randomNumber -= entry.getValue(); // subtract event's rarities one by one until randomNum <= 0
                if (randomNumber <= 0){ // Pick this event that we just subtracted its rarity last
                    event.disableEvent(); // Disable it as to not be picked again - we want event variation in the game
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
    public void processEvents(float delta) {
        // increment this with every frame delta time
        timeSinceLastEvent += delta;

        // when interval reached, pick an event to generate
        if (timeSinceLastEvent >= eventInterval){
             
            GameEvent pickedEvent = pickRandomEvent();
            currentActiveEvent = pickedEvent; // Label it as current active event
            timeSinceLastEvent -= eventInterval; // reset
            eventInterval = generateRandomInterval(); // set this for the event to be generated

            if (pickedEvent != null){
                pickedEvent.setEventStartedAt(LocalDateTime.now());
                listener.raiseEvent(pickedEvent);
            }

            // create method to generate random interval between 40-70 sec every time an event is picked
            // generaterRandomInterval() 
        }
    }


    /**
     * Generate a random event time interval between 30-60 sec
     *  
     * @return new event time interval
     */
    public float generateRandomInterval(){
        Random random = new Random();
        // generate random number between 30-60 
        return 30 +  random.nextFloat() * 30; // times 30 cause this returns a float between 0 and 1
    }
}