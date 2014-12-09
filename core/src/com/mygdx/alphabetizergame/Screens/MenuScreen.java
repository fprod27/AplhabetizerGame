package com.mygdx.alphabetizergame.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.alphabetizergame.AplhabetizerGame;
import com.mygdx.alphabetizergame.Helpers.AssetLoader;

import java.awt.Font;

/**
 * Created by angelo_2 on 12/8/2014.
 */
public class MenuScreen implements Screen {

    private AplhabetizerGame game;
    private Stage stage = new Stage();
    private Table table = new Table();
    private OrthographicCamera cam;

    private Skin skin;
    private ImageButton buttonPlay;
    private TextButton buttonClearHS;

    public MenuScreen(AplhabetizerGame game){

    this.game = game;
        cam = new OrthographicCamera();
        cam.setToOrtho(true,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        skin = new Skin();
        skin.add("playbuttonup", AssetLoader.playButtonUp);
        skin.add("playbuttondown", AssetLoader.playButtonDown);

        buttonPlay = new ImageButton(skin.getDrawable("playbuttonup"), skin.getDrawable("playbuttondown"));
        buttonPlay.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                 game.setScreen(new AlphabetizerGameScreen(game));
            }
        });

        TextButtonStyle style = new TextButtonStyle();
        BitmapFont fontdef = new BitmapFont();
        style.font = fontdef;
        buttonClearHS = new TextButton("Clear High Score", style);
        buttonClearHS.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AssetLoader.setHighScore(0);
                Gdx.app.log("Game", "High Score Cleared");
            }
        });

        table.add(buttonPlay).size(150,60).padBottom(20).row();
        table.add(buttonClearHS).size(150,60).padBottom(20).row();

        table.setFillParent(true);
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
