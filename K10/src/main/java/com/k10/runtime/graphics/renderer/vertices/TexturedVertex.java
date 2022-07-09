package com.k10.runtime.graphics.renderer.vertices;

import java.nio.FloatBuffer;

public class TexturedVertex extends Vertex {
	protected float[] uv;
	public TexturedVertex(float[] position, float[] uv) {
		super(position);
		this.uv = uv;
	}

	public float[] getUv() {
		LOCK.lock();
		try {
			return uv;
		} finally {
			LOCK.unlock();
		}
	}

	public void setUv(float[] uv) {
		LOCK.lock();
		try {
			this.uv = uv;
		} finally {
			LOCK.unlock();
		}
	}

	@Override
	public FloatBuffer getShaderData() {
		LOCK.lock();
		try {
			return super.getShaderData().put(super.lenData(), uv);

		} finally {
			LOCK.unlock();
		}
	}
	@Override
	public int lenData() {
		return super.lenData() + uv.length;
	}
}
