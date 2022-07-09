package com.k10.runtime.windows;

import com.k10.runtime.renderer.Renderer;
import com.k10.runtime.world.World;

public class WorldWindow extends Window{
	protected Renderer renderer;
	protected World world;
	protected WorldWindow(int width, int height, String title,Renderer renderer) {
		super(width, height, title);
		this.renderer = renderer;
		// TODO Auto-generated constructor stub
	}
	public void init() {
		
	}
	public World getWorld() {
		return this.world;
	}
	@Override
	public void loop() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
