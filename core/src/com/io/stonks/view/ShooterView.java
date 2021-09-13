package com.io.stonks.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable;
import com.io.stonks.model.Shooter;

public class ShooterView extends Actor {
    private Shooter shooter;
    private TransformDrawable transformDrawable;

    public ShooterView(Shooter shooter, TransformDrawable transformDrawable) {
        this.shooter = shooter;
        this.transformDrawable = transformDrawable;
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        transformDrawable.draw(batch, shooter.getX(), shooter.getY(), shooter.getWidth()/2f, shooter.getHeight()/2f, shooter.getWidth(), shooter.getHeight(), 1f, 1f, shooter.getRotation());
    }
}
