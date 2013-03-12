package com.damien.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class EndScreen extends BasicGameState{

	int stateID;
	
	public EndScreen(int id)
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
		g.drawString("End Screen", 300, 10);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) throws SlickException {
		
		
	}

	@Override
	public int getID() {
		
		return stateID;
	}

}
