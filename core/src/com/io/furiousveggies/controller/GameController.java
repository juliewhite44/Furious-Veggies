package com.io.furiousveggies.controller;

import com.badlogic.gdx.Gdx;
import com.io.furiousveggies.model.*;
import com.io.furiousveggies.view.GameView;

public class GameController {

	private Controller controller;
	private Levels levels;
	private int currentLevel;
	private Game game;
	private GameView gameView;


	public void act() {
		if(Gdx.input.getInputProcessor() == game) {
			gameView.destroyEnemies(game.getDefeatedEnemies());
			gameView.addRopeView(game.getRope());
			game.setRope(null);
			if(game.isDeleteRope()) {
				gameView.deleteRope();
				game.setDeleteRope(false);
			}
			gameView.act();
			game.setMousePositionX(Gdx.input.getX());
			game.setMousePositionY(Gdx.input.getY());
			game.act();
		}
	}

	private void addActorsToView(LevelElements levelElements) {
		for(Projectile projectile : levelElements.getProjectiles()) {
			gameView.addProjectileView(projectile);
		}
		for(Shooter shooter : levelElements.getShooters()) {
			gameView.addShooterView(shooter);
		}
		for(Block block : levelElements.getBlocks()) {
			gameView.addBlockView(block);
		}
		for(Enemy enemy : levelElements.getEnemies()) {
			gameView.addEnemyView(enemy);
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
					gameView.clear();
					controller.prepareGame();

					game.addListener(controller.game_esc);
					LevelElements levelElements;
					levelElements = levels.getLevel(currentLevel).apply(game);
					addActorsToView(levelElements);
				}
			}

			@Override
			public void onGameOver() { }
		});
		if (currentLevel >= levels.getSize()){
			currentLevel = 0;
		}
		LevelElements levelElements;
		levelElements = levels.getLevel(currentLevel).apply(game);
		addActorsToView(levelElements);
	}

	public GameController(Levels levels, Controller controller, GameView gameView) {
		this.levels = levels;
		this.currentLevel = 0;
		this.controller = controller;
		this.gameView = gameView;
	}
}