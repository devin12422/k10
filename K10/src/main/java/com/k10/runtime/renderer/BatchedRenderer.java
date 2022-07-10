package com.k10.runtime.renderer;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import java.util.Map;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.k10.runtime.renderer.batches.RenderBatch;
import com.k10.runtime.renderer.shaders.VertexShader;
import com.k10.runtime.windows.GlfwWindow;
import com.k10.runtime.world.systems.components.RendererComponent;

public class BatchedRenderer extends Renderer {
	protected Multimap<Integer, RenderBatch> batches;
	protected Map<Integer, Framebuffer> buffers;
	protected GlfwWindow window;
	public static final int DATA_CAP = 1024;

	public BatchedRenderer() {
		super();
//		MutableGraph<Integer> graph = GraphBuilder.undirected().build();

		batches = LinkedHashMultimap.create();
	}

	@Override
	public void add(RendererComponent r) {
		if (!(r.getShader() instanceof VertexShader))
			return;
		if (buffers.containsKey(r.getZIndex())) {
			try {
				batches.get(r.getZIndex()).iterator().forEachRemaining((b) -> {
					if (b.canAdd(r)) {
						throw new BreakException(b);
					}
				});
			} catch (BreakException e) {
				e.getBatch().add(r);
				System.out.println("found");
				return;
			}
			RenderBatch b = new RenderBatch(r.getShader());
			b.start(this);
			b.add(r);
			batches.put(r.getZIndex(), b);
		} else {
			buffers.put(r.getZIndex(), new Framebuffer(window.getWidth(), window.getHeight()));
			RenderBatch b = new RenderBatch(r.getShader());
			b.start(this);
			b.add(r);
			batches.put(r.getZIndex(), b);
		}

	}

	class BreakException extends RuntimeException {
		RenderBatch r;

		BreakException(RenderBatch r) {
			this.r = r;
		}

		public RenderBatch getBatch() {
			return this.r;
		}
	}

	@Override
	public void render() {
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		glClear(GL_COLOR_BUFFER_BIT);
		buffers.keySet().forEach((i) -> {
			renderBuffer(i);
		});
//				int fbo = glGenFramebuffers();
//				glBindFramebuffer(GL_FRAMEBUFFER, fbo);
	}

	@Override
	public void renderBuffer(int i) {
		buffers.get(i).bind();
		System.out.println("buffer" + i + " bound");

		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		glClear(GL_COLOR_BUFFER_BIT);
		batches.get(i).forEach((r) -> {
			r.render();
		});

		buffers.get(i).unbind();
		System.out.println("buffer" + i + " unbound");

	}

	public GlfwWindow getWindow() {
		return this.window;
	}

}
