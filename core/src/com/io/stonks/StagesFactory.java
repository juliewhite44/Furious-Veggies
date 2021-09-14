package com.io.stonks;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.io.stonks.model.Game;
import com.io.stonks.view.settings.Settings;
import com.io.stonks.view.skins.SkinWrapper;

public interface StagesFactory {
    Game createGame(InputListener inputListener, float scale);
    Stage createDefaultStage(ScreenViewport screenViewport, SpriteBatch spriteBatch);
    Settings createSettings(ScreenViewport screenViewport, SpriteBatch spriteBatch, SkinWrapper skinWrapper, float height, float width);
}
