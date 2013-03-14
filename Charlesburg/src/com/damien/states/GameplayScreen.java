package com.damien.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.damien.main.Driver;
import com.damien.sprites.Sprite;

public class GameplayScreen extends BasicGameState
{
	int stateID;
	Sprite player;

	public GameplayScreen(int id)
	{
		stateID = id;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sb) throws SlickException
	{
		player = new Sprite(new Image("images/animated.gif"));
		player.x = 300;
		player.y = 300;
		player.alive = true;
		player.turnSpeed = 0.2f;
		player.speed = 0.2f;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics g) throws SlickException
	{
		player.draw(g);
	}

	public void updatePlayer(GameContainer gc, StateBasedGame sb, int delta, Input input) throws SlickException
	{
		/* Turn Left */
		if(input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_LEFT))
		{
			player.rotateLeft(delta);
		}
		/* Turn Right */
		if(input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT))
		{
			player.rotateRight(delta);
		}
		/* Move Forward */
		if(input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_UP))
		{
			player.moveForward(delta);
		}
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) throws SlickException
	{

		/* Get keyboard and mouse input */
		Input input = gc.getInput();
		
		updatePlayer(gc, sb, delta, input);
		
		if(input.isKeyPressed(Input.KEY_ESCAPE))
		{
			sb.enterState(Driver.ENDSCREEN);
		}
		if(input.isKeyPressed(Input.KEY_P))
		{
			sb.enterState(Driver.PAUSESCREEN);
		}

	}

	@Override
	public int getID()
	{

		return stateID;
	}


}
