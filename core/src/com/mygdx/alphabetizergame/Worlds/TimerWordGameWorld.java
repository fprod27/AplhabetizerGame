package com.mygdx.alphabetizergame.Worlds;

import com.badlogic.gdx.Gdx;
import com.mygdx.alphabetizergame.AplhabetizerGame;
import com.mygdx.alphabetizergame.GameObjects.StopWatch;
import com.mygdx.alphabetizergame.GameObjects.WordCubes;
import com.mygdx.alphabetizergame.Helpers.AssetLoader;
import com.mygdx.alphabetizergame.Helpers.WordGenerator;
import com.mygdx.alphabetizergame.Screens.GameOverScreenTimed;

import java.util.Collections;
import java.util.List;

/**
 * Created by angelo_2 on 12/17/2014.
 */
public class TimerWordGameWorld {
    private WordCubes[] wordCubes;
    private StopWatch stopWatch;
    
    private List<String> wordsGenerated;
    private WordGenerator wordGenerator;

    private AplhabetizerGame game;
    private float prevBestTime;

    private int worldGameWidth;
    private int worldGameHeight;

    public boolean shouldStart = false;

    float time;

    private GameState currentState;

    public enum GameState {
        READY, RUNNING, GAMEOVER, HIGHSCORE
    }

    public TimerWordGameWorld(int gameHeight, int midPointX, int gameWidth, int midPointY, AplhabetizerGame game){
        prevBestTime = AssetLoader.getBestTime();

        currentState = GameState.READY;
        this.game = game;
        this.worldGameHeight = gameHeight;
        this.worldGameWidth = gameWidth;

        wordCubes = new WordCubes[15];
        wordGenerator = new WordGenerator();
        wordGenerator.setGeneratedWordsTimer();
        wordsGenerated = wordGenerator.getGeneratedWords();
        Collections.shuffle(wordsGenerated);
        //Gdx.app.log("timed", wordsGenerated + "");
        int wordCubewidth = 120;
        int wordCubeDistanceY = 220;
        //100/2 = wordcubehalfwidth
        wordCubes[0] = new WordCubes((midPointX - 320)- 100/2, midPointY - wordCubeDistanceY, wordCubewidth, 100, wordsGenerated.get(0));
        wordCubes[1] = new WordCubes((midPointX - 160) - 100/2, midPointY - wordCubeDistanceY, wordCubewidth, 100, wordsGenerated.get(1));
        wordCubes[2] = new WordCubes(midPointX - 100/2, midPointY - wordCubeDistanceY, wordCubewidth, 100, wordsGenerated.get(2));
        wordCubes[3] = new WordCubes((midPointX + 160)- 100/2, midPointY - wordCubeDistanceY, wordCubewidth, 100, wordsGenerated.get(3));
        wordCubes[4] = new WordCubes((midPointX + 320)- 100/2, midPointY - wordCubeDistanceY, wordCubewidth, 100, wordsGenerated.get(4));
        wordCubes[5] = new WordCubes((midPointX - 320)- 100/2, midPointY, wordCubewidth, 100, wordsGenerated.get(5));
        wordCubes[6] = new WordCubes((midPointX - 160) - 100/2, midPointY, wordCubewidth, 100, wordsGenerated.get(6));
        wordCubes[7] = new WordCubes(midPointX - 100/2, midPointY, wordCubewidth, 100, wordsGenerated.get(7));
        wordCubes[8] = new WordCubes((midPointX + 160)- 100/2, midPointY, wordCubewidth, 100, wordsGenerated.get(8));
        wordCubes[9] = new WordCubes((midPointX + 320)- 100/2, midPointY, wordCubewidth, 100, wordsGenerated.get(9));
        wordCubes[10] = new WordCubes((midPointX - 320)- 100/2, midPointY + wordCubeDistanceY, wordCubewidth, 100, wordsGenerated.get(10));
        wordCubes[11] = new WordCubes((midPointX - 160) - 100/2, midPointY + wordCubeDistanceY, wordCubewidth, 100, wordsGenerated.get(11));
        wordCubes[12] = new WordCubes(midPointX - 100/2, midPointY + wordCubeDistanceY, wordCubewidth, 100, wordsGenerated.get(12));
        wordCubes[13] = new WordCubes((midPointX + 160)- 100/2, midPointY + wordCubeDistanceY, wordCubewidth, 100, wordsGenerated.get(13));
        wordCubes[14] = new WordCubes((midPointX + 320)- 100/2, midPointY + wordCubeDistanceY, wordCubewidth, 100, wordsGenerated.get(14));
        Collections.sort(wordsGenerated);
        Gdx.app.log("Answer", wordsGenerated + "");

        stopWatch = new StopWatch(midPointX, 100);

    }

    public void updateRunning(float delta) {
        stopWatch.trigger(delta);
        time = (float)stopWatch.getPlayTime();

        if(wordsGenerated.isEmpty()) {
            //game.setScreen(new GameOverScreenTimed(game, this));
            currentState = GameState.GAMEOVER;
            Gdx.app.log("Game","wordsGenerated.isEmpty");
            if(prevBestTime == 0){
                AssetLoader.setBestScoreTimed(time);
                currentState = GameState.HIGHSCORE;
                Gdx.app.log("Game", "HIGHSCORE");
            }
            if (time < prevBestTime) {
                AssetLoader.setBestScoreTimed(time);
                currentState = GameState.HIGHSCORE;
                Gdx.app.log("Game", "HIGHSCORE");
            }

        }

    }

    public void update(float delta){

        switch (currentState) {

            case READY:

                updateReady(delta);
                break;
            case RUNNING:
                updateRunning(delta);
                break;
            default:
                break;

        }
    }

    public void updateReady(float delta){


    }

    public WordCubes[] getWordCubes(){

        return wordCubes;
    }

    public StopWatch getStopWatch(){
        return stopWatch;
    }

    public float getTime(){

        return time;

    }

    public void checkWord(String word){

        Collections.sort(wordsGenerated);
        String checkTo = wordsGenerated.get(0);
        Gdx.app.log(word + "", checkTo + "");

        if(word == checkTo){
            wordsGenerated.remove(0);
            AssetLoader.wordCubeExplodesound.play();
        }
        else{
            Gdx.app.log("Game", "Dead");
            stopWatch.stop();
            currentState = GameState.GAMEOVER;
            Gdx.app.log("Game", "NOTHIGHSCORE");
            AssetLoader.gameoversound.play();
        }

    }

    public boolean isReady(){
        return currentState == GameState.READY;
    }

    public void start(){
        currentState = GameState.RUNNING;
    }

    public boolean isGameOver(){
        return currentState == GameState.GAMEOVER;
    }

    public boolean isHighScore() {
        return currentState == GameState.HIGHSCORE;
    }
}
