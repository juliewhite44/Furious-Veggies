package com.io.furiousveggies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Disposable;

public class Model implements Disposable {
	Controller controller;
	private Game game;

	//makes adequate stage process any actions its actors should do
	public void act() {
		if(Gdx.input.getInputProcessor() == game) {
			game.act();
		}
	}
	
	public void startGame(Game g) {
		game = g;
		game.addGround();
		game.addBox(14f, 5f, 3f);
		game.addBox(14f, 2f, 1.5f);
		game.addShooter(5f, 2f);
		game.addProjectile(1f);
		game.addProjectile(1f);
		game.addProjectile(1f);
		game.addProjectile(1f);
		//todo
	}

	public Model(){
	}

	@Override
	public void dispose() {
	}
}
