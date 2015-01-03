package com.mygdx.alphabetizergame.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.alphabetizergame.AplhabetizerGame;
import com.mygdx.alphabetizergame.Helpers.AssetLoader;
import com.mygdx.alphabetizergame.Renderers.GamerRendererARS;
import com.mygdx.alphabetizergame.Worlds.ARSGameWorld;

/**
 * Created by angelo_2 on 12/27/2014.
 */
public class ARSGameScreen extends AlphabetizerGameScreen {
    private ARSGameWorld world;
    private GamerRendererARS renderer;

    private float runTime;

    public ARSGameScreen(AplhabetizerGame game) {
        super(game);
        //gameHeight = screenHeight gameWidth = screenWidth
        //TODO rename gameHeight-->screenHeight gameWidth-->screenWidth
        float gameHeight = Gdx.graphics.getHeight();
        float gameWidth = Gdx.graphics.getWidth();
        float gameWidthReal = 1000;
        float gameHeightReal = gameHeight / (gameWidth / gameWidthReal);

        int midPointX = (int) gameWidthReal / 2;
        int midPointY = (int) gameHeightReal / 2;

        world = new ARSGameWorld((int) gameHeightReal, midPointX, (int) gameWidthReal, midPointY, game);

        renderer = new GamerRendererARS(world, (int) gameHeightReal, (int) gameWidthReal, game);
        InputProcessor one = renderer.stage;
        InputProcessor two = renderer.stageARSBUttons;
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(one);
        inputMultiplexer.addProcessor(two);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render(float delta) {
        runTime += delta;
        if(world.shouldStart) {
            world.updateRunning(delta);
        };
        renderer.renderARS(runTime);
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
            game.setScreen(new MenuScreen(game));
            Gdx.input.setCatchBackKey(true);
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void show() {
        if (world.isReady()) {
            world.start();
        }
    }

    @Override
    public void hide() {
        super.hide();
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
    }

}
