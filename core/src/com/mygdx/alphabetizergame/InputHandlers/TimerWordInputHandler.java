package com.mygdx.alphabetizergame.InputHandlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.mygdx.alphabetizergame.AplhabetizerGame;
import com.mygdx.alphabetizergame.GameObjects.WordCubes;
import com.mygdx.alphabetizergame.Renderers.GameRendererTimed;
import com.mygdx.alphabetizergame.Screens.GameOverScreenTimed;
import com.mygdx.alphabetizergame.Worlds.TimerWordGameWorld;

/**
 * Created by angelo_2 on 12/17/2014.
 */
public class TimerWordInputHandler extends  AlphabetizerInputHandler {

    private WordCubes[] myWordCube;
    private TimerWordGameWorld myWorld;
    private GameRendererTimed myRenderer;

    private float scaleFactorX;
    private float scaleFactorY;

    public TimerWordInputHandler(TimerWordGameWorld world, AplhabetizerGame game, float scaleFactorX, float scaleFactorY, GameRendererTimed renderer) {
        super(game);
        this.myWorld = world;
        this.myRenderer = renderer;

        this.scaleFactorX = scaleFactorX;
        this.scaleFactorY = scaleFactorY;

        myWordCube =  myWorld.getWordCubes();
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
        touchDOWN(screenX,screenY,7);
        touchDOWN(screenX,screenY,8);
        touchDOWN(screenX,screenY,9);
        touchDOWN(screenX,screenY,10);
        touchDOWN(screenX,screenY,11);
        touchDOWN(screenX,screenY,12);
        touchDOWN(screenX,screenY,13);
        touchDOWN(screenX,screenY,14);
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
        touchUP(screenX,screenY,7);
        touchUP(screenX,screenY,8);
        touchUP(screenX,screenY,9);
        touchUP(screenX,screenY,10);
        touchUP(screenX,screenY,11);
        touchUP(screenX,screenY,12);
        touchUP(screenX,screenY,13);
        touchUP(screenX,screenY,14);


        myWordCube[0].isTouchedDown = false;
        myWordCube[1].isTouchedDown = false;
        myWordCube[2].isTouchedDown = false;
        myWordCube[3].isTouchedDown = false;
        myWordCube[4].isTouchedDown = false;
        myWordCube[5].isTouchedDown = false;
        myWordCube[6].isTouchedDown = false;
        myWordCube[7].isTouchedDown = false;
        myWordCube[8].isTouchedDown = false;
        myWordCube[9].isTouchedDown = false;
        myWordCube[10].isTouchedDown = false;
        myWordCube[11].isTouchedDown = false;
        myWordCube[12].isTouchedDown = false;
        myWordCube[13].isTouchedDown = false;
        myWordCube[14].isTouchedDown = false;


        if (myWorld.isGameOver()) {
            // Reset all variables, go to GameState.READ
            myRenderer.shouldClose = true;
            //game.setScreen(new GameOverScreenTimed(this.game, this.myWorld));
   }
        if (myWorld.isHighScore()){
            myRenderer.shouldClose = true;
        }
        return true;
    }
    @Override
    public boolean keyDown(int keycode) {
        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        return super.keyUp(keycode);
    }

    @Override
    public boolean keyTyped(char character) {
        return super.keyTyped(character);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return super.touchDragged(screenX, screenY, pointer);
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return super.mouseMoved(screenX, screenY);
    }

    @Override
    public boolean scrolled(int amount) {
        return super.scrolled(amount);
    }

    private int scaleX(int screenX) {
        return (int) (screenX / scaleFactorX);
    }

    private int scaleY(int screenY) {
        return (int) (screenY / scaleFactorY);
    }

    private void touchUP(int screenX, int screenY, int wordNumber){
        if (myWorld.shouldStart) {
            if (screenX >= myWordCube[wordNumber].getX() && screenX <= (myWordCube[wordNumber].getX() + myWordCube[wordNumber].getWidth())) {
                if (screenY >= myWordCube[wordNumber].getY() && screenY <= myWordCube[wordNumber].getY() + myWordCube[wordNumber].getHeight()) {
                    if(myWordCube[wordNumber].isTouchedDown) {
                        String word = myWordCube[wordNumber].getWord();
                        myWorld.checkWord(word);
                        myWordCube[wordNumber].disappearCube();
                   }

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
