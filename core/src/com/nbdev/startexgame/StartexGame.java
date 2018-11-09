package com.nbdev.startexgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.nbdev.startexgame.Screens.MainMenu.MenuScreen;

public class StartexGame extends Game {
	@Override
	public void create () {
		setScreen(new MenuScreen(this));
	}
}
