package com.io.furiousveggies.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.io.furiousveggies.view.skins.SkinWrapper;
import com.io.furiousveggies.view.skins.Skins;

public class View {
	public static final String MENU_GAME_BUTTON_NAME = "Menu Game Button";
	public static final String MENU_SETTINGS_BUTTON_NAME = "Menu Settings Button";

	private OrthographicCamera orthographicCamera;
	private SpriteBatch spriteBatch;
	private Stage current;
	private Stage extraCurrent;
	private SkinWrapper skinWrapper;
	private ScreenViewport screenViewport;
	private float width, height;

	public View(){
		orthographicCamera = new OrthographicCamera();
		orthographicCamera.setToOrtho(false);
		orthographicCamera.update();

		spriteBatch = new SpriteBatch();
		screenViewport = new ScreenViewport(orthographicCamera);

		skinWrapper = Skins.getDefaultSkin();
	}

	public void setOrthographicCamera(OrthographicCamera orthographicCamera) { this.orthographicCamera = orthographicCamera; }
	public void setSpriteBatch(SpriteBatch spriteBatch) { this.spriteBatch = spriteBatch; }
	public void setCurrent(Stage current) { this.current = current; }
	public void setExtraCurrent(Stage extraCurrent) { this.extraCurrent = extraCurrent; }
	public void setSkinWrapper(SkinWrapper skinWrapper) { this.skinWrapper = skinWrapper; }
	public void setScreenViewport(ScreenViewport screenViewport) { this.screenViewport = screenViewport; }
	public void setWidth(float width) { this.width = width; }
	public void setHeight(float height) { this.height = height; }

	public OrthographicCamera getOrthographicCamera() { return orthographicCamera; }
	public SpriteBatch getSpriteBatch() { return spriteBatch; }
	public Stage getCurrent() { return current; }
	public Stage getExtraCurrent() { return extraCurrent; }
	public SkinWrapper getSkinWrapper() { return skinWrapper; }
	public ScreenViewport getScreenViewport() { return screenViewport; }
	public float getWidth() { return width; }
	public float getHeight() { return height; }

	public void draw() {
		Color color = skinWrapper.backgroundColor();
		Gdx.gl.glClearColor(color.r, color.g, color.b, color.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		current.draw();
		if(extraCurrent != null) extraCurrent.draw();
	}

	public Table createMenuTable() {
		Table root = new Table();
		root.setFillParent(true);

		Table table = new Table(skinWrapper.getSkin());
		table.setBackground(skinWrapper.menuBackgroundName());
		table.setBounds(0, 0, width, height);
		
		root.add(table).grow().pad(0);
		
		Table subtable = new Table();
		table.add(subtable).grow().padRight(0);
		
		TextButton menuGame = new TextButton("Play", skinWrapper.getSkin());
		menuGame.setName(MENU_GAME_BUTTON_NAME);
		subtable.add(menuGame).grow().padLeft(width/100).padTop(height/100).padRight(width/100);
		subtable.row();
		
		TextButton menuSettings = new TextButton("Settings", skinWrapper.getSkin());
		menuSettings.setName(MENU_SETTINGS_BUTTON_NAME);
		subtable.add(menuSettings).grow().padLeft(width/100).padTop(height/100).padBottom(width/100).padRight(height/100);

		Label title = new Label("Furious\nVeggies", skinWrapper.getSkin(), "title");
		table.add(title).expand().center().padTop(height/100);

		return subtable;
	}
}
