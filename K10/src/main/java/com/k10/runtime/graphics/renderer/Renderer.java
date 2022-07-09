package com.k10.runtime.graphics.renderer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

import java.util.Map;

import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.SetMultimap;
import com.k10.runtime.graphics.renderer.Framebuffer;
import com.k10.runtime.windows.Window;
import com.k10.runtime.world.systems.components.RendererComponent;

public abstract class Renderer {
	protected Map<Integer, Framebuffer> buffers;
	protected Window window;

	public Renderer() {
	}

	public void render() {
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		glClear(GL_COLOR_BUFFER_BIT);
		buffers.keySet().forEach((i) -> {
			renderBuffer(i);
		});
//		int fbo = glGenFramebuffers();
//		glBindFramebuffer(GL_FRAMEBUFFER, fbo);

	}
	public Window getWindow() {
		return this.window;
	}
	public abstract void renderBuffer(int i);

	/**
	 * @param r cam
	 */
	public abstract void add(RendererComponent r);

}
