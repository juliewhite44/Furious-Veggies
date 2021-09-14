package com.io.stonks;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.io.stonks.model.Game;
import com.io.stonks.model.GameElementsFactoryImpl;
import com.io.stonks.view.settings.Settings;
import com.io.stonks.view.settings.SettingsElementsFactoryImpl;
import com.io.stonks.view.skins.SkinWrapper;

public class StagesFactoryImpl implements StagesFactory {
    @Override
    public Game createGame(InputListener inputListener, float scale) {
        Game game = new Game(new GameElementsFactoryImpl(), scale);
        game.clear();
        game.addListener(inputListener);
        return game;
    }

    @Override
    public Stage createDefaultStage(ScreenViewport screenViewport, SpriteBatch spriteBatch) {
        return new Stage(screenViewport, spriteBatch);
    }

    @Override
    public Settings createSettings(ScreenViewport screenViewport, SpriteBatch spriteBatch, SkinWrapper skinWrapper, float height, float width) {
        return new Settings(screenViewport, spriteBatch, new SettingsElementsFactoryImpl(skinWrapper, height/100, width/100));
    }
}
