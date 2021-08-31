package com.io.furiousveggies;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.io.furiousveggies.skins.SkinWrapper;

import java.util.function.IntFunction;

public class SimpleSettingsFactory implements SettingsElementsFactory{
    private final SkinWrapper skin;
    private final float verticalPadding, horizontalPadding;

    public SimpleSettingsFactory(SkinWrapper skin, float verticalPadding, float horizontalPadding){
        this.skin = skin;
        this.verticalPadding = verticalPadding;
        this.horizontalPadding = horizontalPadding;
    }

    @Override
    public Table prepareSettings(Settings settings){
		Table root = new Table();
		root.setFillParent(true);
		settings.addActor(root);

		Table mainTable = new Table(skin.getSkin());
		mainTable.setBackground(skin.settingsBackgroundName());

		root.add(mainTable).grow().pad(0);

		return mainTable;
    }

    @Override
    public void createRowOfButtons(Table mainTable, String rowName, Array<?> elements, IntFunction<ChangeListener> buttonListener){
		Label title = new Label(rowName, skin.getSkin());
		mainTable.add(title).expand().center().padTop(verticalPadding);
		mainTable.row();

		Table subtable = new Table();
		for (int i = 0; i < elements.size; i++){
			TextButton button = new TextButton(elements.get(i).toString(), skin.getSkin());
			button.addListener(buttonListener.apply(i));
			subtable.add(button).grow().padLeft(horizontalPadding).padTop(verticalPadding).padRight(horizontalPadding);
		}
		mainTable.add(subtable).grow().padRight(0);
    }
}
