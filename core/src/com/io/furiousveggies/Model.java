package com.io.furiousveggies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Disposable;

public class Model implements Disposable {
	Controller controller;
	private Game game;
	private World world;
	private final float width = 20.0f, height = 10.0f;

	//makes adequate stage process any actions its actors should do
	public void act() {
		if(Gdx.input.getInputProcessor() == game) {
			game.act();
			world.step(1.0f/60.0f, 8, 6);
		}
	}
	
	private void addGround() {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(0, -0.5f);
		
		Body body = world.createBody(bodyDef);
		
		PolygonShape box = new PolygonShape();
		box.setAsBox(width, 0.6f);
		
		Fixture fixture = body.createFixture(box, 0.0f);
		fixture.setRestitution(0);
		fixture.setFriction(1);
		
		box.dispose();
	}
	
	public void startGame(Game g) {
		game = g;
		world.dispose();
		world = new World(new Vector2(0,-10f), true);
		addGround();
		game.addBox(world, 14f, 5f, 3f);
		game.addBox(world, 14f, 2f, 1.5f);
		game.addShooter(world, 5f, 2f);
		game.addProjectile(world, 1f);
		game.addProjectile(world, 1f);
		game.addProjectile(world, 1f);
		game.addProjectile(world, 1f);
		//todo
	}

	public Model(){
		world = new World(new Vector2(0,-10f), true);
	}

	@Override
	public void dispose() {
		world.dispose();
	}
}
