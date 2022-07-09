package com.k10.runtime.graphics.renderer.vertices;

import java.nio.FloatBuffer;

import org.joml.Vector2f;
import org.joml.Vector4f;

import com.k10.runtime.graphics.renderer.vertices.Vertex;
import com.k10.util.GlueList;

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
