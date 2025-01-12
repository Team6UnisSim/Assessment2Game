package io.github.universityTycoon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import io.github.universityTycoon.PlaceableObjects.MapObject;
import io.github.universityTycoon.PlaceableObjects.Event;
import io.github.universityTycoon.Events.*;


import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;


/**
 * GameModel holds all the variables the game uses that may need access across multiple classes.
 *
 * @param YEARS_PER_MINUTE Conversion rate from real-world time to in-game years.
 * @param STARTING_YEAR The in-game starting year.
 *
 * @param DEFAULT_SELECTED_BUILDING_TYPE The default building type selected when the game starts.
 *
 * @param START_TIME_SECONDS Initial game time in seconds.
 * @param timeRemainingSeconds Remaining time in the game in seconds.
 *
 * @param font Primary font used for UI text rendering.
 * @param smallerFont Smaller version of the primary font for UI rendering.
 * @param blackFont Black-colored font used for certain UI elements.
 *
 * @param tilesWide Width of the map in tiles.
 * @param tilesHigh Height of the map in tiles.
 *
 * @param noBuildingTypes The total number of building types available in the game.
 * @param foodAndDrinkBuildingCount Number of cafeteria buildings.
 * @param accommodationBuildingCount Number of accommodation buildings.
 * @param leisureBuildingCount Number of leisure buildings.
 * @param teachingBuildingCount Number of teaching buildings.
 *
 * @param satisfactionScore The current satisfaction score.
 *
 * @param isPaused Indicates if the game is currently paused.
 *
 * @param gameState The current state of the game (in progress, paused, in menu, etc.).
 * @param eventManager Manages and dispatches in-game events.
 * @param eventListener Listener for game events, invoking actions in response to events.
 * @param scoreCalculator Calculates player scores based on game state.
 * @param audioSelector Selects which audio to play.
 * @param mapController Controls the map and its objects, updating the state of buildings.
 */
public class GameModel {

    // Game variables
    private final float YEARS_PER_MINUTE = 1f;
    private final int STARTING_YEAR = 2024;

    public final BuildingTypes DEFAULT_SELECTED_BUILDING_TYPE = BuildingTypes.SmallAccommodation;

    final float START_TIME_SECONDS = 300;
    public float timeRemainingSeconds = START_TIME_SECONDS;

    public static BitmapFont font;
    public static BitmapFont smallerFont;
    public static BitmapFont blackFont;

    public int tilesWide = 32;
    public int tilesHigh = 14;

    int noBuildingTypes;
    public int foodAndDrinkBuildingCount;
    public int accommodationBuildingCount;
    public int leisureBuildingCount;
    public int teachingBuildingCount;

    public float satisfactionScore;

    public boolean isPaused;
    
    // Objects
    GameState gameState;
    EventManager eventManager;
    GameEventListener eventListener;
    ScoreCalculator scoreCalculator;
    AudioSelector audioSelector;
    MapController mapController;
    AchievementManager achievementManager;

    // ---> ADDED <---
    // Links the eventType with their handlerType so the appropriate handler can be retrieved
    private Map<EventTypes, GameEventHandler> handlers;     
    HashMap<GameEvent, Float> handledEvents;

    /**
     * Enum representing the possible states of the game.
     */
    public enum GameState {
        inProgress,
        paused,
        inMenu,
        // ... extend as necessary
    }

