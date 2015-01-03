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

/**
 * Created by angelo_2 on 12/24/2014.
 */
public class HighScoreScreen extends AlphabetizerGameScreen {

    private Stage stage = new Stage();
    private Table table = new Table();
    private OrthographicCamera cam;
    private SpriteBatch batch;

    private Skin skin;
    private ImageButton clearHighScore;
    private ImageButton menuButton;
    private TextButton switchHighScore;

    private BitmapFont highScoreFont = AssetLoader.highScoreFontLarge;
    private String hs;

    private float runTime;
    private float sumRuntime;
    private float gameHeight;
    private float gameWidth;
    private float gameWidthReal;
    private float gameHeightReal;

    public boolean shouldClose = false;

    public HighScoreScreen(AplhabetizerGame game) {
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
        super.render(delta);
        runTime += delta;
        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
        batch.begin();
        batch.setProjectionMatrix(cam.combined);
        highScoreFont.draw(batch, hs, gameWidthReal/2 - highScoreFont.getBounds(hs).width/2, gameHeightReal/2 + highScoreFont.getBounds(hs).height);

        if(shouldClose){
            sumRuntime += Gdx.graphics.getDeltaTime();
            batch.draw(AssetLoader.cubeSplashClosing.getKeyFrame(sumRuntime), 0, 0, gameWidthReal, gameHeightReal - 1);
            stage.cancelTouchFocus();
            if (AssetLoader.cubeSplashClosing.isAnimationFinished(sumRuntime)) {
                game.setScreen(new MenuScreen(game));
            }
        }
        batch.draw(AssetLoader.cubeSplashOpening.getKeyFrame(runTime), 0, 0, gameWidthReal, gameHeightReal - 1);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void show() {
        super.show();
        hs = String.valueOf(AssetLoader.getHighScoreFalling());
        skin = new Skin();
        skin.add("menubuttonup", AssetLoader.playButtonUp);
        skin.add("menubuttondown", AssetLoader.playButtonDown);
        skin.add("clearbuttonup", AssetLoader.clearButtonUp);
        skin.add("clearbuttondown", AssetLoader.clearButtonDown);
        skin.add("backup", AssetLoader.menuButtonUp);
        skin.add("backdown", AssetLoader.menuButtonDown);

        TextButtonStyle style = new TextButtonStyle();
        BitmapFont fontdef = AssetLoader.switchHighFont;
        fontdef.setScale(1.8f, -2.5f);
        style.font = fontdef;
        style.up = skin.getDrawable("menubuttonup");
        style.down = skin.getDrawable("menubuttondown");
        Color lightbluegreen = new Color(10f/255f,134f/255f,179f/255f,1);
        Color darkbluegreen = new Color(1f/255f,84f/255f,114f/255f,1);
        style.fontColor = lightbluegreen;
        style.downFontColor = darkbluegreen;

        switchHighScore = new TextButton("Classic",style);
        switchHighScore.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AssetLoader.clicksound.play();
                if (AssetLoader.cubeSplashOpening.isAnimationFinished(runTime)) {
                    //Gdx.app.log("String", switchHighScore.getText() + "");
                    //switchHighScore.setText("Timed");
                    String s = switchHighScore.getText() + "";
                    if(s.equals("Classic")){
                        Gdx.app.log(switchHighScore.getText() + "", "Classic");
                        switchHighScore.setText("Timed");
                        hs = AssetLoader.getBestTime() + "";
                    }
                    if(s.equals("Timed")){
                        switchHighScore.setText("ARS");
                        hs = AssetLoader.getHighScoreARS() + "";
                    }
                    if(s.equals("ARS")){
                        switchHighScore.setText("Classic");
                        hs = AssetLoader.getHighScoreFalling() + "";
                    }
                }
            }
        });
        clearHighScore = new ImageButton(skin.getDrawable("clearbuttonup"), skin.getDrawable("clearbuttondown"));
        clearHighScore.getImage().setAlign(Align.left);
        clearHighScore.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AssetLoader.clicksound.play();
                if (AssetLoader.cubeSplashOpening.isAnimationFinished(runTime)) {
                    String s = switchHighScore.getText() + "";
                    if(s.equals("Classic")){
                        hs = 0 + "";
                        AssetLoader.setHighScoreFalling(0);

                    }
                    if(s.equals("Timed")){
                        AssetLoader.setBestScoreTimed(0.00f);
                        hs = 0.00 + "";
                    }
                    if(s.equals("ARS")){
                        AssetLoader.setHighSCoreARS(0);
                        hs = 0 + "";
                    }
                }
            }
        });
        menuButton = new ImageButton(skin.getDrawable("backup"), skin.getDrawable("backdown"));
        menuButton.getImageCell().size(150,150);
        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AssetLoader.clicksound.play();
                if (AssetLoader.cubeSplashOpening.isAnimationFinished(runTime)) {
                    shouldClose = true;
                }
            }
        });

        table.add(menuButton).size(150, 150);
        table.add(switchHighScore).size(400,150);
        table.add(clearHighScore).size(150, 150);
        table.setPosition(cam.viewportWidth/2, cam.viewportHeight - 100);
        stage.addActor(table);
        stage.getViewport().setCamera(cam);
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
