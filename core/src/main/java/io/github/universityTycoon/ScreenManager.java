package io.github.universityTycoon;

import java.util.HashMap;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 * ScreenManager extends the Game abstract class, and is used to control which screen is displayed.
 *
 * @param batch The batch which draws textures.
 * @param gameScreen An instance of the GameScreen class.
 * @param titleScreen An instance of the FirstScreen class.
 * @param endScreen An instance of the FinalScreen class, added during Assessment 2.
 * @param fullScreen A check for if the game is in fullscreen or not.
 * @param leaderboardNames
 * @param leaderboardScores
 * @param leaderboard A HashMap that stores the top 5 high scores, added during Assessment 2.
 *
 */

public class ScreenManager extends Game {

    public SpriteBatch batch;

    public MainScreen gameScreen;
    public FirstScreen titleScreen;
    public FinalScreen endScreen; 
    public ScoreEntryScreen scoreScreen;

    public Boolean fullScreen;

    private Preferences leaderboardNames;
    private Preferences leaderboardScores;
    public HashMap<Integer, SavedScore> leaderboard;

    /**
     * Create is responsible for setting all variables.
     * It is effectively the constructor.
     */
    public void create() {

        //Create instances of the screens, this allows access to non-static variables
        gameScreen = new MainScreen(this);
        titleScreen = new FirstScreen(this);
        endScreen = new FinalScreen(this);
        scoreScreen = new ScoreEntryScreen(this);

        batch = new SpriteBatch();

        leaderboardNames = Gdx.app.getPreferences("LeaderboardNames");
        leaderboardScores = Gdx.app.getPreferences("LeaderboardScores");
        leaderboard = new HashMap<>();

        for(int i = 1; i < 6; i++) {
            leaderboard.put(Integer.valueOf(i), new SavedScore(leaderboardNames.getString(String.valueOf(i), "-"), leaderboardScores.getFloat(String.valueOf(i), 0f)));
        }

        fullScreen = false;
        // Initiate game to the title screen.
        setScreen(titleScreen);
    }

    /**
     * Doesn't actually render anything, but instead is used to check for the user pressing F11 at any time.
     */
    public void render() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.F11)){
            fullScreen = Gdx.graphics.isFullscreen();
            Graphics.DisplayMode currentMode = Gdx.graphics.getDisplayMode();
            if (fullScreen)
                Gdx.graphics.setWindowedMode(currentMode.width, currentMode.height);
            else
                Gdx.graphics.setFullscreenMode(currentMode);
        }

        super.render();
    }

    // Disposes of all textures.
    public void dispose() {
        batch.dispose();
        GameModel.font.dispose();
        GameModel.smallerFont.dispose();
        GameModel.blackFont.dispose();

    }

    // Changes to main screen.
    public void switchToMainScreen() {
        setScreen(gameScreen);
    }

    // Changes to final screen and resets the main game, added during Assessment 2.
    public void switchToFinalScreen() {
        setScreen(endScreen);
        gameScreen.restartGame();
    }
}
