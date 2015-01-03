package com.mygdx.alphabetizergame.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.alphabetizergame.AplhabetizerGame;
import com.mygdx.alphabetizergame.Helpers.AssetLoader;
import com.mygdx.alphabetizergame.Worlds.TimerWordGameWorld;

public class GameOverScreenTimed extends AlphabetizerGameScreen {
    private TimerWordGameWorld myWorld;
    private Stage stage = new Stage();
    private Table table = new Table();
    private Skin skin;
    private SpriteBatch batch;
    private OrthographicCamera cam;

    private ImageButton buttonPlay;
    private ImageButton buttonMenu;

    private float runTime;
    private float sumRuntime;
    private float gameHeight;
    private float gameWidth;
    private float gameWidthReal;
    private float gameHeightReal;

    private boolean shouldCloseToWorld = false;
    private boolean shouldCloseToMenu = false;

    private String bestTime;
    private BitmapFont font;

    public GameOverScreenTimed(AplhabetizerGame game, TimerWordGameWorld world ) {
        super(game);
        this.myWorld = world;
        gameHeight = Gdx.graphics.getHeight();
        gameWidth = Gdx.graphics.getWidth();
        gameWidthReal = 1000;
        gameHeightReal = gameHeight/(gameWidth/gameWidthReal);
        cam = new OrthographicCamera();
        cam.setToOrtho(true, gameWidthReal,gameHeightReal);

        bestTime = myWorld.getTime() + "";
        font = new BitmapFont();
        batch = new SpriteBatch();
        sumRuntime = 0;

    }


    @Override
    public void render(float delta) {
        //super.render(delta);
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
        AssetLoader.stopWatchFont.draw(batch, bestTime, gameWidthReal/2 - AssetLoader.stopWatchFont.getBounds(bestTime).width/2, 0 - (AssetLoader.stopWatchFont.getBounds(bestTime).height + 20));

        if(myWorld.isHighScore()){
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
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void show() {
        super.show();
        //AssetLoader.loadskin();
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
    public void pause() {
        super.pause();
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
    public void dispose() {
        super.dispose();
    }
    private void shouldCloseToWorld(){
        if(this.shouldCloseToWorld) {
            sumRuntime += Gdx.graphics.getDeltaTime();
            batch.draw(AssetLoader.cubeSplashClosing.getKeyFrame(sumRuntime), 0, 0, gameWidthReal, gameHeightReal - 1);
            if (AssetLoader.cubeSplashClosing.isAnimationFinished(sumRuntime)) {
                game.setScreen(new TimerWordGameScreen(game));
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
