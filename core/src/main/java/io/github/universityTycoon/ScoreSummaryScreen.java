package io.github.universityTycoon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * FirstScreen is an implementation of the screen interface.
 * It is used for the initial startup screen, and an instance of it is created within ScreenManager.
 *
 * @param batch The batch which draws textures.
 * @param input An instance of the PlayerInputHandler class, which handles inputs.
 * @param viewport The viewport things are displayed on.
 * @param startButton The rectangle used to check for mouse inputs on the start button.
 * @param mousePos The vector position of the mouse.
 * @param mouseDown The updated when the mouse is clicked.
 * @param music The music.
 *
 * @param game An instance of the ScreenManager class, used in the constructor so that stuff works.
 */
public class ScoreSummaryScreen implements Screen {
    SpriteBatch batch;
    PlayerInputHandler input;
    FitViewport viewport;

    Rectangle startButton;
    Vector2 mousePos;
    boolean mouseDown;

    Music music = Gdx.audio.newMusic(Gdx.files.internal("music/title.mp3"));

    Texture background;
    Texture start; 

    ShapeRenderer sr;

    GameModel gameModel;

    String[] playerName;
    int namePointer;
    GlyphLayout layout;

    final ScreenManager game;
    public ScoreSummaryScreen(ScreenManager main, GameModel gameModel) {
        this.game = main;
        this.gameModel = gameModel;
    }


    /**
     * Show is responsible for setting all variables.
     * It is effectively the constructor.
     */
    @Override
    public void show() {
        batch = new SpriteBatch();
        viewport = new FitViewport(16, 9);
        input = new PlayerInputHandler();

        music.setVolume(0.3f);
        music.setLooping(true);
        //music.play(); TURN THIS BACK ON

        startButton = new Rectangle();
        mousePos = new Vector2(0,0);

        background = new Texture(Gdx.files.internal("images/title_page.png"));
        start = new Texture(Gdx.files.internal("images/start.png"));

        sr = new ShapeRenderer();

        gameModel.achievementManager.checkGameEndAchievements(gameModel.getSatisfactionScore(), gameModel.getMapObjects());
    
        playerName = new String[6];
        namePointer = 0;
        layout = new GlyphLayout();
    }

    /**
     * Calls three functions which are used to split up the rendering method.
     *
     * @param v Not sure what this does, but it's part of the screen interface ¯\_(ツ)_/¯
     */
    @Override
    public void render(float v) {
        input();
        logic();
        draw();
    }

    /**
     * Resizes the viewport
     *
     * @param width The new width
     * @param height The new height
     */
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    /**
     * Does things when inputs are received.
     */
    private void input() {
        if (input.getKeyJustPressed(Input.Keys.ENTER)) {
            music.stop();
            changeScreen();
        }
        
        for (int i = 29; i < 55; i++) {
            if (input.getKeyJustPressed(i)) {
                if (namePointer < 5) {
                    playerName[namePointer] = Input.Keys.toString(i);
                    namePointer += 1;
                } else if (namePointer == 5 && playerName[namePointer] == null) {
                    playerName[namePointer] = Input.Keys.toString(i);
                }
            }
        }

        if (input.getKeyJustPressed(Input.Keys.BACKSPACE)) {
            playerName[namePointer] = null;
            if (!(namePointer == 0)) {
                namePointer -= 1;
            }
        }

        if (input.getIsMouseDown()) {
            mousePos = input.getMousePos();
            mouseDown = true;
        }
        else {mouseDown = false;}
    }


    /**
     * Deals with the logic of the screen.
     */
    private void logic() {
        Vector3 touch = new Vector3(mousePos.x, mousePos.y, 0);
        viewport.getCamera().unproject(touch);

        if (mouseDown && startButton.contains(touch.x, touch.y)) {
            music.stop();
            changeScreen();
        }
    }


    /**
     * Draws all textures on the screen.
     * Note: Batch.begin() and batch.end() must contain all draw statements, and cannot overlap with other begin/ends.
     */
    private void draw() {
        
        batch.begin();

        ScreenUtils.clear(Color.BLACK);

        startButton.set(3.8f, 0.7f, 4f, 0.55f);

        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);

        batch.draw(background, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());

        batch.end();

        sr.setProjectionMatrix(viewport.getCamera().combined);
        sr.begin(ShapeRenderer.ShapeType.Filled);

        sr.setColor((float) 84 / 255, (float) 120 / 255, (float) 125 / 255, 1);
        sr.rect(2.4f, 5.1f, 11f, 3.5f);
        sr.rect(2.4f, 1.4f, 11f, 3.5f);

        sr.setColor(0, 0, 0, 1);
        sr.rect(5.3f, 2.8f, 5.2f, 1.2f);

        sr.setColor(255f, 255f, 255f, 1);
        sr.rect(5.4f, 2.9f, 5f, 1f);
        
        sr.end();
        batch.begin();  
    
        GameModel.blackFont.draw(batch, "Final Student Satisfaction: " + Float.toString(gameModel.achievementManager.calculateNewSatisfactionScore(gameModel.getSatisfactionScore())) + "%", 2.6f, 8.4f);
        GameModel.blackFont.draw(batch, "Achievements Completed: ", 2.6f, 7.8f);

        float displayed = 0;
        for (int i = 0; i < gameModel.achievementManager.getAchievements().length; i++) {
            if (gameModel.achievementManager.getAchievements()[i] == true) {
                GameModel.blackFont.draw(batch, AchievementTypes.values()[i].name + ": " + AchievementTypes.values()[i].description, 2.6f, 7.4f - displayed);
                displayed += 0.4f;
            }
        }

        GameModel.blackFont.draw(batch, "Type your name here if you want to save your score:", 4.4f, 4.5f);
        GameModel.blackFont.getData().setScale(0.006f);
        for (int i = 0; i < playerName.length; i++) {
            if (playerName[i] == null) {
                layout.setText(GameModel.blackFont, "-");
                GameModel.blackFont.draw(batch, "-", 5.9f + (0.78f * i - layout.width / 2), 3.65f);
            } else {
                layout.setText(GameModel.blackFont, playerName[i]);
                GameModel.blackFont.draw(batch, playerName[i], 5.9f + (0.78f * i - layout.width / 2), 3.65f);
            }
        }
        GameModel.blackFont.getData().setScale(0.002f);

        batch.draw(start, 3.8f, 0.7f, 4, 0.55f);
        batch.draw(start, 8.3f, 0.7f, 4, 0.55f);

        batch.end();
    }

    public void changeScreen() {
        boolean completeName = true;
        for (String i : playerName) {
            if (i == null) {
                completeName = false;
            }
        }  

        if (completeName) {
            StringBuilder sb = new StringBuilder();
            for (String i : playerName) {
                sb.append(i);
            }
            String playerNameString = sb.toString();
            game.switchToFinalScreen(playerNameString, gameModel.achievementManager.calculateNewSatisfactionScore(gameModel.getSatisfactionScore()));  // Switch to MainScreen
        }
    }


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        background.dispose();
        start.dispose();
    }
}

