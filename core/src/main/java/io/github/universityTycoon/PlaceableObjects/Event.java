package io.github.universityTycoon.PlaceableObjects;

// import java.time.LocalDateTime;

import io.github.universityTycoon.GameEvent;

/**
 * ADDED IN ASSESSMENT 2
 * Represents events as placeable objects on the map (extending MapObject), but doesn't deal with their logic.
 * 
 * @param gameEvent the instance of GameEvent being represented on the map.
 */
public class Event extends MapObject {

    private GameEvent gameEvent;


    /**
     * Constructor taking the following parameters.
     * 
     * @param gameEvent the instance of GameEvent being represented on the map.
     */
    public Event(GameEvent gameEvent) {
        if (gameEvent == null) {
            throw new IllegalArgumentException("GameEvent cannot be null");
        }
        this.gameEvent = gameEvent;
    }

    /**
     * Returns the GameEvent associated with this instance of Event.
     * 
     * @return an instance of GameEvent.
     */
    public GameEvent getGameEvent(){
        return gameEvent;
    }

    /**
     * Returns the name of the GameEvent associated with this instance of Event.
     * 
     * @return the name for the given event type.
     */
    @Override
    public String getName(){
        return gameEvent.getEventType().name();
    }

    /**
     * Returns the texture path of the GameEvent associated with this instance of Event.
     * 
     * @return the texture path for the given event type.
     */
    @Override
    public String getTexturePath(){
        return gameEvent.getIconPath();
    }


    @Override
    public boolean getIsStackable(){
        return false; // events are not stackable
    }

    // As a percentage (not implemented)
    public float calculateSatisfaction(int ownX, int ownY, MapObject[][] mapObjects) {
        return 0f;
    }

    /**
     * Gets the width of the building.
     * @return The width of the building
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of the building.
     * @return The height of the building
     */
    public int getHeight() {
        return height;
    }

    public float getSatisfactionBonus() {
        return satisfactionBonus;
    }

}