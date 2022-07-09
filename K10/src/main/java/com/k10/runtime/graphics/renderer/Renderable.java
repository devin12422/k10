package com.k10.runtime.graphics.renderer;

import java.util.List;

import com.google.common.graph.Graph;
import com.k10.runtime.graphics.renderer.vertices.ShaderData;


public interface Renderable<T extends ShaderData> {
	public abstract T[] getRenderData();
	public abstract T getRenderData(int index);
	public abstract int lenShaderData();
}
