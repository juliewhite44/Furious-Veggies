package com.io.furiousveggies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable;
import com.badlogic.gdx.utils.Align;

public class Projectile extends Actor {
    Body body;
    TransformDrawable texture;
    private boolean aiming;
    private float oldX, oldY;

    static final float scr_width = 20.0f, scr_height = 10.0f;
    final float scale, size;

    @Override
    public void draw (Batch batch, float parentAlpha) {
        texture.draw(batch, getX(), getY(), getWidth() / 2f, getHeight() / 2f, getWidth(), getHeight(), 1f, 1f, getRotation());
    }

    @Override
    public void act (float delta) {
        if (body.isActive()){
            Vector2 pos = body.getWorldCenter();
            setRotation((float) Math.toDegrees(body.getAngle()));
            setPosition(pos.x * scale, pos.y * scale, Align.center);
        }
        else {
            if (aiming) {
                // Pozycja kursora jest podawana z punktem (0, 0) w lewym górnym rogu, zaś pozycja obiektu z punktem (0, 0) w lewym dolnym rogu
                setPosition(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY(), Align.center);
            } else {
                super.act(delta);
            }
            body.setTransform((getX() + getWidth() / 2) / scale, (getY() + getHeight() / 2) / scale, 0);
        }
    }

    public void aim(){
        Vector2 pos = body.getWorldCenter();
        oldX = pos.x * scale;
        oldY = pos.y * scale;
        aiming = true;
    }

    public void shoot(){
        aiming = false;
        Vector2 pos = body.getWorldCenter();
        body.setActive(true);
        body.applyLinearImpulse((oldX - Gdx.input.getX())/10, (oldY - (Gdx.graphics.getHeight() - Gdx.input.getY()))/10, pos.x, pos.y, true);
    }

    Projectile(Body body, float size){
        this.body = body;
        this.size = size;
        body.setBullet(true);
        body.setActive(false);
        scale = Gdx.graphics.getWidth()/scr_width;
        aiming = false;
        oldX = oldY = -1;

        Vector2 pos = body.getPosition();
        setPosition(pos.x * scale, pos.y * scale, Align.center);
        setSize(size * scale, size * scale);

        texture = (TransformDrawable)View.skin.getDrawable("logo");
    }
}
