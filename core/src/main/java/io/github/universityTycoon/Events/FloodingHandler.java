package io.github.universityTycoon.Events;

import io.github.universityTycoon.GameEvent;
import io.github.universityTycoon.GameModel;
import io.github.universityTycoon.MapController;
import io.github.universityTycoon.ScoreCalculator;
import io.github.universityTycoon.PlaceableObjects.Event;

/**
 * Deals with the logic of the event handling
 */
public class FloodingHandler implements GameEventHandler {

    GameModel gameModel; // GameModel holds all game-related data we might need access to
    MapController mapController;

    /*
     * Constructor for EventHandler
     */
    public EventHandler(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    /**
     * Method for handling the events based on their type, delegates the handling to 
     * event-specific methods (e.g., hanldeFloodingEvent)
     * 
     * @param GameEvent event to handle by retrieving its type
     */
    @Override
    public void handle(GameEvent event){

        // Get the current score object so score can be accessed
        ScoreCalculator score = gameModel.getScoreCalculator();

        // delegates the handling to event-specific methods based on event type
        switch (event.getEventType()){

            case FLOODING:
                handleFloodingEvent(event, score);
                break;
            case HURRICANE: 
                handleHurricaneEvent(event, score);
                break;
            case POWER_OUTAGE:
                handlePowerOutageEvent(event, score);
                break;
            case STUDENT_PROTEST:
                handleStudentProtestEvent(event, score);
                break;
            case CELEBRITY_GUEST:
                handleCelebrityGuestEvent(event, score);
                break;
            case FOOTBALL_VICTORY:
                handleFootballVictoryEvent(event, score);
                break;
            case ANONYMOUS_GRANT:
                handleAnonymousGrantEvent(event, score);
                break;
            case GOOD_WEATHER:
                handleGoodWeatherEvent(event, score);
                break;
            default:
                throw new IllegalArgumentException("Unknown event type");
        }
    }



    private void applyModifiers(GameEvent event){

       int newScore = score.calculateScore(event); // calculate new score

       MapController mapController = gameModel.get

    }


        // NEGATIVE EVENTS:

        /**
         * Hanldes a flooding event
         *     -> 1. Modify score: reduces score by 3
         *     -> 2. Place event icon on map randomly: flooding icon
         *     -> 3. Displayes a message to the player on the event and how to respond
         */
    private void handleFloodingEvent(GameEvent currentEvent, ScoreCalculator currentScore) throws Exception{

        // modify score using ScoreCalculator
        int modifiedScore = currentScore.calculateScore(currentEvent);
        gameModel.setSatisfactionScore(modifiedScore);
            
        // place icon on map using mapController
        int[] freeTileCoordinates = mapController.findRandomFreeTile();
        
        if (freeTileCoordinates != null){
            int x = freeTileCoordinates[0];
            int y = freeTileCoordinates[1];

            boolean iconPlacedSuccesfully = mapController.addEventIcon(currentEvent.getMapEvent(), x, y);
            if (!iconPlacedSuccesfully){
                throw new Exception("Flooding event handling failed, unable to place event icon on map");
            }
        }

        // display a message to player in main screen
        displayEventMessage(currentEvent.getDescription());
    }


    private void handleHurricaneEvent(GameEvent currentEvent, ScoreCalculator currentScore){
    }
    private void handlePowerOutageEvent(GameEvent currentEvent, ScoreCalculator currentScore){
    }
    private void handleStudentProtestEvent(GameEvent currentEvent, ScoreCalculator currentScore){
    }
    // POSITIVE EVENTS
    private void handleCelebrityGuestEvent(GameEvent currentEvent, ScoreCalculator currentScore){
    }
    private void handleFootballVictoryEvent(GameEvent currentEvent, ScoreCalculator currentScore){
    }
    private void handleAnonymousGrantEvent(GameEvent currentEvent, ScoreCalculator currentScore){
    }
    private void handleGoodWeatherEvent(GameEvent currentEvent, ScoreCalculator currentScore){
    }
}
