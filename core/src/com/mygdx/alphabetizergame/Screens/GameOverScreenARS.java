package com.mygdx.alphabetizergame.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.alphabetizergame.AplhabetizerGame;
import com.mygdx.alphabetizergame.Helpers.AssetLoader;
import com.mygdx.alphabetizergame.Worlds.ARSGameWorld;
import com.mygdx.alphabetizergame.Worlds.FallingWordGameWorld;

/**
 * Created by angelo_2 on 12/27/2014.
 */
public class GameOverScreenARS extends AlphabetizerGameScreen {

    private ARSGameWorld myWorld;
    private Stage stage = new Stage();
    private Table table = new Table();
    private Skin skin;
    private SpriteBatch batch;

    private ImageButton buttonPlay;
    private ImageButton buttonMenu;
    private OrthographicCamera cam;

    private float runTime;
    private float sumRuntime;
    private float gameHeight;
    private float gameWidth;
    private float gameWidthReal;
    private float gameHeightReal;

    private boolean shouldCloseToWorld = false;
    private boolean shouldCloseToMenu = false;
    private boolean shouldDisplayHSTexture = false;

    private String highScore;

    public GameOverScreenARS(AplhabetizerGame game, ARSGameWorld world) {
        super(game);
        gameHeight = Gdx.graphics.getHeight();
        gameWidth = Gdx.graphics.getWidth();
        gameWidthReal = 1000;
        gameHeightReal = gameHeight/(gameWidth/gameWidthReal);
        cam = new OrthographicCamera();
        cam.setToOrtho(true, gameWidthReal,gameHeightReal);

        this.myWorld = world;

        highScore = myWorld.getScore() + "";
        batch = new SpriteBatch();
        sumRuntime = 0;

        if(myWorld.getScore() > AssetLoader.getHighScoreARS()){
            AssetLoader.setHighSCoreARS(myWorld.getScore());
            shouldDisplayHSTexture = true;
        }
    }

    @Override
    public void render(float delta) {
        runTime += delta;
        if(myWorld.isGameOver()){
            Gdx.gl.glClearColor(255/255f, 112/255f, 112/255f, 1);
        }
        else {
            Gdx.gl.glClearColor(255, 255, 255, 1);
        }
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();

        batch.begin();
        batch.setProjectionMatrix(cam.combined);


        AssetLoader.stopWatchFont.draw(batch, highScore, gameWidthReal/2 - AssetLoader.stopWatchFont.getBounds(highScore).width/2, 0 - (AssetLoader.stopWatchFont.getBounds(highScore).height + 20));
        if(shouldDisplayHSTexture){
            batch.disableBlending();
            batch.draw(AssetLoader.hsTexture, gameWidthReal/2 - 150, 0 + 150, 300, 300);
            batch.enableBlending();
        }
        batch.draw(AssetLoader.cubeSplashOpening.getKeyFrame(runTime), 0,0,gameWidthReal, gameHeightReal - 1);
        shouldCloseToMenu();
        shouldCloseToWorld();
        batch.end();
    }
    @Override
    public void show() {
        Gdx.app.log("GameOverScreen", "show called");
        skin = new Skin();
        skin.add("playbuttonup", AssetLoader.replayButtonUP);
        skin.add("playbuttondown", AssetLoader.replayButtonDown);
        skin.add("menubuttonup", AssetLoader.menuButtonUp);
        skin.add("menubuttondown", AssetLoader.menuButtonDown);

        buttonPlay = new ImageButton(skin.getDrawable("playbuttonup"), skin.getDrawable("playbuttondown"));
        buttonMenu = new ImageButton(skin.getDrawable("menubuttonup"), skin.getDrawable("menubuttondown"));
        buttonPlay.getImageCell().size(150,150);
        buttonMenu.getImageCell().size(150,150);
        buttonPlay.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AssetLoader.clicksound.play();
                if(AssetLoader.cubeSplashOpening.isAnimationFinished(runTime)) {
                    shouldCloseToWorld = true;
                }
                shouldCloseToWorld = true;
            }
        });

        buttonMenu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AssetLoader.clicksound.play();
                if(AssetLoader.cubeSplashOpening.isAnimationFinished(runTime)) {
                    shouldCloseToMenu = true;
                }
            }
        });

        table.add(buttonPlay).size(150,150).padBottom(20).row();
        table.add(buttonMenu).size(150,150).padBottom(20).row();
        table.setPosition(cam.viewportWidth/2, cam.viewportHeight/2);
        stage.addActor(table);
        stage.getViewport().setCamera(cam);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
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
    private void shouldCloseToWorld(){
        if(this.shouldCloseToWorld) {
            sumRuntime += Gdx.graphics.getDeltaTime();
            batch.draw(AssetLoader.cubeSplashClosing.getKeyFrame(sumRuntime), 0, 0, gameWidthReal, gameHeightReal - 1);
            if (AssetLoader.cubeSplashClosing.isAnimationFinished(sumRuntime)) {
                game.setScreen(new ARSGameScreen(game));
            }
        }
    }
    private void shouldCloseToMenu(){
        if(this.shouldCloseToMenu) {
            sumRuntime += Gdx.graphics.getDeltaTime();
            batch.draw(AssetLoader.cubeSplashClosing.getKeyFrame(sumRuntime), 0, 0, gameWidthReal, gameHeightReal - 1);
            stage.cancelTouchFocus();
            if (AssetLoader.cubeSplashClosing.isAnimationFinished(sumRuntime)) {
                game.setScreen(new MenuScreen(game));
            }
        }
    }

}