    /**
     * The constructor is responsible for setting all variables.
     */
    public GameModel() {

        eventListener = new GameEventListener(this::handleEvent); // If you're confused, look into "Java listener pattern"
        eventManager = new EventManager(eventListener, this); // ######## ADDED FOR YEAR REPOR ########
        scoreCalculator = new ScoreCalculator();
        audioSelector = new AudioSelector();
        mapController = new MapController(tilesWide, tilesHigh);
        achievementManager = new AchievementManager();

        isPaused = false;

        noBuildingTypes = 4;

        font = new BitmapFont(Gdx.files.internal("ui/font.fnt"),
            Gdx.files.internal("ui/font.png"), false);

        smallerFont = new BitmapFont(Gdx.files.internal("ui/font.fnt"),
            Gdx.files.internal("ui/font.png"), false);

        blackFont = new BitmapFont(Gdx.files.internal("ui/arial.fnt"),
            Gdx.files.internal("ui/arial.png"), false);

        // font is 150x150 pixels, but we need to scale it down to fit on screen
        // We don't use a smaller initial font, as it's then hard to read when scaled up.
        font.setUseIntegerPositions(false);
        font.getData().setScale(0.003f, 0.003f);

        smallerFont.setUseIntegerPositions(false);
        smallerFont.getData().setScale(0.0015f, 0.0015f);

        blackFont.setUseIntegerPositions(false);
        blackFont.getData().setScale(0.002f, 0.002f);

        handlers =  new HashMap<>();
        handledEvents = new HashMap<>();
    }

    /**
     * Updates everything that should be updated every frame.
     * @param delta The time since the last frame.
     */
    public void runGame(float delta) {
        if (!getIsPaused()) {
            timeRemainingSeconds -= Gdx.graphics.getDeltaTime();
            mapController.updateBuildings(getGameTimeGMT());
            mapController.updateEvents(getGameTimeGMT());
            satisfactionScore = scoreCalculator.calculateScore(mapController.mapObjects);
            achievementManager.checkContinuousAchievements(satisfactionScore, timeRemainingSeconds);
        }
    }

    /**
     * Returns the current state of the map's objects.
     * @return A 2D array of MapObject representing objects on the map.
     */
    public MapObject[][] getMapObjects() {
        return mapController.mapObjects;
    }


    /**
     * Remove event from the MapObject grid
     * 
     * @param x x coordinate in MapObjedt of event to remove
     * @param y y coordinate in MapObjedt of event to remove
     */
    public void removeEvent(int x, int y){
        MapObject[][] mapObjects = getMapObjects();
        if (mapObjects[x][y] instanceof Event){
            mapObjects[x][y] = null;
        };
    }

    /**
     * Calculates the elapsed game time since the start.
     * @return The elapsed game time in seconds.
     */
    public float getTimeElapsed() {
        return START_TIME_SECONDS - timeRemainingSeconds;
    }

    /**
     * Gets the remaining time in the game.
     * @return The time remaining in seconds.
     */
    public float getTimeRemainingSeconds() {
        return timeRemainingSeconds;
    }

    /**
     * Gets the width of the map in tiles.
     * @return The width in tiles.
     */
    public int getTilesWide() {
        return tilesWide;
    }

    /**
     * Gets the height of the map in tiles.
     * @return The height in tiles.
     */
    public int getTilesHigh() {
        return tilesHigh;
    }

    /**
     * Returns whether the game is currently paused.
     * @return True if the game is paused, otherwise false.
     */
    public boolean getIsPaused() {
        return isPaused;
    }

    /**
     * Gets the count of cafeteria buildings.
     * @return The number of cafeteria buildings.
     */
    public int getFoodAndDrinkBuildingCount() {
        return foodAndDrinkBuildingCount;
    }

    /**
     * Gets the count of accommodation buildings.
     * @return The number of accommodation buildings.
     */
    public int getAccommodationBuildingCount() {
        return accommodationBuildingCount;
    }

    /**
     * Gets the count of leisure buildings.
     * @return The number of leisure buildings.
     */
    public int getLeisureBuildingCount() {
        return leisureBuildingCount;
    }

    /**
     * Gets the count of teaching buildings.
     * @return The number of teaching buildings.
     */
    public int getTeachingBuildingCount() {
        return teachingBuildingCount;
    }


    /**
     * Gets the current game state.
     * @return The current GameState.
     */
    public GameState getGameState() {
        return gameState;
    }


    /**
     * Gets the number of building types.
     * @return The number of building types in the game.
     */
    public int getNoBuildingTypes() {
        return noBuildingTypes;
    }


    // ---> ADDED <---
    /**
     * Returns EventManager object
     */
    public EventManager getEventManager(){
        return eventManager;
    }


