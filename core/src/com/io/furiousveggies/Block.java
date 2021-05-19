package com.io.furiousveggies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;

public class Block extends Actor {
	Body body;
	Drawable texture;
	
	final float width = 20.0f, height = 10.0f, scale, size;
	
	@Override
	public void draw (Batch batch, float parentAlpha) {
		Vector2 pos = body.getPosition();
		texture.draw(batch, (pos.x - size/2) * scale, (pos.y - size/2) * scale, size * scale, size * scale);
	}
	
	@Override
	public void act (float delta) {
		Vector2 pos = body.getPosition();
		setPosition(pos.x * scale, pos.y * scale, Align.center);
	}
	
	Block(Body body, float size){
		this.body = body;
		this.size = size;
		scale = Gdx.graphics.getWidth()/width;
		
		Vector2 pos = body.getPosition();
		setPosition(pos.x * scale, pos.y * scale, Align.center);
		setSize(size * scale, size * scale);
		
		texture = View.skin.getDrawable("button");
	}
}
