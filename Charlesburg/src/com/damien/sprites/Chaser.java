package com.damien.sprites;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.damien.Map.Area;

public class Chaser extends Enemy{

	public Chaser(Image i) {
		super(i);
		// TODO Auto-generated constructor stub
	}//end constructor
	
	public void act(int delta, Area a, Player p, ArrayList<Bullet> bullets) throws SlickException
	{
		this.simpleChase(p, delta);
	}//end act

}//end class Chaser
