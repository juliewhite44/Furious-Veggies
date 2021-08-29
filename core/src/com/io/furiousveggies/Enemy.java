package com.io.furiousveggies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable;
import com.badlogic.gdx.utils.Align;

public class Enemy extends Actor {
	Body body;
	TransformDrawable texture;

	final float scale, size;

	@Override
	public void draw (Batch batch, float parentAlpha) {
		texture.draw(batch, getX(), getY(), getWidth()/2f, getHeight()/2f, getWidth(), getHeight(), 1f, 1f, getRotation());
	}

	@Override
	public void act (float delta) {
		Vector2 pos = body.getWorldCenter();
		setRotation((float)Math.toDegrees(body.getAngle()));
		setPosition(pos.x * scale, pos.y * scale, Align.center);
	}

	Enemy(Body body, float size){
		this.body = body;
		this.size = size;
		scale = Gdx.graphics.getWidth()/Game.width;

		Vector2 pos = body.getPosition();
		setPosition(pos.x * scale, pos.y * scale, Align.center);
		setSize(size * scale, size * scale);

		texture = (TransformDrawable)View.skin.getDrawable("pixthulhu");
	}
}
