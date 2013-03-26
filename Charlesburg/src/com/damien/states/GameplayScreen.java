package com.damien.states;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.damien.Map.Area;
import com.damien.main.Driver;
import com.damien.sprites.Bullet;
import com.damien.sprites.Sprite;

public class GameplayScreen extends BasicGameState
{
	int stateID;
	Sprite player;

	ArrayList<Bullet> bullets;
	
	Area testArea;

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

		bullets = new ArrayList<Bullet>();
		
		testArea = new Area("data/map1.tmx");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics g) throws SlickException
	{
		testArea.draw(g);
		
		renderBullets(gc, sb, g);
		player.draw(g);
	}

	public void renderBullets(GameContainer gc, StateBasedGame sb, Graphics g) throws SlickException
	{
		//for every bullet in the list
		for(int i=0; i < bullets.size(); i++)
		{
			bullets.get(i).draw(g); //draw the current bullet
		}//end for
	}//end renderBullets

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
		}//end if
		/* Move Forward */
		if(input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_UP))
		{
			player.moveForward(delta);
		}//end if

		/* Shoot bullets */
		if(input.isKeyPressed(Input.KEY_SPACE))
		{
			Bullet b = new Bullet(new Image("images/sprite_bullet.png"));
			b.x = player.x;//put bullets x-value at the players x-value
			b.y = player.y;//put bullets y-value at the players y-value
			b.alive = true;//set the bullet alive so it appears on screen
			b.angle = player.angle; //set the bullet angle to be the same as the player
			b.owner = player;//set the bullets owner to player so it doesn't harm player
			b.speed = 0.5f; //set the bullet speed
			bullets.add(b);//add to the list of bullets
		}//end if
	}//end updatePlayer

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

		updateBullets(gc,sb,delta);
	}

	public void updateBullets(GameContainer gc, StateBasedGame sb, int delta) throws SlickException
	{
		//for every bullet in the list
		for(int i=0; i < bullets.size(); i++)
		{
			bullets.get(i).moveForward(delta);//move the current bullet
			//if the current goes off the screen
			if(bullets.get(i).isOffScreen(gc))
			{
				bullets.remove(i); //remove the bullet from the list
				i--; //decrement the size of the list
			}//end if
		}//end for loop
	}//end updateBullets

	@Override
	public int getID()
	{

		return stateID;
	}


}
