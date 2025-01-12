package io.github.universityTycoon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

/**
 * PlayerInputHandler handles the user inputs.
 *
 * @param PAUSE_KEY Assigns a specific key to the pause button.
 */
public class PlayerInputHandler {
    final int PAUSE_KEY = Input.Keys.P;

    /**
     * Retrieves the current mouse position on the screen.
     * @return A Vector2 object with the coordinates of the mouse.
     */
    public Vector2 getMousePos() {
        return new Vector2(Gdx.input.getX(), Gdx.input.getY());
    }

    /**
     * Checks if the left mouse button is currently being pressed.
     * 
     * @return true if the left mouse button is pressed, false otherwise.
     */
    public boolean getIsMouseDown() {
        try {
            return Gdx.input.isButtonPressed(Input.Buttons.LEFT);
        } catch (Exception e) {
            System.err.println("Error checking mouse down state: " + e.getMessage());
            return false; // Default fallback to not pressed.
        }
    }
    /**
     * Checks if the left mouse button was just clicked (pressed and released).
     * Testing was done here to make things more efficient and errorhandle edge
     * cases
     * 
     * @return true if the left mouse button was just clicked, false otherwise.
     */
    public boolean mouseJustClicked() {
        try {
            return Gdx.input.isButtonJustPressed(Input.Buttons.LEFT);
        } catch (Exception e) {
            System.err.println("Error checking mouse click state: " + e.getMessage());
            return false; // Default fallback to not clicked.
        }
    }

    /**
     * Checks if the pause key was just pressed.
     * 
     * @return true if the pause key was just pressed, false otherwise.
     */
    public boolean getIsPauseJustPressed() {
        try {
            return Gdx.input.isKeyJustPressed(PAUSE_KEY);
        } catch (Exception e) {
            System.err.println("Error checking pause key state: " + e.getMessage());
            return false; // Default fallback to not pressed.
        }
    }

    /**
     * Checks if a specified key was just pressed.
     * 
     * @param key The integer code representing the key to check.
     * @return true if the specified key was just pressed, false otherwise.
     */
    public boolean getKeyJustPressed(int key) {
        try {
            return Gdx.input.isKeyJustPressed(key);
        } catch (Exception e) {
            System.err.println("Error checking key press state for key: " + key + " - " + e.getMessage());
            return false; // Default fallback to not pressed.
        }
    }
}
