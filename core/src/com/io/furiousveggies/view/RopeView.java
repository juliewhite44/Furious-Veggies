package com.io.furiousveggies.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.io.furiousveggies.model.Projectile;
import com.io.furiousveggies.model.Rope;

public class RopeView extends Actor {
    private Rope rope;
    private ShapeRenderer shapeRenderer;
    private Color color;

    public RopeView(Rope rope, Color color) {
        this.rope = rope;
        this.shapeRenderer = new ShapeRenderer();
        this.color = color;
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        batch.end();
        Gdx.gl.glLineWidth(3);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(color.r, color.g, color.b, color.a);
        Projectile projectile = rope.getProjectile();
        shapeRenderer.line(projectile.getX() + projectile.getWidth() / 2, projectile.getY() + projectile.getHeight() / 2, rope.getEndX(), rope.getEndY());
        shapeRenderer.end();
        batch.begin();
    }
}
