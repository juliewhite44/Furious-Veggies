package com.io.furiousveggies;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FuriousVeggies extends ApplicationAdapter {
	private Levels levels;
	private View view;
	private Controller controller;
	
	@Override
	public void create () {
		levels = new Levels();
		view = new View();
		controller = new Controller(levels, view);
		
		view.createMenu();
		view.createSettings();
		
	}

	@Override
	public void render () {
		levels.act();
		view.draw();
	}
	
	@Override
	public void dispose () {
		view.dispose();
	}
}
