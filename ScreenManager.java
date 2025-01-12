package io.github.universityTycoon;

import java.util.ArrayList;
import java.util.Collections;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 * CHANGED IN ASSESSMENT 2 - leaderboard and additional screens added
 * ScreenManager extends the Game abstract class, and is used to control which screen is displayed.
 *
 * @param batch The batch which draws textures.
 * @param gameScreen An instance of the GameScreen class.
 * @param titleScreen An instance of the FirstScreen class.
 * @param endScreen An instance of the FinalScreen class.
 * @param fullScreen A check for if the game is in fullscreen or not.
 * @param leaderboardNames an instance of Preferences (https://libgdx.com/wiki/preferences for more info),
 * holds Integer keys and the associated String names.
 * @param leaderboardScores an instance of Preferences, holds Integer keys and aassociated Float scores.
 * @param leaderboard An ArrayList that stores the top 5 high scores.
 */

public class ScreenManager extends Game {

    public SpriteBatch batch;

    public MainScreen gameScreen;
    public FirstScreen titleScreen; // ADDED IN ASSESSMENT 2
    public FinalScreen endScreen; // ADDED IN ASSESSMENT 2

    public Boolean fullScreen;

    private Preferences leaderboardNames; // ADDED IN ASSESSMENT 2
    private Preferences leaderboardScores; // ADDED IN ASSESSMENT 2
    public ArrayList<SavedScore> leaderboard; // ADDED IN ASSESSMENT 2

    /**
     * CHANGED IN ASSESSMENT 2 - brings in preferences and creates array to be updated in-game.
     * Create is responsible for setting all variables.
     * It is effectively the constructor.
     */
    public void create() {

        //Create instances of the screens, this allows access to non-static variables
        gameScreen = new MainScreen(this);
        titleScreen = new FirstScreen(this);
        endScreen = new FinalScreen(this);

        batch = new SpriteBatch();

        // ADDED IN ASSESSMENT 2
        leaderboardNames = Gdx.app.getPreferences("LeaderboardNames");
        leaderboardScores = Gdx.app.getPreferences("LeaderboardScores");
        leaderboard = new ArrayList<>();

        // ADDED IN ASSESSMENT 2
        for(int i = 1; i < 6; i++) {
            leaderboard.add(new SavedScore(leaderboardNames.getString(String.valueOf(i), "PLAYER"), leaderboardScores.getFloat(String.valueOf(i), 0f)));
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

    /**
     * ADDED IN ASSESSMENT 2
     * Changes to the final screen and updates the leaderboard.
     * @param playerName the name of the player of the previous game
     * @param playerScore the score of the previous game
     */
    public void switchToFinalScreen(String playerName, float playerScore) {
        leaderboard.add(new SavedScore(playerName, playerScore));
        Collections.sort(leaderboard);
        leaderboard.remove(leaderboard.size() - 1);
        
        // Updates the preferences with the new leaderboard so they are saved for future games.
        for (int i = 0; i < leaderboard.size(); i++) {
            leaderboardNames.putString(String.valueOf(i + 1), leaderboard.get(i).getName());
            leaderboardScores.putFloat(String.valueOf(i + 1), leaderboard.get(i).getScore());
        }
        leaderboardNames.flush();
        leaderboardScores.flush();

        setScreen(endScreen);
        gameScreen.restartGame();
    }

    /**
     * ADDED IN ASSESSMENT 2
     * Changes current screen to ScoreSummaryScreen, passing in the gameModel of the previous game.
     */
    public void switchToScoreSummaryScreen() {
        setScreen(new ScoreSummaryScreen(this, gameScreen.gameModel));
    }
}
