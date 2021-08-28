package com.io.furiousveggies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable;
import com.badlogic.gdx.utils.Align;

public class Shooter extends Actor {
	Body body;
	TransformDrawable texture;

	static final float scr_width = 20.0f, scr_height = 10.0f;
	static final float widthToHeight = 0.5f;
	final float scale, size;

	@Override
	public void draw (Batch batch, float parentAlpha) {
		texture.draw(batch, getX(), getY(), getWidth()/2f, getHeight()/2f, getWidth(), getHeight(), 1f, 1f, getRotation());
	}

	@Override
	public void act (float delta) {
		toFront();
		Vector2 pos = body.getWorldCenter();
		setRotation((float)Math.toDegrees(body.getAngle()));
		setPosition(pos.x * scale, pos.y * scale * 2, Align.center);
	}

	Shooter(Body body, float size){
		this.body = body;
		this.size = size;
		scale = Gdx.graphics.getWidth()/scr_width;

		Vector2 pos = body.getPosition();
		setPosition(pos.x * scale, pos.y * scale, Align.center);
		setSize(size * widthToHeight * scale * 0.9f, size * scale * 2);

		texture = (TransformDrawable)View.skin.getDrawable("split-pane-horizontal");
	}
}
