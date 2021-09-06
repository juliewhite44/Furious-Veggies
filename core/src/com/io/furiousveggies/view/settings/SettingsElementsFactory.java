package com.io.furiousveggies.view.settings;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.io.furiousveggies.view.settings.Settings;
import com.io.furiousveggies.view.skins.SkinWrapper;

import java.util.function.IntFunction;

public interface SettingsElementsFactory {
    Table prepareSettings(Settings settings);
    void createRowOfButtons(Table mainTable, String rowName, Array<?> elements, IntFunction<ChangeListener> buttonListener);
    void setSkinWrapper(SkinWrapper skinWrapper);
}
