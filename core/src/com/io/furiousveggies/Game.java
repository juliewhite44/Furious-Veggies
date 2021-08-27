package com.io.furiousveggies;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Game extends Stage {
    private final Array<Projectile> projectiles;
    private int currentProjectile;
    private float shooterX;

    public Game(Viewport viewport, Batch batch){
        super(viewport, batch);
        projectiles = new Array<Projectile>();
        currentProjectile = 0;
        shooterX = 0;
    }

    public void addBox(World world, float x, float y, float size) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);

        Body body = world.createBody(bodyDef);

        PolygonShape box = new PolygonShape();
        box.setAsBox(0.5f * size, 0.5f * size);

        Fixture fixture = body.createFixture(box, 1.0f);
        fixture.setRestitution(0);
        fixture.setFriction(1);

        box.dispose();

        addActor(new Block(body, size));
    }

    public void addShooter(World world, float x, float y, float size) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);

        Body body = world.createBody(bodyDef);

        PolygonShape box = new PolygonShape();
        box.setAsBox(0.5f * size, 0.5f * size);

        Fixture fixture = body.createFixture(box, 1.0f);
        fixture.setRestitution(0);
        fixture.setFriction(1);

        box.dispose();

        shooterX = x;
        addActor(new Block(body, size));
    }

    public void addProjectile(World world, float size){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(shooterX - 2*size, 0);

        Body body = world.createBody(bodyDef);

        CircleShape box = new CircleShape();
        box.setRadius(0.5f * size);

        Fixture fixture = body.createFixture(box, 1.0f);
        fixture.setRestitution(0);
        fixture.setFriction(1);

        box.dispose();

        Projectile projectile = new Projectile(body, size);
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
