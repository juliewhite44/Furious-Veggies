package com.io.stonks.view;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable;
import com.io.stonks.model.Enemy;

public class EnemyView extends Actor {
    private Enemy enemy;
    private TransformDrawable transformDrawable;

    public Enemy getEnemy() {
        return enemy;
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        transformDrawable.draw(batch, enemy.getX(), enemy.getY(), enemy.getWidth()/2f, enemy.getHeight()/2f, enemy.getWidth(), enemy.getHeight(), 1f, 1f, enemy.getRotation());
    }

    public void defeated(){
        addAction(Actions.sequence(
                Actions.fadeOut(0.1f),
                Actions.hide(),
                Actions.fadeIn(0.1f),
                Actions.show(),
                Actions.fadeOut(0.1f),
                Actions.hide()
        ));
    }

    public EnemyView(Enemy enemy, TransformDrawable transformDrawable) {
        this.enemy = enemy;
        this.transformDrawable = transformDrawable;
    }
}
