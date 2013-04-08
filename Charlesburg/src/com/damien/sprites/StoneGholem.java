package com.damien.sprites;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.damien.Map.Area;

public class StoneGholem extends Enemy{
	
	private final int delay = 2000; //in milliseconds, 1000 = 1 second
	private long nextShot = 0; //the time of the next shot

	public StoneGholem(Image i) {
		super(i);
		
		nextShot = System.currentTimeMillis() + delay;
	}

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
			b.vx = calcAngleMoveX(b.angle);
			b.vy = calcAngleMoveY(b.angle);
			
			bullets.add(b);
			
			nextShot = System.currentTimeMillis() + delay;
		}
	}
}
