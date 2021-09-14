package com.io.stonks.model;

import java.util.LinkedList;

public class LevelElements {
    private LinkedList<Shooter> shooters = new LinkedList<>();
    private LinkedList<Projectile> projectiles = new LinkedList<>();
    private LinkedList<Block> blocks = new LinkedList<>();
    private LinkedList<Enemy> enemies = new LinkedList<>();

    public void addElement(GameElement gameElement) {
        if(gameElement.getClass().equals(Shooter.class)) {
            shooters.add((Shooter) gameElement);
        }
        else if(gameElement.getClass().equals(Projectile.class)) {
            projectiles.add((Projectile) gameElement);
        }
        else if(gameElement.getClass().equals(Block.class)) {
            blocks.add((Block) gameElement);
        }
        else if(gameElement.getClass().equals(Enemy.class)) {
            enemies.add((Enemy) gameElement);
        }
    }
    public void addElements(LinkedList<GameElement> gameElements) {
        for(GameElement gameElement : gameElements) {
            addElement(gameElement);
        }
    }

    public LinkedList<Block> getBlocks() {
        return blocks;
    }

    public LinkedList<Enemy> getEnemies() {
        return enemies;
    }

    public LinkedList<Projectile> getProjectiles() {
        return projectiles;
    }

    public LinkedList<Shooter> getShooters() {
        return shooters;
    }
}
