package com.mygdx.alphabetizergame.GameObjects;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by angelo_2 on 12/27/2014.
 */
public class Timer {

    private Vector2 position;

    private float playTime = 45f;
    private double playTimeRounded;

    public Timer(float x, float y){

        position = new Vector2(x, y);
    }

    public void trigger(float delta){
        if(playTime != 0) {
            playTime -= delta;
            playTimeRounded = ((double)Math.round(playTime * 1) / 1);
        }
    }

    public void stop(){
        playTimeRounded = 0;
    }
    public void disappearTimer(int x){
        position.x = x + 200;
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
