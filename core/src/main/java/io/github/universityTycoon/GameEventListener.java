package io.github.universityTycoon;

import io.github.universityTycoon.Events.*;
import java.util.function.Consumer;




/**
 * The GameEventListener "listens" for events triggered by the EventManager.
 * It then creates a handler to delegate the event handling to. It calls the handle method 
 * in GameEventHandler that deals with the event.
 *
 */
public class GameEventListener {

    private Consumer<GameEvent> eventHandler;


    /**
     * Constructor - GameEventListener
     * 
     * @param gameModel passes the current game model
     */
    public GameEventListener(Consumer<GameEvent> eventHandler) {  // by using this interace we can pass (this::handleEvent) to the listener during its construction
        this.eventHandler = eventHandler;
    }


    /**
     * Forwards the event to the gameModel which will call the approprate handler type
     * 
     * @param event triggered and passed from EventManager
     */
    public void raiseEvent(GameEvent event){
        // the GameModel.handleEvent method was passed to this during the construction of GameModel in the  
        // MainScreen in line 97 will be invoked here - see GameModel constructor
        eventHandler.accept(event); 
        // gameModel.handleEvent(event); old code, but this is the method i want to call
    }
}