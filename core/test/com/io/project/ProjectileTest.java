package com.io.project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Align;
import com.io.stonks.model.GameElementsFactory;
import com.io.stonks.model.GameElementsFactoryImpl;
import com.io.stonks.model.Projectile;

@TestInstance(Lifecycle.PER_CLASS)
class ProjectileTest {
	private final float START_X = 10, START_Y = 10, SIZE = 3;
	GameElementsFactory factory = null;
	World world;
	Projectile projectile;
	
	@BeforeAll
	void prepare() {
		factory = new GameElementsFactoryImpl();
	}

	@BeforeEach
	void prepareWorld() {
		world = new World(new Vector2(0, -10), false);
		projectile = factory.createProjectile(world, START_X, START_Y, SIZE, 1);
	}
	
	void runTime(int seconds) {
		for(int i = 0; i < 60 * seconds; i++) {
			world.step(1.0f/60.0f, 8, 6);
			projectile.act(1.0f/60.0f);
		}		
	}
	
	@Test
	void testNoShoot() {
		projectile.shoot(0, 0);
		runTime(100);
		assertEquals(projectile.getX(Align.center), START_X);
	}
	
	@Test
	void testShootHorizontal() {
		projectile.shoot(100, 0);
		runTime(2);
		assertTrue(projectile.getX(Align.center) > START_X + 20);
	}

	@Test
	void testShootVertical() {
		projectile.shoot(0, 100);
		runTime(2);
		assertTrue(projectile.getY(Align.center) > START_Y + 5);
	}
}
