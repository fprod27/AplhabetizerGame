package com.mygdx.alphabetizergame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mygdx.alphabetizergame.Helpers.AssetLoader;
import com.mygdx.alphabetizergame.Screens.SplashScreen;

public class AplhabetizerGame extends Game {
	public boolean gameStarted = false;

	@Override
	public void create () {
        Gdx.app.log("ZBGame", "created");
        setScreen(new SplashScreen(this));
	}

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
        AssetLoader.disposeSkin();
        AssetLoader.disposeSplash();
    }
}
