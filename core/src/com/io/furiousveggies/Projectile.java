package com.io.furiousveggies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ObjectMap;

public class Projectile extends Actor {
    Body body;
    TransformDrawable texture;
    boolean shot;
    int cursorX, cursorY;

    static final float scr_width = 20.0f, scr_height = 10.0f;
    final float scale, size;

    @Override
    public void draw (Batch batch, float parentAlpha) {
        if (!shot && Gdx.input.isTouched()) {
            texture.draw(batch, Gdx.input.getX() - (getWidth() / 2f), Gdx.graphics.getHeight() - Gdx.input.getY() - (getHeight() / 2f), getWidth() / 2f, getHeight() / 2f, getWidth(), getHeight(), 1f, 1f, getRotation());
        }
        else {
            texture.draw(batch, getX(), getY(), getWidth() / 2f, getHeight() / 2f, getWidth(), getHeight(), 1f, 1f, getRotation());
        }
    }

    @Override
    public void act (float delta) {
        Vector2 pos = body.getWorldCenter();
        setRotation((float)Math.toDegrees(body.getAngle()));
        setPosition(pos.x * scale, pos.y * scale, Align.center);

        if (!shot) {
            if (Gdx.input.isTouched()) {
                cursorX = Gdx.input.getX();
                // Pozycja kursora jest podawana z punktem (0, 0) w lewym górnym rogu, zaś pozycja obiektu z punktem (0, 0) w lewym dolnym rogu
                cursorY = Gdx.graphics.getHeight() - Gdx.input.getY();
            }
            else if (cursorX != -1 || cursorY != -1){
                shot = true;
                body.applyLinearImpulse(((pos.x * scale) - cursorX)/10, ((pos.y * scale) - cursorY)/10, pos.x, pos.y, true);
            }
        }
    }

    Projectile(Body body, float size){
        this.body = body;
        this.size = size;
        scale = Gdx.graphics.getWidth()/scr_width;
        shot = false;
        cursorX = cursorY = -1;

        Vector2 pos = body.getPosition();
        setPosition(pos.x * scale, pos.y * scale, Align.center);
        setSize(size * scale, size * scale);

        texture = (TransformDrawable)View.skin.getDrawable("logo");
    }
}
