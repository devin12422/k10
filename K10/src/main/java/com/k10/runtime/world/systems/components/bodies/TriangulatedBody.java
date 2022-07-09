package com.k10.runtime.world.systems.components.bodies;

import java.util.List;

import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.SetMultimap;
import com.k10.runtime.renderer.vertices.Vertex;

public class TriangulatedBody<V extends Vertex> extends Body<V> {
	SetMultimap<Integer, V> triangles;
	V[] points;
	public TriangulatedBody(V[] points) {
		triangles = MultimapBuilder.hashKeys().hashSetValues().build();
		this.points = points;
	}

	public void triangulate() {

	}

	public void update(float dt) {
		super.update(dt);
	}

	@Override
	public V[] getRenderData() {
		return points;
	}

	@Override
	public V getRenderData(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int lenShaderData() {
		// TODO Auto-generated method stub
		return 0;
	}
}
