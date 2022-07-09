package com.k10.runtime.renderer.batches;

import com.k10.runtime.renderer.batches.RenderBatch;
import com.k10.runtime.renderer.shaders.VertexShader;
import com.k10.runtime.renderer.vertices.Vertex;

public class TriangleRenderBatch<V extends Vertex, S extends VertexShader<V>> extends RenderBatch<V,S> {

	public TriangleRenderBatch(S shader) {
		super(shader);
		// TODO Auto-generated constructor stub
	}

}
