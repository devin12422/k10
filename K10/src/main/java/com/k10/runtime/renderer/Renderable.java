package com.k10.runtime.renderer;

import com.k10.runtime.renderer.vertices.ShaderData;


public interface Renderable {
	public abstract ShaderData[] getRenderData();
	public abstract ShaderData getRenderData(int index);
	public abstract int lenShaderData();
}
