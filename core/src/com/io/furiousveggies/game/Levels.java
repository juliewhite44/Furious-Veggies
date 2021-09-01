package com.io.furiousveggies.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.io.furiousveggies.Controller;

import java.util.function.Consumer;

public class Levels {
	public Controller controller;
	private Game game;
	private Array<Consumer<Game>> levels;
	private int currentLevel;

	//makes adequate stage process any actions its actors should do
	public void act() {
		if(Gdx.input.getInputProcessor() == game) {
			game.act();
		}
	}
	
	public void level1(Game game) {
		game.addGround();
		game.addBox(14f, 5f, 3f);
		game.addBox(14f, 2f, 1.5f);
		game.addShooter(5f, 2f);
		game.addProjectile(1f);
		game.addProjectile(1f);
		game.addProjectile(1f);
		game.addProjectile(1f);
		game.addBox(10f, 2f, 1.5f);
		game.addEnemy(10f, 5f, 2f);
		game.addEnemy(13f, 7f, 1f);
		game.addEnemy(15f, 7f, 1f);
	}

	public void level2(Game game){
		game.addGround();
		game.addShooter(3f, 1.5f);
		game.addProjectile(0.75f);
		game.addProjectile(0.75f);
		game.addProjectile(0.75f);
		game.addProjectile(0.75f);
		game.addBox(7f, 1f, 1f);
		game.addBox(7f, 2f, 1f);
		game.addBox(7f, 3f, 1f);
		game.addEnemy(7f, 4f, 1f);
		game.addBox(10f, 1f, 1f);
		game.addBox(10f, 2f, 1f);
		game.addBox(10f, 3f, 1f);
		game.addBox(10f, 4f, 1f);
		game.addEnemy(10f, 5f, 1f);
		game.addBox(13f, 1f, 1f);
		game.addBox(13f, 2f, 1f);
		game.addEnemy(13f, 3f, 1f);
	}

	public void startGame(Game g) {
		game = g;
		game.setResultListener(new GameResultListener() {
			@Override
			public void onGameWin() {
				currentLevel++;
				if (currentLevel < levels.size){
					game.clear();
					game.addListener(controller.game_esc);
					levels.get(currentLevel).accept(game);
				}
			}

			@Override
			public void onGameOver() { }
		});
		if (currentLevel >= levels.size){
			currentLevel = 0;
		}
		levels.get(currentLevel).accept(game);
	}

	public Levels(){
		levels = new Array<>();
		levels.add(this::level1);
		levels.add(this::level2);
		currentLevel = 0;
	}
}