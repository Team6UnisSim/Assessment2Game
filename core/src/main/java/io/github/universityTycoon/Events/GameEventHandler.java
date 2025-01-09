package io.github.universityTycoon.Events;

import io.github.universityTycoon.GameEvent;

/**
 * Interfaces that all handlers implement
 */
public interface GameEventHandler {
    void handle(GameEvent event);
    boolean isResolved(GameEvent event); // added this
}