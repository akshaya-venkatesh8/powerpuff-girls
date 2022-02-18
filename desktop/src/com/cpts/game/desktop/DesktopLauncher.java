package com.cpts.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.cpts.game.MyGdxGame;
import com.cpts.game.SpaceShooterGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 760;
		config.width = 390;
		new LwjglApplication(new SpaceShooterGame(), config);
	}
}
