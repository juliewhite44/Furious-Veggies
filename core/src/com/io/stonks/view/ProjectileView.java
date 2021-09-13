package com.io.stonks.view;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable;
import com.io.stonks.model.Projectile;

public class ProjectileView extends Actor {
    private Projectile projectile;
    private TransformDrawable transformDrawable;

    @Override
    public void draw (Batch batch, float parentAlpha) {
        transformDrawable.draw(batch, projectile.getX(), projectile.getY(), projectile.getWidth()/2f, projectile.getHeight()/2f, projectile.getWidth(), projectile.getHeight(), 1f, 1f, projectile.getRotation());
    }

    public ProjectileView(Projectile projectile, TransformDrawable transformDrawable) {
        this.projectile = projectile;
        this.transformDrawable = transformDrawable;
    }
}
