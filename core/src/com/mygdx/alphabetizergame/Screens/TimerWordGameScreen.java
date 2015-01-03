package com.mygdx.alphabetizergame.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.alphabetizergame.AplhabetizerGame;
import com.mygdx.alphabetizergame.Helpers.AssetLoader;
import com.mygdx.alphabetizergame.InputHandlers.TimerWordInputHandler;
import com.mygdx.alphabetizergame.Renderers.GameRendererTimed;
import com.mygdx.alphabetizergame.Worlds.TimerWordGameWorld;

/**
 * Created by angelo_2 on 12/17/2014.
 */
public class TimerWordGameScreen extends AlphabetizerGameScreen {

    private TimerWordGameWorld timerWordGameWorld;
    private GameRendererTimed renderer;

    private float runTime;
    public TimerWordGameScreen(AplhabetizerGame game) {
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

        timerWordGameWorld = new TimerWordGameWorld((int)gameHeightReal, midPointX, (int)gameWidthReal, midPointY, game);
        renderer = new GameRendererTimed(timerWordGameWorld, (int)gameHeightReal, (int)gameWidthReal, game);
        InputProcessor one = renderer.stage;
        InputProcessor two = new TimerWordInputHandler(timerWordGameWorld, game, scaleFactorX, scaleFactorY, renderer);
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(one);
        inputMultiplexer.addProcessor(two);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render(float delta) {
        //super.render(delta);
        runTime += delta;
        if(timerWordGameWorld.shouldStart) {
            timerWordGameWorld.updateRunning(delta);
        }
        //timerWordGameWorld.update(delta);
        renderer.renderTimed(runTime);
        if(timerWordGameWorld.isHighScore() || timerWordGameWorld.isGameOver()){
            renderer.shouldClose = true;
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void show() {
        super.show();
        Gdx.app.log("TimerWorldGameScreen", "show called");
        //AssetLoader.load();

        if (timerWordGameWorld.isReady()) {
            timerWordGameWorld.start();
        }
    }

    @Override
    public void hide() {

        super.hide();
        dispose();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void dispose() {
        super.dispose();
        //AssetLoader.dispose();
    }


}
