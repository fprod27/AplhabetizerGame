package com.mygdx.alphabetizergame.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import org.w3c.dom.css.Rect;

/**
 * Created by angelo_2 on 11/20/2014.
 */
public class WordCubes {

    private Vector2 position;
    private Vector2 velocity;

    private int width;
    private int height;

    private String word;

    public boolean isOnMiddleY;

    private Rectangle boundingRectangle;

    public WordCubes(float x, float y, int width, int height, String word){

        this.word = word;
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 30);
        boundingRectangle = new Rectangle();
        isOnMiddleY = false;
    }

    public void update(float delta){

        position.add(velocity.cpy().scl(delta));

        boundingRectangle.set(position.x, position.y, width, height);

        if (position.y < Gdx.graphics.getHeight()/2){
            isOnMiddleY = true;
        }
    }

    public void reset(){

        velocity.y = 30;
    }
    public void disappearCube(){
        position.y = 0 - height;
        velocity.y = 0;
    }

    public float getX() {
        return position.x;
    }

    public void updateWord(String word){

        this.word = word;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public String getWord(){
        return word;
    }

    public float getVelocity(){
        return velocity.y;
    }

    public Rectangle getBoundingRectangle(){
        return boundingRectangle;
    }

    public void onRestart(int resetY) {

        position.y = resetY;
        velocity.y = 30;
    }
}
