package com.io.furiousveggies.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

public class Game extends Stage {
    private GameElementsFactory gameElementsFactory;
    private Array<Projectile> projectiles;
    private ObjectMap<Body, Enemy> enemies;
    private Array<Body> defeatedEnemies;
    private float scale;
    private World world;
    private Body ground;
    private GameResultListener gameResultListener;
    private int currentProjectile;
    private float shooterX, shooterSize;
    private Shooter shooter;
    private Rope rope;
    private boolean deleteRope;

    public static final float width = 20.0f, height = 10.0f;

    public Game(GameElementsFactory gameElementsFactory, float scale){
        this.gameElementsFactory = gameElementsFactory;
        world = new World(new Vector2(0,-10f), true);
        projectiles = new Array<>();
        deleteRope = false;
        enemies = new ObjectMap<>();
        defeatedEnemies = new Array<>();
        this.scale = scale;
        gameResultListener = new GameResultListener() {
            @Override
            public void onGameWin() { }

            @Override
            public void onGameOver() { }
        };
        clear();
    }

    public GameElementsFactory getGameElementsFactory() { return gameElementsFactory; }
    public void setGameElementsFactory(GameElementsFactory gameElementsFactory){
        this.gameElementsFactory = gameElementsFactory;
    }

    public void setGameResultListener(GameResultListener gameResultListener){
        this.gameResultListener = gameResultListener;
    }

    public Rope getRope() {
        return rope;
    }

    public void setRope(Rope rope) {
        this.rope = rope;
    }

    public boolean isDeleteRope() {
        return deleteRope;
    }

    public void setDeleteRope(boolean deleteRope) {
        this.deleteRope = deleteRope;
    }

    public void addGround() {
        ground = gameElementsFactory.createGround(world, width * 2);
    }


    public Block addBox(float x, float y, float size, float scale) {
        Block block = gameElementsFactory.createBlock(world, x, y, size, scale);
        addActor(block);
        return block;
    }

    public Enemy addEnemy(float x, float y, float size, float scale) {
        Enemy enemy = gameElementsFactory.createEnemy(world, x, y, size, scale);
        addActor(enemy);
        enemies.put(enemy.getBody(), enemy);
        return enemy;
    }

    public Shooter addShooter(float x, float size, float scale) {
        shooterX = x;
        shooterSize = size;
        shooter = gameElementsFactory.createShooter(world, x, size, scale);
        addActor(shooter);
        return shooter;
    }

    public Projectile addProjectile(float size, float scale){
        Projectile projectile;
        if (projectiles.size == 0){
            projectile = gameElementsFactory.createProjectile(world, shooterX - size / 2, shooterSize * 1.5f, size, scale);
        }
        else {
            projectile = gameElementsFactory.createProjectile(world, shooterX - 1.5f * size * projectiles.size - size / 2, 0, size, scale);
        }
        addActor(projectile);
        projectiles.add(projectile);
        return projectile;
    }

    public Array<Body> getDefeatedEnemies() {
        return defeatedEnemies;
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
                deleteRope = true;

            }
            gameResultListener.onGameWin();
        }
        else if (currentProjectile == projectiles.size){
            gameResultListener.onGameOver();
        }
        world.step(1.0f/60.0f, 8, 6);
    }

    @Override
    public void clear(){
        super.clear();
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
            rope = shooter.aim(projectiles.get(currentProjectile));
            addActor(rope);
        }
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button){
        if (shooter.loaded() && currentProjectile < projectiles.size){
            Projectile projectile;
            shooter.shoot();
            deleteRope = true;
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
