package com.nbdev.startexgame;

import com.badlogic.gdx.Game;
import com.nbdev.startexgame.Screens.MenuScreen;

public class StartexGame extends Game {
	@Override
	public void create () {
		setScreen(new MenuScreen(this));
	}
}
