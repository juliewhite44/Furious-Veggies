package com.io.stonks.view;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable;
import com.io.stonks.model.Block;

public class BlockView extends Actor {
    private Block block;
    private TransformDrawable transformDrawable;

    public BlockView(Block block, TransformDrawable transformDrawable) {
        this.block = block;
        this.transformDrawable = transformDrawable;
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        transformDrawable.draw(batch, block.getX(), block.getY(), block.getWidth()/2f, block.getHeight()/2f, block.getWidth(), block.getHeight(), 1f, 1f, block.getRotation());
    }
}
