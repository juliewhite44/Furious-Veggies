package com.io.project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Align;
import com.io.stonks.model.Block;
import com.io.stonks.model.Enemy;
import com.io.stonks.model.GameElementsFactory;
import com.io.stonks.model.GameElementsFactoryImpl;
import com.io.stonks.model.Projectile;

@TestInstance(Lifecycle.PER_CLASS)
class GameElementTest {
	static final float BLOCK_START_X = 5;
	static final float ENEMY_START_X = 10;
	static final float BLOCK_ENEMY_START_Y = 5;
	static final float PROJECTILE_START_X = 20;
	static final float PROJECTILE_START_Y = 5;
	static final float SIZE_BLOCK_ENEMY = 5;
	static final float SIZE_PROJECTILE = 5;
	
	World world;
	GameElementsFactory factory;
	Block block;
	Enemy enemy;
	Projectile projectile;
	Body ground;
	
	@BeforeAll
	void prepare() {
		world = new World(new Vector2(0, -10), false);
		factory = new GameElementsFactoryImpl();
		ground = factory.createGround(world, 1000);
		block = factory.createBlock(world, BLOCK_START_X, BLOCK_ENEMY_START_Y, SIZE_BLOCK_ENEMY, 1);
		enemy = factory.createEnemy(world, ENEMY_START_X, BLOCK_ENEMY_START_Y, SIZE_BLOCK_ENEMY, 1);
		projectile = factory.createProjectile(world, PROJECTILE_START_X, PROJECTILE_START_Y, SIZE_PROJECTILE, 1);
		projectile.shoot(-1000, 0);
	}
	
	@Test
	void testShoot() {
		assertFalse(block.getX(Align.center) < BLOCK_START_X - 0.1);
		assertFalse(enemy.getX(Align.center) < ENEMY_START_X - 0.1);
		assertFalse(projectile.getY(Align.center) == PROJECTILE_START_Y);

		for(int i = 0; i < 60 * 100; i++) {
			world.step(1.0f/60.0f, 8, 6);
			block.act(1.0f/60.0f);
			enemy.act(1.0f/60.0f);
			projectile.act(1.0f/60.0f);
		}
		
		assertTrue(block.getX(Align.center) < BLOCK_START_X - 1);
		assertTrue(enemy.getX(Align.center) < ENEMY_START_X - 1);
		assertTrue(projectile.getY(Align.center) < SIZE_PROJECTILE/2 + 0.1f);
	}
}
