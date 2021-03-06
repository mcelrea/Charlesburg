package com.damien.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.damien.main.Driver;

public class StartScreen extends BasicGameState
{
	int stateID;
	
	public StartScreen(int id)
	{
		stateID = id;	
	}//end of constructor
	
	@Override
	public void init(GameContainer gc, StateBasedGame sb) throws SlickException
	{
			
	}//end of init

	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics g) throws SlickException
	{
		g.setColor(new Color(Color.cyan));
		g.drawString("Start Screen", 300, 10);
	}//end of render

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) throws SlickException
	{
		/* Get keyboard and mouse input */
		Input input = gc.getInput();
		
		if(input.isKeyPressed(Input.KEY_ENTER))
		{
			sb.enterState(Driver.GAMEPLAYSCREEN);
		}
		
	}//end of update

	@Override
	public int getID()
	{
		return stateID;	
	}//end of getID

}//end of class StartScreen
