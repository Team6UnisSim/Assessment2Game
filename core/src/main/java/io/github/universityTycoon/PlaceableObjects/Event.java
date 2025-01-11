package io.github.universityTycoon.PlaceableObjects;

// import java.time.LocalDateTime;

import io.github.universityTycoon.GameEvent;

/**
 * Represents events as placeable objects on the map. 
 * Doesn't deal with their logic.
 */
public class Event extends MapObject {

    private GameEvent gameEvent;


    /**
     * Constructor for Event class
     * @param gameEvent 
     */
    public Event(GameEvent gameEvent) {
        if (gameEvent == null) {
            throw new IllegalArgumentException("GameEvent cannot be null");
        }
        this.gameEvent = gameEvent;
    }

    // public void update(LocalDateTime currentGameTime){
    // }

    public GameEvent getGameEvent(){
        return gameEvent;
    }

    @Override
    public String getName(){
        return gameEvent.getEventType().name();
    }


    @Override
    public String getTexturePath(){
        return gameEvent.getIconPath();
    }


    @Override
    public boolean getIsStackable(){
        return false; // eventnts are not stackable
    }

    // As a percentage (not implemented)
    public float calculateSatisfaction(int ownX, int ownY, MapObject[][] mapObjects) {
        return 1f;
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