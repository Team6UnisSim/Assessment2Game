package io.github.universityTycoon;

import io.github.universityTycoon.PlaceableObjects.Event;

import java.time.Duration;
import java.time.LocalDateTime;
import com.badlogic.gdx.Gdx;

/**
 * Represents a specific instance of a event currently taking place.
 * 
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
    private boolean dealtWith;


    /**
     * Constructor for GameEvent
     *
     * @param eventType type of event
     * @param description description of the event
     * @param rarity how rare the event is
     * @param iconPath file path for the event's icon
     */
    public GameEvent(EventTypes eventType){
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
 * Checks all the events against the current game time and removes them if their duration has exceeded a limit or if they have been dealt with.
 * 
 * @param gameTime The current in-game time.
 * @param eventX The x-coordinate of the event on the map.
 * @param eventY The y-coordinate of the event on the map.
 * @param gameModel The game model handling the game state.
 * @return true if the event was removed, false if it is still ongoing.
 * @throws IllegalArgumentException if gameTime or gameModel is null, or if coordinates are out of bounds.
 */
public boolean updateEvent(LocalDateTime gameTime, int eventX, int eventY, GameModel gameModel) {
    if (gameTime == null) {
        throw new IllegalArgumentException("gameTime cannot be null.");
    }
    if (gameModel == null) {
        throw new IllegalArgumentException("gameModel cannot be null.");
    }

    if (eventX < 0 || eventY < 0 || eventX >= gameModel.getMapWidth() || eventY >= gameModel.getMapHeight()) {
        throw new IllegalArgumentException("Event coordinates are out of bounds.");
    }

    // Check event duration
    if (eventStartedAt == null) {
        Gdx.app.error("GameEvent", "eventStartedAt is null. Ensure setEventStartedAt() is called before updateEvent().");
        return false; // Cannot proceed if event start time is not initialized
    }
    
    Duration durationOfEvent = Duration.between(eventStartedAt, gameTime);
    
    if (durationOfEvent.isNegative()) {
        Gdx.app.error("GameEvent", "Invalid gameTime: event started at " + eventStartedAt + ", but gameTime is " + gameTime + ".");
        return false; // Invalid time, ignore update
    }

    // Remove event if it has been handled or duration exceeded
    if (durationOfEvent.getSeconds() >= 10_000_000 || dealtWith) {
        gameModel.removeEvent(eventX, eventY); // remove event
        return true;
    }
    
    return false; // event is still ongoing
    }
}
