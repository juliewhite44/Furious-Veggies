package com.io.stonks.model;

import com.badlogic.gdx.utils.Array;

import java.util.LinkedList;
import java.util.function.Function;

public class Levels {
	private Array<Function<Game, LevelElements>> levels;
	private float scale;

	public LevelElements level1(Game game){
		LevelElements levelElements = new LevelElements();
		LinkedList<GameElement> gameElements = new LinkedList<>();

		game.addWalls();
		gameElements.add(game.addShooter(4f, 2f, scale));
		gameElements.add(game.addProjectile(0.75f, scale));
		gameElements.add(game.addProjectile(0.75f, scale));
		gameElements.add(game.addBox(11f, 0.6f, 1f, scale));
		gameElements.add(game.addBox(12f, 0.6f, 1f, scale));
		gameElements.add(game.addBox(10.5f, 1.6f, 0.98f, scale));
		gameElements.add(game.addEnemy(11.5f, 1.6f, 1f, scale));
		gameElements.add(game.addBox(12.5f, 1.6f, 0.98f, scale));
		gameElements.add(game.addBox(11f, 2.6f, 1f, scale));
		gameElements.add(game.addBox(12f, 2.6f, 1f, scale));

		levelElements.addElements(gameElements);
		return levelElements;
	}

	public LevelElements level2(Game game){
		LevelElements levelElements = new LevelElements();
		LinkedList<GameElement> gameElements = new LinkedList<>();

		game.addWalls();
		gameElements.add(game.addShooter(4f, 1.5f, scale));
		gameElements.add(game.addProjectile(0.75f, scale));
		gameElements.add(game.addProjectile(0.75f, scale));
		gameElements.add(game.addProjectile(0.75f, scale));
		gameElements.add(game.addProjectile(0.75f, scale));

		gameElements.add(game.addBox(8.5f, 0.6f, 1, scale));
		gameElements.add(game.addBox(9.5f, 0.6f, 1, scale));
		gameElements.add(game.addBox(10.5f, 0.6f, 1, scale));
		gameElements.add(game.addBox(11.5f, 0.6f, 1, scale));
		gameElements.add(game.addBox(10, 1.6f, 1, scale));

		gameElements.add(game.addEnemy(9, 1.6f, 1, scale));
		gameElements.add(game.addEnemy(11, 1.6f, 1, scale));
		gameElements.add(game.addEnemy(9.5f, 2.6f, 1, scale));
		gameElements.add(game.addEnemy(10.5f, 2.6f, 1, scale));
		gameElements.add(game.addEnemy(10, 3.6f, 1, scale));

		levelElements.addElements(gameElements);
		return levelElements;
	}

