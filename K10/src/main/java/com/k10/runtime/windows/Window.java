package com.k10.runtime.windows;

public abstract class Window {
	protected int width, height;
	protected String title;

	protected Window(int width, int height, String title) {
		this.width = width;
		this.height = height;
		this.title = title;
	}

	public void run() {
		init();
		loop();
		destroy();
	}

	public abstract void destroy();

	public abstract void init();

	public abstract void loop();

	public int getWidth() {
		return this.width;// get().width;
	}

	public int getHeight() {
		return this.height;// get().height;
	}

}
