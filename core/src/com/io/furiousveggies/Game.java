package com.io.furiousveggies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Game extends Stage {
    private final GameElementsFactory elementsFactory;
    private final Array<Projectile> projectiles;
    private final ObjectMap<Body, Enemy> enemies;
    private final Array<Body> defeatedEnemies;
    private final float scale;
    private World world;
    private Body ground;
    private GameResultListener resultListener;
    private int currentProjectile;
    private float shooterX, shooterSize;
    private Shooter shooter;

    static final float width = 20.0f, height = 10.0f;

    public Game(Viewport viewport, Batch batch, GameElementsFactory elementsFactory){
        super(viewport, batch);
        this.elementsFactory = elementsFactory;
        world = new World(new Vector2(0,-10f), true);
        projectiles = new Array<Projectile>();
        enemies = new ObjectMap<Body, Enemy>();
        defeatedEnemies = new Array<Body>();
        scale = Gdx.graphics.getWidth()/width;
        resultListener = new GameResultListener() {
            @Override
            public void onGameWin() { }

            @Override
            public void onGameOver() { }
        };
        clear();
    }

    public void setResultListener(GameResultListener resultListener){
        this.resultListener = resultListener;
    }

    public void addGround() {
        ground = elementsFactory.createGround(world, width * 2);
    }

    public void addBox(float x, float y, float size) {
        addActor(elementsFactory.createBox(world, x, y, size));
    }

    public void addEnemy(float x, float y, float size) {
        Enemy enemy = elementsFactory.createEnemy(world, x, y, size);
        addActor(enemy);
        enemies.put(enemy.body, enemy);
    }

    public void addShooter(float x, float size) {
        shooterX = x;
        shooterSize = size;
        shooter = elementsFactory.createShooter(world, x, size);
        addActor(shooter);
    }

    public void addProjectile(float size){
        Projectile projectile;
        if (projectiles.size == 0){
            projectile = elementsFactory.createProjectile(world, shooterX - size / 2, shooterSize * 1.5f, size);
        }
        else {
            projectile = elementsFactory.createProjectile(world, shooterX - 1.5f * size * projectiles.size - size / 2, 0, size);
        }

        addActor(projectile);
        projectiles.add(projectile);
    }

    @Override
    public void act(float delta){
        super.act(delta);
        while (defeatedEnemies.size > 0){
            world.destroyBody(defeatedEnemies.pop());
        }
        if (enemies.size == 0){
            currentProjectile = projectiles.size;
            if (shooter.loaded()){
                shooter.shoot();
            }
            resultListener.onGameWin();
        }
        else if (currentProjectile == projectiles.size){
            resultListener.onGameOver();
        }
        world.step(1.0f/60.0f, 8, 6);
    }

    @Override
    public void clear(){
        super.clear();
        elementsFactory.prepareGame(this);
        projectiles.clear();
        enemies.clear();
        defeatedEnemies.clear();
        world.dispose();
        world = new World(new Vector2(0,-10f), true);
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Body bodyA = contact.getFixtureA().getBody();
                Body bodyB = contact.getFixtureB().getBody();
                if (bodyB == ground){
                    bodyA = contact.getFixtureB().getBody();
                    bodyB = contact.getFixtureA().getBody();
                }
                if (bodyA == ground && enemies.containsKey(bodyB)){
                    enemies.get(bodyB).defeated();
                    enemies.remove(bodyB);
                    defeatedEnemies.add(bodyB);
                }
            }

            @Override
            public void endContact(Contact contact) { }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) { }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) { }
        });
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
        if (shooter.loaded() && currentProjectile < projectiles.size){
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
