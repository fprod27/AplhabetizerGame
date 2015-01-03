package com.mygdx.alphabetizergame.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.*;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.alphabetizergame.AplhabetizerGame;
import com.mygdx.alphabetizergame.Helpers.AssetLoader;

public class MenuScreen extends AlphabetizerGameScreen {

    private Stage stage = new Stage();
    private Table tablePlay = new Table();
    private Table tableOptions = new Table();
    private OrthographicCamera cam;
    private SpriteBatch batch;

    private Skin skin;
    private TextButton buttonPlayFalling;
    private TextButton buttonPlayTimer;
    private TextButton buttonPlayChoose;
    private ImageButton highScoreScreenButton;

    private float runTime;
    private float sumRuntime;
    private float gameHeight;
    private float gameWidth;
    private float gameWidthReal;
    private float gameHeightReal;

    private int shouldCloseTo;

    public MenuScreen(AplhabetizerGame game){
        super(game);
        gameHeight = Gdx.graphics.getHeight();
        gameWidth = Gdx.graphics.getWidth();
        gameWidthReal = 1000;
        gameHeightReal = gameHeight/(gameWidth/gameWidthReal);
        cam = new OrthographicCamera();
        cam.setToOrtho(true, gameWidthReal,gameHeightReal);
        batch = new SpriteBatch();
        sumRuntime = 0;
    }

    @Override
    public void render(float delta) {
        runTime += delta;
        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        batch.begin();
        batch.setProjectionMatrix(cam.combined);

        int wordCubeWidth = 660;
        int wordCubeHeight = 300;
        batch.draw(AssetLoader.wordCube1, gameWidthReal/2 - wordCubeWidth/2, 60, wordCubeWidth, wordCubeHeight);
        AssetLoader.switchHighFont.drawMultiLine(batch, "ALPHABETIZER\nGAME", gameWidthReal / 2 - AssetLoader.switchHighFont.getBounds("ALPHABETIZER").width / 2, 150,AssetLoader.switchHighFont.getBounds("ALPHABETIZER").width, BitmapFont.HAlignment.CENTER);
        AssetLoader.switchHighFont.setColor(Color.BLACK);

        if(game.gameStarted) {
            batch.draw(AssetLoader.cubeSplashOpening.getKeyFrame(runTime), 0, 0, gameWidthReal, gameHeightReal - 1);
        }
        switch (shouldCloseTo){
            case 1:
                sumRuntime += Gdx.graphics.getDeltaTime();
                batch.draw(AssetLoader.cubeSplashClosing.getKeyFrame(sumRuntime), 0, 0, gameWidthReal, gameHeightReal - 1);
                stage.cancelTouchFocus();
                if (AssetLoader.cubeSplashClosing.isAnimationFinished(sumRuntime)) {
                    game.setScreen(new FallingWordGameScreen(game));
                }
                break;
            case 2:
                sumRuntime += Gdx.graphics.getDeltaTime();
                batch.draw(AssetLoader.cubeSplashClosing.getKeyFrame(sumRuntime), 0, 0, gameWidthReal, gameHeightReal - 1);
                stage.cancelTouchFocus();
                if (AssetLoader.cubeSplashClosing.isAnimationFinished(sumRuntime)) {
                    game.setScreen(new TimerWordGameScreen(game));
                }
                break;
            case 3:
                sumRuntime += Gdx.graphics.getDeltaTime();
                batch.draw(AssetLoader.cubeSplashClosing.getKeyFrame(sumRuntime), 0, 0, gameWidthReal, gameHeightReal - 1);
                stage.cancelTouchFocus();
                if (AssetLoader.cubeSplashClosing.isAnimationFinished(sumRuntime)) {
                    game.setScreen(new ARSGameScreen(game));
                }
                break;
            case 4:
                sumRuntime += Gdx.graphics.getDeltaTime();
                batch.draw(AssetLoader.cubeSplashClosing.getKeyFrame(sumRuntime), 0, 0, gameWidthReal, gameHeightReal - 1);
                stage.cancelTouchFocus();
                if (AssetLoader.cubeSplashClosing.isAnimationFinished(sumRuntime)) {
                    game.setScreen(new HighScoreScreen(game));
                }
                break;
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
        skin.add("highscorebuttonup", AssetLoader.highScoreButtonUP);
        skin.add("highscorebuttondown", AssetLoader.highScoreButtonDown);

        TextButtonStyle style = new TextButtonStyle();
        BitmapFont fontdef = AssetLoader.switchHighFont;
        fontdef.setScale(1.8f, -2.5f);
        style.font = fontdef;
        style.up = skin.getDrawable("playbuttonup");
        style.down = skin.getDrawable("playbuttondown");
        Color lightbluegreen = new Color(10f/255f,134f/255f,179f/255f,1);
        Color darkbluegreen = new Color(1f/255f,84f/255f,114f/255f,1);
        style.fontColor = lightbluegreen;
        style.downFontColor = darkbluegreen;
        buttonPlayFalling = new TextButton("Classic",style);
        buttonPlayFalling.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AssetLoader.clicksound.play();
                if (AssetLoader.cubeSplashOpening.isAnimationFinished(runTime)) {
                    shouldCloseTo = 1;
                }
            }
        });
        buttonPlayTimer = new TextButton("Timed",style);
        buttonPlayTimer.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AssetLoader.clicksound.play();
                if(AssetLoader.cubeSplashOpening.isAnimationFinished(runTime)) {
                    shouldCloseTo = 2;
                }
            }
        });
        buttonPlayChoose = new TextButton("ARS", style);
        buttonPlayChoose.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AssetLoader.clicksound.play();
                if (AssetLoader.cubeSplashOpening.isAnimationFinished(runTime)) {
                    shouldCloseTo = 3;
                }
            }
        });
        highScoreScreenButton = new ImageButton(skin.getDrawable("highscorebuttonup"), skin.getDrawable("highscorebuttondown"));
        highScoreScreenButton.getImageCell().size(100, 100);
        highScoreScreenButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AssetLoader.clicksound.play();
                if(AssetLoader.cubeSplashOpening.isAnimationFinished(runTime)) {
                    shouldCloseTo = 4;
                }
            }
        });
        tablePlay.add(buttonPlayChoose).size(400,150).row().pad(15);
        tablePlay.add(buttonPlayTimer).size(400,150).row();
        tablePlay.add(buttonPlayFalling).size(400,150).row().pad(15);
        tablePlay.setPosition(cam.viewportWidth/2, cam.viewportHeight/2);
        tableOptions.setPosition(0 + 100, cam.viewportHeight - 100);
        tableOptions.add(highScoreScreenButton).size(150, 150).padBottom(50);
        stage.addActor(tablePlay);
        stage.addActor(tableOptions);
        stage.getViewport().setCamera(cam);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() {
        dispose();
        game.gameStarted = true;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
    }
}
