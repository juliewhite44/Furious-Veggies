package com.io.stonks.model;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public interface GameElementsFactory {
    Body createGround(World world, float width);
    Body createCeiling(World world, float width);
    Body createRightWall(World world, float height);
    Body createLeftWall(World world, float height);
    Block createBlock(World world, float x, float y, float size, float scale);
    Enemy createEnemy(World world, float x, float y, float size, float scale);
    Projectile createProjectile(World world, float x, float y, float size, float scale);
    Shooter createShooter(World world, float x, float size, float scale);
}
