package com.io.furiousveggies.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;

public class Rope extends Actor {
    private final Projectile projectile;
    private final float startX, startY, endX, endY;
    private int mousePositionX;
    private int mousePositionY;
    private static final int height = 512;

    Rope(Projectile projectile, float x, float y){
        this.projectile = projectile;
        startX = projectile.getX();
        startY = projectile.getY();
        endX = x;
        endY = y;
    }

    public void setMousePositionX(int mousePositionX) {
        this.mousePositionX = mousePositionX;
    }

    public void setMousePositionY(int mousePositionY) {
        this.mousePositionY = mousePositionY;
    }

    @Override
    public void act(float delta){
        projectile.setPosition(mousePositionX, height - mousePositionY, Align.center);
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
