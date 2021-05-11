package com.io.furiousveggies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Disposable;

public class View implements Disposable {
	Model model;
	Controller controller;
	Skin skin;
	
	//draw current stage
	void draw() {
		Gdx.gl.glClearColor(0, 0.4f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		switch (model.screen) {
		case menu:
			model.menu.draw();
			break;
		case game:
			model.game.draw();
			break;
		default:
			break;
		}
	}
	
	void createGame() {
		Table root = new Table();
		root.setFillParent(true);
		model.game.addActor(root);
		
		Table mainTable = new Table(skin);
		mainTable.setBackground("window-round");
		
		root.add(mainTable).grow().pad(0);
	}
	
	void createMenu() {
		Table root = new Table();
		root.setFillParent(true);
		model.menu.addActor(root);
		
		Table table = new Table(skin);
		table.setBackground("window");
		table.setBounds(0, 0, model.width, model.height);
		
		root.add(table).grow().pad(0);
		
		Table subtable = new Table();
		
		TextButton menu1 = new TextButton("Play", skin);
		menu1.addListener(controller.menu_button_1);
		subtable.add(menu1).grow().padLeft(model.width/100).padTop(model.height/100).padRight(model.height/100);
		subtable.row();
		
		TextButton menu2 = new TextButton("Settings", skin);
		menu2.addListener(controller.menu_button_2);
		subtable.add(menu2).grow().padLeft(model.width/100).padTop(model.height/100).padBottom(model.width/100).padRight(model.height/100);
		table.add(subtable).grow().padRight(0);
		
		Label title = new Label("Furious\nVeggies", skin, "title");
		table.add(title).expand().center().padTop(model.height/100);
		
	}

	View(Model model, Controller controller){
		this.model = model;
		this.controller = controller;

		skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
		createMenu();
	}

	@Override
	public void dispose() {
		skin.dispose();
	}
}
