package com.io.furiousveggies.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;

public class Rope extends Actor {
    private final Projectile projectile;
    private final float startX, startY, endX, endY;

    Rope(Projectile projectile, float x, float y){
        this.projectile = projectile;
        startX = projectile.getX();
        startY = projectile.getY();
        endX = x;
        endY = y;
    }

    @Override
    public void act(float delta){
        //todo FIX THIS
        projectile.setPosition(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY(), Align.center);
        //todo FIX THIS
    }

    @Override
    public boolean remove(){
        projectile.shoot((startX - projectile.getX())/5, (startY - projectile.getY())/5);
        return super.remove();
    }

    public Projectile getProjectile() {
        return projectile;
    }

    public float getEndX() {
        return endX;
    }
    public float getEndY() {
        return endY;
    }
}
