package com.io.furiousveggies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable;
import com.badlogic.gdx.utils.Align;

public class Shooter extends Actor {
	private Rope rope;

	Body body;
	TransformDrawable texture;

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

	public void aim(Projectile projectile){
		rope = new Rope(projectile, getX() + getWidth() / 2, getY() + getHeight() - projectile.getHeight() / 2);
		getStage().addActor(rope);
	}

	public void shoot(){
		rope.remove();
		//todo zamienić null jakąś pustą implementacją
		rope = null;
	}

	Shooter(Body body, float size){
		this.body = body;
		this.size = size;
		scale = Gdx.graphics.getWidth()/Game.width;
		rope = null;

		Vector2 pos = body.getPosition();
		setPosition(pos.x * scale, pos.y * scale, Align.center);
		setSize(size * widthToHeight * scale * 0.9f, size * scale * 2);

		texture = (TransformDrawable)View.skin.getDrawable("split-pane-horizontal");
	}

	private class Rope extends Actor {
		private final Projectile projectile;
		private final float startX, startY, endX, endY;
		private final ShapeRenderer shape;

		Rope(Projectile projectile, float x, float y){
			this.projectile = projectile;
			startX = projectile.getX();
			startY = projectile.getY();
			endX = x;
			endY = y;
			shape = new ShapeRenderer();
		}

		@Override
		public void draw(Batch batch, float parentAlpha){
			batch.end();
			Gdx.gl.glLineWidth(3);
			shape.begin(ShapeRenderer.ShapeType.Line);
			shape.setColor(0.3f, 0.5f, 0.5f, 1);
			shape.line(projectile.getX() + projectile.getWidth() / 2, projectile.getY() + projectile.getHeight() / 2, endX, endY);
			shape.end();
			batch.begin();
		}

		@Override
		public void act(float delta){
			projectile.setPosition(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY(), Align.center);
		}

		@Override
		public boolean remove(){
			projectile.shoot((startX - projectile.getX())/10, (startY - projectile.getY())/10);
			return super.remove();
		}
	}
}
