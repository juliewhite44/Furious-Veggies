package com.io.furiousveggies.settings;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.io.furiousveggies.skins.Pixthulhu;
import com.io.furiousveggies.skins.Rural;
import com.io.furiousveggies.skins.SkinWrapper;

public class Settings  extends Stage {
    private SettingsElementsFactory elementsFactory;
    private SettingsChangeListener changeListener;
    private Table mainTable;
    private final Array<SkinWrapper> availableSkins;
    private int currentSkin;

    public Settings(Viewport viewport, Batch batch, SettingsElementsFactory elementsFactory){
        super(viewport, batch);
        this.elementsFactory = elementsFactory;
        changeListener = new SettingsChangeListener() {
            @Override
            public void onSkinChanged(SkinWrapper newSkin) { }
        };
        availableSkins = new Array<SkinWrapper>();
        availableSkins.add(new Rural());
        availableSkins.add(new Pixthulhu());
        currentSkin = 0;
    }

    public SkinWrapper getDefaultSkin(){
        return availableSkins.get(0);
    }

    public void setElementsFactory(SettingsElementsFactory elementsFactory){
        this.elementsFactory = elementsFactory;
    }

    public void setChangeListener(SettingsChangeListener changeListener){
        this.changeListener = changeListener;
    }

    @Override
    public void clear(){
        super.clear();
        mainTable = elementsFactory.prepareSettings(this);
        elementsFactory.createRowOfButtons(mainTable, "Skin", availableSkins, i -> new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                currentSkin = i;
                changeListener.onSkinChanged(availableSkins.get(currentSkin));
            }
        });
    }

    @Override
    public void dispose(){
        for (int i = 0; i < availableSkins.size; i++){
            availableSkins.get(i).dispose();
        }
    }
}
