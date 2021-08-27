package com.io.furiousveggies;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Game extends Stage {
    private final Array<Projectile> projectiles;
    private int currentProjectile;

    public Game(Viewport viewport, Batch batch){
        super(viewport, batch);
        projectiles = new Array<Projectile>();
        currentProjectile = 0;
    }

    public void addProjectile(Projectile projectile){
        addActor(projectile);
        projectiles.add(projectile);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button){
        if (currentProjectile < projectiles.size){
            projectiles.get(currentProjectile).aim();
        }
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button){
        if (currentProjectile < projectiles.size){
            projectiles.get(currentProjectile).shoot();
            currentProjectile++;
        }
        return super.touchDown(screenX, screenY, pointer, button);
    }
}
