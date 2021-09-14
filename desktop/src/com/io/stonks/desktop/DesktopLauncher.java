package com.io.stonks.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.io.stonks.controller.Controller;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Stonks";
		config.width = 1024;
		config.height = 512;
		config.resizable = false;
		//config.fullscreen = true;
		new LwjglApplication(new Controller(), config);
	}
}
