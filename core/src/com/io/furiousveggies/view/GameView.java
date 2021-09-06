package com.io.furiousveggies.view;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.io.furiousveggies.model.Block;
import com.io.furiousveggies.model.Enemy;
import com.io.furiousveggies.model.Projectile;
import com.io.furiousveggies.model.Shooter;

public class GameView extends Stage {
    private View view;
    private ObjectMap<Body, EnemyView> enemies = new ObjectMap<>();

    public GameView(Viewport viewport, Batch batch, View view) {
        super(viewport, batch);
        this.view = view;
    }

    public void addShooterView(Shooter shooter) {

    }

    public void addProjectileView(Projectile projectile) {
    }

    public void addBlockView(Block block) {
        BlockView blockView = new BlockView(block, view.getSkinWrapper().boxDrawable());
        addActor(blockView);
    }

    public void addEnemyView(Enemy enemy) {
        EnemyView enemyView = new EnemyView(enemy, view.getSkinWrapper().enemyDrawable());
        addActor(enemyView);
        enemies.put(enemyView.getEnemy().getBody(), enemyView);
    }

    public void destroyEnemies(Array<Body> defeatedEnemies) {
        for(Body body : defeatedEnemies) {
            enemies.get(body).defeated();
        }
    }
}
