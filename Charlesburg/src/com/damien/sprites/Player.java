package com.damien.sprites;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player extends Sprite{

	public Player(Image i) {
		super(i);
		// TODO Auto-generated constructor stub
	}

	public Bullet shootBullet(int xloc, int yloc) throws SlickException
	{
		Bullet b = new Bullet(new Image("Images/sprite_bullet.png"));
		b.x = x;
		b.y = y;
		b.speed = 0.5f;
		b.angle = (float) computeAngleBetweenPoints(b.x, b.y, xloc, yloc);
		b.vx = calcAngleMoveX(b.angle);
		b.vy = calcAngleMoveY(b.angle);
		b.alive = true;
		b.image.setRotation(b.angle);
		b.damage = 1f;
		b.owner = this;
		
		return b;
	}
}
