package com.io.furiousveggies.model;

import com.badlogic.gdx.utils.Array;
import com.io.furiousveggies.game.Game;

import java.util.function.Consumer;

public class Levels {
	private Array<Consumer<Game>> levels;
	private float scale;

	public void level1(Game game) {
		game.addGround();
		game.addBox(14f, 5f, 3f, scale);
		game.addBox(14f, 2f, 1.5f, scale);
		game.addShooter(5f, 2f, scale);
		game.addProjectile(1f, scale);
		game.addProjectile(1f, scale);
		game.addProjectile(1f, scale);
		game.addProjectile(1f, scale);
		game.addBox(10f, 2f, 1.5f, scale);
		game.addEnemy(10f, 5f, 2f, scale);
		game.addEnemy(13f, 7f, 1f, scale);
		game.addEnemy(15f, 7f, 1f, scale);
	}

	public void level2(Game game){
		game.addGround();
		game.addShooter(3f, 1.5f, scale);
		game.addProjectile(0.75f, scale);
		game.addProjectile(0.75f, scale);
		game.addProjectile(0.75f, scale);
		game.addProjectile(0.75f, scale);
		game.addBox(7f, 1f, 1f, scale);
		game.addBox(7f, 2f, 1f, scale);
		game.addBox(7f, 3f, 1f, scale);
		game.addEnemy(7f, 4f, 1f, scale);
		game.addBox(10f, 1f, 1f, scale);
		game.addBox(10f, 2f, 1f, scale);
		game.addBox(10f, 3f, 1f, scale);
		game.addBox(10f, 4f, 1f, scale);
		game.addEnemy(10f, 5f, 1f, scale);
		game.addBox(13f, 1f, 1f, scale);
		game.addBox(13f, 2f, 1f, scale);
		game.addEnemy(13f, 3f, 1f, scale);
	}

	public Levels(float scale) {
		levels = new Array<>();
		levels.add(this::level1);
		levels.add(this::level2);
		this.scale = scale;
	}

	public Consumer<Game> getLevel(int level) {
		return levels.get(level);
	}
	public int getSize() {
		return levels.size;
	}
}
