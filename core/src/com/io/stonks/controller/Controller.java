package com.io.stonks.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.io.stonks.StagesFactory;
import com.io.stonks.StagesFactoryImpl;
import com.io.stonks.model.GameElementsFactory;
import com.io.stonks.model.GameElementsFactoryImpl;
import com.io.stonks.view.settings.Settings;
import com.io.stonks.view.GameView;
import com.io.stonks.view.View;
import com.io.stonks.model.Game;
import com.io.stonks.model.Levels;
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
	private Stage gameOver;
	private Settings settings;
	private Sound sound;

	private Levels levels;
	private View view;
	private float scale;



	@Override
	public void create () {
		view = new View();

		stagesFactory = new StagesFactoryImpl();
		gameElementsFactory = new GameElementsFactoryImpl();
		scale = Gdx.graphics.getWidth()/Game.width;

		menu = stagesFactory.createDefaultStage(view.getScreenViewport(), view.getSpriteBatch());
		gameOver = stagesFactory.createDefaultStage(view.getScreenViewport(), view.getSpriteBatch());
		game = stagesFactory.createGame(game_esc, scale);
		gameView = new GameView(view.getScreenViewport(), view.getSpriteBatch(), view);
		settings = stagesFactory.createSettings(view.getScreenViewport(), view.getSpriteBatch(),  view.getSkinWrapper(), view.getHeight(), view.getWidth());

		levels = new Levels(scale);
		gameController = new GameController(levels, this, gameView);

		sound = Gdx.audio.newSound(Gdx.files.internal("sound/mainTheme.wav"));
		sound.loop();

		setupMenu();
	}

	@Override
	public void render () {
		gameController.act();
		view.draw();
	}

	public void setupEndGame(int endLevel) {
		Table buttonsTable = view.createEndGameTable();
		buttonsTable.findActor(View.END_GAME_MENU_BUTTON_NAME).addListener(end_game_button_menu);
		buttonsTable.findActor(View.END_GAME_RETRY_BUTTON_NAME).addListener(new ChangeListener() {
			@Override
			public void changed(ChangeListener.ChangeEvent event, Actor actor) {
				setupGame(endLevel);
				Gdx.input.setInputProcessor(game);
			}
		});
		gameOver.addActor(buttonsTable.getParent().getParent());
		Gdx.input.setInputProcessor(gameOver);
		view.setCurrent(gameOver);
	}
	private void setupMenu() {
		Table buttonsTable = view.createMenuTable();
		buttonsTable.findActor(View.MENU_GAME_BUTTON_NAME).addListener(getMenu_button_game());
		buttonsTable.findActor(View.MENU_SETTINGS_BUTTON_NAME).addListener(getMenu_button_settings());
		menu.addActor(buttonsTable.getParent().getParent());
		Gdx.input.setInputProcessor(menu);
		view.setCurrent(menu);
	}
	private void setupGame(int fromLevel) {
		gameView.clear();
		game.clear();
		prepareGame();
		game.addListener(game_esc);
		gameController.startGame(game, fromLevel);
		view.setCurrent(gameView);
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
	}
	public void prepareGame() {
		Table root = new Table();
		root.setFillParent(true);
		gameView.addActor(root);
		Table mainTable = new Table(view.getSkinWrapper().getSkin());
		mainTable.setBackground(view.getSkinWrapper().gameBackgroundName());
		root.add(mainTable).grow().pad(0);
	}

	public final ChangeListener end_game_button_menu = new ChangeListener() {
		@Override
		public void changed(ChangeListener.ChangeEvent event, Actor actor) {
			setupMenu();
			Gdx.input.setInputProcessor(menu);
		}
	};

	public final ChangeListener menu_button_game = new ChangeListener() {
		@Override
		public void changed(ChangeListener.ChangeEvent event, Actor actor) {
			setupGame(0);
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
