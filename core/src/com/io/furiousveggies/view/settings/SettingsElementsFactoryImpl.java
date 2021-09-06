package com.io.furiousveggies.settings;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.io.furiousveggies.view.skins.SkinWrapper;

import java.util.function.IntFunction;

public class SettingsElementsFactoryImpl implements SettingsElementsFactory{
    private SkinWrapper skinWrapper;
    private final float verticalPadding, horizontalPadding;

    public SettingsElementsFactoryImpl(SkinWrapper skinWrapper, float verticalPadding, float horizontalPadding){
        this.skinWrapper = skinWrapper;
        this.verticalPadding = verticalPadding;
        this.horizontalPadding = horizontalPadding;
    }

    @Override
    public Table prepareSettings(Settings settings){
		Table root = new Table();
		root.setFillParent(true);
		settings.addActor(root);

		Table mainTable = new Table(skinWrapper.getSkin());
		mainTable.setBackground(skinWrapper.settingsBackgroundName());

		root.add(mainTable).grow().pad(0);

		return mainTable;
    }

    @Override
    public void createRowOfButtons(Table mainTable, String rowName, Array<?> elements, IntFunction<ChangeListener> buttonListener){
		Label title = new Label(rowName, skinWrapper.getSkin());
		mainTable.add(title).expand().center().padTop(verticalPadding);
		mainTable.row();

		Table subtable = new Table();
		for (int i = 0; i < elements.size; i++){
			TextButton button = new TextButton(elements.get(i).toString(), skinWrapper.getSkin());
			button.addListener(buttonListener.apply(i));
			subtable.add(button).grow().padLeft(horizontalPadding).padTop(verticalPadding).padRight(horizontalPadding);
		}
		mainTable.add(subtable).grow().padRight(0);
		mainTable.row();
    }

	@Override
	public void setSkinWrapper(SkinWrapper skinWrapper) {
		this.skinWrapper = skinWrapper;
	}
}
