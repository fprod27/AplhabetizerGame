package com.mygdx.alphabetizergame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.alphabetizergame.AplhabetizerGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new AplhabetizerGame(), config);

        config.width = 500;
        config.height = 500;
        config.title = "Alphabetizer Game";
	}
}
