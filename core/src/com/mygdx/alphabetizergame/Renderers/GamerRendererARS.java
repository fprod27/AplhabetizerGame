package com.mygdx.alphabetizergame.Renderers;

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
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Scaling;
import com.mygdx.alphabetizergame.AplhabetizerGame;
import com.mygdx.alphabetizergame.GameObjects.Timer;
import com.mygdx.alphabetizergame.GameObjects.WordCubes;
import com.mygdx.alphabetizergame.Helpers.AssetLoader;
import com.mygdx.alphabetizergame.Screens.GameOverScreenARS;
import com.mygdx.alphabetizergame.Worlds.ARSGameWorld;


public class GamerRendererARS {
    private ARSGameWorld ARSWorld;
    private AplhabetizerGame myGame;

    private OrthographicCamera cam;
    private SpriteBatch batch;
    private WordCubes[] ARSWordCubes;
    private Timer timer;

    private float[] sumRuntime;
    private float[] prevX;
    private float[] prevY;

    private int gameHeight;
    private int gameWidth;

    public Stage stage;
    public Stage stageARSBUttons;

    public boolean shouldClose = false;

    public GamerRendererARS(final ARSGameWorld world, int gameHeight, int gameWidth, AplhabetizerGame game){
        this.gameHeight = gameHeight;
        this.gameWidth = gameWidth;
        this.myGame = game;
        ARSWorld = world;
        cam = new OrthographicCamera();
        cam.setToOrtho(true, gameWidth, gameHeight);

        ARSWordCubes = ARSWorld.getWordCube();
        timer = ARSWorld.getTimer();

        prevX = new float[4];
        prevY = new float[4];
        sumRuntime = new float[5];
        setPrevX(0);
        setPrevX(1);
        setPrevX(2);
        setPrevX(3);

        setPrevY(0);
        setPrevY(1);
        setPrevY(2);
        setPrevY(3);

        batch = new SpriteBatch();
        Skin skin = new Skin();
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        Table table = new Table();
        Table tableARS = new Table();
        stage = new Stage();
        stageARSBUttons = new Stage();

        skin.add("startbuttonup", AssetLoader.playButtonUp);
        skin.add("startbuttondown", AssetLoader.playButtonDown);
        skin.add("ascendingbutton", AssetLoader.ascendingButton);
        skin.add("descendingbutton", AssetLoader.descendingButton);
        skin.add("shufflebutton", AssetLoader.shuffleButton);
        BitmapFont font = AssetLoader.stopWatchFont;
        style.font = font;
        style.up = skin.getDrawable("startbuttonup");
        style.down = skin.getDrawable("startbuttondown");
        Color lightbluegreen = new Color(10f/255f,134f/255f,179f/255f,1);
        Color darkbluegreen = new Color(1f/255f,84f/255f,114f/255f,1);
        style.fontColor = lightbluegreen;
        style.downFontColor = darkbluegreen;

        final TextButton startButton = new TextButton("Start", style);
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AssetLoader.clicksound.play();
                ARSWorld.shouldSwitchWords = true;
                ARSWorld.shouldStart = true;
                stage.clear();
            }
        });

        ImageButton ascendingButton = new ImageButton(skin.getDrawable("ascendingbutton"));
        ascendingButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ARSWorld.clickedTo = 1;
                ARSWorld.shouldSwitchWords = true;
            }
        });
        ImageButton descendingButton = new ImageButton(skin.getDrawable("descendingbutton"));
        descendingButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ARSWorld.clickedTo = 2;
                ARSWorld.shouldSwitchWords = true;
            }
        });
        ImageButton shuffleButton = new ImageButton(skin.getDrawable("shufflebutton"));
        shuffleButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ARSWorld.clickedTo = 3;
                ARSWorld.shouldSwitchWords = true;
            }
        });
        table.add(startButton).size(300, 110);
        tableARS.add(ascendingButton).size(220, 110).pad(20).align(Align.top);
        tableARS.add(descendingButton).size(220, 110).pad(20).align(Align.top);
        tableARS.add(shuffleButton).size(220, 110).pad(20);
        table.setPosition(cam.viewportWidth / 2, cam.viewportHeight - 100);
        tableARS.setPosition(cam.viewportWidth / 2, cam.viewportHeight - 100);
        stage.addActor(table);
        stage.getViewport().setCamera(cam);
        stageARSBUttons.addActor(tableARS);
        stageARSBUttons.getViewport().setCamera(cam);
    }

    public void renderARS(float runTime) {
        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(ARSWorld.isGameOver()){
            stageARSBUttons.cancelTouchFocus();
        }
        if(!ARSWorld.shouldStart){
            stage.act();
            stage.draw();
        }
        if(ARSWorld.shouldStart){
            stageARSBUttons.act();
            stageARSBUttons.draw();
        }

        batch.begin();
        batch.setProjectionMatrix(cam.combined);

        drawWordCube(0);
        drawWordCube(1);
        drawWordCube(2);
        drawWordCube(3);

        if (!ARSWorld.shouldStart) {
            String instruction = "Choose!\nascending reversed\nshuffled";
            AssetLoader.stopWatchFont.drawMultiLine(batch, instruction, gameWidth / 2 - AssetLoader.stopWatchFont.getBounds("ascending descending").width / 2, gameHeight / 2, AssetLoader.stopWatchFont.getBounds("ascending descending").width, BitmapFont.HAlignment.CENTER);
        }
        if (ARSWorld.shouldStart) {
            drawWordFont(0);
            drawWordFont(1);
            drawWordFont(2);
            drawWordFont(3);
        }

        animateWordCube(0,sumRuntime);
        animateWordCube(1,sumRuntime);
        animateWordCube(2, sumRuntime);
        animateWordCube(3, sumRuntime);
        batch.enableBlending();

        int playTimeInt = (int)timer.getPlayTime();
        String playTime = playTimeInt + "";
        AssetLoader.stopWatchFont.draw(batch, playTime, timer.getX() - AssetLoader.stopWatchFont.getBounds(playTime).width/2, timer.getY());

        String score = ARSWorld.getScore() + "";
        String recentHighScore = AssetLoader.getHighScoreARS() + "";
        AssetLoader.thinFont.draw(batch, score, gameWidth - (AssetLoader.thinFont.getBounds(score).width + 20), 105);
        AssetLoader.thickFontHighScore.draw(batch, recentHighScore, 0 + 10, 105);

        batch.draw(AssetLoader.cubeSplashOpening.getKeyFrame(runTime), 0,0,gameWidth, gameHeight - 1);
        if(AssetLoader.cubeSplashOpening.isAnimationFinished(runTime)){
        }
        if(shouldClose){
            sumRuntime[4] += Gdx.graphics.getDeltaTime();
            batch.draw(AssetLoader.cubeSplashClosing.getKeyFrame(sumRuntime[4]), 0,0,gameWidth, gameHeight - 1);
            if(AssetLoader.cubeSplashClosing.isAnimationFinished(sumRuntime[4])){
                myGame.setScreen(new GameOverScreenARS(this.myGame, this.ARSWorld));
            }
        }

        batch.end();
    }

    private void drawWordFont(int wordCubeNumber) {
        AssetLoader.ARSFont.draw(batch, ARSWordCubes[wordCubeNumber].getWord(), (ARSWordCubes[wordCubeNumber].getX() + ARSWordCubes[wordCubeNumber].getWidth() / 2) - AssetLoader.ARSFont.getBounds(ARSWordCubes[wordCubeNumber].getWord()).width / 2, ARSWordCubes[wordCubeNumber].getY() + (ARSWordCubes[wordCubeNumber].getHeight() / 2) + AssetLoader.ARSFont.getBounds(ARSWordCubes[wordCubeNumber].getWord()).height / 2);
    }

    private void drawWordCube(int wordCubeNumber) {
        batch.draw(AssetLoader.wordCube1, ARSWordCubes[wordCubeNumber].getX(), ARSWordCubes[wordCubeNumber].getY(), ARSWordCubes[wordCubeNumber].getWidth(), ARSWordCubes[wordCubeNumber].getHeight());
    }
    public void animateWordCube(int wordCubeNumber, float[] runtime) {
        if (ARSWordCubes[wordCubeNumber].shouldDisappear()) {
            runtime[wordCubeNumber] += Gdx.graphics.getDeltaTime();
            batch.draw(AssetLoader.wordCubeAnimation.getKeyFrame(runtime[wordCubeNumber]), prevX[wordCubeNumber], prevY[wordCubeNumber], ARSWordCubes[wordCubeNumber].getWidth(), ARSWordCubes[wordCubeNumber].getHeight());

            if (AssetLoader.wordCubeAnimation.isAnimationFinished(runtime[wordCubeNumber])) {
                shouldClose = true;
            }

        }
    }
    private void setPrevX(int wordCubeNumber) {
        prevX[wordCubeNumber] = ARSWordCubes[wordCubeNumber].getX();
    }
    private void setPrevY(int wordCubeNumber) {
        prevY[wordCubeNumber] = ARSWordCubes[wordCubeNumber].getY();

    }
}
