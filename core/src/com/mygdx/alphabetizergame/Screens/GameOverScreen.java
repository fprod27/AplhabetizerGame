package com.mygdx.alphabetizergame.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.alphabetizergame.AlphabetizerGameWorld;
import com.mygdx.alphabetizergame.AplhabetizerGame;
import com.mygdx.alphabetizergame.Helpers.AssetLoader;

/**
 * Created by angelo_2 on 12/8/2014.
 */
public class GameOverScreen implements Screen {

    private AplhabetizerGame game;
    private AlphabetizerGameWorld myWorld;
    private Stage stage = new Stage();
    private Table table = new Table();
    private Skin skin;
    private SpriteBatch batch;

    private ImageButton buttonPlay;
    private ImageButton buttonMenu;

    String highScore;
    BitmapFont font;

    public GameOverScreen(AplhabetizerGame game, AlphabetizerGameWorld world){

        this.game = game;
        this.myWorld = world;

        highScore = myWorld.getScore() + "";
        font = new BitmapFont();
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();

        batch = new SpriteBatch();
        batch.begin();

        font.setColor(Color.RED);
        font.draw(batch, highScore, Gdx.graphics.getWidth()/2 - highScore.length()/2, 30);
        if(myWorld.isHighScore()){
            batch.disableBlending();
            batch.draw(AssetLoader.highscoretexture, Gdx.graphics.getWidth()/2 - 150, Gdx.graphics.getHeight() - 160, 300, 150);
            batch.enableBlending();
        }
        batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        skin = new Skin();
        skin.add("playbuttonup", AssetLoader.playButtonUp);
        skin.add("playbuttondown", AssetLoader.playButtonDown);
        skin.add("menubuttonup", AssetLoader.menuButtonUp);
        skin.add("menubuttondown", AssetLoader.menuButtonDown);
        buttonPlay = new ImageButton(skin.getDrawable("playbuttonup"), skin.getDrawable("playbuttondown"));
        buttonMenu = new ImageButton(skin.getDrawable("menubuttonup"), skin.getDrawable("menubuttondown"));

        buttonPlay.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new AlphabetizerGameScreen(game));
            }
        });

        buttonMenu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
            }
        });

        table.add(buttonPlay).size(150,60).padBottom(20).row();
        table.add(buttonMenu).size(150,60).padBottom(20).row();

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
        //AssetLoader.dispose();
    }
}
