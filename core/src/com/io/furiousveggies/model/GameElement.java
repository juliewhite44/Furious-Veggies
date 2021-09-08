package com.io.furiousveggies.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;

public abstract class GameElement extends Actor {
    protected Body body;
    protected float size;
    protected float scale;

    public GameElement(Body body, float size, float scale){
        this.body = body;
        this.size = size;
        this.scale = scale;

        Vector2 pos = body.getPosition();
        setPosition(pos.x * scale, pos.y * scale, Align.center);
        setSize(size * scale, size * scale);
    }
}
