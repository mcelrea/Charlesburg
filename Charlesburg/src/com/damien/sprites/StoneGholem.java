package com.damien.sprites;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.damien.Map.Area;

public class StoneGholem extends Enemy{
	
	private final int delay = 2000; //in milliseconds, 1000 = 1 second
	private long nextShot = 0; //the time of the next shot

	public StoneGholem(Image i)
	{
		super(i);
		health = 5;
		nextShot = System.currentTimeMillis() + delay;
	}
	
	public void draw(Graphics g)
	{
		if(alive)
		{
			if(x > 400)
				g.drawImage(image, x, y);
			else //draw it flipped so that it faces the correct way
				g.drawImage(image.getFlippedCopy(true, false), x, y);
		}
	}//end method draw

	public void act(int delta, Area a, Player p, ArrayList<Bullet> bullets) throws SlickException
	{
		if(nextShot <= System.currentTimeMillis())
		{
			Bullet b = new Bullet(new Image("images/sprite_bullet.png"));
			b.alive = true;
			b.x = x;
			b.y = y;
			b.speed = 0.4f;
			b.angle = angle;
			b.owner = this;
			if(x > 400)//shoot to the left
			{
				b.vx = calcAngleMoveX(270);
				b.vy = calcAngleMoveY(270);
			}
			else//shoot to the right
			{
				b.vx = calcAngleMoveX(90);
				b.vy = calcAngleMoveY(90);
			}
			
			bullets.add(b);
			
			nextShot = System.currentTimeMillis() + delay;
		}
	}
}
