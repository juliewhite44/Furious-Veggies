package com.io.stonks.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Align;

public class Enemy extends GameElement {
	public Enemy(Body body, float size, float scale) {
		super(body, size, scale);
	}

	@Override
	public void act (float delta) {
		super.act(delta);
		Vector2 pos = body.getWorldCenter();
		setRotation((float)Math.toDegrees(body.getAngle()));
		setPosition(pos.x * scale, pos.y * scale, Align.center);
	}

	public Body getBody() {
		return body;
	}
}
