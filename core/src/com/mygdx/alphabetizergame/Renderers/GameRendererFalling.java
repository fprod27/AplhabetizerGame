package com.mygdx.alphabetizergame.Renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.alphabetizergame.AplhabetizerGame;
import com.mygdx.alphabetizergame.GameObjects.LimitLine;
import com.mygdx.alphabetizergame.GameObjects.WordCubes;
import com.mygdx.alphabetizergame.Helpers.AssetLoader;
import com.mygdx.alphabetizergame.Screens.GameOverScreenFalling;
import com.mygdx.alphabetizergame.Worlds.FallingWordGameWorld;

public class GameRendererFalling {

    private FallingWordGameWorld fallingWordGameWorld;
    private AplhabetizerGame myGame;

    private OrthographicCamera cam;
    private SpriteBatch batch;

    public LimitLine limitLine;
    public WordCubes[] fallingWordCubes;

    private int gameHeight;
    private int gameWidth;

    public boolean shouldClose = false;

    private float[] prevX;
    private float[] prevY;
    private float[] sumRuntime;

    public Stage stage;

    private ShapeRenderer shapeRenderer;

    public GameRendererFalling(FallingWordGameWorld world, int gameHeight, int gameWidth, AplhabetizerGame game) {
        this.gameHeight = gameHeight;
        this.gameWidth = gameWidth;
        myGame = game;

        fallingWordGameWorld = world;
        cam = new OrthographicCamera();
        cam.setToOrtho(true,gameWidth, gameHeight);

        shapeRenderer = new ShapeRenderer();

        fallingWordCubes = fallingWordGameWorld.getWordCubes();
        limitLine = fallingWordGameWorld.getLimitLine();

        prevX = new float[7];
        prevY = new float[7];
        sumRuntime = new float[8];

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
                fallingWordGameWorld.shouldStart = true;
            }
        });

        table.add(startButton).size(300, 110);
        table.setPosition(cam.viewportWidth / 2, cam.viewportHeight/2);
        stage.addActor(table);
        stage.getViewport().setCamera(cam);

    }

    public void renderFalling(float runTime) {

        setClickedLocationX(0);
        setClickedLocationX(1);
        setClickedLocationX(2);
        setClickedLocationX(3);
        setClickedLocationX(4);
        setClickedLocationX(5);
        setClickedLocationX(6);

        setClickedLocationY(0);
        setClickedLocationY(1);
        setClickedLocationY(2);
        setClickedLocationY(3);
        setClickedLocationY(4);
        setClickedLocationY(5);
        setClickedLocationY(6);

        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        //shapeRenderer.setColor(new Color( 0, 0, 0, 0.5f));
        shapeRenderer.setProjectionMatrix(cam.combined);
        shapeRenderer.rect(fallingWordCubes[0].getBoundingRectangle().x, fallingWordCubes[0].getBoundingRectangle().y, fallingWordCubes[0].getBoundingRectangle().width, fallingWordCubes[0].getBoundingRectangle().height);
        shapeRenderer.rect(fallingWordCubes[1].getBoundingRectangle().x, fallingWordCubes[1].getBoundingRectangle().y, fallingWordCubes[1].getBoundingRectangle().width, fallingWordCubes[1].getBoundingRectangle().height);
        shapeRenderer.rect(fallingWordCubes[2].getBoundingRectangle().x, fallingWordCubes[2].getBoundingRectangle().y, fallingWordCubes[2].getBoundingRectangle().width, fallingWordCubes[2].getBoundingRectangle().height);
        shapeRenderer.rect(fallingWordCubes[3].getBoundingRectangle().x, fallingWordCubes[3].getBoundingRectangle().y, fallingWordCubes[3].getBoundingRectangle().width, fallingWordCubes[3].getBoundingRectangle().height);
        shapeRenderer.rect(fallingWordCubes[4].getBoundingRectangle().x, fallingWordCubes[4].getBoundingRectangle().y, fallingWordCubes[4].getBoundingRectangle().width, fallingWordCubes[4].getBoundingRectangle().height);
        shapeRenderer.rect(fallingWordCubes[5].getBoundingRectangle().x, fallingWordCubes[5].getBoundingRectangle().y, fallingWordCubes[5].getBoundingRectangle().width, fallingWordCubes[5].getBoundingRectangle().height);
        shapeRenderer.rect(fallingWordCubes[6].getBoundingRectangle().x, fallingWordCubes[6].getBoundingRectangle().y, fallingWordCubes[6].getBoundingRectangle().width, fallingWordCubes[6].getBoundingRectangle().height);
        shapeRenderer.rect(limitLine.getBoundingRectangle().x, limitLine.getBoundingRectangle().y, limitLine.getBoundingRectangle().width, limitLine.getBoundingRectangle().height);
        shapeRenderer.end();

        if(!fallingWordGameWorld.shouldStart){
            stage.act();
            stage.draw();
        }

        batch.begin();
        batch.setProjectionMatrix(cam.combined);

        batch.draw(AssetLoader.limitlinetexture, limitLine.getX(), limitLine.getY(), limitLine.getWidth(), limitLine.getHeight());
        drawWordCube(0);
        drawWordCube(1);
        drawWordCube(2);
        drawWordCube(3);
        drawWordCube(4);
        drawWordCube(5);
        drawWordCube(6);
        if (!fallingWordGameWorld.shouldStart) {
            String instruction = "Alphabetize\n before it reaches \nthe ground!";
            AssetLoader.stopWatchFont.drawMultiLine(batch, instruction, gameWidth / 2 - AssetLoader.stopWatchFont.getBounds("before it reaches").width / 2, gameHeight / 2 - 300, AssetLoader.stopWatchFont.getBounds("before it reaches").width, BitmapFont.HAlignment.CENTER);
        }

        drawWordFont(0);
        drawWordFont(1);
        drawWordFont(2);
        drawWordFont(3);
        drawWordFont(4);
        drawWordFont(5);
        drawWordFont(6);

        animateWordCube(0, sumRuntime);
        animateWordCube(1, sumRuntime);
        animateWordCube(2, sumRuntime);
        animateWordCube(3, sumRuntime);
        animateWordCube(4, sumRuntime);
        animateWordCube(5, sumRuntime);
        animateWordCube(6, sumRuntime);

        String score = fallingWordGameWorld.getScore() + "";
        String recentHighScore = AssetLoader.getHighScoreFalling() + "";
        AssetLoader.thinFont.draw(batch, score, gameWidth - (AssetLoader.thinFont.getBounds(score).width + 20), 105);
        AssetLoader.thickFontHighScore.draw(batch, recentHighScore, 0 + 10, 105);

        batch.draw(AssetLoader.cubeSplashOpening.getKeyFrame(runTime), 0,0,gameWidth, gameHeight - 1);
        if(AssetLoader.cubeSplashOpening.isAnimationFinished(runTime)){

        }
        if(shouldClose){
            sumRuntime[7] += Gdx.graphics.getDeltaTime();
            batch.draw(AssetLoader.cubeSplashClosing.getKeyFrame(sumRuntime[7]), 0,0,gameWidth, gameHeight - 1);
            if(AssetLoader.cubeSplashClosing.isAnimationFinished(sumRuntime[7])){
                myGame.setScreen(new GameOverScreenFalling(this.myGame, this.fallingWordGameWorld));
            }
        }

        batch.end();
}
    private void drawWordFont(int wordCubeNumber){
            //(fallingWordCubes[0].getX() + fallingWordCubes[0].getWidth()/2) - AssetLoader.thinFont.getBounds(fallingWordCubes[0].getWord()).width/2 == CENTERING THE FONT
            AssetLoader.fallingFont.draw(batch, fallingWordCubes[wordCubeNumber].getWord(), (fallingWordCubes[wordCubeNumber].getX() + fallingWordCubes[wordCubeNumber].getWidth()/2) - AssetLoader.fallingFont.getBounds(fallingWordCubes[wordCubeNumber].getWord()).width/2, fallingWordCubes[wordCubeNumber].getY() + (fallingWordCubes[wordCubeNumber].getHeight()/2) + AssetLoader.fallingFont.getBounds(fallingWordCubes[wordCubeNumber].getWord()).height/2);
    }

    private void drawWordCube(int wordCubeNumber){

        batch.draw(AssetLoader.wordCube1,  fallingWordCubes[wordCubeNumber].getX(),  fallingWordCubes[wordCubeNumber].getY(),  fallingWordCubes[wordCubeNumber].getWidth(),  fallingWordCubes[wordCubeNumber].getHeight());
    }
    public void animateWordCube(int wordCubeNumber, float[] runtime) {
        if (fallingWordCubes[wordCubeNumber].shouldDisappear()) {
            runtime[wordCubeNumber] += Gdx.graphics.getDeltaTime();
            batch.draw(AssetLoader.wordCubeAnimation.getKeyFrame(runtime[wordCubeNumber]), prevX[wordCubeNumber], prevY[wordCubeNumber], fallingWordCubes[wordCubeNumber].getWidth(), fallingWordCubes[wordCubeNumber].getHeight());

            if(AssetLoader.wordCubeAnimation.isAnimationFinished(runtime[wordCubeNumber])){

                fallingWordCubes[wordCubeNumber].restartAnimation();
                runtime[wordCubeNumber] = 0;
            }
        }
    }
    public void setClickedLocationX(int wordCubeNumber) {
        prevX[wordCubeNumber] = fallingWordCubes[wordCubeNumber].getClickLocationX();

    }
    public void setClickedLocationY(int wordCubeNumber) {
        prevY[wordCubeNumber] = fallingWordCubes[wordCubeNumber].getClickLocationY();
    }
}
