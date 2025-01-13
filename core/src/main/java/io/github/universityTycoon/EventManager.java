package io.github.universityTycoon;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Random;

import java.util.Map;


/**
 * ADDED IN ASSESSMENT 2
 * Responsible for raising an event and dispatching it to the GameEventListener
 * 
 * @param eventMap keeps track of what event types have been raised, to avoid repetition.
 * @param listener the GameEventListener responsible for handling raised events.
 * @param timeSinceLastEvent the time since the last event in seconds.
 * @param eventInterval the time until the next event is triggered.
 * @param currentActiveEvent holds the current event taking place.
 * @param currentPlannedEvent holds the current planned event.
 * @param yearlyReportInterval interval to be checked against to trigger yearly report methods
 * @param startingYearlyScore year starting score of the year
 * @param endingYearlyScore year ending score of the year
 * @param startingNumOfBuildings year starting number of buildings on map
 * @param endingNumOfBuildings year ending number of buildings on map
 * @param numOfEvents number of events dealth with in one in-game year
 * @param gameModel gamemodel object
 */
public class EventManager {
    private Map<GameEvent, Integer> eventMap = new HashMap<>();
    private GameEventListener listener;
    private float timeSinceLastEvent = 0.0f;
    private float eventInterval = 60; // 60 sec before first event is triggered
    private GameEvent currentActiveEvent;  
    private GameEvent currentPlannedEvent;
    // yearly report variables
    private float yearlyReportInterval = 500f / 30f; // One in-game year is 166.67 seconds
    private float startingYearlyScore = 0.0f; 
    private float endingYearlyScore = 0.0f; 
    private int startingNumOfBuildings = 0;
    private int endingNumOfBuildings = 0; 
    private int numOfEvents = 0; 
    private GameModel gameModel; 


    /**
     * Constructor - Assigns the GameEventListener
     * @param listener The listener that can process the event
     * @param gameModel The game modell taht will be used to access event attributes and methods
     */
    public EventManager(GameEventListener listener, GameModel gameModel) {
        this.listener = listener;
        this.gameModel = gameModel;
        currentPlannedEvent = null;
        initialiseEventMap(); 
    }

