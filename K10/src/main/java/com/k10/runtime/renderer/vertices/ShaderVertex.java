package com.k10.runtime.renderer.vertices;

import java.nio.FloatBuffer;

public class ShaderVertex extends Vertex {
	protected float[] color;

	/**
	 * @param position
	 * @param color
	 */
	public ShaderVertex(float[] position, float[] color) {
		super(position);
		this.color = color;
	}

	public float[] getColor() {
		LOCK.lock();
		try {
			return color;
		} finally {
			LOCK.unlock();
		}
	}

	public void setColor(float[] color) {
		LOCK.lock();
		try {
			this.color = color;
		} finally {
			LOCK.unlock();
		}
	}
	@Override
	public FloatBuffer getShaderData() {
		LOCK.lock();
		try {
			return super.getShaderData().put(super.lenData(), color);

		} finally {
			LOCK.unlock();
		}
	}
	@Override
	public int lenData() {
		return super.lenData() + color.length;
	}
}
