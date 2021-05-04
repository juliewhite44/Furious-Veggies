package com.io.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Model implements Disposable {
	OrthographicCamera cam;
	SpriteBatch batch;
	Stage menu;
	float width, height;
	
	Screen screen;
	
	//makes adequate stage process any actions its actors should do
	void act() {
		switch (screen) {
		case menu:
			menu.act();
			break;

		default:
			break;
		}
	}
	
	Model(){
		cam = new OrthographicCamera();
		cam.setToOrtho(false);
		cam.update();
		
		batch = new SpriteBatch();
		
		ScreenViewport viewport = new ScreenViewport(cam);
		
		menu = new Stage(viewport, batch);
		
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		
		screen = Screen.menu;
		
		menu = new Stage();
	}

	@Override
	public void dispose() {
		batch.dispose();
		menu.dispose();		
	}
}
