package com.k10.runtime.graphics.renderer.batches;

import com.k10.runtime.graphics.renderer.batches.RenderBatch;
import com.k10.runtime.graphics.renderer.shaders.VertexShader;
import com.k10.runtime.graphics.renderer.vertices.Vertex;

public class TriangleRenderBatch<V extends VertexShader<? extends Vertex>> extends RenderBatch<V> {

	public TriangleRenderBatch(V shader) {
		super(shader);
		// TODO Auto-generated constructor stub
	}

}
