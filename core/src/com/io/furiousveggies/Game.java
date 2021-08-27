package com.io.furiousveggies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Game extends Stage {
    private final Array<Projectile> projectiles;
    private int currentProjectile;
    private float shooterX, shooterSize;
    private float scale;

    public Game(Viewport viewport, Batch batch){
        super(viewport, batch);
        projectiles = new Array<Projectile>();
        clear();
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

    public void addShooter(World world, float x, float size) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x, size/2);

        Body body = world.createBody(bodyDef);

        PolygonShape box = new PolygonShape();
        box.setAsBox(0.5f * size, 0.5f * size);

        Fixture fixture = body.createFixture(box, 1.0f);
        fixture.setRestitution(0);
        fixture.setFriction(1);

        box.dispose();

        shooterX = x;
        shooterSize = size;
        Block shooter = new Block(body, size);
        scale = shooter.getX()/x;
        addActor(shooter);
    }

    public void addProjectile(World world, float size){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        if (projectiles.size == 0){
            bodyDef.position.set(shooterX, shooterSize);
        }
        else {
            bodyDef.position.set(shooterX - 2 * size, 0);
        }

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
    public void clear(){
        super.clear();
        projectiles.clear();
        currentProjectile = 0;
        shooterX = shooterSize = 0;
        scale = 1;
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
            Projectile projectile = projectiles.get(currentProjectile);
            projectile.shoot();
            currentProjectile++;
            if (currentProjectile < projectiles.size) {
                projectile = projectiles.get(currentProjectile);
                projectile.addAction(Actions.sequence(Actions.moveBy(0, shooterSize * 1.3f * scale - projectile.getY(), 0.5f),
                        Actions.moveBy(shooterX * scale - projectile.getX(), 0, 0.5f)));
            }
        }
        return super.touchDown(screenX, screenY, pointer, button);
    }
}
