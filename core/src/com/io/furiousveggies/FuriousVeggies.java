package com.io.furiousveggies;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FuriousVeggies extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture img;
	
	private Model model;
	private View view;
	private Controller controller;
	
	@Override
	public void create () {
		model = new Model();
		controller = new Controller(model);
		view = new View(controller);
		
		
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		model.act();
		view.draw();
		controller.control();
		
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
