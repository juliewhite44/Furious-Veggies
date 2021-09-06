package com.io.furiousveggies.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.io.furiousveggies.StagesFactory;
import com.io.furiousveggies.StagesFactoryImpl;
import com.io.furiousveggies.model.GameElementsFactory;
import com.io.furiousveggies.model.GameElementsFactoryImpl;
import com.io.furiousveggies.settings.Settings;
import com.io.furiousveggies.view.GameView;
import com.io.furiousveggies.view.View;
import com.io.furiousveggies.model.Game;
import com.io.furiousveggies.model.Levels;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class Controller extends ApplicationAdapter {
	private GameController gameController;
	private StagesFactory stagesFactory;
	private GameElementsFactory gameElementsFactory;
	private Game game;
	private GameView gameView;
	private Stage menu;
	private Settings settings;

	private Levels levels;
	private View view;
	private float scale;



	@Override
	public void create () {
		view = new View();

		stagesFactory = new StagesFactoryImpl();
		gameElementsFactory = new GameElementsFactoryImpl();
		scale = Gdx.graphics.getWidth()/Game.width;

		menu = stagesFactory.createMenu(view.getScreenViewport(), view.getSpriteBatch());
		game = stagesFactory.createGame(view.getScreenViewport(), view.getSpriteBatch(), game_esc, view);
		gameView = new GameView(view.getScreenViewport(), view.getSpriteBatch(), view);
		settings = stagesFactory.createSettings(view.getScreenViewport(), view.getSpriteBatch(),  view.getSkinWrapper(), view.getHeight(), view.getWidth());

		levels = new Levels(scale);
		gameController = new GameController(levels, this, gameView);

		setupMenu();
	}

	@Override
	public void render () {
		gameController.act();
		view.draw();
	}

	private void setupMenu() {
		Table buttonsTable = view.createMenuTable();
		buttonsTable.findActor(View.MENU_GAME_BUTTON_NAME).addListener(getMenu_button_game());
		buttonsTable.findActor(View.MENU_SETTINGS_BUTTON_NAME).addListener(getMenu_button_settings());
		menu.addActor(buttonsTable.getParent().getParent());
		Gdx.input.setInputProcessor(menu);
		view.setCurrent(menu);
		view.setExtraCurrent(null);
	}
	private void setupGame() {
		gameView.clear();
		game.clear();
		prepareGame();
		game.addListener(game_esc);
		gameController.startGame(game);
		view.setCurrent(game);
		view.setExtraCurrent(gameView);
	}
	private void setupSettings() {
		settings.clear();
		settings.addListener(game_esc);
		settings.setSettingsChangeListener(newSkin -> {
			view.setSkinWrapper(newSkin);
			settings.getSettingsElementsFactory().setSkinWrapper(newSkin);
			setupSettings();
		});
		view.setCurrent(settings);
		view.setExtraCurrent(null);
	}
	public void prepareGame() {
		Table root = new Table();
		root.setFillParent(true);
		gameView.addActor(root);
		Table mainTable = new Table(view.getSkinWrapper().getSkin());
		mainTable.setBackground(view.getSkinWrapper().gameBackgroundName());
		root.add(mainTable).grow().pad(0);
	}

	public final ChangeListener menu_button_game = new ChangeListener() {
		@Override
		public void changed(ChangeListener.ChangeEvent event, Actor actor) {
			setupGame();
			Gdx.input.setInputProcessor(game);
		}
	};

	public final ChangeListener menu_button_settings = new ChangeListener() {
		@Override
		public void changed(ChangeListener.ChangeEvent event, Actor actor) {
			setupSettings();
			Gdx.input.setInputProcessor(settings);
		}
	};

	public final InputListener game_esc = new InputListener(){
		@Override
		public boolean keyDown (InputEvent event, int keycode){
			if(keycode == Input.Keys.ESCAPE) {
				setupMenu();
				Gdx.input.setInputProcessor(menu);
			}
			return true;
		}
	};

	public ChangeListener getMenu_button_game() {
		return menu_button_game;
	}

	public ChangeListener getMenu_button_settings() {
		return menu_button_settings;
	}
}
