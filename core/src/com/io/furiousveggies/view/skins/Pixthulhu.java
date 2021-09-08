package com.io.furiousveggies.view.skins;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable;

public class Pixthulhu implements SkinWrapper {
    private final Skin skin;

    public Pixthulhu(){
        skin = new Skin(Gdx.files.internal("skin/pixthulhu/pixthulhu-ui.json"));
    }

    @Override
    public String toString(){
        return "pixthulhu";
    }

    @Override
    public Skin getSkin(){
        return skin;
    }

    @Override
    public String menuBackgroundName(){
        return "window";
    }

    @Override
    public String gameBackgroundName(){
        return "window-round";
    }

    @Override
    public String settingsBackgroundName(){
        return "window-round";
    }

    @Override
    public Color backgroundColor(){
        return new Color(0, 0.4f, 0.1f, 1);
    }

    @Override
    public Color shooterRopeColor(){
        return new Color(0.3f, 0.5f, 0.5f, 1);
    }

    @Override
    public TransformDrawable boxDrawable(){
        return (TransformDrawable)skin.getDrawable("button");
    }

    @Override
    public TransformDrawable enemyDrawable(){
        return (TransformDrawable)skin.getDrawable("pixthulhu");
    }

    @Override
    public TransformDrawable projectileDrawable(){
        return (TransformDrawable)skin.getDrawable("logo");
    }

    @Override
    public TransformDrawable shooterDrawable(){
        return (TransformDrawable)skin.getDrawable("split-pane-horizontal");
    }

    @Override
    public void dispose(){
        skin.dispose();
    }
}
