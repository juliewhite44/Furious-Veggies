package com.io.stonks.view.skins;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable;

public class Rural implements SkinWrapper {
	private static SkinWrapper instance = null;
    private final Skin skin;

    private Rural(){
        skin = new Skin(Gdx.files.internal("skin/rural/rural.json"));
    }
    
    public static SkinWrapper getInstance() {
    	if(instance == null)
    		instance = new Rural();
    	return instance;
    }

    @Override
    public String toString(){
        return "rural";
    }

    @Override
    public TransformDrawable boxDrawable(){
        return (TransformDrawable)skin.getDrawable("crate");
    }

    @Override
    public TransformDrawable enemyDrawable(){
        return (TransformDrawable)skin.getDrawable("colorado-beetle");
    }

    @Override
    public TransformDrawable projectileDrawable(){
        return (TransformDrawable)skin.getDrawable("potato");
    }

    @Override
    public TransformDrawable shooterDrawable(){
        return (TransformDrawable)skin.getDrawable("plank");
    }

    @Override
    public String menuBackgroundName(){
        return "field-medium-bleak";
    }

    @Override
    public String gameBackgroundName(){
        return "field-medium";
    }

    @Override
    public String settingsBackgroundName(){
        return "field-medium-bleak";
    }

    @Override
    public Color backgroundColor(){
        return Color.CYAN;
    }

    @Override
    public Color shooterRopeColor(){
        return Color.BLACK;
    }

    @Override
    public Skin getSkin(){
        return skin;
    }

    @Override
    public void dispose(){
        skin.dispose();
    }
}
