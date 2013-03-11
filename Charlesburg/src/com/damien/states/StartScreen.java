package com.damien.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class StartScreen extends BasicGameState
{

	int stateID;
	
	public StartScreen(int id)
	{
		
		stateID = id;
		
	}//end of constructor
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException
	{
		
		
		
	}//end of init

	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics delta) throws SlickException
	{
		
		
		
	}//end of render

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) throws SlickException
	{
		
		
		
	}//end of update

	@Override
	public int getID()
	{
		
		return stateID;
		
	}//end of getID

}//end of class StartScreen
