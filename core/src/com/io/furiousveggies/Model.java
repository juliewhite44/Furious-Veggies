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
			world.step(1.0f/60.0f, 8, 3);
		}
	}
	
	private void addBox(float x, float y, float size) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(x, y);
		
		Body body = world.createBody(bodyDef);
		
		PolygonShape box = new PolygonShape();
		box.setAsBox(0.5f * size, 0.5f * size);
		
		body.createFixture(box, 1.0f);
		
		box.dispose();
		
		game.addActor(new Block(body, size));
	}
	
	private void addGround() {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(0, -0.5f);
		
		Body body = world.createBody(bodyDef);
		
		PolygonShape box = new PolygonShape();
		box.setAsBox(width, 0.6f);
		
		body.createFixture(box, 1.0f);
		
		box.dispose();
	}
	
	public void startGame(Stage g) {
		game = g;
		addGround();
		addBox(15, 5, 1);
		addBox(16, 2, 2);
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
