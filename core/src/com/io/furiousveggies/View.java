package com.io.furiousveggies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.io.furiousveggies.game.Game;
import com.io.furiousveggies.settings.Settings;
import com.io.furiousveggies.settings.SettingsChangeListener;
import com.io.furiousveggies.skins.SkinWrapper;

public class View implements Disposable {
	private OrthographicCamera cam;
	private SpriteBatch batch;
	private Game game;
	private Settings settings;
	private Stage menu, current;
	Controller controller;
	private SkinWrapper skin;
	private float width, height;
	
	//draw current stage
	public void draw() {
		Color color = skin.backgroundColor();
		Gdx.gl.glClearColor(color.r, color.g, color.b, color.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		current.draw();
	}


	public Game createGame() {
		game.setElementsFactory(new SimpleGameFactory(skin));
		game.clear();
		game.addListener(controller.game_esc);
		return game;
	}

	public void createSettings() {
		settings.clear();
		settings.addListener(controller.game_esc);
		settings.setChangeListener(new SettingsChangeListener() {
			@Override
			public void onSkinChanged(SkinWrapper newSkin) {
				skin = newSkin;
				game.setElementsFactory(new SimpleGameFactory(skin));
				settings.setElementsFactory(new SimpleSettingsFactory(skin, height/100, width/100));
				createSettings();
			}
		});
	}
	
	public InputProcessor setMenu() {
		createMenu();
		return current = menu;
	}
	
	public InputProcessor setGame() {
		return current = game;
	}
	
	public InputProcessor setSettings() {
		createSettings();
		return current = settings;
	}


	//nie mozemy odnosic sie tutaj do model.width, jak wczesniej, wiec jest aktualnie
	//controller.getModel().getWidth(), ale czemu po prostu nie zrobić Gdx.graphics.getWidth();
	//czy zmienna width jest do czegoś używana w klasie Model?
	public void createMenu() {
		Table root = new Table();
		root.setFillParent(true);
		menu.addActor(root);
		
		Table table = new Table(skin.getSkin());
		table.setBackground(skin.menuBackgroundName());
		table.setBounds(0, 0, width, height);
		
		root.add(table).grow().pad(0);
		
		Table subtable = new Table();
		
		TextButton menu_game = new TextButton("Play", skin.getSkin());
		menu_game.addListener(controller.getMenu_button_game());
		subtable.add(menu_game).grow().padLeft(width/100).padTop(height/100).padRight(width/100);
		subtable.row();
		
		TextButton menu_settings = new TextButton("Settings", skin.getSkin());
		menu_settings.addListener(controller.getMenu_button_settings());
		subtable.add(menu_settings).grow().padLeft(width/100).padTop(height/100).padBottom(width/100).padRight(height/100);
		table.add(subtable).grow().padRight(0);
		
		Label title = new Label("Furious\nVeggies", skin.getSkin(), "title");
		table.add(title).expand().center().padTop(height/100);
		
	}

	public View(){		
		cam = new OrthographicCamera();
		cam.setToOrtho(false);
		cam.update();
		
		batch = new SpriteBatch();
		
		ScreenViewport viewport = new ScreenViewport(cam);
		
		menu = new Stage(viewport, batch);
		settings = new Settings(viewport, batch, null);
		skin = settings.getDefaultSkin();
		settings.setElementsFactory(new SimpleSettingsFactory(skin, height/100, width/100));
		game = new Game(viewport, batch, new SimpleGameFactory(skin));
		
		current = menu;
	}

	@Override
	public void dispose() {
		batch.dispose();
		menu.dispose();
		settings.dispose();
		game.dispose();
	}
}
