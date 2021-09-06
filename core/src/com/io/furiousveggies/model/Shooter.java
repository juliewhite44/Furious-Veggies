package com.io.furiousveggies.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Align;
import com.io.furiousveggies.view.RopeView;

public class Shooter extends GameElement {
	public static final float WIDTH_TO_HEIGHT = 0.25f;

	private Rope rope;

	//todo FIX THIS START
	private RopeView ropeView;
	//todo FIX THIS END

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

	public void aim(Projectile projectile){
		rope = new Rope(projectile, getX() + getWidth() / 2, getY() + getHeight() - projectile.getHeight() / 2);

		//todo FIX THIS START
		Game game = (Game) getStage();
		ropeView = new RopeView(rope, game.getView().getSkinWrapper().shooterRopeColor());
		game.addActor(rope);
		game.addActor(ropeView);
		//todo FIX THIS END
	}

	public void shoot(){
		rope.remove();
		//todo zamienić null jakąś pustą implementacją
		rope = null;

		//todo FIX THIS START
		ropeView.remove();
		ropeView = null;
		//todo FIX THIS END
	}

	public boolean loaded(){
		return rope != null;
	}

}
