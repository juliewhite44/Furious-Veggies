package com.io.stonks.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Align;

public class Projectile extends GameElement {
    public Projectile(Body body, float size, float scale){
        super(body, size, scale);
        body.setBullet(true);
        body.setActive(false);
    }

    @Override
    public void act (float delta) {
        if (body.isActive()){
            Vector2 pos = body.getWorldCenter();
            setRotation((float) Math.toDegrees(body.getAngle()));
            setPosition(pos.x * scale, pos.y * scale, Align.center);
        }
        else {
            super.act(delta);
            body.setTransform((getX() + getWidth() / 2) / scale, (getY() + getHeight() / 2) / scale, 0);
        }
    }

    public void shoot(float impulseX, float impulseY){
        Vector2 pos = body.getWorldCenter();
        body.setActive(true);
        body.applyLinearImpulse(impulseX, impulseY, pos.x, pos.y, true);
    }
}
