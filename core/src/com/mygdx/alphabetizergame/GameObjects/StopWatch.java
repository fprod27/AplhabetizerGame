package com.mygdx.alphabetizergame.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by angelo_2 on 12/19/2014.
 */
public class StopWatch {
    private Vector2 position;

    private float playTime = 0f;
    private double playTimeRounded;

    private boolean shouldStop = false;

    public StopWatch(float x, float y){

        position = new Vector2(x, y);
    }

    public void trigger(float delta){
        //update is calling twice so delta must be divided into 2.
        if(shouldStop == false) {
            playTime += delta / 2;
            playTimeRounded = ((double) Math.round(playTime * 100) / 100);
        }
    }

    public void stop(){
        shouldStop = true;
    }

    public double getPlayTime(){
        return playTimeRounded;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }


}
