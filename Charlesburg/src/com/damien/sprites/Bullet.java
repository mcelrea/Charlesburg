package com.damien.sprites;

import org.newdawn.slick.Image;


public class Bullet extends Sprite {

	public Sprite owner;
	float damage;
	
	public Bullet(Image i) {
		super(i);
	}
	
	public void update(int delta)
	{
		x += vx * speed * delta;
		y += vy * speed * delta; 
	}

}
