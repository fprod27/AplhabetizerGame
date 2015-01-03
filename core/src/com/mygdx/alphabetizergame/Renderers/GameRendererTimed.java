package com.mygdx.alphabetizergame.Renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.alphabetizergame.AplhabetizerGame;
import com.mygdx.alphabetizergame.GameObjects.StopWatch;
import com.mygdx.alphabetizergame.GameObjects.WordCubes;
import com.mygdx.alphabetizergame.Helpers.AssetLoader;
import com.mygdx.alphabetizergame.Screens.GameOverScreenTimed;
import com.mygdx.alphabetizergame.Worlds.TimerWordGameWorld;

public class GameRendererTimed {
    private TimerWordGameWorld timerWordGameWorld;
    private AplhabetizerGame myGame;

    private OrthographicCamera cam;
    private SpriteBatch batch;
    private WordCubes[] timerWordCubes;
    private StopWatch stopWatch;

    private float[] prevX;
    private float[] prevY;

    private float[] sumRuntime;

    private int gameHeight;
    private int gameWidth;

    public Stage stage;

    public boolean shouldClose = false;

    public GameRendererTimed(TimerWordGameWorld world, int gameHeight, int gameWidth, AplhabetizerGame game) {
        this.gameHeight = gameHeight;
        this.gameWidth = gameWidth;

        timerWordGameWorld = world;
        myGame = game;
        cam = new OrthographicCamera();
        cam.setToOrtho(true, gameWidth, gameHeight);

        timerWordCubes = timerWordGameWorld.getWordCubes();
        stopWatch = timerWordGameWorld.getStopWatch();

        prevX = new float[15];
        prevY = new float[15];
        sumRuntime = new float[16];
        setPrevX(0);
        setPrevX(1);
        setPrevX(2);
        setPrevX(3);
        setPrevX(4);
        setPrevX(5);
        setPrevX(6);
        setPrevX(7);
        setPrevX(8);
        setPrevX(9);
        setPrevX(10);
        setPrevX(11);
        setPrevX(12);
        setPrevX(13);
        setPrevX(14);

        setPrevY(0);
        setPrevY(1);
        setPrevY(2);
        setPrevY(3);
        setPrevY(4);
        setPrevY(5);
        setPrevY(6);
        setPrevY(7);
        setPrevY(8);
        setPrevY(9);
        setPrevY(10);
        setPrevY(11);
        setPrevY(12);
        setPrevY(13);
        setPrevY(14);

        batch = new SpriteBatch();
        Skin skin = new Skin();
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        Table table = new Table();
        stage = new Stage();

        skin.add("startbuttonup", AssetLoader.playButtonUp);
        skin.add("startbuttondown", AssetLoader.playButtonDown);
        BitmapFont font = AssetLoader.stopWatchFont;
        style.font = font;
        style.up = skin.getDrawable("startbuttonup");
        style.down = skin.getDrawable("startbuttondown");
        Color lightbluegreen = new Color(10f/255f,134f/255f,179f/255f,1);
        Color darkbluegreen = new Color(1f/255f,84f/255f,114f/255f,1);
        style.fontColor = lightbluegreen;
        style.downFontColor = darkbluegreen;

        TextButton startButton = new TextButton("Start", style);

        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AssetLoader.clicksound.play();
                timerWordGameWorld.shouldStart = true;
            }
        });
        table.add(startButton).size(300, 110);
        table.setPosition(cam.viewportWidth / 2, cam.viewportHeight - 100);
        stage.addActor(table);
        stage.getViewport().setCamera(cam);
    }
    public void renderTimed(float runTime) {
        Gdx.app.log("FPS:", Gdx.graphics.getFramesPerSecond() + "");
        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(!timerWordGameWorld.shouldStart){
            stage.act();
            stage.draw();
        }

        batch.begin();
        batch.setProjectionMatrix(cam.combined);

        drawWordCube(0);
        drawWordCube(1);
        drawWordCube(2);
        drawWordCube(3);
        drawWordCube(4);
        drawWordCube(5);
        drawWordCube(6);
        drawWordCube(7);
        drawWordCube(8);
        drawWordCube(9);
        drawWordCube(10);
        drawWordCube(11);
        drawWordCube(12);
        drawWordCube(13);
        drawWordCube(14);
        if (!timerWordGameWorld.shouldStart) {
            String instruction = "Alphabetize! Fast!";
            AssetLoader.stopWatchFont.draw(batch,instruction, gameWidth/2 - AssetLoader.stopWatchFont.getBounds(instruction).width/2, gameHeight/2 + 400);
        }
        if(timerWordGameWorld.shouldStart){
            drawWordFont(0);
            drawWordFont(1);
            drawWordFont(2);
            drawWordFont(3);
            drawWordFont(4);
            drawWordFont(5);
            drawWordFont(6);
            drawWordFont(7);
            drawWordFont(8);
            drawWordFont(9);
            drawWordFont(10);
            drawWordFont(11);
            drawWordFont(12);
            drawWordFont(13);
            drawWordFont(14);
        }

        animateWordCube(0, sumRuntime);
        animateWordCube(1, sumRuntime);
        animateWordCube(2, sumRuntime);
        animateWordCube(3, sumRuntime);
        animateWordCube(4, sumRuntime);
        animateWordCube(5, sumRuntime);
        animateWordCube(6, sumRuntime);
        animateWordCube(7, sumRuntime);
        animateWordCube(8, sumRuntime);
        animateWordCube(9, sumRuntime);
        animateWordCube(10, sumRuntime);
        animateWordCube(11, sumRuntime);
        animateWordCube(12, sumRuntime);
        animateWordCube(13, sumRuntime);
        animateWordCube(14, sumRuntime);

        batch.enableBlending();

        double playTimeInt = stopWatch.getPlayTime();
        String playTime = playTimeInt + "";
        int playtimeLength = 5;
        if (playTimeInt > 10) {
            playtimeLength = 6;
        }
        if (playTimeInt > 100) {
            playtimeLength = 7;
        }
        //playTimeLength not including decimal point
        float bounds = AssetLoader.stopWatchFont.getBounds("0").width * playtimeLength;
        AssetLoader.stopWatchFont.draw(batch, playTime, stopWatch.getX() - bounds / 2, stopWatch.getY());

        batch.draw(AssetLoader.cubeSplashOpening.getKeyFrame(runTime), 0,0,gameWidth, gameHeight - 1);
        if(AssetLoader.cubeSplashOpening.isAnimationFinished(runTime)){
            Gdx.app.log("opening", "finished");
            //timerWordGameWorld.shouldStart = true;
        }
        if(shouldClose){
            sumRuntime[15] += Gdx.graphics.getDeltaTime();
            batch.draw(AssetLoader.cubeSplashClosing.getKeyFrame(sumRuntime[15]), 0,0,gameWidth, gameHeight - 1);
            if(AssetLoader.cubeSplashClosing.isAnimationFinished(sumRuntime[15])){
                myGame.setScreen(new GameOverScreenTimed(this.myGame, this.timerWordGameWorld));
            }
        }
        batch.end();

    }

    public void animateWordCube(int wordCubeNumber, float[] runtime) {
        if (timerWordCubes[wordCubeNumber].shouldDisappear()) {
            runtime[wordCubeNumber] += Gdx.graphics.getDeltaTime();
            batch.draw(AssetLoader.wordCubeAnimation.getKeyFrame(runtime[wordCubeNumber]), prevX[wordCubeNumber], prevY[wordCubeNumber], timerWordCubes[wordCubeNumber].getWidth(), timerWordCubes[wordCubeNumber].getHeight());
            if (AssetLoader.wordCubeAnimation.isAnimationFinished(runtime[wordCubeNumber])) {
                timerWordCubes[wordCubeNumber].restartAnimation();
            }
        }
    }
    private void drawWordFont(int wordCubeNumber) {
        AssetLoader.timedFont.draw(batch, timerWordCubes[wordCubeNumber].getWord(), (timerWordCubes[wordCubeNumber].getX() + timerWordCubes[wordCubeNumber].getWidth() / 2) - AssetLoader.timedFont.getBounds(timerWordCubes[wordCubeNumber].getWord()).width / 2, timerWordCubes[wordCubeNumber].getY() + (timerWordCubes[wordCubeNumber].getHeight() / 2) + AssetLoader.timedFont.getBounds(timerWordCubes[wordCubeNumber].getWord()).height / 2);
    }
    private void drawWordCube(int wordCubeNumber) {
        batch.draw(AssetLoader.wordCube1, timerWordCubes[wordCubeNumber].getX(), timerWordCubes[wordCubeNumber].getY(), timerWordCubes[wordCubeNumber].getWidth(), timerWordCubes[wordCubeNumber].getHeight());
    }
    private void setPrevX(int wordCubeNumber) {
        prevX[wordCubeNumber] = timerWordCubes[wordCubeNumber].getX();
    }
    private void setPrevY(int wordCubeNumber) {
        prevY[wordCubeNumber] = timerWordCubes[wordCubeNumber].getY();

    }
}
