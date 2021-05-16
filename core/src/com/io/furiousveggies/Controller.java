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
	            model.setScreen(Screen.game);
	            model.startGame();
	        }
	    },
			menu_button_2 = new ChangeListener() {
	        @Override
	        public void changed(ChangeListener.ChangeEvent event, Actor actor) {
				model.setScreen(Screen.settings);
				model.startSettings();
	        }
	    };
	InputListener game_esc = new InputListener(){
        @Override
        public boolean keyDown (InputEvent event, int keycode){
            if(keycode == Input.Keys.ESCAPE)
                    model.setScreen(Screen.menu);
            return true;
        }
    };
	    
	//directs input at the right stage - so that its buttons can process clicks, key presses and such
	public void control() {
		model.setInputProcessor();
	}
	
	public Controller(Model model) {
		this.model = model;
		model.addListenerForGame(game_esc);
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
