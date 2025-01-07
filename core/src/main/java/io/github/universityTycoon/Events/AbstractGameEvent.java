package io.github.universityTycoon.Events;

import io.github.universityTycoon.*;


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
        // to do
    }


    /**
     * Default placement logic: find a random free tile
     * Subclasses can override this for specific placement 
     * @return 
     */
    public int[] deterinePlacement(){
        return gameModel.getMapController().();
    }
}
