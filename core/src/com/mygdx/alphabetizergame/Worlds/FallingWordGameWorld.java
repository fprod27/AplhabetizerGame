package com.mygdx.alphabetizergame.Worlds;

import com.badlogic.gdx.Gdx;
import com.mygdx.alphabetizergame.AplhabetizerGame;
import com.mygdx.alphabetizergame.GameObjects.LimitLine;
import com.mygdx.alphabetizergame.GameObjects.WordCubes;
import com.mygdx.alphabetizergame.Helpers.AssetLoader;
import com.mygdx.alphabetizergame.Helpers.WordGenerator;
import com.mygdx.alphabetizergame.Screens.GameOverScreenFalling;

import java.util.Collections;
import java.util.List;

public class FallingWordGameWorld {

    private List<String> wordsGenerated;
    private WordGenerator wordGenerator;
    private WordCubes[] wordCubes;
    private LimitLine limitLine;
    private AplhabetizerGame game;
    private int prevHighScore;

    private int worldGameWidth;
    private int worldGameHeight;
    private float prevVelocityY;
    int score = 0;

    private GameState currentState;

    public boolean shouldStart = false;

    public enum GameState {
        READY, RUNNING, GAMEOVER, HIGHSCORE
    }

    public FallingWordGameWorld(int gameHeight, int midPointX, int gameWidth, int midPointY, AplhabetizerGame game){
        prevHighScore = AssetLoader.getHighScoreFalling();

        currentState = GameState.READY;
        this.game = game;
        this.worldGameHeight = gameHeight;
        this.worldGameWidth = gameWidth;

        wordCubes = new WordCubes[7];
        wordGenerator = new WordGenerator();
        wordGenerator.setGeneratedWordsFalling();
        wordsGenerated = wordGenerator.getGeneratedWords();
        Collections.shuffle(wordsGenerated);

        //100/2 = wordcubehalfwidth
        wordCubes[0] = new WordCubes((midPointX - 360)- 100/2, 0 - 100, 100, 100, wordsGenerated.get(0));
        wordCubes[1] = new WordCubes((midPointX - 240)- 100/2, 0 - 100, 100, 100, wordsGenerated.get(1));
        wordCubes[2] = new WordCubes((midPointX - 120) - 100/2, 0 - 100, 100, 100, wordsGenerated.get(2));
        wordCubes[3] = new WordCubes(midPointX - 100/2, 0 - 100, 100, 100, wordsGenerated.get(3));
        wordCubes[4] = new WordCubes((midPointX + 120)- 100/2, 0 - 100, 100, 100, wordsGenerated.get(4));
        wordCubes[5] = new WordCubes((midPointX + 240)- 100/2, 0 - 100, 100, 100, wordsGenerated.get(5));
        wordCubes[6] = new WordCubes((midPointX + 360)- 100/2, 0 - 100, 100, 100, wordsGenerated.get(6));
        //TODO change Limiline width and y
        limitLine = new LimitLine(0, worldGameHeight - 50, worldGameWidth, 10);
        setPrevVelocityY();
    }

    public void updateRunning(float delta) {
        //Gdx.app.log("wordCubeSpeed", wordCubes[0].getVelocity() + "");
        wordCubes[0].update(delta);
        wordCubes[1].update(delta);
        wordCubes[2].update(delta);
        wordCubes[3].update(delta);
        wordCubes[4].update(delta);
        wordCubes[5].update(delta);
        wordCubes[6].update(delta);
        limitLine.update(delta);
        if(wordsGenerated.isEmpty()) {
            //Gdx.app.log("Game","wordsGenerated.isEmpty");
            wordGenerator.setGeneratedWordsFalling();
            wordsGenerated = wordGenerator.getGeneratedWords();
            wordCubes[0].updateWord(wordsGenerated.get(0));
            wordCubes[1].updateWord(wordsGenerated.get(1));
            wordCubes[2].updateWord(wordsGenerated.get(2));
            wordCubes[3].updateWord(wordsGenerated.get(3));
            wordCubes[4].updateWord(wordsGenerated.get(4));
            wordCubes[5].updateWord(wordsGenerated.get(5));
            wordCubes[6].updateWord(wordsGenerated.get(6));

            wordCubes[0].velocity.y = prevVelocityY + 10;
            wordCubes[1].velocity.y = prevVelocityY + 10;
            wordCubes[2].velocity.y = prevVelocityY + 10;
            wordCubes[3].velocity.y = prevVelocityY + 10;
            wordCubes[4].velocity.y = prevVelocityY + 10;
            wordCubes[5].velocity.y = prevVelocityY + 10;
            wordCubes[6].velocity.y = prevVelocityY + 10;
            setPrevVelocityY();
        }
        if(limitLine.collides(wordCubes[0]) || limitLine.collides(wordCubes[1]) || limitLine.collides(wordCubes[2]) || limitLine.collides(wordCubes[3]) || limitLine.collides(wordCubes[4]) || limitLine.collides(wordCubes[5]) || limitLine.collides(wordCubes[6])){
            AssetLoader.gameoversound.play();
            game.setScreen(new GameOverScreenFalling(game, this));
            currentState = GameState.GAMEOVER;
        }


    }

//    public void update(float delta){
//
//        switch (currentState) {
//
//            case READY:
//
//                updateReady(delta);
//                break;
//            case RUNNING:
//                updateRunning(delta);
//                break;
//            default:
//                break;
//
//        }
//    }
//
//    public void updateReady(float delta){
//
//
//    }

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
        Gdx.app.log(word + "", checkTo + "");

        if(word == checkTo){
            addScore(1);
            wordsGenerated.remove(0);
            AssetLoader.wordCubeExplodesound.play();

        }
        else{
            Gdx.app.log("Game", "Dead");
            currentState = GameState.GAMEOVER;
            Gdx.app.log("Game", "NOTHIGHSCORE");
            AssetLoader.gameoversound.play();
            if (score > prevHighScore) {
                AssetLoader.setHighScoreFalling(score);
                currentState = GameState.HIGHSCORE;
                Gdx.app.log("Game", "HIGHSCORE");
            }
        }

        if (score > AssetLoader.getHighScoreFalling()) {
            AssetLoader.setHighScoreFalling(score);
            Gdx.app.log("Game", "HIGHSCORE");
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

    private void setPrevVelocityY(){
        prevVelocityY = wordCubes[0].velocity.y;
    }

}
