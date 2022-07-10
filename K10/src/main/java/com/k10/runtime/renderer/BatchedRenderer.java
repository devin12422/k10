package com.k10.runtime.renderer;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;

import java.util.HashMap;
import java.util.HashSet;
import com.k10.runtime.renderer.batches.RenderBatch;
import com.k10.runtime.windows.GlfwWindow;
import com.k10.runtime.world.systems.components.RendererComponent;

public class BatchedRenderer extends Renderer {
	protected HashMap<Integer, HashSet<RenderBatch>> batches;
	protected HashMap<Integer, Framebuffer> buffers;
	protected GlfwWindow window;
	public static final int DATA_CAP = 1024;

	public BatchedRenderer() {
//		MutableGraph<Integer> graph = GraphBuilder.undirected().build();
//		ListMultimap<String, Integer> treeListMultimap =
//			    MultimapBuilder.treeKeys().arrayListValues().build();
		batches = new HashMap<Integer,HashSet<RenderBatch>>();
		buffers = new HashMap<Integer,Framebuffer>();
	}

	@Override
	public void add(RendererComponent r) {
//		if (!(r.getShader() instanceof VertexShader))
//			return;
//		if (buffers.containsKey(r.getZIndex())) {
//			try {
//				batches.get(r.getZIndex()).iterator().forEachRemaining((b) -> {
//					if (b.canAdd(r)) {
//						throw new BreakException(b);
//					}
//				});
//			} catch (BreakException e) {
//				e.getBatch().add(r);
//				System.out.println("found");
//				return;
//			}
//			RenderBatch b = new RenderBatch(r.getShader());
//			b.start(this);
//			b.add(r);
//			batches.put(r.getZIndex(), b);
//		} else {
//			buffers.put(r.getZIndex(), new Framebuffer(window.getWidth(), window.getHeight()));
//			RenderBatch b = new RenderBatch(r.getShader());
//			b.start(this);
//			b.add(r);
//			batches.put(r.getZIndex(), b);
//		}
//
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
			System.out.println(i);
			renderFramebuffer(framebuffer);
		});
		renderBuffer();
//				int fbo = glGenFramebuffers();
//				glBindFramebuffer(GL_FRAMEBUFFER, fbo);
	}
	public void renderFramebuffer(Framebuffer buffer) {
		
	}
	@Override
	public void renderBuffer() {
//		buffers.get(i).bind();
//		System.out.println("buffer" + i + " bound");
//
//		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
//		glClear(GL_COLOR_BUFFER_BIT);
//		batches.get(i).forEach((r) -> {
//			r.render();
//		});
//
//		buffers.get(i).unbind();
//		System.out.println("buffer" + i + " unbound");

	}

	public GlfwWindow getWindow() {
		return this.window;
	}

}
