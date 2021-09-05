package com.io.furiousveggies.model;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.io.furiousveggies.model.Block;
import com.io.furiousveggies.model.Enemy;
import com.io.furiousveggies.model.Projectile;
import com.io.furiousveggies.model.Shooter;

public interface GameElementsFactory {
    Body createGround(World world, float width);
    Block createBlock(World world, float x, float y, float size, float scale);
    Enemy createEnemy(World world, float x, float y, float size, float scale);
    Projectile createProjectile(World world, float x, float y, float size, float scale);
    Shooter createShooter(World world, float x, float size, float scale);
}