	public LevelElements level3(Game game){
		LevelElements levelElements = new LevelElements();
		LinkedList<GameElement> gameElements = new LinkedList<>();

		game.addWalls();
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

	public LevelElements level4(Game game){
		LevelElements levelElements = new LevelElements();
		LinkedList<GameElement> gameElements = new LinkedList<>();

		game.addWalls();
		gameElements.add(game.addShooter(4f, 1.5f, scale));
		gameElements.add(game.addProjectile(0.75f, scale));
		gameElements.add(game.addProjectile(0.75f, scale));
		gameElements.add(game.addProjectile(0.75f, scale));
		gameElements.add(game.addProjectile(0.75f, scale));

		gameElements.add(game.addBox(9, 0.6f, 2, scale));
		gameElements.add(game.addBox(11.5f, 0.6f, 2, scale));
		gameElements.add(game.addBox(10.25f, 2.6f, 1.5f, scale));
		gameElements.add(game.addBox(13.875f, 0.6f, 0.5f, scale));
		gameElements.add(game.addBox(15.875f, 0.6f, 0.5f, scale));
		gameElements.add(game.addBox(14.875f, 2, 2.5f, scale));

		gameElements.add(game.addEnemy(10.25f, 3.6f, 1, scale));
		gameElements.add(game.addEnemy(14.875f, 4.6f, 1, scale));

		levelElements.addElements(gameElements);
		return levelElements;
	}

	public LevelElements level5(Game game){
		LevelElements levelElements = new LevelElements();
		LinkedList<GameElement> gameElements = new LinkedList<>();

		game.addWalls();
		gameElements.add(game.addShooter(5f, 1.7f, scale));
		gameElements.add(game.addProjectile(0.75f, scale));
		gameElements.add(game.addProjectile(0.75f, scale));
		gameElements.add(game.addProjectile(0.75f, scale));
		gameElements.add(game.addProjectile(0.75f, scale));
		gameElements.add(game.addProjectile(0.75f, scale));


		gameElements.add(game.addBox(9f, 1f, 1, scale));
		gameElements.add(game.addBox(11f, 1f, 1, scale));

		for(int i = 0; i < 3; i ++) {
			gameElements.add(game.addBox(9f+(float)i, 1f+(float)((i+1)%2), 1f, scale));
			gameElements.add(game.addEnemy(9f+(float)i, 2f+(float)((i+1)%2), 1f, scale));
			gameElements.add(game.addBox(9f+(float)i, 3f+(float)((i+1)%2), 1f, scale));
				gameElements.add(game.addEnemy(9f+(float)i, 4f+(float)((i+1)%2), 1f, scale));
		}

		levelElements.addElements(gameElements);
		return levelElements;
	}

	public LevelElements level6(Game game) {
		LevelElements levelElements = new LevelElements();
		LinkedList<GameElement> gameElements = new LinkedList<>();

		game.addWalls();
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

	public LevelElements level7(Game game){
		LevelElements levelElements = new LevelElements();
		LinkedList<GameElement> gameElements = new LinkedList<>();

		game.addWalls();
		gameElements.add(game.addShooter(4f, 2f, scale));
		gameElements.add(game.addProjectile(1.1f, scale));
		gameElements.add(game.addProjectile(0.75f, scale));
		gameElements.add(game.addBox(8f, 1f, 1f, scale));
		gameElements.add(game.addBox(8f, 2f, 1f, scale));
		gameElements.add(game.addBox(8f, 3f, 1f, scale));
		gameElements.add(game.addBox(8f, 4f, 1f, scale));
		gameElements.add(game.addBox(8f, 5f, 1f, scale));
		gameElements.add(game.addBox(8f, 6f, 1f, scale));
		gameElements.add(game.addBox(8f, 7f, 1f, scale));
		gameElements.add(game.addBox(8f, 8f, 1f, scale));
		gameElements.add(game.addBox(13f, 1f, 1f, scale));
		gameElements.add(game.addEnemy(13f, 2f, 1f, scale));
		gameElements.add(game.addBox(16f, 1f, 1f, scale));
		gameElements.add(game.addBox(16f, 2f, 1f, scale));
		gameElements.add(game.addEnemy(16f, 3f, 1f, scale));

		levelElements.addElements(gameElements);
		return levelElements;
	}

	public LevelElements level8(Game game){
		LevelElements levelElements = new LevelElements();
		LinkedList<GameElement> gameElements = new LinkedList<>();

		game.addWalls();
		gameElements.add(game.addShooter(4f, 1.5f, scale));
		gameElements.add(game.addProjectile(0.75f, scale));
		gameElements.add(game.addProjectile(0.75f, scale));
		gameElements.add(game.addProjectile(0.75f, scale));
		gameElements.add(game.addProjectile(0.75f, scale));
		gameElements.add(game.addProjectile(0.75f, scale));

		gameElements.add(game.addBox(8.5f, 0.6f, 2f, scale));
		gameElements.add(game.addBox(12f, 0.6f, 2.6f, scale));
		gameElements.add(game.addBox(10.30f, 2.6f, 1.8f, scale));
		gameElements.add(game.addBox(15, 0.6f, 1, scale));

		gameElements.add(game.addEnemy(8.2f, 2.6f, 1, scale));
		gameElements.add(game.addEnemy(10.2f, 3.8f, 1, scale));
		gameElements.add(game.addEnemy(12.2f, 2.6f, 1, scale));
		gameElements.add(game.addEnemy(15, 1.6f, 1, scale));


		levelElements.addElements(gameElements);
		return levelElements;
	}

	public LevelElements level9(Game game){
		LevelElements levelElements = new LevelElements();
		LinkedList<GameElement> gameElements = new LinkedList<>();

		game.addWalls();
		gameElements.add(game.addShooter(4f, 1.5f, scale));
		gameElements.add(game.addProjectile(0.75f, scale));
		gameElements.add(game.addProjectile(0.75f, scale));
		gameElements.add(game.addProjectile(0.75f, scale));
		gameElements.add(game.addProjectile(0.75f, scale));

		gameElements.add(game.addBox(10, 0.6f, 1, scale));
		gameElements.add(game.addBox(10, 2.6f, 2, scale));
		gameElements.add(game.addBox(11.5f, 4.6f, 2, scale));
		gameElements.add(game.addBox(13.25f, 0.6f, 2.5f, scale));
		gameElements.add(game.addBox(12.75f, 3.1f, 1.5f, scale));

		gameElements.add(game.addEnemy(10, 1.6f, 1, scale));
		gameElements.add(game.addEnemy(11.5f, 6.6f, 1, scale));
		gameElements.add(game.addEnemy(13.875f, 3.1f, 1, scale));
		//todo gdzie jest obracanie ziemniakow lol???

		levelElements.addElements(gameElements);
		return levelElements;
	}

	public LevelElements level10(Game game){ //bonusowy xd
		LevelElements levelElements = new LevelElements();
		LinkedList<GameElement> gameElements = new LinkedList<>();

		game.addWalls();
		gameElements.add(game.addShooter(4f, 1.5f, scale));
		gameElements.add(game.addProjectile(0.75f, scale));

		gameElements.add(game.addBox(12, 1, 1, scale));
		gameElements.add(game.addBox(12, 2, 1, scale));
		gameElements.add(game.addBox(12, 3, 1, scale));
		gameElements.add(game.addBox(12, 5, 3, scale));

		for(int i = 0; i < 3; i ++)
			for(int j = 0; j < 3; j ++) {
				gameElements.add(game.addEnemy(11 + (float)i, 7.6f + (float)j, 1, scale));
			}

		levelElements.addElements(gameElements);
		return levelElements;
	}

	public Levels(float scale) {
		levels = new Array<>();
		levels.add(this::level1);
		levels.add(this::level2);
		levels.add(this::level3);
		levels.add(this::level4);
		levels.add(this::level5);
		levels.add(this::level6);
		levels.add(this::level7);
		levels.add(this::level8);
		levels.add(this::level9);
		levels.add(this::level10);
		this.scale = scale;
	}

	public Function<Game, LevelElements> getLevel(int level) {
		return levels.get(level);
	}
	public int getSize() {
		return levels.size;
	}
}
