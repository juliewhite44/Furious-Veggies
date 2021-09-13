package com.io.stonks.view.settings;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.io.stonks.view.settings.Settings;
import com.io.stonks.view.skins.SkinWrapper;

import java.util.function.IntFunction;

public interface SettingsElementsFactory {
    Table prepareSettings(Settings settings);
    void createRowOfButtons(Table mainTable, String rowName, Array<?> elements, IntFunction<ChangeListener> buttonListener);
    void setSkinWrapper(SkinWrapper skinWrapper);
}
