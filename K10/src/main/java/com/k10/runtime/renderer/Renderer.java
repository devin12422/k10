package com.k10.runtime.renderer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

import java.util.Map;

import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.SetMultimap;
import com.k10.runtime.renderer.Framebuffer;
import com.k10.runtime.windows.Window;
import com.k10.runtime.world.systems.components.RendererComponent;

public abstract class Renderer {
	protected Window window;
	protected int width;
	protected int height;

	public Renderer() {
	}

	public abstract void render();

	public Window getWindow() {
		return this.window;
	}

	public abstract void renderBuffer(int i);

	public abstract void add(RendererComponent r);

}
