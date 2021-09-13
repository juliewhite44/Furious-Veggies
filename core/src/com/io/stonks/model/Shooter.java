package com.io.stonks.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Align;

public class Shooter extends GameElement {
	public static final float WIDTH_TO_HEIGHT = 0.25f;

	private PossibleRope rope;

	public Shooter(Body body, float size, float scale){
		super(body, size, scale);
		rope = null;
		setSize(size * WIDTH_TO_HEIGHT * scale, size * scale * 2);
	}

	@Override
	public void act (float delta) {
		toFront();
		Vector2 pos = body.getWorldCenter();
		setRotation((float)Math.toDegrees(body.getAngle()));
		setPosition(pos.x * scale, pos.y * scale * 2, Align.center);
	}

	public PossibleRope aim(Projectile projectile){
		rope = new Rope(projectile, getX() + getWidth() / 2, getY() + getHeight() - projectile.getHeight() / 2);
		return rope;
	}

	public void shoot(){
		rope.shoot();
		//todo zamienić null jakąś pustą implementacją
		rope = new PossibleRope();
	}

	public boolean loaded(){
		return rope instanceof Rope;
	}

}
