package com.io.furiousveggies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class Controller {
	Model model;
	ChangeListener
		menu_button_1 = new ChangeListener() {
	        @Override
	        public void changed(ChangeListener.ChangeEvent event, Actor actor) {
	            model.screen = Screen.game;
	            model.startGame();
	        }
	    },
			menu_button_2 = new ChangeListener() {
	        @Override
	        public void changed(ChangeListener.ChangeEvent event, Actor actor) {
	            
	        }
	    };
	InputListener game_esc = new InputListener(){
        @Override
        public boolean keyDown (InputEvent event, int keycode){
            if(keycode == Input.Keys.ESCAPE)
                    model.screen = Screen.menu;
            return true;
        }
    };
	    
	//directs input at the right stage - so that its buttons can process clicks, key presses and such
	void control() {
		switch (model.screen) {
		case menu:
			Gdx.input.setInputProcessor(model.menu);
			break;
		case game:
			Gdx.input.setInputProcessor(model.game);
		default:
			break;
		}
	}
	
	
	public Controller(Model model) {
		this.model = model;
		model.game.addListener(game_esc);
	}
}
