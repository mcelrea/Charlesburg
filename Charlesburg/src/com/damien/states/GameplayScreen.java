package com.damien.states;

import java.util.ArrayList;
import java.util.Random;

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
import com.damien.sprites.Chaser;
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
	
	public static int score = 0;
	
	/* variables to control the spawning of StoneGholems */
	long gholemDelay = 3000; //1000 = 1 second
	long nextGholem; //the exact time the next StoneGholem will appear
	Random rand = new Random(); //random number generator
	
	/* variables to control the spawning of Chasers */
	long chaserDelay = 2000; //1000 = 1 second
	long nextChaser;
	
	
	Area testArea;

	public GameplayScreen(int id)
	{
		stateID = id;
	}
	
	//this method runs every time the GAMEPLAY screen is entered
	public void enter(GameContainer gc, StateBasedGame sb)
	{
		nextGholem = System.currentTimeMillis() + gholemDelay;
		nextChaser = System.currentTimeMillis() + chaserDelay;
	}
 
	@Override
	public void init(GameContainer gc, StateBasedGame sb) throws SlickException
	{
		player = new Player(new Image("images/Ruby trainer.png"));
		player.x = 300;
		player.y = 300;
		player.alive = true; 
		player.speed = 0.2f;

		bullets = new ArrayList<Bullet>();
		enemies = new ArrayList<Enemy>();
		initEnemies();
		
		testArea = new Area("data/map1.tmx");
	}
	
	public void initEnemies() throws SlickException
	{
		StoneGholem e = new StoneGholem(new Image("Images/stoneGolem.png"));
		e.x = 10;
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
		
		Chaser c = new Chaser(new Image("Images/chaser.png"));
		c.x = 770;
		c.y = 0;
		c.angle = 90;
		c.alive = true;
		c.speed = 0.4f;
		enemies.add(c);
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
	
	public void renderGUI(GameContainer gc, StateBasedGame sb, Graphics g) throws SlickException
	{
		g.drawString("Score: " + score, 350, 5);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics g) throws SlickException
	{
		testArea.draw(g);
		
		renderBullets(gc, sb, g);
		renderEnemies(gc, sb, g);
		player.draw(g);
		
		renderGUI(gc, sb, g);
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
	
	public void spawnChasers() throws SlickException
	{
		if(System.currentTimeMillis() >= nextChaser)
		{
			//0-3
			int choice = rand.nextInt(4);
			
			Chaser c = new Chaser(new Image("Images/chaser.png"));
			c.alive = true;
			c.speed = 0.4f;
			
			//top left
			if(choice == 0)
			{
				c.x = 0;
				c.y = 0;
			}//end if
			//top right
			else if(choice == 1)
			{
				c.x = 770;
				c.y = 0;
			}//end else if
			//bottom left
			else if(choice == 2)
			{
				c.x = 0;
				c.y = 550;
			}//end else if
			//else bottom right
			else
			{
				c.x = 770;
				c.y = 550;
			}//end else
			
			enemies.add(c); //add the new chaser to the enemy list
			
			//compute the time for the next chaser spawn
			nextChaser = System.currentTimeMillis() + chaserDelay;
		}//end if
	}//end spawnChasers
	
	public void spawnGholems() throws SlickException
	{
		if(System.currentTimeMillis() >= nextGholem)
		{
			//choice will either be a 0 or 1
			int choice = rand.nextInt(2);
			
			StoneGholem e = new StoneGholem(new Image("Images/stoneGolem.png"));
			
			if(choice == 0)
				e.x = 10;
			else
				e.x = 700;
			
			e.y = rand.nextInt(500);
			e.angle = 270; //to the left
			e.alive = true;
			enemies.add(e);
			
			nextGholem = System.currentTimeMillis() + gholemDelay;
		}//end if
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) throws SlickException
	{

		/* Get keyboard and mouse input */
		Input input = gc.getInput();

		updatePlayer(gc, sb, delta, input);
		spawnGholems();
		spawnChasers();

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
			
			
			//for every enemy
			for(int j=0; j < enemies.size(); j++)
			{
				//get the current enemy
				Enemy e = enemies.get(j);
				
				//if the bullet is hitting the enemy and its the players bullet
				if(b.spriteCollision(e) && b.owner == player)
				{
					e.health--; //remove one health from the enemy (he was hit)
					if(e.health <= 0)//if the enemies health drops to 0
					{
						score++; //add one point to player score
						enemies.remove(j); //kill the enemy
						j--; //shorten the enemy list by one (an enemy died)
					}//end if
					
					bullets.remove(i); //kill the bullet
					i--; //shorten the bullet list (a bullet died)
				}//end if
			}//end for loop
			
			//if its an enemy bullet
			if(b.owner != player)
			{
				//if the enemy bullet hits the player
				if(b.spriteCollision(player))
				{
					player.health--; //take one health away from player
					//if player health becomes less than 0
					if(player.health <= 0)
					{
						System.exit(0); //exit the game (the player died)
					}//end if
					
					bullets.remove(i); //kill the bullet
					i--; //make the size of bullet list one less (a bullet died)
					
				}//end if
			}//end if
			
			
		}//end for loop
	}//end updateBullets

	@Override
	public int getID()
	{

		return stateID;
	}


}
