package com.io.furiousveggies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Disposable;

public class View implements Disposable {
	private Controller controller;
	private Skin skin;

	
	//draw current stage
	public void draw() {
		controller.getModel().draw();
	}


	public void createGame() {
		Table root = new Table();
		root.setFillParent(true);
		controller.getModel().addActorForGame(root);
		
		Table mainTable = new Table(skin);
		mainTable.setBackground("window-round");
		
		root.add(mainTable).grow().pad(0);
	}

	public void createSettings() {
		Table root = new Table();
		root.setFillParent(true);
		controller.getModel().addActorForSettings(root);

		Table mainTable = new Table(skin);
		mainTable.setBackground("window-round");

		root.add(mainTable).grow().pad(0);
	}


	//nie mozemy odnosic sie tutaj do model.width, jak wczesniej, wiec jest aktualnie
	//controller.getModel().getWidth(), ale czemu po prostu nie zrobić Gdx.graphics.getWidth();
	//czy zmienna width jest do czegoś używana w klasie Model?
	public void createMenu() {
		Table root = new Table();
		root.setFillParent(true);
		controller.getModel().addActorForMenu(root);
		
		Table table = new Table(skin);
		table.setBackground("window");
		table.setBounds(0, 0, controller.getModel().getWidth(), controller.getModel().getHeight());
		
		root.add(table).grow().pad(0);
		
		Table subtable = new Table();
		
		TextButton menu1 = new TextButton("Play", skin);
		menu1.addListener(controller.getMenu_button_1());
		subtable.add(menu1).grow().padLeft(controller.getModel().getWidth()/100).padTop(controller.getModel().getHeight()/100).padRight(controller.getModel().getHeight()/100);
		subtable.row();
		
		TextButton menu2 = new TextButton("Settings", skin);
		menu2.addListener(controller.getMenu_button_2());
		subtable.add(menu2).grow().padLeft(controller.getModel().getWidth()/100).padTop(controller.getModel().getHeight()/100).padBottom(controller.getModel().getWidth()/100).padRight(controller.getModel().getHeight()/100);
		table.add(subtable).grow().padRight(0);
		
		Label title = new Label("Furious\nVeggies", skin, "title");
		table.add(title).expand().center().padTop(controller.getModel().getHeight()/100);
		
	}

	public View(Controller controller){
		this.controller = controller;

		skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
		createMenu();
	}

	@Override
	public void dispose() {
		skin.dispose();
	}
}
