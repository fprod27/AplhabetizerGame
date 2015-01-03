package com.mygdx.alphabetizergame.Worlds;

import com.badlogic.gdx.Gdx;
import com.mygdx.alphabetizergame.AplhabetizerGame;
import com.mygdx.alphabetizergame.GameObjects.StopWatch;
import com.mygdx.alphabetizergame.GameObjects.Timer;
import com.mygdx.alphabetizergame.GameObjects.WordCubes;
import com.mygdx.alphabetizergame.Helpers.AssetLoader;
import com.mygdx.alphabetizergame.Helpers.WordGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ARSGameWorld {

    private WordCubes[] wordCube;
    private Timer timer;

    private List<String> wordsGenerated;
    private WordGenerator wordGenerator;

    private AplhabetizerGame game;
    int score = 0;

    private int worldGameWidth;
    private int worldGameHeight;
    private int randomNumber;


    public boolean shouldStart = false;
    public static int clickedTo = 0;

    private GameState currentState;
    public boolean shouldSwitchWords = false;

    public enum GameState {
        READY, RUNNING, GAMEOVER, HIGHSCORE
    }

    public ARSGameWorld(int gameHeight, int midPointX, int gameWidth, int midPointY, AplhabetizerGame game){

        currentState = GameState.READY;
        this.game = game;
        this.worldGameHeight = gameHeight;
        this.worldGameWidth = gameWidth;

        wordCube = new WordCubes[4];
        wordGenerator = new WordGenerator();
        wordGenerator.setGeneratedWordsARS();
        wordsGenerated = wordGenerator.getGeneratedWords();
        Collections.shuffle(wordsGenerated);

        int wordCubeWidth = 170;
        int wordCubeHeight = 150;
        int wordCubeDistanceY = 220;

        wordCube[0] = new WordCubes(midPointX - 150/2 - 300, midPointY - wordCubeDistanceY, wordCubeWidth, wordCubeHeight, wordsGenerated.get(0));
        wordCube[1] = new WordCubes(midPointX - 150/2 - 100, midPointY - wordCubeDistanceY, wordCubeWidth, wordCubeHeight, wordsGenerated.get(1));
        wordCube[2] = new WordCubes(midPointX - 150/2 + 100, midPointY - wordCubeDistanceY, wordCubeWidth, wordCubeHeight, wordsGenerated.get(2));
        wordCube[3] = new WordCubes(midPointX -150/2 + 300, midPointY - wordCubeDistanceY, wordCubeWidth, wordCubeHeight, wordsGenerated.get(3));

        timer = new Timer(midPointX, 100);
    }

    public void updateRunning(float delta){
        timer.trigger(delta);
        if(shouldSwitchWords){
            wordGenerator.setGeneratedWordsARS();
            wordsGenerated = wordGenerator.getGeneratedWords();
            if(clickedTo == randomNumber && clickedTo != 0){
                AssetLoader.positiveSound.play();
                addScore(1);
            }
            if(clickedTo != randomNumber && score != 0){
                AssetLoader.negativeSound.play();
                addScore(-1);
            }
            if(clickedTo != randomNumber && score == 0 && clickedTo != 0){
                AssetLoader.negativeSound.play();
            }
            Random genRandom = new Random();
            randomNumber =  genRandom.nextInt((3 - 1) + 1) + 1;
            if(randomNumber == 1){
                Gdx.app.log("randomNumber", "1");
                Collections.sort(wordsGenerated);
                wordCube[0].updateWord(wordsGenerated.get(0));
                wordCube[1].updateWord(wordsGenerated.get(1));
                wordCube[2].updateWord(wordsGenerated.get(2));
                wordCube[3].updateWord(wordsGenerated.get(3));
            }
            if(randomNumber == 2){
                Gdx.app.log("randomNumber", "2");
                Collections.sort(wordsGenerated);
                Collections.reverse(wordsGenerated);
                wordCube[0].updateWord(wordsGenerated.get(0));
                wordCube[1].updateWord(wordsGenerated.get(1));
                wordCube[2].updateWord(wordsGenerated.get(2));
                wordCube[3].updateWord(wordsGenerated.get(3));
            }
            if(randomNumber == 3){
                Gdx.app.log("randomNumber", "3");
                List wordsSorted = new ArrayList();
                wordsSorted.addAll(wordsGenerated);
                Collections.sort(wordsSorted);
                List wordsReversed = new ArrayList();
                wordsReversed.addAll(wordsGenerated);
                Gdx.app.log("wordsSorted", wordsSorted + "");
                Collections.sort(wordsReversed);
                Collections.reverse(wordsReversed);
                Gdx.app.log("wordsReversed", wordsReversed + "");
                Collections.shuffle(wordsGenerated);
                while (wordsGenerated.get(0).equals(wordsSorted.get(0)) && wordsGenerated.get(1).equals(wordsSorted.get(1)) && wordsGenerated.get(2).equals(wordsSorted.get(2)) && wordsGenerated.get(3).equals(wordsSorted.get(3))) {
                    Gdx.app.log(wordsGenerated.get(0) + "", wordsSorted.get(0) + "");
                    Collections.shuffle(wordsGenerated);
                    Gdx.app.log("shuffled", "againSort");
                }
                while (wordsGenerated.get(0).equals(wordsReversed.get(0)) && wordsGenerated.get(1).equals(wordsReversed.get(1)) && wordsGenerated.get(2).equals(wordsReversed.get(2)) && wordsGenerated.get(3).equals(wordsReversed.get(3))){
                    Collections.shuffle(wordsGenerated);
                    Gdx.app.log("shuffled", "againReversed");
                }
                Gdx.app.log(wordsGenerated.get(0) + "", wordsSorted.get(0) + "");
                wordCube[0].updateWord(wordsGenerated.get(0));
                wordCube[1].updateWord(wordsGenerated.get(1));
                wordCube[2].updateWord(wordsGenerated.get(2));
                wordCube[3].updateWord(wordsGenerated.get(3));

            }
            shouldSwitchWords = false;
        }
        int playtime = (int)timer.getPlayTime();
        if(playtime ==  0){
            Gdx.app.log("return", "return");
            wordCube[0].disappearCube();
            wordCube[1].disappearCube();
            wordCube[2].disappearCube();
            wordCube[3].disappearCube();
            timer.disappearTimer(worldGameWidth);
            currentState = GameState.GAMEOVER;
        }
    }

    public WordCubes[] getWordCube(){

        return wordCube;
    }

    public int getScore() {
        return score;
    }

    public boolean isHighScore() {
        return currentState == GameState.HIGHSCORE;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }

    public boolean isReady() {
        return currentState == GameState.READY;
    }

    public void start() {
        currentState = GameState.RUNNING;
    }

    public void addScore(int increment){

        score += increment;
    }
    public Timer getTimer(){
        return timer;
    }

}
