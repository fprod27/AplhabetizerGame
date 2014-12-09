package com.mygdx.alphabetizergame.GameObjects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


/**
 * Created by angelo_2 on 11/20/2014.
 */
public class LimitLine {

    private int width;
    private int height;

    private Rectangle boundingRectangle;

    private Vector2 position;

    public LimitLine(float x, float y, int width, int height ){

        this.width = width;
        this.height = height;
        position = new Vector2(x, y);

        boundingRectangle = new Rectangle();
        boundingRectangle.set((int)x, (int)y, width, height);

    }

    public void update(float delta){

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

    public Rectangle getBoundingRectangle(){

        return boundingRectangle;

    }
    public boolean collides(WordCubes cubes){
        if (position.y < cubes.getY() + cubes.getHeight()) {
            return (Intersector.overlaps(cubes.getBoundingRectangle(), this.getBoundingRectangle()));
        }
        return false;

    }

}
