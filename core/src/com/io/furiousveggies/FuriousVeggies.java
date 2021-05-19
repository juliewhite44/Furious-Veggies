package com.io.furiousveggies;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FuriousVeggies extends ApplicationAdapter {
	private Model model;
	private View view;
	private Controller controller;
	
	@Override
	public void create () {
		model = new Model();
		view = new View();
		controller = new Controller(model, view);
		
		view.createMenu();
		view.createSettings();
		
	}

	@Override
	public void render () {
		model.act();
		view.draw();
	}
	
	@Override
	public void dispose () {
		model.dispose();
		view.dispose();
	}
}
