package com.k10.runtime.world.systems.components.bodies;

import java.util.List;

import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.SetMultimap;
import com.k10.runtime.renderer.vertices.Vertex;

public class TriangulatedBody extends Body {
	SetMultimap<Integer, Vertex> triangles;
	Vertex[] points;
	public TriangulatedBody(Vertex[] points) {
		triangles = MultimapBuilder.hashKeys().hashSetValues().build();
		this.points = points;
	}

	public void triangulate() {

	}

	public void update(float dt) {
		super.update(dt);
	}

	@Override
	public Vertex[] getRenderData() {
		return points;
	}

	@Override
	public Vertex getRenderData(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int lenShaderData() {
		// TODO Auto-generated method stub
		return 0;
	}
}
