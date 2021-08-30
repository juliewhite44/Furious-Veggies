package com.io.furiousveggies.skins;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable;
import com.badlogic.gdx.utils.Disposable;

public interface Skin extends Disposable {
    TransformDrawable boxDrawable();
    TransformDrawable enemyDrawable();
    TransformDrawable projectileDrawable();
    TransformDrawable shooterDrawable();
    String menuBackgroundName();
    String gameBackgroundName();
    String settingsBackgroundName();
    Color backgroundColor();
    Color shooterRopeColor();
    com.badlogic.gdx.scenes.scene2d.ui.Skin getSkin();
}
