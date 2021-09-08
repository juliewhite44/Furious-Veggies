package com.io.furiousveggies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.io.furiousveggies.model.Game;
import com.io.furiousveggies.model.GameElementsFactoryImpl;
import com.io.furiousveggies.view.settings.Settings;
import com.io.furiousveggies.view.settings.SettingsElementsFactoryImpl;
import com.io.furiousveggies.view.skins.SkinWrapper;

public class StagesFactoryImpl implements StagesFactory {
    @Override
    public Game createGame(InputListener inputListener, float scale) {
        Game game = new Game(new GameElementsFactoryImpl(), scale);
        game.clear();
        game.addListener(inputListener);
        return game;
    }

    @Override
    public Stage createMenu(ScreenViewport screenViewport, SpriteBatch spriteBatch) {
        return new Stage(screenViewport, spriteBatch);
    }

    @Override
    public Settings createSettings(ScreenViewport screenViewport, SpriteBatch spriteBatch, SkinWrapper skinWrapper, float height, float width) {
        return new Settings(screenViewport, spriteBatch, new SettingsElementsFactoryImpl(skinWrapper, height/100, width/100));
    }
}
