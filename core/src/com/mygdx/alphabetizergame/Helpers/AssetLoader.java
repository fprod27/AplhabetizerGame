package com.mygdx.alphabetizergame.Helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.*;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


/**
 * Created by angelo_2 on 11/20/2014.
 */
public class AssetLoader {

    public static Texture limitlinetexture;
    public static Texture splashLogo;
    public static Texture highscoretexture;
    public static Texture menuButtons;
    public static Texture wordCubeAnimationTexture;
    public static Texture cubeSplashTexture;
    public static Texture ARSButtonTexture;

    public static BitmapFont thinFont;
    public static BitmapFont fallingFont;
    public static BitmapFont stopWatchFont;
    public static BitmapFont thickFontHighScore;
    public static BitmapFont menuFont;
    public static BitmapFont highScoreFontLarge;
    public static BitmapFont switchHighFont;
    public static BitmapFont timedFont;
    public static BitmapFont ARSFont;

    public static TextureRegion wordCube1,wordCube2,wordCube3,wordCube4,wordCube5,wordCube6,wordCube7,wordCube8;
    public static TextureRegion cubeSplash1, cubeSplash2, cubeSplash3, cubeSplash4, cubeSplash5, cubeSplash6;
    public static TextureRegion playButtonUp, playButtonDown, menuButtonUp, menuButtonDown, highScoreButtonDown, highScoreButtonUP, replayButtonDown, replayButtonUP, clearButtonDown, clearButtonUp, hsTexture;
    public static TextureRegion ascendingButton, descendingButton, shuffleButton;


    public static Animation wordCubeAnimation;
    public static Animation cubeSplashOpening, cubeSplashClosing;

    public static Sound wordCubeExplodesound;
    public static Sound clicksound;
    public static Sound gameoversound;
    public static Sound negativeSound;
    public static Sound positiveSound;

    private static Preferences prefs;

    public static void load(){

        wordCubeAnimationTexture = new Texture(Gdx.files.internal("Animations/WordCubeAtlas.png"));
        wordCubeAnimationTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        wordCube1 = new TextureRegion(wordCubeAnimationTexture,2,2,128,128);
        wordCube2 = new TextureRegion(wordCubeAnimationTexture,2,132,132, 132);
        wordCube3 = new TextureRegion(wordCubeAnimationTexture,2, 266,136, 136);
        wordCube4 = new TextureRegion(wordCubeAnimationTexture,2, 404, 144, 144);
        wordCube5 = new TextureRegion(wordCubeAnimationTexture,2, 550, 156, 156);
        wordCube6 = new TextureRegion(wordCubeAnimationTexture, 2, 708, 160, 160);
        wordCube7 = new TextureRegion(wordCubeAnimationTexture, 2, 870, 156, 94);
        wordCube8 = new TextureRegion(wordCubeAnimationTexture,1,1,0,0);

        cubeSplashTexture = new Texture(Gdx.files.internal("Animations/cubeSplash.png"));
        cubeSplashTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        cubeSplash1 = new TextureRegion(cubeSplashTexture,2, 2, 256, 256);
        cubeSplash2 = new TextureRegion(cubeSplashTexture, 260, 2, 256, 256);
        cubeSplash3 = new TextureRegion(cubeSplashTexture, 518, 2, 256, 256);
        cubeSplash4 = new TextureRegion(cubeSplashTexture, 776, 2, 256, 256);
        cubeSplash5 = new TextureRegion(cubeSplashTexture, 1034, 2, 256, 256);
        cubeSplash6 = new TextureRegion(cubeSplashTexture, 0,0,0,0);

        TextureRegion[] cubeSplashClosingRegions = {cubeSplash1, cubeSplash2, cubeSplash3};
        TextureRegion[] cubeSplashOpeningRegions = {cubeSplash3, cubeSplash4, cubeSplash5,cubeSplash6};

        cubeSplashClosing = new Animation(.5f/3f, cubeSplashClosingRegions);
        cubeSplashClosing.setPlayMode(Animation.PlayMode.NORMAL);
        cubeSplashOpening = new Animation(.5f/3f, cubeSplashOpeningRegions);
        cubeSplashOpening.setPlayMode(Animation.PlayMode.NORMAL);

        TextureRegion[] wordCubes = {wordCube1, wordCube2, wordCube3, wordCube4, wordCube5, wordCube6, wordCube7,wordCube8};
        wordCubeAnimation = new Animation(.07f, wordCubes);
        wordCubeAnimation.setPlayMode(Animation.PlayMode.NORMAL);

        limitlinetexture = new Texture(Gdx.files.internal("rainbowline.png"));

        //FONTS
        switchHighFont = new BitmapFont(Gdx.files.internal("fonts/jd_eugenia.fnt"));
        menuFont = new BitmapFont(Gdx.files.internal("fonts/jd_eugenia.fnt"));
        thinFont = new BitmapFont(Gdx.files.internal("fonts/jd_eugenia.fnt"));
        thinFont.setScale(1f, -1.5f);
        thinFont.setColor(Color.BLACK);
        thinFont.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
        highScoreFontLarge = new BitmapFont(Gdx.files.internal("fonts/jd_eugenia.fnt"));
        highScoreFontLarge.setScale(5f, -7f);
        Color gold = new Color(226f/255f, 226f/255f,0,1);
        highScoreFontLarge.setColor(gold);
        highScoreFontLarge.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
        fallingFont = new BitmapFont(Gdx.files.internal("fonts/jd_eugenia.fnt"));
        fallingFont.setScale(.9f, -1.5f);
        timedFont = new BitmapFont(Gdx.files.internal("fonts/jd_eugenia.fnt"));
        timedFont.setScale(1.2f, -1.5f);
        Color darkbluegreen = new Color(44f/255f, 106f/255f, 116f/255f,1);
        timedFont.setColor(darkbluegreen);
        timedFont.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
        ARSFont = new BitmapFont(Gdx.files.internal("fonts/jd_eugenia.fnt"));
        ARSFont.setScale(1.6f, -2.2f);
        ARSFont.setColor(darkbluegreen);
        ARSFont.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
        fallingFont.setColor(darkbluegreen);
        fallingFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        thickFontHighScore = new BitmapFont(Gdx.files.internal("fonts/jd_eugenia.fnt"));
        thickFontHighScore.setScale(1f, -1.5f);
        thickFontHighScore.setColor(Color.MAGENTA);
        stopWatchFont = new BitmapFont(Gdx.files.internal("fonts/jd_eugenia.fnt"));
        stopWatchFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        stopWatchFont.setScale(2f, -2f);
        stopWatchFont.setColor(darkbluegreen);
        //SOUNDS
        wordCubeExplodesound = Gdx.audio.newSound(Gdx.files.internal("Audio/wordcubeexplode.wav"));
        clicksound = Gdx.audio.newSound(Gdx.files.internal("Audio/select.wav"));
        gameoversound = Gdx.audio.newSound(Gdx.files.internal("Audio/gameover.mp3"));
        negativeSound = Gdx.audio.newSound(Gdx.files.internal("Audio/negative.wav"));
        positiveSound = Gdx.audio.newSound(Gdx.files.internal("Audio/positive.wav"));

        if (!prefs.contains("highScoreFalling")) {
            prefs.putInteger("highScoreFalling", 0);
        }
        if (!prefs.contains("bestTime")){
            prefs.putFloat("bestTime", 0.00f);
        }
        if (!prefs.contains("highScoreARS")){
            prefs.putInteger("highScoreARS", 0);
        }

    }

