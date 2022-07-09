package com.k10.runtime.windows;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.openal.ALC10.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import com.k10.runtime.graphics.renderer.Renderer;
import com.k10.runtime.windows.scenes.Scene;

public abstract class Window {
	protected int width, height;
	protected String title;
	protected Scene currentScene;
	protected Renderer renderer;

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

	public void destroy() {

	}

	public void init() {
	}
	public Scene getScene() {
		return this.currentScene;
	}

	public abstract void loop();

	public int getWidth() {
		return this.width;// get().width;
	}
	public int getHeight() {
		return this.height;// get().height;
	}

}
