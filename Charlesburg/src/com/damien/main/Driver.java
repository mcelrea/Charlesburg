package com.damien.main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.damien.states.StartScreen;

public class Driver extends StateBasedGame
{

	static final int STARTSCREEN = 1;

	public Driver(String name)
	{

		super(name);
		this.addState(new StartScreen(STARTSCREEN));

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

	}//end of initStatesList

}//end of class Driver
