package com.io.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class Controller {
	Model model;
	ChangeListener
		menu_button_1 = new ChangeListener() {
        @Override
        public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            
        }
    },
		menu_button_2 = new ChangeListener() {
        @Override
        public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            
        }
    };
	
	//directs input at the right stage - so that its buttons can process clicks, key presses and such
	void control() {
		switch (model.screen) {
		case menu:
			Gdx.input.setInputProcessor(model.menu);
			break;

		default:
			break;
		}
	}
	
	public Controller(Model model) {
		this.model = model;
	}
}
