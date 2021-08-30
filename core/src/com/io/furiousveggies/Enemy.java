package com.io.furiousveggies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

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
		super.act(delta);
		Vector2 pos = body.getWorldCenter();
		setRotation((float)Math.toDegrees(body.getAngle()));
		setPosition(pos.x * scale, pos.y * scale, Align.center);
	}

	public void defeated(){
		addAction(Actions.sequence(
				Actions.fadeOut(0.1f),
				Actions.hide(),
				Actions.fadeIn(0.1f),
				Actions.show(),
				Actions.fadeOut(0.1f),
				Actions.hide()
		));
	}

	Enemy(Body body, float size, TransformDrawable texture){
		this.body = body;
		this.size = size;
		this.texture = texture;
		scale = Gdx.graphics.getWidth()/Game.width;

		Vector2 pos = body.getPosition();
		setPosition(pos.x * scale, pos.y * scale, Align.center);
		setSize(size * scale, size * scale);
	}
}
