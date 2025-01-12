import static org.mockito.Mockito.*;

import com.badlogic.gdx.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FirstScreenTest {
    private FirstScreen firstScreen;
    private ScreenManager mockScreenManager;

    @BeforeEach
    public void setUp() {
        mockScreenManager = mock(ScreenManager.class);
        firstScreen = new FirstScreen(mockScreenManager);
    }

    @Test
    public void testSwitchToMainScreenOnSpaceKey() {
        firstScreen.input = mock(PlayerInputHandler.class);
        when(firstScreen.input.getKeyJustPressed(Input.Keys.SPACE)).thenReturn(true);

        firstScreen.input(); // Simulate input handling

        verify(mockScreenManager).switchToMainScreen(); // Ensure the screen change happens
    }

    @Test
    public void testMouseClickTriggersScreenSwitch() {
        firstScreen.startButton = new com.badlogic.gdx.math.Rectangle(6.05f, 1f, 4f, 0.55f);
        firstScreen.mousePos.set(7f, 1.2f); // Simulate mouse position within the button
        firstScreen.mouseDown = true;

        firstScreen.logic(); // Check logic handling for button click

        verify(mockScreenManager).switchToMainScreen(); // Ensure it switches screens
    }
}
