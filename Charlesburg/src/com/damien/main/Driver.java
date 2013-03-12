package com.damien.main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.damien.states.EndScreen;
import com.damien.states.GameplayScreen;
import com.damien.states.PauseScreen;
import com.damien.states.StartScreen;

public class Driver extends StateBasedGame
{

	public static final int STARTSCREEN = 1;
	public static final int GAMEPLAYSCREEN = 2;
	public static final int ENDSCREEN = 3;
	public static final int PAUSESCREEN = 4;

	public Driver(String name)
	{
		super(name);
		this.addState(new StartScreen(STARTSCREEN));
		this.addState(new GameplayScreen(GAMEPLAYSCREEN));
		this.addState(new EndScreen(ENDSCREEN));
		this.addState(new PauseScreen(PAUSESCREEN));
	}//end of constructor

	public static void main(String[] args) throws SlickException
	{
		AppGameContainer app = new AppGameContainer(new Driver("The Awesome City of Charlesburg"));

		/* 800x600 resolution and it is NOT fullscreen */
		app.setDisplayMode(800, 600, false);

		app.start();
	}//end of Main

	@Override
	public void initStatesList(GameContainer gc) throws SlickException
	{
		this.getState(STARTSCREEN).init(gc, this);
		this.getState(GAMEPLAYSCREEN).init(gc, this);
		this.getState(ENDSCREEN).init(gc, this);
		this.getState(PAUSESCREEN).init(gc, this);
	}//end of initStatesList

}//end of class Driver
