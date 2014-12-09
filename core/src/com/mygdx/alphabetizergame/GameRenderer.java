package com.mygdx.alphabetizergame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.alphabetizergame.GameObjects.LimitLine;
import com.mygdx.alphabetizergame.GameObjects.WordCubes;
import com.mygdx.alphabetizergame.Helpers.AssetLoader;

/**
 * Created by angelo_2 on 11/20/2014.
 */
public class GameRenderer {

    private AlphabetizerGameWorld myWorld;
    WordCubes[] wordCubes;
    private OrthographicCamera cam;

    private SpriteBatch batch;

    private int gameHeight;
    private int gameWidth;

    private ShapeRenderer shapeRenderer;

    public GameRenderer(AlphabetizerGameWorld world, int gameHeight, int gameWidth) {
        this.gameHeight = gameHeight;
        this.gameWidth = gameWidth;

        myWorld = world;
        cam = new OrthographicCamera();
        cam.setToOrtho(true,gameWidth, 480);

        shapeRenderer = new ShapeRenderer();

        //batch = new SpriteBatch();
        //batch.setProjectionMatrix(cam.combined);

        wordCubes = myWorld.getWordCubes();

    }

    public void render(float runTime) {
        //Gdx.app.log("GameRenderer", "render");


//        WordCubes centerwordcube = myWorld.getCenterwordcube();
//        WordCubes firstwordcube = myWorld.getFirstwordcube();
//        WordCubes secondwordcube = myWorld.getSecondwordcube();
//        WordCubes fourthwordcube = myWorld.getFourthwordcube();
//        WordCubes fifthwordcube = myWorld.getFifthwordcube();

        LimitLine limitLine = myWorld.getLimitLine();

        batch = new SpriteBatch();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(new Color( 0, 0, 0, 0.5f));
        shapeRenderer.setProjectionMatrix(cam.combined);
        shapeRenderer.rect(wordCubes[0].getBoundingRectangle().x, wordCubes[0].getBoundingRectangle().y, wordCubes[0].getBoundingRectangle().width, wordCubes[0].getBoundingRectangle().height);
        shapeRenderer.rect(wordCubes[1].getBoundingRectangle().x, wordCubes[1].getBoundingRectangle().y, wordCubes[1].getBoundingRectangle().width, wordCubes[1].getBoundingRectangle().height);
        shapeRenderer.rect(wordCubes[2].getBoundingRectangle().x, wordCubes[2].getBoundingRectangle().y, wordCubes[2].getBoundingRectangle().width, wordCubes[2].getBoundingRectangle().height);
        shapeRenderer.rect(wordCubes[3].getBoundingRectangle().x, wordCubes[3].getBoundingRectangle().y, wordCubes[3].getBoundingRectangle().width, wordCubes[3].getBoundingRectangle().height);
        shapeRenderer.rect(wordCubes[4].getBoundingRectangle().x, wordCubes[4].getBoundingRectangle().y, wordCubes[4].getBoundingRectangle().width, wordCubes[4].getBoundingRectangle().height);
        shapeRenderer.rect(limitLine.getBoundingRectangle().x, limitLine.getBoundingRectangle().y, limitLine.getBoundingRectangle().width, limitLine.getBoundingRectangle().height);
        shapeRenderer.end();
        batch.begin();
        batch.setProjectionMatrix(cam.combined);
        batch.disableBlending();
        batch.draw(AssetLoader.bgtexture, 0, 0, gameWidth, gameHeight);
        batch.enableBlending();
        batch.draw(AssetLoader.limitlinetexture, limitLine.getX(), limitLine.getY(), limitLine.getWidth(), limitLine.getHeight());
        batch.draw(AssetLoader.wordcubetexture, wordCubes[0].getX(),  wordCubes[0].getY(),  wordCubes[0].getWidth(),  wordCubes[0].getHeight());
        batch.draw(AssetLoader.wordcubetexture,  wordCubes[1].getX(),  wordCubes[1].getY(),  wordCubes[1].getWidth(),  wordCubes[1].getHeight());
        batch.draw(AssetLoader.wordcubetexture,  wordCubes[2].getX(),  wordCubes[2].getY(),  wordCubes[2].getWidth(),  wordCubes[2].getHeight());
        batch.draw(AssetLoader.wordcubetexture,  wordCubes[3].getX(),  wordCubes[3].getY(),  wordCubes[3].getWidth(),  wordCubes[3].getHeight());
        batch.draw(AssetLoader.wordcubetexture,  wordCubes[4].getX(),  wordCubes[4].getY(),  wordCubes[4].getWidth(),  wordCubes[4].getHeight());

        AssetLoader.font.drawMultiLine(batch, wordCubes[0].getWord(), wordCubes[0].getX(), wordCubes[0].getY());
        AssetLoader.font.draw(batch, wordCubes[1].getWord(), wordCubes[1].getX(), wordCubes[1].getY());
        AssetLoader.font.draw(batch, wordCubes[2].getWord(), wordCubes[2].getX(), wordCubes[2].getY());
        AssetLoader.font.draw(batch, wordCubes[3].getWord(), wordCubes[3].getX(), wordCubes[3].getY());
        AssetLoader.font.draw(batch, wordCubes[4].getWord(), wordCubes[4].getX(), wordCubes[4].getY());

        String score = myWorld.getScore() + "";
        AssetLoader.font.draw(batch, score, gameWidth/2, gameHeight/2);

        batch.end();

        /*if (myWorld.isGameOver() || myWorld.isHighScore()) {

            if (myWorld.isGameOver()) {
                AssetLoader.shadow.draw(batch, "Game Over", 25, 56);
                AssetLoader.font.draw(batch, "Game Over", 24, 55);

                AssetLoader.shadow.draw(batch, "High Score:", 23, 106);
                AssetLoader.font.draw(batch, "High Score:", 22, 105);

                String highScore = AssetLoader.getHighScore() + "";

                // Draw shadow first
                AssetLoader.shadow.draw(batch, highScore, (136 / 2)
                        - (3 * highScore.length()), 128);
                // Draw text
                AssetLoader.font.draw(batch, highScore, (136 / 2)
                        - (3 * highScore.length() - 1), 127);
            } else {
                AssetLoader.shadow.draw(batch, "High Score!", 19, 56);
                AssetLoader.font.draw(batch, "High Score!", 18, 55);
            }

            AssetLoader.shadow.draw(batch, "Try again?", 23, 76);
            AssetLoader.font.draw(batch, "Try again?", 24, 75);

            // Draw shadow first
            AssetLoader.shadow.draw(batch, score,
                    (136 / 2) - (3 * score.length()), 12);
            // Draw text
            AssetLoader.font.draw(batch, score,
                    (136 / 2) - (3 * score.length() - 1), 11);

        }*/


    }

}
