package com.io.furiousveggies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Game extends Stage {
    private final Array<Projectile> projectiles;
    private final float scale;
    private World world;
    private int currentProjectile;
    private float shooterX, shooterSize;
    private Shooter shooter;

    static final float width = 20.0f, height = 10.0f;

    public Game(Viewport viewport, Batch batch){
        super(viewport, batch);
        world = new World(new Vector2(0,-10f), true);
        projectiles = new Array<Projectile>();
        scale = Gdx.graphics.getWidth()/width;
        clear();
    }

    public void addGround() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, -0.5f);

        Body body = world.createBody(bodyDef);

        PolygonShape box = new PolygonShape();
        box.setAsBox(width, 0.6f);

        Fixture fixture = body.createFixture(box, 0.0f);
        fixture.setRestitution(0);
        fixture.setFriction(1);

        box.dispose();
    }

    public void addBox(float x, float y, float size) {
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

    public void addEnemy(float x, float y, float size) {
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

        addActor(new Enemy(body, size));
    }

    public void addShooter(float x, float size) {
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

        shooterX = x;
        shooterSize = size;
        shooter = new Shooter(body, size);
        addActor(shooter);
    }

    public void addProjectile(float size){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        if (projectiles.size == 0){
            bodyDef.position.set(shooterX - size / 2, shooterSize * 1.5f);
        }
        else {
            bodyDef.position.set(shooterX - 1.5f * size * projectiles.size - size / 2, 0);
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
    public void act(float delta){
        super.act(delta);
        world.step(1.0f/60.0f, 8, 6);
    }

    @Override
    public void clear(){
        super.clear();
        projectiles.clear();
        world.dispose();
        world = new World(new Vector2(0,-10f), true);
        currentProjectile = 0;
        shooterX = shooterSize = 0;
    }

    @Override
    public void dispose(){
        super.dispose();
        world.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button){
        if (currentProjectile < projectiles.size){
            shooter.aim(projectiles.get(currentProjectile));
        }
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button){
        if (currentProjectile < projectiles.size){
            Projectile projectile;
            shooter.shoot();
            currentProjectile++;
            if (currentProjectile < projectiles.size) {
                projectile = projectiles.get(currentProjectile);
                projectile.addAction(Actions.sequence(
                        Actions.moveBy(0, shooterSize * scale * 1.5f - projectile.getY(), 0.5f),
                        Actions.moveBy(shooterX * scale - projectile.getX() - projectile.getWidth() / 2, 0, 0.5f)));
            }
            for (int i = currentProjectile + 1; i < projectiles.size; i++){
                projectile = projectiles.get(i);
                projectile.addAction(Actions.parallel(
                        Actions.moveBy(1.5f * projectile.getWidth(), 0, 1),
                        Actions.rotateBy(-360, 1)
                ));
            }
        }
        return super.touchDown(screenX, screenY, pointer, button);
    }
}
