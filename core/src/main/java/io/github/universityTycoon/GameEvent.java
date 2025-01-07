package io.github.universityTycoon;

import io.github.universityTycoon.PlaceableObjects.Event;


import java.util.ArrayList;

/**
 * Represents a specific instance of a event currently taking place.
 * 
 * Something that can happen during the course of the game. A pop-up is shown to the user which they can react to by
 * adding/removing buildings.
 */
public class GameEvent {

    private EventType eventType; // Enum type for event
    private String description;
    private float rarity; // 1(rare) - 5 (common)
    private String iconPath; // E.g. "assets/icons/goose_event.png"
    private ArrayList<GameModifiers> modifiers; // The effects the event (e.g. flooding) applies (e.g. -3 score)
    private Event mapEvent; // Corresponding MapObject for placement of the event icon -> used in EventHandler
    private boolean isActive; // True/false if active/inactive


    /**
     * Constructor for GameEvent
     *
     * @param eventType type of event
     * @param description description of the event
     * @param rarity how rare the event is
     * @param iconPath file path for the event's icon
     */
    public GameEvent(EventType eventType){
        this.eventType = eventType;
        this.isActive = true; // default is active
        this.description = eventType.getDescription();
        this.rarity = eventType.getRarity();
        this.iconPath = eventType.getIconPath();
        this.modifiers = new ArrayList<>();
        this.mapEvent = new Event(this); // Create the corresponding map Event in the PlaceableObjects package
    }


    /**
     * Retrieves the EventType of the current event
     * @return
     */
    public EventType getEventType(){
        return eventType;
    }


    /**
     * Retrieves description of current event 
     * @return event description
     */
    public String getDescription(){
        return description;
    }


    /**
     * Retrieves rarity of the current event
     * @return event rarity
     */
    public float getRarity(){
        return rarity;
    }


    /**
     * Retrieves icon file path of the current event
     * @return event icon path
     */
    public String getIconPath(){
        return iconPath;
    }


    /**
     * Retrieve the list of existing modifiers
     */
    public ArrayList<GameModifiers> getModifiers(){
        return modifiers;
    }

    public Event getMapEvent(){
        return mapEvent;
    }

    public boolean isActive(){
        return isActive;
    }

    public void disableEvent(){
        this.isActive = false;
    }


    /**
     * Add a modifier to the list of modifiers (things that modify the score)
     * @param modifier
     */
    public void addModifier(GameModifiers modifier){
        modifiers.add(modifier);
    }
}