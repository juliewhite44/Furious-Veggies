package com.io.furiousveggies.model;

import com.badlogic.gdx.physics.box2d.*;

public class GameElementsFactoryImpl implements GameElementsFactory {

    @Override
    public Body createGround(World world, float width){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, -0.5f);

        Body ground = world.createBody(bodyDef);

        PolygonShape box = new PolygonShape();
        box.setAsBox(width, 0.6f);

        Fixture fixture = ground.createFixture(box, 0.0f);
        fixture.setRestitution(0);
        fixture.setFriction(1);

        box.dispose();

        return ground;
    }

    @Override
    public Block createBlock(World world, float x, float y, float size, float scale){
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
        return new Block(body, size, scale);
    }

    @Override
    public Enemy createEnemy(World world, float x, float y, float size, float scale){
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
        return new Enemy(body, size, scale);
    }

    @Override
    public Projectile createProjectile(World world, float x, float y, float size, float scale){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);

        Body body = world.createBody(bodyDef);

        CircleShape box = new CircleShape();
        box.setRadius(0.5f * size);

        Fixture fixture = body.createFixture(box, 1.0f);
        fixture.setRestitution(0);
        fixture.setFriction(1);

        box.dispose();

        return new Projectile(body, size, scale);
    }

    @Override
    public Shooter createShooter(World world, float x, float size, float scale){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x, size/2);

        Body body = world.createBody(bodyDef);

        PolygonShape box = new PolygonShape();
        box.setAsBox(0.5f * size * Shooter.WIDTH_TO_HEIGHT, 0.5f * size);

        Fixture fixture = body.createFixture(box, 1.0f);
        fixture.setRestitution(0);
        fixture.setFriction(1);

        box.dispose();
        return new Shooter(body, size, scale);
    }
}
