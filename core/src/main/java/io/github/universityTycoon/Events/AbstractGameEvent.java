package io.github.universityTycoon.Events;

import io.github.universityTycoon.*;

/**
 * Abstract method used for all event hanlders to access the methods modifyScore and displayMessageToPlayer
 */
public abstract class AbstractGameEvent implements GameEventHandler{
    GameModel gameModel;
    

    /**
     * Constructor - AbstractGameEvent 
     * @param gameModel
     */
    public AbstractGameEvent(GameModel gameModel){
        this.gameModel = gameModel;
    }


    /**
     * Main method that delegates logic of event handling to following 3
     */
    @Override
    public void handle(GameEvent event){
        modifyScore(event);
        // displayMessageToPlayer(event); dealt with in mainScreen
        placeIconOnMap(event); 
        
    }

    /**
     * Modifies satisfaction score based on the event-specific effect value
     * 
     * @param event to process its score effect
     */
    public void modifyScore(GameEvent event){
        ScoreCalculator scoreCalculator = gameModel.getScoreCalculator();
        float modifiedScore = scoreCalculator.calculateScore(event);
        gameModel.setSatisfactionScore(modifiedScore);
    }


    /**
     * Passes the event description and calls the GameModel method to add the message
     * 
     * @param event event's description to retrieve
     */
    public void displayMessageToPlayer(GameEvent event){
        // this is implemented actually in the MainScreen - 
        // it retrieves the active event and retrieves its description and draws it
        // therefore this is unneccessary
    } 



    /**
     * Finds a free tile on the map for placing the event icon. 
     * 
     * @param event event to process
     * @return coordinates of the placed icon
     */
    public void placeIconOnMap(GameEvent event){
        // retrieve the tile the event was placed at in MapController 
        gameModel.getMapController().placeEvent(event);
    }   
}
