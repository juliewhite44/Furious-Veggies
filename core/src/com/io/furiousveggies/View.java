package com.io.furiousveggies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class View implements Disposable {
	private OrthographicCamera cam;
	private SpriteBatch batch;
	private Stage menu, settings, game, current;
	Controller controller;
	final static Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
	private float width, height;
	
	//draw current stage
	public void draw() {
		Gdx.gl.glClearColor(0, 0.4f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		current.draw();
	}


	public Stage createGame() {
		game.clear();
		game.addListener(controller.game_esc);
		Table root = new Table();
		root.setFillParent(true);
		game.addActor(root);
		
		Table mainTable = new Table(skin);
		mainTable.setBackground("window-round");
		
		root.add(mainTable).grow().pad(0);
		return game;
	}

	public void createSettings() {
		settings.addListener(controller.game_esc);
		Table root = new Table();
		root.setFillParent(true);
		settings.addActor(root);

		Table mainTable = new Table(skin);
		mainTable.setBackground("window-round");

		root.add(mainTable).grow().pad(0);
	}
	
	public InputProcessor setMenu() {
		return current = menu;
	}
	
	public InputProcessor setGame() {
		return current = game;
	}
	
	public InputProcessor setSettings() {
		return current = settings;
	}


	//nie mozemy odnosic sie tutaj do model.width, jak wczesniej, wiec jest aktualnie
	//controller.getModel().getWidth(), ale czemu po prostu nie zrobić Gdx.graphics.getWidth();
	//czy zmienna width jest do czegoś używana w klasie Model?
	public void createMenu() {
		Table root = new Table();
		root.setFillParent(true);
		menu.addActor(root);
		
		Table table = new Table(skin);
		table.setBackground("window");
		table.setBounds(0, 0, width, height);
		
		root.add(table).grow().pad(0);
		
		Table subtable = new Table();
		
		TextButton menu_game = new TextButton("Play", skin);
		menu_game.addListener(controller.getMenu_button_game());
		subtable.add(menu_game).grow().padLeft(width/100).padTop(height/100).padRight(width/100);
		subtable.row();
		
		TextButton menu_settings = new TextButton("Settings", skin);
		menu_settings.addListener(controller.getMenu_button_settings());
		subtable.add(menu_settings).grow().padLeft(width/100).padTop(height/100).padBottom(width/100).padRight(height/100);
		table.add(subtable).grow().padRight(0);
		
		Label title = new Label("Furious\nVeggies", skin, "title");
		table.add(title).expand().center().padTop(height/100);
		
	}

	public View(){		
		cam = new OrthographicCamera();
		cam.setToOrtho(false);
		cam.update();
		
		batch = new SpriteBatch();
		
		ScreenViewport viewport = new ScreenViewport(cam);
		
		menu = new Stage(viewport, batch);
		settings = new Stage(viewport, batch);
		game = new Stage(viewport, batch);
		
		current = menu;
	}

	@Override
	public void dispose() {
		batch.dispose();
		menu.dispose();
		settings.dispose();
		game.dispose();
		skin.dispose();
	}
}
