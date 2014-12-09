package com.mygdx.alphabetizergame.Helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.alphabetizergame.AlphabetizerGameWorld;
import com.mygdx.alphabetizergame.AplhabetizerGame;
import com.mygdx.alphabetizergame.GameObjects.WordCubes;
import com.mygdx.alphabetizergame.Screens.GameOverScreen;
import com.mygdx.alphabetizergame.UI.SimpleButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by angelo_2 on 11/30/2014.
 */
public class InputHandler implements InputProcessor {

    private WordCubes[] myWordCube;
    private AlphabetizerGameWorld myWorld;
    private AplhabetizerGame game;

    private List<SimpleButton> menuButtons;

    private SimpleButton playButton;

    public InputHandler(AlphabetizerGameWorld world, AplhabetizerGame game) {
        this.myWorld = world;
        this.game = game;
        myWordCube = myWorld.getWordCubes();

        menuButtons = new ArrayList<SimpleButton>();
        playButton = new SimpleButton(Gdx.graphics.getWidth() / 2 - 25, Gdx.graphics.getHeight() / 2, 25, 50, AssetLoader.playButtonUp, AssetLoader.playButtonDown);

        menuButtons.add(playButton);

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {


        if (myWorld.isReady()) {
            myWorld.start();
        }

        if (screenX >= myWordCube[0].getX() && screenX <= (myWordCube[0].getX() + myWordCube[0].getWidth())) {
            if (screenY >= myWordCube[0].getY() && screenY <= myWordCube[0].getY() + myWordCube[0].getHeight()) {
                String word = myWordCube[0].getWord();
                myWordCube[0].disappearCube();
                myWorld.checkWord(word);

            }
        }
        if (screenX >= myWordCube[1].getX() && screenX <= (myWordCube[1].getX() + myWordCube[1].getWidth())) {
            if (screenY >= myWordCube[1].getY() && screenY <= myWordCube[1].getY() + myWordCube[1].getHeight()) {
                String word = myWordCube[1].getWord();
                myWordCube[1].disappearCube();
                myWorld.checkWord(word);

            }
        }
        if (screenX >= myWordCube[2].getX() && screenX <= (myWordCube[2].getX() + myWordCube[2].getWidth())) {
            if (screenY >= myWordCube[2].getY() && screenY <= myWordCube[2].getY() + myWordCube[2].getHeight()) {
                String word = myWordCube[2].getWord();
                myWordCube[2].disappearCube();
                myWorld.checkWord(word);

            }
        }
        if (screenX >= myWordCube[3].getX() && screenX <= (myWordCube[3].getX() + myWordCube[3].getWidth())) {
            if (screenY >= myWordCube[3].getY() && screenY <= myWordCube[3].getY() + myWordCube[3].getHeight()) {
                String word = myWordCube[3].getWord();
                myWordCube[3].disappearCube();
                myWorld.checkWord(word);

            }
        }
        if (screenX >= myWordCube[4].getX() && screenX <= (myWordCube[4].getX() + myWordCube[4].getWidth())) {
            if (screenY >= myWordCube[4].getY() && screenY <= myWordCube[4].getY() + myWordCube[4].getHeight()) {
                String word = myWordCube[4].getWord();
                myWordCube[4].disappearCube();
                myWorld.checkWord(word);

            }
        }

        if (myWorld.isGameOver()) {
            // Reset all variables, go to GameState.READ
            game.setScreen(new GameOverScreen(this.game, this.myWorld));
        }
        if (myWorld.isGameOver() || myWorld.isHighScore()) {
            // Reset all variables, go to GameState.READ
            game.setScreen(new GameOverScreen(this.game, this.myWorld));
        }


        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}