    // ---> ADDED <---
    /**
     * Returns the current event -> taking place now
     */
    public GameEvent getCurrentActiveEvent(){
        return eventManager.getCurrentActiveEvent();
    }


    // ---> ADDED <---
    /**
     * Returns the current player satisfaction score.
     * @return The satisfaction score as a float.
     */
    public float getSatisfactionScore() {
        return satisfactionScore;
    }


    // ---> ADDED <---
    /**
     * Retrieves the score calculator object
     * @return
     */
    public ScoreCalculator getScoreCalculator(){
        return scoreCalculator;
    }


    // ---> ADDED <---
    /**
     * Updates the satisfaction score with a new value
     * @param updatedScore new updated value for the score
     */
    public void setSatisfactionScore(float updatedScore){
        satisfactionScore = updatedScore;
    }


    public void addDescriptionMessage(String message) {
        // Add logic to store or display the message to the player
        System.out.println("Event Message: " + message);
    }


    public MapController getMapController(){
        return mapController;
    }

    public Map<EventTypes, GameEventHandler> getHandlers() {
        return handlers;
    }

    public HashMap<GameEvent, Float> getHandledEvents() {
        return handledEvents;
    }

    // ---> IMPLEMENTED <---
    /**
     * Handles game events by doing something.
     * @param event The GameEvent to handle.
     */
    public void handleEvent(GameEvent event) {
        EventTypes eventType = event.getEventType(); // retrieve its type to call correct handler class
        
        // if map does not have handler - create it, store it inside map
        if (!handlers.containsKey(eventType)){ // 
            GameEventHandler handler = createHandler(eventType);
            if (handler != null){
                handlers.put(eventType, handler); 
            }
        }
        GameEventHandler handler = handlers.get(eventType);
        if (handler == null){
            throw new IllegalStateException("Handler for event type: " + eventType + "not found.");
        }

        // If eventType is flooding, flooding handler is created corresponding class methods are called  - for now it gores to AbstractGameHandler   
        handler.handle(event);  
    }


    // ---> ADDED <---  
    /**
     * HELPER METHOD FOR ABOVE: handleEvent()
     * Creates relevant type handler object based on eventType passed
     * 
     * @param eventType the type of the current event being processed
     * @return hanlder object of corresponding type, else throws exception
     */
    private GameEventHandler createHandler(EventTypes type) {
        return switch (type) {
            // Negative Events:
            case FLOODING -> new FloodingHandler(this);
            case HURRICANE -> new HurricaneHandler(this);
            case COFFEE_MACHINE_BREAKDOWN -> new CoffeeMachineBreakdownHandler(this);
            case STUDENT_PROTEST -> new StudentProtestHandler(this);
            // Positive Events:
            case CELEBRITY_GUEST -> new CelebrityGuestHandler(this);
            case FOOTBALL_VICTORY -> new FootballVictoryHandler(this);
            case ANONYMOUS_GRANT -> new AnonymousGrantHandler(this);
            case CULTURAL_FAIR -> new CulturalFairHandler(this);
            // Neutral Events:
            case GOOD_WEATHER -> new GoodWeatherHandler(this);
            case GEESE_INVASION -> new GeeseInvasionHandler(this);
            default -> null;
        };
    }


    /**
     * Converts the value in the timer to the relative game time
     * (for example, after 2 minutes of real world time, the game year might be 2026)
     * @return The current in-game time as a LocalDateTime object.
     */
    public LocalDateTime getGameTimeGMT() {
        // Not bothering with leap days for now. Only difference will be the game ends an in-game day or two early, but time will still be 5 minutes
        long inGameSeconds = (long)(getTimeElapsed() * (365 * 24 * 60) * YEARS_PER_MINUTE);
        long startOfYearSeconds = LocalDateTime.of(STARTING_YEAR, 1, 1, 0, 0).toEpochSecond(ZoneOffset.UTC);

        return LocalDateTime.ofEpochSecond(inGameSeconds + startOfYearSeconds, 0, ZoneOffset.UTC);
    }
}
