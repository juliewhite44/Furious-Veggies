package com.io.stonks.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;

public class Rope extends PossibleRope {
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

    @Override
    public void setMousePositionX(int mousePositionX) {
        this.mousePositionX = mousePositionX;
    }

    @Override
    public void setMousePositionY(int mousePositionY) {
        this.mousePositionY = mousePositionY;
    }

    @Override
    public void act(float delta){
    	float posX = mousePositionX, posY = height - mousePositionY;
    	if(mousePositionX < 0)
    		posX = 0;
		if(mousePositionX > endX) {
    		posX = endX;
        }
    	if(mousePositionY > height)
    		posY = 0;
    	else if(mousePositionY < 0)
    		posY = height;
    	projectile.setPosition(posX, posY, Align.center);
    	
    }

    @Override
    public boolean remove(){
        return super.remove();
    }

    @Override
    public Projectile getProjectile() {
        return projectile;
    }

    @Override
    public float getEndX() {
        return endX;
    }
    
    @Override
    public float getEndY() {
        return endY;
    }
    
    @Override
    public void shoot() {
    	projectile.shoot((startX - projectile.getX())/5, (startY - projectile.getY())/5);
    	remove();
    }
}
