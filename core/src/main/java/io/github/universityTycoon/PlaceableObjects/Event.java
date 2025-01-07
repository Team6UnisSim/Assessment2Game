package io.github.universityTycoon.PlaceableObjects;

// import java.time.LocalDateTime;

import io.github.universityTycoon.GameEvent;

/**
 * Represents events as placeable objects on the map. 
 * Doesn't deal with their logic.
 */
public class Event extends MapObject{

    private GameEvent gameEvent;


    /**
     * Constructor for Event class
     * @param gameEvent 
     */
    public Event(GameEvent gameEvent) {
        this.gameEvent = gameEvent;
    }

    // public void update(LocalDateTime currentGameTime){
    // }

    @Override
    public float calculateSatisfaction(){
        return 0; // satisfaction calculation logic is implemented elsewhere
    }

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


    @Override
    public int getSize(){
        return 1; // events occupy a single tile
    }
}