    /**
     * Iterates over enum class EventTypes and adds all types to eventMap as active events
     */
    private void initialiseEventMap(){
        for (EventTypes eventType : EventTypes.values()){
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
     *  Get starting of year score
     */
    public float getStartingScore(){ 
        return startingYearlyScore;
    }

    /**
     *  Get ending of year score
     */
    public float getEndingScore(){ 
        return endingYearlyScore;
    }

    /**
     *  Get starting of year number of buildings
     */
    public int getStartingNumOfBuildings(){
        return startingNumOfBuildings;
    }

    /**
     * Get ending of year number of buildings
     */
    public int getEndingNumOfBuildings(){ 
        return endingNumOfBuildings;
    }

    /**
     * Set starting yearly score 
     * @param startingScore starting score -> float
     */
    public void setStartingYearlyScore(float startingScore){ 
        startingYearlyScore = startingScore;
    }

    /**
     * Set yearly ending score 
     * @param endingScore ending score -> float
     */
    public void setEndingYearlyScore(float endingScore){ 
        endingYearlyScore = endingScore;
    }

    /**
     * Set starting of year number of buildings
     * @param buildingNum number of buildings -> int
     */
    public void setStartingNumOfBuildings(int buildingsNum){ 
        startingNumOfBuildings = buildingsNum;
    }

    /**
     * Set ending of year number of buildings
     * @param buildingNum number of buildings -> int
     */
    public void setEndingNumOfBuildings(int buildingsNum){ 
        endingNumOfBuildings = buildingsNum;
    }

    /**
     * Retrieves current number of buildings on the map
     * @return current numOfBuildings -> int
     */
    public int getNumOfBuildings(){ 
        MapObject[][] mapObjects = gameModel.getMapObjects();
        if (mapObjects == null){
            return 0;
        }

        int total = 0;
        for (int i = 0; i < mapObjects.length; i++){ 
            for (int j = 0; j < mapObjects[i].length; j++){

                MapObject object = mapObjects[i][j];
                if (object instanceof Building){
                    total++;
                }
            }
        }
        return total;
    }

    /**
     * Generates yearly report and will pass to mainScreen  for drawing
     * Will pass all these to the MainScreen
     */
    public void generateYearlyReport(){
        // pass all the info to main screen to display it
        MainScreen.displayAnnualReport( 
            startingYearlyScore, 
            endingYearlyScore, 
            getEndingNumOfBuildings() - getStartingNumOfBuildings(),
            numOfEvents
        );
        numOfEvents = 0; // reset
    } 


    /**
     * Picks event from eventMap based on their rarity value, 
     * higher >> more chance to be picked (i know it should be the reverse)
     * 
     * Example: (E1:2), (E2:1), (E3:4), (E4:3) -> (Event, rarity)
     * totalWeight = 10 (1+2+3+4), lowestRarity <= randomNumber <= totalWeight
     * if randomNumber = 4, randomNumber - 2 = 2, randomNumber - 1 = 1, randomNumber - 4 = -3 (event E3 picked)
     * E1 picked if randomNumber = 2,3 (20% chance)
     * E2 picked if randomNumber = 1 (10% chance)
     * E3 picked if randomNumber = 7,8,9,10 (40% chance)
     * E4 picked if randomNumber = 4,5,6 (30% chance)
     * 
     * @return game event
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
            GameEvent event = entry.getKey();
            if (event.isActive()){
                randomNumber -= entry.getValue(); // subtract event's rarities one by one until randomNum <= 0
                if (randomNumber <= 0){ // Pick this event that we just subtracted its rarity last
                    event.disableEvent(); // Disable it as to not be picked again -> event variation
                    return event;
                }
            }
        } throw new IllegalStateException("Random event selection failed");
    }



    /**
     * Run this inside the main process loop (MainScreen.logic()). Raises events at random intervals determined by
     * the rarity of said event.
     * @param delta time in seconds since the last frame
     * @param gameTimeGMT current game time
     */
    public void processEvents(float delta, LocalDateTime gameTimeGMT) {

        // ----- Yearly report section code -----
        yearlyReportInterval -= delta;
        if (yearlyReportInterval <= 0.0f){
            // update starting values
            setStartingYearlyScore(endingYearlyScore); 
            setStartingNumOfBuildings(endingNumOfBuildings); 
            // update ending values to current values
            setEndingYearlyScore(gameModel.getSatisfactionScore());
            setEndingNumOfBuildings(getNumOfBuildings());

            generateYearlyReport();
            yearlyReportInterval = 500f / 3f; // reset to 166.67 seconds
        } // ----- Yearly report section code -----
        

        // increment this with every frame delta time
        timeSinceLastEvent += delta;

        // when interval reached, pick an event to generate
        if (timeSinceLastEvent >= eventInterval && currentPlannedEvent == null){
             
            GameEvent pickedEvent = pickRandomEvent();
            if (!pickedEvent.getEventType().getPlanned()) {
                currentActiveEvent = pickedEvent; // label it as current active event
                timeSinceLastEvent -= eventInterval; // reset
                eventInterval = generateRandomInterval(); // set this for the event to be generated
            } else {
                currentPlannedEvent = pickedEvent;
            }

            if (pickedEvent != null){
                pickedEvent.setEventStartedAt(gameTimeGMT);
                listener.raiseEvent(pickedEvent);
                numOfEvents++; // increment numOfEvents for yearly report
            }
        }
    }

    /**
     * Handles raised planned events.
     * @param delta time in seconds since the last frame.
     * @param gameTimeGMT the current game time.
     * @param pickedEvent the planned event that has been chosen.
     */
    public void processPlannedEvent(float delta, LocalDateTime gameTimeGMT, GameEvent pickedEvent) {
        if (gameTimeGMT.isAfter(pickedEvent.getEventStartedAt().plusDays(110))) { // delays event response
            currentActiveEvent = pickedEvent; // Label it as current active event
            timeSinceLastEvent -= eventInterval; // reset
            eventInterval = generateRandomInterval(); // set this for the event to be generated
            currentPlannedEvent = null;
        }

        // increment this with every frame delta time
        timeSinceLastEvent += delta;
    }

    /**
     * Returns the current planned event.
     * @return the current planned event -> GameEvent
     */
    public GameEvent getPlannedEvent() {
        return currentPlannedEvent;
    }

    /**
     * Generate a random event time interval between 30-60 sec
     * @return new event time interval -> float
     */
    public float generateRandomInterval(){
        Random random = new Random();
        return 30 +  random.nextFloat() * 30;
    }
}