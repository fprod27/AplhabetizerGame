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
    public Vector2 velocity;

    private int width;
    private int height;

    private String word;

    public boolean isOnMiddleY;
    public boolean isTouchedDown;
    private boolean shouldDisappear;

    private Rectangle boundingRectangle;
    private float clickLocationY;
    private float clickLocationX;

    public WordCubes(float x, float y, int width, int height, String word){

        this.word = word;
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 30);
        boundingRectangle = new Rectangle();
        isOnMiddleY = false;
        isTouchedDown = false;
        shouldDisappear = false;
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
        shouldDisappear = true;
    }
    public void updateWord(String word){

        this.word = word;
    }

    public float getX() {
        return position.x;
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

    public Rectangle getBoundingRectangle(){
        return boundingRectangle;
    }

    public void restartAnimation() {

        shouldDisappear = false;
    }

    public void addVelocity(){


    }

    public void setTouchedDown(){
        isTouchedDown = true;
    }

    public boolean shouldDisappear(){
        return shouldDisappear;
    }

    public float getClickLocationX() {

        return clickLocationX;
    }

    public float getClickLocationY() {
        return clickLocationY;
    }

    public void setClickLocationX(float locX){
        clickLocationX = locX;
    }
    public void setClickLocationY(float locY){
        clickLocationY = locY;
    }

    public float getVelocity() {
        return velocity.y;
    }
}
