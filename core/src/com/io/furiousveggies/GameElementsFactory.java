package com.io.furiousveggies;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public interface GameElementsFactory {
    void prepareGame(Game game);
    Body createGround(World world, float width);
    Block createBox(World world, float x, float y, float size);
    Enemy createEnemy(World world, float x, float y, float size);
    Projectile createProjectile(World world, float x, float y, float size);
    Shooter createShooter(World world, float x, float size);
}
