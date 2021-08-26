package com.io.furiousveggies;

import java.io.Console;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Model implements Disposable {
	Controller controller;
	private Stage game;
	private World world;
	private final float width = 20.0f, height = 10.0f;

	//makes adequate stage process any actions its actors should do
	public void act() {
		if(Gdx.input.getInputProcessor() == game) {
			game.act();
			world.step(1.0f/60.0f, 8, 6);
		}
	}
	
	private void addBox(float x, float y, float size) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(x, y);
		
		Body body = world.createBody(bodyDef);
		
		PolygonShape box = new PolygonShape();
		box.setAsBox(0.5f * size, 0.5f * size);
		
		Fixture fixture = body.createFixture(box, 1.0f);
		fixture.setRestitution(0);
		fixture.setFriction(1);
		
		box.dispose();
		
		game.addActor(new Block(body, size));
	}

	private void addProjectile(float x, float y, float size) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(x, y);

		Body body = world.createBody(bodyDef);

		PolygonShape box = new PolygonShape();
		box.setAsBox(0.5f * size, 0.5f * size);

		Fixture fixture = body.createFixture(box, 1.0f);
		fixture.setRestitution(0);
		fixture.setFriction(1);

		box.dispose();

		game.addActor(new Projectile(body, size));
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
	
	public void startGame(Stage g) {
		game = g;
		world.dispose();
		world = new World(new Vector2(0,-10f), true);
		addGround();
		addBox(14f, 5f, 3f);
		addBox(14f, 2f, 1.5f);
		addBox(5f, 2f, 2f);
		addProjectile(5f, 3f, 1f);
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
