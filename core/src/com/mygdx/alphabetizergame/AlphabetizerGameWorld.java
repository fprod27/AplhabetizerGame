package com.mygdx.alphabetizergame;

import com.badlogic.gdx.Gdx;
import com.mygdx.alphabetizergame.GameObjects.LimitLine;
import com.mygdx.alphabetizergame.GameObjects.WordCubes;
import com.mygdx.alphabetizergame.Helpers.AssetLoader;
import com.mygdx.alphabetizergame.Helpers.WordGenerator;
import com.mygdx.alphabetizergame.Screens.GameOverScreen;
import com.mygdx.alphabetizergame.Screens.MenuScreen;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * Created by angelo_2 on 11/20/2014.
 */
public class AlphabetizerGameWorld {

    private List<String> wordsGenerated;
    private WordGenerator wordGenerator;
    private WordCubes[] wordCubes;
    private LimitLine limitLine;
    AplhabetizerGame game;

    int score = 0;

    private GameState currentState;

    public enum GameState {
        READY, RUNNING, GAMEOVER, HIGHSCORE
    }

    public AlphabetizerGameWorld(int gameHeight, int midPointX, int gameWidth, int midPointY, AplhabetizerGame game){
        currentState = GameState.READY;
        this.game = game;

        wordCubes = new WordCubes[5];
        wordGenerator = new WordGenerator();
        wordGenerator.setGeneratedWords();
        wordsGenerated = wordGenerator.getGeneratedWords();

        //50/2 = wordcubehalfsize
        wordCubes[0] = new WordCubes((midPointX - 200)- 100/2, 0 - 115, 75, 50, wordsGenerated.get(0));
        wordCubes[1] = new WordCubes((midPointX - 100) - 100/2, 0 - 115, 75, 50, wordsGenerated.get(1));
        wordCubes[2] = new WordCubes(midPointX - 100/2, 0 - 115, 75, 50, wordsGenerated.get(2));
        wordCubes[3] = new WordCubes((midPointX + 100)- 100/2, 0 - 115, 75, 50, wordsGenerated.get(3));
        wordCubes[4] = new WordCubes((midPointX + 200)- 100/2, 0 - 115, 75, 50, wordsGenerated.get(4));

        limitLine = new LimitLine(0, gameHeight - 50, gameWidth, 10);

    }

    public void updateRunning(float delta) {
        wordCubes[0].update(delta);
        wordCubes[1].update(delta);
        wordCubes[2].update(delta);
        wordCubes[3].update(delta);
        wordCubes[4].update(delta);
        limitLine.update(delta);

        //Gdx.app.log("Game", wordCubes[0].getVelocity() + "");

        if(wordsGenerated.isEmpty()) {

            Gdx.app.log("Game","wordsGenerated.isEmpty");

            wordCubes[0].reset();
            wordCubes[1].reset();
            wordCubes[2].reset();
            wordCubes[3].reset();
            wordCubes[4].reset();

            wordGenerator.setGeneratedWords();

            wordsGenerated = wordGenerator.getGeneratedWords();

            wordCubes[0].updateWord(wordsGenerated.get(0));
            wordCubes[1].updateWord(wordsGenerated.get(1));
            wordCubes[2].updateWord(wordsGenerated.get(2));
            wordCubes[3].updateWord(wordsGenerated.get(3));
            wordCubes[4].updateWord(wordsGenerated.get(4));

        }
        if(limitLine.collides(wordCubes[0]) || limitLine.collides(wordCubes[1]) || limitLine.collides(wordCubes[2]) || limitLine.collides(wordCubes[3]) || limitLine.collides(wordCubes[4])){
            game.setScreen(new GameOverScreen(game, this));
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


    public LimitLine getLimitLine(){

        return limitLine;

    }

    public int getScore(){

        return score;

    }

    public void addScore(int increment){

        score += increment;
    }

    public void checkWord(String word){

        Collections.sort(wordsGenerated);
        String checkTo = wordsGenerated.get(0);

        if(word == checkTo){

            addScore(1);
            wordsGenerated.remove(0);
        }
        else{
            Gdx.app.log("Game", "Dead");
            currentState = GameState.GAMEOVER;
            Gdx.app.log("Game", "NOTHIGHSCORE");

            if (score > AssetLoader.getHighScore()) {
                AssetLoader.setHighScore(score);
                currentState = GameState.HIGHSCORE;
                Gdx.app.log("Game", "HIGHSCORE");
            }
        }

    }

    public boolean isReady(){
        return currentState == GameState.READY;
    }

    public void start(){
        currentState = GameState.RUNNING;
    }

    public void restart(){
        currentState = GameState.READY;
        score = 0;
        //reset y 0 - cubeheight + 15
        int resetY = 0 - 115;
        wordCubes[0].onRestart(resetY);
        wordCubes[1].onRestart(resetY);
        wordCubes[2].onRestart(resetY);
        wordCubes[3].onRestart(resetY);
        wordCubes[4].onRestart(resetY);
        currentState = GameState.READY;
        wordGenerator.setGeneratedWords();

        wordCubes[0].updateWord(wordsGenerated.get(0));
        wordCubes[1].updateWord(wordsGenerated.get(1));
        wordCubes[2].updateWord(wordsGenerated.get(2));
        wordCubes[3].updateWord(wordsGenerated.get(3));
        wordCubes[4].updateWord(wordsGenerated.get(4));

        Gdx.app.log("Game", "restarting");
    }

    public boolean isGameOver(){
        return currentState == GameState.GAMEOVER;
    }

    public boolean isHighScore() {
        return currentState == GameState.HIGHSCORE;
    }

}
