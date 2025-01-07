package io.github.universityTycoon.Events;

import io.github.universityTycoon.GameEvent;

/*
 * Interfaces for class(es) handling the event (currently EventHandler class)
 */
public interface GameEventHandler {
    void handle(GameEvent event);
}