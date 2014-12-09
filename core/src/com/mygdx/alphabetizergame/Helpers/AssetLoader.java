package com.mygdx.alphabetizergame.Helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.*;
import com.mygdx.alphabetizergame.AplhabetizerGame;

/**
 * Created by angelo_2 on 11/20/2014.
 */
public class AssetLoader {

    public static Texture bgtexture;
    public static Texture wordcubetexture;
    public static Texture limitlinetexture;
    public static Texture splashLogo;
    public static Texture highscoretexture;

    public static Texture playButtonUp, playButtonDown, menuButtonUp, menuButtonDown;

    public static BitmapFont font, shadow;

    private static Preferences prefs;

    public static void load(){

        bgtexture = new Texture(Gdx.files.internal("bg.jpg"));
        wordcubetexture = new Texture(Gdx.files.internal("bluerectangle.png"));
        limitlinetexture = new Texture(Gdx.files.internal("rainbowline.png"));
        splashLogo = new Texture(Gdx.files.internal("logo.png"));
        highscoretexture = new Texture(Gdx.files.internal("nhslogo.png"));

        playButtonDown = new Texture(Gdx.files.internal("playbuttondown.png"));
        playButtonUp = new Texture(Gdx.files.internal("PlayButtonUp.png"));
        menuButtonDown = new Texture(Gdx.files.internal("menubuttondown.png"));
        menuButtonUp = new Texture(Gdx.files.internal("menubuttonup.png"));

        font = new BitmapFont(Gdx.files.internal("text.fnt"));
        font.setScale(.5f, -.5f);

        shadow = new BitmapFont(Gdx.files.internal("shadow.fnt"));
        shadow.setScale(.5f, -.5f);

        prefs = Gdx.app.getPreferences("Alphabetizer");

        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }

    }

    public static void setHighScore(int val) {
        prefs.putInteger("highScore", val);
        prefs.flush();
    }

    public static int getHighScore() {
        return prefs.getInteger("highScore");
    }

    public static void dispose(){

        bgtexture.dispose();
        wordcubetexture.dispose();
        limitlinetexture.dispose();

        font.dispose();
        shadow.dispose();
        splashLogo.dispose();
        highscoretexture.dispose();

        playButtonUp.dispose();
        playButtonDown.dispose();
        menuButtonUp.dispose();
        menuButtonDown.dispose();
    }

}
