package io.github.universityTycoon.Events;

import io.github.universityTycoon.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Abstract method used for all event hanlders to access the methods modifyScore and displayMessageToPlayer
 */
public abstract class AbstractGameEvent {
    GameModel gameModel;


    public AbstractGameEvent(GameModel gameModel){
        this.gameModel = gameModel;
    }

    /**
     * Modifies satisfaction score based on the event-specific effect attribute
     */
    public void modifyScore(GameEvent event){
        ScoreCalculator scoreCalculator = gameModel.getScoreCalculator();
        float modifiedScore = scoreCalculator.calculateScore(event);
        gameModel.setSatisfactionScore(modifiedScore);
    }

    /**
     * Displays an event-specific message in the main screen for the player 
     */
    public void displayMessageToPlayer(GameEvent event){
        String eventDescription = event.getDescription();
        // communicate with the main screen via GameModel i think passing the description
        // this could also be done in the Event-specific handler directly rather than the abstract class
    }


    /**
     * Default placement logic: find a random free tile
     * Subclasses can override this for specific icon placement 
     */
    public int[] placeIconOnMap(GameEvent event){
        // find a random free tile to place icon
        int[] tileCoord = gameModel.getMapController().findRandomFreeTile();
        
        // send to main screen to draw the icon on map
        // to do - needs to communicate with:
        //    - MapController: handles logic of the Icon Placement
        //    - MainScreen: draws the actual icon on the map during the game
    }
}
