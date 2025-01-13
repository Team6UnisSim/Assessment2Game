package io.github.universityTycoon;

import io.github.universityTycoon.PlaceableObjects.Event;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * ADDED IN ASSESSMENT 2
 * Represents a specific instance of a event currently taking place.
 * Something that can happen during the course of the game. A pop-up is shown to the user which they can react to by
 * adding/removing buildings.
 */
public class GameEvent {

    private EventTypes eventType; // Enum type for event
    private String description;
    private float rarity; // 1(rare) - 5 (common)
    private String iconPath; // E.g. "assets/icons/goose_event.png"
    private Event mapEvent; // Corresponding MapObject for placement of the event icon -> used in EventHandler
    private boolean isActive; // True/false if active/inactive
    private LocalDateTime eventStartedAt;
    private float responseEffect; // the effect on the score the picked event response caused - this will be applied to the total score
    private String responseDescription; 
    private boolean dealtWith; // has this event been handled by the user.


    /**
     * Constructor for GameEvent
     *
     * @param eventType type of event
     * @param description description of the event
     * @param rarity how rare the event is
     * @param iconPath file path for the event's icon
     */
    public GameEvent(EventTypes eventType) {
        // tests if the eventtype is acceptable
        if (eventType == null) {
            throw new IllegalArgumentException("EventType cannot be null.");
        }
        this.eventType = eventType;
        this.isActive = true; // default is active
        this.description = eventType.getDescription();
        this.rarity = eventType.getRarity();
        this.iconPath = "images/event.jpeg";
        this.mapEvent = new Event(this); // Create the corresponding map Event in the PlaceableObjects package
        dealtWith = false;
    }


    /**
     * Retrieves the EventType of the current event
     * @return
     */
    public EventTypes getEventType(){
        return eventType;
    }


    /**
     * Retrieves description of current event
     * Handled via Testing
     * 
     * @return event description
     */
    public String getDescription() {
        return description != null ? description : "No description available.";
    }


    /**
     * Retrieves rarity of the current event
     * Done via Testing
     * 
     * @return event rarity
     */
    public float getRarity() {
        if (rarity < 1 || rarity > 5) {
            throw new IllegalStateException("Rarity must be between 1 and 5.");
        }
        return rarity;
    }


    /**
     * Retrieves icon file path of the current event
     * @return event icon path
     */
    public String getIconPath(){
        return iconPath;
    }
    
    public Event getMapEvent(){
        return mapEvent;
    }


    public void setEventStartedAt(LocalDateTime eventStartedAt) {
        this.eventStartedAt = eventStartedAt;
    }


    public boolean isActive(){
        return isActive;
    }

    public void disableEvent(){
        this.isActive = false;
    }


    public float getResponseEffect(){
        return responseEffect;
    }


    public String getResponseDescription(){
        return responseDescription;
    }


    public void setResponseEffect(float effect){
        this.responseEffect = effect;
    }


    public void setResponseDescription(String description){
        this.responseDescription = description;
    }

    public LocalDateTime getEventStartedAt() {
        return eventStartedAt;
    }

    public void setEventDealtWith(boolean eventDealtWith) {
        dealtWith = eventDealtWith;
    }

    public boolean getEventDealtWith() {
        return dealtWith;
    }

    /**
     * This function checks all the events against the current game time, and removes them.
     * Only one 
     * @param gameTime The current in game time.
     */
    public boolean updateEvent(LocalDateTime gameTime, int eventX, int eventY, GameModel gameModel) {
        // duration the event has been taking place
        Duration durationOfEvent = Duration.between(eventStartedAt, gameTime);

        if (durationOfEvent.getSeconds() >= 10000000 || dealtWith){ 
            gameModel.removeEvent(eventX, eventY);; // remove event
            return true;
        }
        //DO NOT CHANGE THE ABOVE FUNCTION IT WILL BREAK THE GAME --Adam (Testing)
        return false; // event is still ongoing
    }
}
