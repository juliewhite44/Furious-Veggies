package com.io.furiousveggies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.io.furiousveggies.model.Game;
import com.io.furiousveggies.model.GameElementsFactoryImpl;
import com.io.furiousveggies.settings.Settings;
import com.io.furiousveggies.settings.SettingsElementsFactoryImpl;
import com.io.furiousveggies.view.View;
import com.io.furiousveggies.view.skins.SkinWrapper;

public class StagesFactoryImpl implements StagesFactory {
    @Override
    public Game createGame(ScreenViewport screenViewport, SpriteBatch spriteBatch, InputListener inputListener, View view) {
        Game game = new Game(screenViewport, spriteBatch, new GameElementsFactoryImpl(), view);
        game.setGameElementsFactory(new GameElementsFactoryImpl());
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
