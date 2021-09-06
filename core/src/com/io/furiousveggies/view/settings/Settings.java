package com.io.furiousveggies.view.settings;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.io.furiousveggies.view.skins.Pixthulhu;
import com.io.furiousveggies.view.skins.Rural;
import com.io.furiousveggies.view.skins.SkinWrapper;

public class Settings  extends Stage {
    private SettingsElementsFactory settingsElementsFactory;
    private SettingsChangeListener settingsChangeListener;
    private Table mainTable;
    private final Array<SkinWrapper> availableSkins;
    private int currentSkin;

    public Settings(Viewport viewport, Batch batch, SettingsElementsFactory settingsElementsFactory){
        super(viewport, batch);
        this.settingsElementsFactory = settingsElementsFactory;
        settingsChangeListener = newSkin -> { };
        availableSkins = new Array<>();
        availableSkins.add(new Rural());
        availableSkins.add(new Pixthulhu());
        currentSkin = 0;
    }

    public void setSettingsElementsFactory(SettingsElementsFactory settingsElementsFactory){
        this.settingsElementsFactory = settingsElementsFactory;
    }

    public SettingsElementsFactory getSettingsElementsFactory() {
        return settingsElementsFactory;
    }

    public void setSettingsChangeListener(SettingsChangeListener settingsChangeListener){
        this.settingsChangeListener = settingsChangeListener;
    }

    @Override
    public void clear(){
        super.clear();
        mainTable = settingsElementsFactory.prepareSettings(this);
        settingsElementsFactory.createRowOfButtons(mainTable, "Skin", availableSkins, i -> new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                currentSkin = i;
                settingsChangeListener.onSkinChanged(availableSkins.get(currentSkin));
            }
        });
    }
}