    public static void loadskin() {
        menuButtons = new Texture(Gdx.files.internal("menubuttons.png"));
        menuButtons.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        playButtonDown = new TextureRegion(menuButtons,518, 2, 512, 192 );
        playButtonUp = new TextureRegion(menuButtons, 1032, 2, 512, 192);
        menuButtonDown = new TextureRegion(menuButtons, 1546, 120,66, 66);
        menuButtonUp = new TextureRegion(menuButtons, 1546, 188, 66, 66);
        hsTexture = new TextureRegion(menuButtons, 1664, 2, 116, 116);
        hsTexture.flip(false, true);
        highScoreButtonUP = new TextureRegion(menuButtons, 1664, 2, 116, 116);
        highScoreButtonUP.flip(false, true);
        highScoreButtonDown = new TextureRegion(menuButtons, 1546, 2, 116, 116);
        highScoreButtonDown.flip(false, true);
        replayButtonDown = new TextureRegion(menuButtons, 1614, 120, 66, 66);
        replayButtonDown.flip(false, true);
        replayButtonUP = new TextureRegion(menuButtons, 1614, 188, 66, 66);
        replayButtonUP.flip(false, true);
        clearButtonDown = new TextureRegion(menuButtons,2, 2, 256, 256);
        clearButtonDown.flip(false, true);
        clearButtonUp = new TextureRegion(menuButtons, 260, 2, 256, 256);
        clearButtonUp.flip(false, true);
        highscoretexture = new Texture(Gdx.files.internal("nhslogo.png"));
        ARSButtonTexture = new Texture(Gdx.files.internal("ARSButtons.png"));
        ascendingButton = new TextureRegion(ARSButtonTexture, 2, 2, 256, 128);
        descendingButton = new TextureRegion(ARSButtonTexture, 518, 2, 256, 128);
        shuffleButton = new TextureRegion(ARSButtonTexture,260, 2 , 256, 128);
    }

    public static void loadSplash(){
        prefs = Gdx.app.getPreferences("Alphabetizer");
        splashLogo = new Texture(Gdx.files.internal("logo.png"));
    }

    public static void setHighScoreFalling(int val) {
        prefs.putInteger("highScoreFalling", val);
        prefs.flush();
    }

    public static void setBestScoreTimed(float val){
        prefs.putFloat("bestTime", val);
        prefs.flush();
    }

    public static void setHighSCoreARS(int val){
        prefs.putInteger("highScoreARS", val);
        prefs.flush();
    }
    public static int getHighScoreFalling() {
        return prefs.getInteger("highScoreFalling");
    }

    public static float getBestTime(){
        return prefs.getFloat("bestTime");
    }

    public static int getHighScoreARS() {
        return prefs.getInteger("highScoreARS");
    }

    public static void dispose(){
        limitlinetexture.dispose();
        fallingFont.dispose();
        highScoreFontLarge.dispose();
        thinFont.dispose();
        stopWatchFont.dispose();
        wordCubeAnimationTexture.dispose();
        cubeSplashTexture.dispose();
        menuFont.dispose();
        switchHighFont.dispose();
        wordCubeExplodesound.dispose();
        clicksound.dispose();
        gameoversound.dispose();
        negativeSound.dispose();
        positiveSound.dispose();
        ARSFont.dispose();
        Gdx.app.log("Assets", "disposed");
    }

    public static void disposeSplash(){
        splashLogo.dispose();
    }

    public static void disposeSkin(){
        highscoretexture.dispose();
        menuButtons.dispose();
        ARSButtonTexture.dispose();
    }
}
