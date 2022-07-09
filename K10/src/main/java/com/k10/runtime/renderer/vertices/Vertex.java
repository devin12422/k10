package com.k10.runtime.renderer.vertices;

import java.nio.FloatBuffer;
import java.util.concurrent.locks.ReentrantLock;

public class Vertex implements ShaderData {
	protected float[] position;
	protected static final ReentrantLock LOCK = new ReentrantLock();

	/**
	 * @param position
	 */
	public Vertex(float[] position) {
		this.position = position;
	}

	public float[] getPosition() {
		LOCK.lock();
		try {
			return position;
		} finally {
			LOCK.unlock();
		}
	}

	public void setPostion(float[] pos) {
		LOCK.lock();
		try {
			position = pos;
		} finally {
			LOCK.unlock();
		}
	}

	@Override
	public FloatBuffer getShaderData() {
		LOCK.lock();
		try {
			return FloatBuffer.wrap(position);

		} finally {
			LOCK.unlock();
		}
	}

	@Override
	public int lenData() {
		return position.length;
	}


}
