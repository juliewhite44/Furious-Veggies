package com.io.furiousveggies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.io.furiousveggies.game.Levels;

public class Controller {
	private Levels levels;
	private View view;
	
	public final ChangeListener
		menu_button_game = new ChangeListener() {
	        @Override
	        public void changed(ChangeListener.ChangeEvent event, Actor actor) {
	        	levels.startGame(view.createGame());
	        	Gdx.input.setInputProcessor(view.setGame());
	        }
	    },
			menu_button_settings = new ChangeListener() {
	        @Override
	        public void changed(ChangeListener.ChangeEvent event, Actor actor) {
	        	Gdx.input.setInputProcessor(view.setSettings());
	        }
	    };
	public final InputListener game_esc = new InputListener(){
        @Override
        public boolean keyDown (InputEvent event, int keycode){
            if(keycode == Input.Keys.ESCAPE)
            	Gdx.input.setInputProcessor(view.setMenu());
            return true;
        }
    };
    
	
	public Controller(Levels levels, View view) {
		this.levels = levels;
		this.view = view;
		view.controller = this;
		levels.controller = this;
		
		Gdx.input.setInputProcessor(view.setMenu());
	}

	public ChangeListener getMenu_button_game() {
		return menu_button_game;
	}

	public ChangeListener getMenu_button_settings() {
		return menu_button_settings;
	}
}
