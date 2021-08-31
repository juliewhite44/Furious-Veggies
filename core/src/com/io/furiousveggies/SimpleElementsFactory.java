package com.io.furiousveggies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.io.furiousveggies.skins.SkinWrapper;

public class SimpleElementsFactory implements GameElementsFactory {
    SkinWrapper skin;

    public SimpleElementsFactory(SkinWrapper skin){
        this.skin = skin;
    }

    @Override
    public void prepareGame(Game game){
        Table root = new Table();
        root.setFillParent(true);
        game.addActor(root);

        Table mainTable = new Table(skin.getSkin());
        mainTable.setBackground(skin.gameBackgroundName());

        root.add(mainTable).grow().pad(0);
    }

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
    public Block createBox(World world, float x, float y, float size){
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

        return new Block(body, size, Gdx.graphics.getWidth(), skin.boxDrawable());
    }

    @Override
    public Enemy createEnemy(World world, float x, float y, float size){
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

        return new Enemy(body, size, skin.enemyDrawable());
    }

    @Override
    public Projectile createProjectile(World world, float x, float y, float size){
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

        return new Projectile(body, size, skin.projectileDrawable());
    }

    @Override
    public Shooter createShooter(World world, float x, float size){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x, size/2);

        Body body = world.createBody(bodyDef);

        PolygonShape box = new PolygonShape();
        box.setAsBox(0.5f * size * Shooter.widthToHeight, 0.5f * size);

        Fixture fixture = body.createFixture(box, 1.0f);
        fixture.setRestitution(0);
        fixture.setFriction(1);

        box.dispose();

        return new Shooter(body, size, skin.shooterDrawable(), skin.shooterRopeColor());
    }
}
