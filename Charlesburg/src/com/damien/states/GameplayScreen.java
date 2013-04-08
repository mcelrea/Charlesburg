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
import com.damien.sprites.Enemy;
import com.damien.sprites.Player;
import com.damien.sprites.Sprite;
import com.damien.sprites.StoneGholem;

public class GameplayScreen extends BasicGameState
{
	int stateID;
	Player player;
	
	ArrayList<Enemy> enemies;

	ArrayList<Bullet> bullets;
	
	Area testArea;

	public GameplayScreen(int id)
	{
		stateID = id;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sb) throws SlickException
	{
		player = new Player(new Image("images/Ruby trainer.png"));
		player.x = 300;
		player.y = 300;
		player.alive = true;
		player.turnSpeed = 0.2f;
		player.speed = 0.2f;

		bullets = new ArrayList<Bullet>();
		enemies = new ArrayList<Enemy>();
		initEnemies();
		
		testArea = new Area("data/map1.tmx");
	}
	
	public void initEnemies() throws SlickException
	{
		StoneGholem e = new StoneGholem(new Image("Images/stoneGolem.png"));
		e.x = 700;
		e.y = 300;
		e.angle = 270; //to the left
		e.alive = true;
		enemies.add(e);
		
		e = new StoneGholem(new Image("Images/stoneGolem.png"));
		e.x = 700;
		e.y = 500;
		e.angle = 270; //to the left
		e.alive = true;
		enemies.add(e);
	}
	
	public void renderEnemies(GameContainer gc, StateBasedGame sb, Graphics g) throws SlickException
	{
		//for every enemy
		for(int i=0; i < enemies.size(); i++)
		{
			Enemy e = enemies.get(i); //get the current enemy
			e.draw(g); //draw the current enemy
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics g) throws SlickException
	{
		testArea.draw(g);
		
		renderBullets(gc, sb, g);
		renderEnemies(gc, sb, g);
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
			player.moveLeft(delta);
		}
		/* Turn Right */
		if(input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT))
		{
			player.moveRight(delta);
		}//end if
		/* Move Forward */
		if(input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_UP))
		{
			player.moveUp(delta);
		}//end if
		if(input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_DOWN))
		{
			player.moveDown(delta);
		}//end if
		
		/* Shoot bullets with mouse */
		if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
		{
			//create a bullet that travels towards the mouse location
			Bullet b = player.shootBullet(input.getMouseX(), input.getMouseY());
			bullets.add(b); //add the new bullet to the list
		}

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
	
	public void updateEnemies(GameContainer gc, StateBasedGame sb, int delta) throws SlickException
	{
		//for every enemy
		for(int i=0; i < enemies.size(); i++)
		{
			Enemy e = enemies.get(i);  //get the current enemy
			e.act(delta, testArea, player, bullets); //tell the current enemy to act
		}//end for
		
	}//end updateEnemies

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
		updateEnemies(gc, sb, delta);
	}

	public void updateBullets(GameContainer gc, StateBasedGame sb, int delta) throws SlickException
	{
		//for every bullet in the list
		for(int i=0; i < bullets.size(); i++)
		{
			Bullet b = bullets.get(i); //get the current bullet
			b.update(delta); //update the current bullet
		}//end for loop
	}//end updateBullets

	@Override
	public int getID()
	{

		return stateID;
	}


}
