package com.mygdx.alphabetizergame.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.alphabetizergame.Worlds.FallingWordGameWorld;
import com.mygdx.alphabetizergame.AplhabetizerGame;
import com.mygdx.alphabetizergame.Renderers.GameRendererFalling;
import com.mygdx.alphabetizergame.Helpers.AssetLoader;
import com.mygdx.alphabetizergame.InputHandlers.FallingWordInputHandler;
import com.mygdx.alphabetizergame.Worlds.TimerWordGameWorld;

/**
 * Created by angelo_2 on 11/20/2014.
 */
public class FallingWordGameScreen extends AlphabetizerGameScreen {

    private FallingWordGameWorld fallingWordGameWorld;
    private GameRendererFalling renderer;

    private float runTime;

    public FallingWordGameScreen(AplhabetizerGame game){
        super(game);
        //gameHeight = screenHeight gameWidth = screenWidth
        //TODO rename gameHeight-->screenHeight gameWidth-->screenWidth
        float gameHeight = Gdx.graphics.getHeight();
        float gameWidth = Gdx.graphics.getWidth();
        float gameWidthReal = 1000;
        float gameHeightReal = gameHeight/(gameWidth/gameWidthReal);
        float scaleFactorX = gameWidth/gameWidthReal;
        float scaleFactorY = gameHeight/gameHeightReal;

        int midPointX = (int)gameWidthReal/2;
        int midPointY = (int)gameHeightReal/2;

        fallingWordGameWorld = new FallingWordGameWorld((int)gameHeightReal, midPointX, (int)gameWidthReal, midPointY, game);

        renderer = new GameRendererFalling(fallingWordGameWorld, (int)gameHeightReal, (int)gameWidthReal, game);
        InputProcessor one = renderer.stage;
        InputProcessor two = new FallingWordInputHandler(fallingWordGameWorld, game, scaleFactorX, scaleFactorY, renderer);
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(one);
        inputMultiplexer.addProcessor(two);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render(float delta) {
        runTime += delta;
        if(fallingWordGameWorld.shouldStart) {
            fallingWordGameWorld.updateRunning(delta);
        }
        renderer.renderFalling(runTime);
        if(fallingWordGameWorld.isHighScore() || fallingWordGameWorld.isGameOver()){
            renderer.shouldClose = true;
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        Gdx.app.log("FallingGameScreen", "show called");
        //AssetLoader.load();

        if (fallingWordGameWorld.isReady()) {
            fallingWordGameWorld.start();
        }
    }

    @Override
    public void hide() {
        Gdx.app.log("GameScreen", "hide called");
        dispose();
    }

    @Override
    public void pause() {
        Gdx.app.log("GameScreen", "pause called");
    }

    @Override
    public void resume() {
        Gdx.app.log("GameScreen", "resume called");
    }

    @Override
    public void dispose() {
        //AssetLoader.dispose();
    }
}
