package com.io.stonks.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.io.stonks.model.*;

public class GameView extends Stage {
    private View view;
    private ObjectMap<Body, EnemyView> enemies = new ObjectMap<>();
    private RopeView ropeView;

    public GameView(Viewport viewport, Batch batch, View view) {
        super(viewport, batch);
        this.view = view;
    }

    public void addShooterView(Shooter shooter) {
        ShooterView shooterView = new ShooterView(shooter, view.getSkinWrapper().shooterDrawable());
        addActor(shooterView);
    }


    public void addProjectileView(Projectile projectile) {
        ProjectileView projectileView = new ProjectileView(projectile, view.getSkinWrapper().projectileDrawable());
        addActor(projectileView);
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

    public void addRopeView(PossibleRope rope) {
        if(rope == null || ropeView != null) return;
        ropeView = new RopeView(rope, view.getSkinWrapper().shooterRopeColor());
        addActor(ropeView);
    }

    public void destroyEnemies(Array<Body> defeatedEnemies) {
        for(Body body : defeatedEnemies) {
            enemies.get(body).defeated();
        }
    }

    public void deleteRope() {
        if(ropeView == null) return;
        ropeView.remove();
        ropeView = null;
    }
}
