package com.k10.runtime.renderer.vertices;

import java.util.List;
import java.nio.FloatBuffer;
public interface ShaderData {
	public abstract FloatBuffer getShaderData();
	public abstract int lenData();
	public boolean isDirty = true;
}
