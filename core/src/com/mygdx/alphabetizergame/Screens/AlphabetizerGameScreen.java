package com.mygdx.alphabetizergame.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.alphabetizergame.AlphabetizerGameWorld;
import com.mygdx.alphabetizergame.AplhabetizerGame;
import com.mygdx.alphabetizergame.GameRenderer;
import com.mygdx.alphabetizergame.Helpers.AssetLoader;
import com.mygdx.alphabetizergame.Helpers.InputHandler;

/**
 * Created by angelo_2 on 11/20/2014.
 */
public class AlphabetizerGameScreen implements Screen {

    private AlphabetizerGameWorld world;
    private GameRenderer renderer;
    private AplhabetizerGame game;

    private float runTime;

    public AlphabetizerGameScreen(AplhabetizerGame game){

        this.game = game;
        float gameHeight = Gdx.graphics.getHeight();
        float gameWidth = Gdx.graphics.getWidth();

        int midPointX = (int)gameWidth/2;
        int midPointY = (int)gameHeight/2;

        world = new AlphabetizerGameWorld((int)gameHeight, midPointX, (int)gameWidth, midPointY, game);
        renderer = new GameRenderer(world, (int)gameHeight, (int)gameWidth);

        Gdx.input.setInputProcessor(new InputHandler(world, game));
    }

    @Override
    public void render(float delta) {

        runTime += delta;
        world.updateRunning(delta);
        world.update(delta);
        renderer.render(runTime);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        Gdx.app.log("GameScreen", "show called");
    }

    @Override
    public void hide() {
        Gdx.app.log("GameScreen", "hide called");
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
    }
}
