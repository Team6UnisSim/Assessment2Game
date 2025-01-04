package io.github.universityTycoon;

import java.util.ArrayList;

/**
 * >>> Represents base class for events in the game.
 * >>> Impacts satisfaction score based on event specific attributes.
 * 
 * Something that can happen during the course of the game. A pop-up is shown to the user which they can react to by
 * adding/removing buildings.
 */
public class GameEvent {

    private EventTypes eventType; // Enum type for event
    private String description;
    private float rarity; // 1(rare) - 5 (common)
    private String iconPath; // E.g. "assets/icons/goose_event.png"
    private ArrayList<GameModifiers> modifiers; // The effects the event (e.g. flooding) applies (e.g. -3 score)


    /**
     * Constructor for GameEvent
     *
     * @param eventType type of event
     * @param description description of the event
     * @param rarity how rare the event is
     * @param iconPath file path for the event's icon
     */
    public GameEvent(EventTypes eventType, String description, float rarity, String iconPath){
        this.eventType = eventType;
        this.description = description;
        this.rarity = rarity;
        this.iconPath = iconPath;
        this.modifiers = new ArrayList<>();
    }


    /**
     * Add a modifier to the list of modifiers (so far only score is modified)
     * @param modifier
     */
    public void addModifier(GameModifiers modifier){
        modifiers.add(modifier);
    }


    /**
     * Retrieve the list of existing modifiers
     */
    public ArrayList<GameModifiers> getModifiers(){
        return modifiers;
    }


    /**
     * Updates satisfaction score via ScoreCalculator and updates the satisfactionScore in GameModel to the new one
     * @param gameModel 
     */

    public void triggerEvent(GameModel gameModel){

        // Get the current scoreCalculator object from GameModel
        ScoreCalculator scoreCalculator = gameModel.getScoreCalculator();

        // Add the modifiers of this event to the list of active modifiers in the ScoreCalculator class
        for (GameModifiers modifier : modifiers){ // so far we only have one modifier per event -> [-/+ sat.score]
            scoreCalculator.addActiveModifier(modifier);         
        }

        float scoreChange = scoreCalculator.calculateScore(this); // passes itself as the event
        float updatedScore = gameModel.getSatisfactionScore() + scoreChange;
        gameModel.setSatisfactionScore(updatedScore);
    }


    /**
     * Retrieves event description
     * @return event description
     */
    public String getDescription(){
        return description;
    }


    /**
     * Retrieves event rarity
     * @return event rarity
     */
    public float getRarity(){
        return rarity;
    }


    /**
     * Retrieves event icon path
     * @return event icon path
     */
    public String geticonPath(){
        return iconPath;
    }
}