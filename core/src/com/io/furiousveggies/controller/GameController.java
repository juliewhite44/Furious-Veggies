package com.io.furiousveggies.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.io.furiousveggies.game.GameResultListener;
import com.io.furiousveggies.model.Levels;
import com.io.furiousveggies.game.Game;

public class LevelsController {

	private Controller controller;
	private Levels levels;
	private int currentLevel;
	private Game game;

	public void act() {
		if(Gdx.input.getInputProcessor() == game) {
			game.act();
		}
	}

	public void startGame(Game g) {
		game = g;
		currentLevel = 0;
		game.setGameResultListener(new GameResultListener() {
			@Override
			public void onGameWin() {
				currentLevel++;
				if (currentLevel < levels.getSize()){
					game.clear();
					controller.prepareGame();

					game.addListener(controller.game_esc);
					levels.getLevel(currentLevel).accept(game);
				}
			}

			@Override
			public void onGameOver() { }
		});
		if (currentLevel >= levels.getSize()){
			currentLevel = 0;
		}
		levels.getLevel(currentLevel).accept(game);
	}

	public LevelsController(Levels levels, Controller controller) {
		this.levels = levels;
		this.currentLevel = 0;
		this.controller = controller;
	}
}