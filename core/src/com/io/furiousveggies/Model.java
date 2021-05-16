package com.io.furiousveggies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Model implements Disposable {
	private OrthographicCamera cam;
	private SpriteBatch batch;
	private Stage menu, game, settings;
	private float width, height;
	
	private Screen screen;

	public void setScreen(Screen sc) {
		screen = sc;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	//makes adequate stage process any actions its actors should do
	public void act() {
		switch (screen) {
		case menu:
			menu.act();
			break;
		case game:
			game.act();
			break;
		case settings:
			settings.act();
		default:
			break;
		}
	}
	
	public void startGame() {
		//todo
	}

	public void startSettings() {
		//todo
	}

	public void setInputProcessor() {
		switch (screen) {
			case menu:
				Gdx.input.setInputProcessor(menu);
				break;
			case game:
				Gdx.input.setInputProcessor(game);
				break;
			case settings:
				Gdx.input.setInputProcessor(settings);
				break;
			default:
				break;
		}
	}

	public void draw() {
		Gdx.gl.glClearColor(0, 0.4f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		switch (screen) {
			case menu:
				menu.draw();
				break;
			case game:
				game.draw();
				break;
			case settings:
				settings.draw();
				break;
			default:
				break;
		}
	}

	public void addActorForGame(Table table) {
		game.addActor(table);
	}
	public void addActorForMenu(Table table) {
		menu.addActor(table);
	}
	public void addActorForSettings(Table table) {
		settings.addActor(table);
	}

	public void addListenerForGame(InputListener listener) {
		game.addListener(listener);
	}

	public Model(){
		cam = new OrthographicCamera();
		cam.setToOrtho(false);
		cam.update();
		
		batch = new SpriteBatch();
		
		ScreenViewport viewport = new ScreenViewport(cam);
		
		menu = new Stage(viewport, batch);
		game = new Stage(viewport, batch);
		settings = new Stage(viewport, batch);
		
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		
		screen = Screen.menu;
	}

	@Override
	public void dispose() {
		batch.dispose();
		menu.dispose();		
	}
}
