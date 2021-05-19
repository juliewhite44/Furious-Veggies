package com.io.furiousveggies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class Controller {
	private Model model;
	private View view;
	
	public final ChangeListener
		menu_button_1 = new ChangeListener() {
	        @Override
	        public void changed(ChangeListener.ChangeEvent event, Actor actor) {
	        	model.startGame(view.createGame());
	        	Gdx.input.setInputProcessor(view.setGame());
	        }
	    },
			menu_button_2 = new ChangeListener() {
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
    
	
	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
		view.controller = this;
		model.controller = this;
		
		Gdx.input.setInputProcessor(view.setMenu());
	}

	public ChangeListener getMenu_button_1() {
		return menu_button_1;
	}

	public ChangeListener getMenu_button_2() {
		return menu_button_2;
	}

	public Model getModel() {
		return model;
	}
}
