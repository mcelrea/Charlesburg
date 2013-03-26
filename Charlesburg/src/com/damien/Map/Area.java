package com.damien.Map;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Area 
{
	public TiledMap area;
	
	public Area(String s) throws SlickException
	{
		area = new TiledMap(s);
	}
	
	public void draw(Graphics g)
	{
		area.render(0, 0);
	}

}
