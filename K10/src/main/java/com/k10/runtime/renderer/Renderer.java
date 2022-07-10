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
	protected int width;
	protected int height;
	protected Framebuffer framebuffer;

	public abstract void render();

	public abstract void renderBuffer();

	public abstract void add(RendererComponent r);

}
