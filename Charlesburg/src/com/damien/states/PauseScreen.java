package com.damien.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.damien.main.Driver;

public class PauseScreen extends BasicGameState
{

	int stateID;
	
	public PauseScreen(int id)
	{
		stateID = id;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sb) throws SlickException
	{
		
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics g) throws SlickException
	{
		
		g.drawString("Pause Screen", 300, 10);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) throws SlickException
	{
		
		/* Get keyboard and mouse input */
		Input input = gc.getInput();
		
		if(input.isKeyPressed(Input.KEY_P))
		{
			sb.enterState(Driver.GAMEPLAYSCREEN);
		}
		
	}

	@Override
	public int getID()
	{
		
		return stateID;
	}

}
