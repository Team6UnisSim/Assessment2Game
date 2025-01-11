package io.github.universityTycoon;

import java.util.function.Consumer;

/**
 * The GameEventListener "listens" for events triggered by the EventManager.
 * It then creates a handler to delegate the event handling to.
 * When created in GameModel it was passed the GamneModel.handleEvents which it accets in raiseEvent()
 * essentially calling GameModel.handleEvent 
 */
public class GameEventListener {

    private Consumer<GameEvent> eventHandler; 

    /**
     * Constructor - GameEventListener 
     * Implements Consumer class to be alke to accept events in its constructor
     * 
     * @param eventHandler eventHandler class passed 
     */
    public GameEventListener(Consumer<GameEvent> eventHandler) {  // by using this interace we can pass (this::handleEvent) to the listener during its construction
        this.eventHandler = eventHandler;
    }


    /**
     * Forwards the event to the GameModel which will call the approprate handler
     * 
     * @param event triggered in and passed from EventManager
     */
    public void raiseEvent(GameEvent event){
        eventHandler.accept(event); // GameModel.hanldleEvents() was passed to it in its construction in GameModel
    }
}