package com.io.furiousveggies.model;

import com.badlogic.gdx.utils.Array;

import java.util.LinkedList;
import java.util.function.Function;

public class Levels {
	private Array<Function<Game, LevelElements>> levels;
	private float scale;

	public LevelElements level1(Game game) {
		LevelElements levelElements = new LevelElements();
		LinkedList<GameElement> gameElements = new LinkedList<>();

		game.addGround();
		gameElements.add(game.addBox(14f, 5f, 3f, scale));
		gameElements.add(game.addBox(14f, 2f, 1.5f, scale));
		gameElements.add(game.addShooter(5f, 2f, scale));
		gameElements.add(game.addProjectile(1f, scale));
		gameElements.add(game.addProjectile(1f, scale));
		gameElements.add(game.addProjectile(1f, scale));
		gameElements.add(game.addProjectile(1f, scale));
		gameElements.add(game.addBox(10f, 2f, 1.5f, scale));
		gameElements.add(game.addEnemy(10f, 5f, 2f, scale));
		gameElements.add(game.addEnemy(13f, 7f, 1f, scale));
		gameElements.add(game.addEnemy(15f, 7f, 1f, scale));

		levelElements.addElements(gameElements);
		return levelElements;
	}

	public LevelElements level2(Game game){
		LevelElements levelElements = new LevelElements();
		LinkedList<GameElement> gameElements = new LinkedList<>();

		game.addGround();
		gameElements.add(game.addShooter(3f, 1.5f, scale));
		gameElements.add(game.addProjectile(0.75f, scale));
		gameElements.add(game.addProjectile(0.75f, scale));
		gameElements.add(game.addProjectile(0.75f, scale));
		gameElements.add(game.addProjectile(0.75f, scale));
		gameElements.add(game.addBox(7f, 1f, 1f, scale));
		gameElements.add(game.addBox(7f, 2f, 1f, scale));
		gameElements.add(game.addBox(7f, 3f, 1f, scale));
		gameElements.add(game.addEnemy(7f, 4f, 1f, scale));
		gameElements.add(game.addBox(10f, 1f, 1f, scale));
		gameElements.add(game.addBox(10f, 2f, 1f, scale));
		gameElements.add(game.addBox(10f, 3f, 1f, scale));
		gameElements.add(game.addBox(10f, 4f, 1f, scale));
		gameElements.add(game.addEnemy(10f, 5f, 1f, scale));
		gameElements.add(game.addBox(13f, 1f, 1f, scale));
		gameElements.add(game.addBox(13f, 2f, 1f, scale));
		gameElements.add(game.addEnemy(13f, 3f, 1f, scale));

		levelElements.addElements(gameElements);
		return levelElements;
	}

	public Levels(float scale) {
		levels = new Array<>();
		levels.add(this::level1);
		levels.add(this::level2);
		this.scale = scale;
	}

	public Function<Game, LevelElements> getLevel(int level) {
		return levels.get(level);
	}
	public int getSize() {
		return levels.size;
	}
}
