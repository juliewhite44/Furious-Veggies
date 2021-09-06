package com.io.furiousveggies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.io.furiousveggies.model.Game;
import com.io.furiousveggies.view.settings.Settings;
import com.io.furiousveggies.view.skins.SkinWrapper;

public interface StagesFactory {
    Game createGame(InputListener inputListener, float scale);
    Stage createMenu(ScreenViewport screenViewport, SpriteBatch spriteBatch);
    Settings createSettings(ScreenViewport screenViewport, SpriteBatch spriteBatch, SkinWrapper skinWrapper, float height, float width);
}
