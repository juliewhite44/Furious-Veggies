package com.io.furiousveggies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.io.furiousveggies.game.Game;
import com.io.furiousveggies.settings.Settings;
import com.io.furiousveggies.view.View;
import com.io.furiousveggies.view.skins.SkinWrapper;

public interface StagesFactory {
    Game createGame(ScreenViewport screenViewport, SpriteBatch spriteBatch, InputListener inputListener, View view);
    Stage createMenu(ScreenViewport screenViewport, SpriteBatch spriteBatch);
    Settings createSettings(ScreenViewport screenViewport, SpriteBatch spriteBatch, SkinWrapper skinWrapper, float height, float width);
}
