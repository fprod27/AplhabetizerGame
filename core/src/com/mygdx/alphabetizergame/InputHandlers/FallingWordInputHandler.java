package com.mygdx.alphabetizergame.InputHandlers;

import com.badlogic.gdx.Gdx;
import com.mygdx.alphabetizergame.Renderers.GameRendererFalling;
import com.mygdx.alphabetizergame.Worlds.FallingWordGameWorld;
import com.mygdx.alphabetizergame.AplhabetizerGame;
import com.mygdx.alphabetizergame.GameObjects.WordCubes;
import com.mygdx.alphabetizergame.Screens.GameOverScreenFalling;

/**
 * Created by angelo_2 on 11/30/2014.
 */
public class FallingWordInputHandler extends AlphabetizerInputHandler {

    private WordCubes[] myWordCube;
    private FallingWordGameWorld myWorld;
    private GameRendererFalling myRenderer;

    private float scaleFactorX;
    private float scaleFactorY;

    public FallingWordInputHandler(FallingWordGameWorld world, AplhabetizerGame game, float scaleFactorX, float scaleFactorY, GameRendererFalling renderer) {
        super(game);
        this.myWorld = world;
        this.myRenderer = renderer;

        this.scaleFactorX = scaleFactorX;
        this.scaleFactorY = scaleFactorY;

        myWordCube = myWorld.getWordCubes();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screenX = scaleX(screenX);
        screenY = scaleY(screenY);

        touchDOWN(screenX,screenY,0);
        touchDOWN(screenX,screenY,1);
        touchDOWN(screenX,screenY,2);
        touchDOWN(screenX,screenY,3);
        touchDOWN(screenX,screenY,4);
        touchDOWN(screenX,screenY,5);
        touchDOWN(screenX,screenY,6);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        screenX = scaleX(screenX);
        screenY = scaleY(screenY);

        touchUP(screenX,screenY,0);
        touchUP(screenX,screenY,1);
        touchUP(screenX,screenY,2);
        touchUP(screenX,screenY,3);
        touchUP(screenX,screenY,4);
        touchUP(screenX,screenY,5);
        touchUP(screenX,screenY,6);

        myWordCube[0].isTouchedDown = false;
        myWordCube[1].isTouchedDown = false;
        myWordCube[2].isTouchedDown = false;
        myWordCube[3].isTouchedDown = false;
        myWordCube[4].isTouchedDown = false;
        myWordCube[5].isTouchedDown = false;
        myWordCube[6].isTouchedDown = false;

        if (myWorld.isGameOver()) {
            // Reset all variables, go to GameState.READ
            myRenderer.shouldClose = true;
        }
        if (myWorld.isHighScore()) {
            // Reset all variables, go to GameState.READ
            myRenderer.shouldClose = true;
        }
        return true;
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
    public boolean scrolled(int amount) {
        return false;
    }

    private int scaleX(int screenX) {
        return (int) (screenX / scaleFactorX);
    }

    private int scaleY(int screenY) {
        return (int) (screenY / scaleFactorY);
    }

    private void touchUP(int screenX, int screenY, int wordNumber){
        if (screenX >= myWordCube[wordNumber].getX() && screenX <= (myWordCube[wordNumber].getX() + myWordCube[wordNumber].getWidth())) {
            if (screenY >= myWordCube[wordNumber].getY() && screenY <= myWordCube[wordNumber].getY() + myWordCube[wordNumber].getHeight()) {
                if(myWordCube[wordNumber].isTouchedDown) {
                    String word = myWordCube[wordNumber].getWord();
                    myWorld.checkWord(word);
                    myWordCube[wordNumber].setClickLocationX(myWordCube[wordNumber].getX());
                    myWordCube[wordNumber].setClickLocationY(myWordCube[wordNumber].getY());
                    Gdx.app.log("locY",myWordCube[wordNumber].getY() + "");
                    myWordCube[wordNumber].disappearCube();
                }

            }
        }
    }

    private void touchDOWN(int screenX, int screenY, int wordNumber){
        if (screenX >= myWordCube[wordNumber].getX() && screenX <= (myWordCube[wordNumber].getX() + myWordCube[wordNumber].getWidth())) {
            if (screenY >= myWordCube[wordNumber].getY() && screenY <= myWordCube[wordNumber].getY() + myWordCube[wordNumber].getHeight()) {
                myWordCube[wordNumber].setTouchedDown();
            }
        }
    }
}

