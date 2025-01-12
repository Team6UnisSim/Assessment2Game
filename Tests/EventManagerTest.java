package io.github.universityTycoon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EventManagerTest {
    private EventManager eventManager;
    private GameEventListener mockListener;

    @BeforeEach
    public void setUp() {
        mockListener = Mockito.mock(GameEventListener.class);
        eventManager = new EventManager(mockListener);
    }

    @Test
    public void testPickRandomEventThrowsExceptionWhenNoActiveEvents() {
        eventManager.eventMap.clear();
        assertThrows(IllegalStateException.class, eventManager::pickRandomEvent,
                "Should throw exception if no events available.");
    }

    @Test
    public void testPickRandomEventDisablesEventAfterSelection() {
        GameEvent event = new GameEvent(EventTypes.EVENT_B); // Replace with actual event type
        event.activate();
        eventManager.eventMap.put(event, 10);

        GameEvent pickedEvent = eventManager.pickRandomEvent();
        assertNotNull(pickedEvent, "Picked event should not be null.");
        assertFalse(pickedEvent.isActive(), "Picked event should be disabled after selection.");
    }

    @Test
    public void testGenerateRandomIntervalRange() {
        float interval = eventManager.generateRandomInterval();
        assertTrue(interval >= 30 && interval <= 60, "Generated interval should be between 30 and 60 seconds.");
    }
}
