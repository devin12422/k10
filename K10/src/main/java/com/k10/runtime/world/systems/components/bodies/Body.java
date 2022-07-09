package com.k10.runtime.world.systems.components.bodies;

import java.util.List;

import com.k10.runtime.renderer.Renderable;
import com.k10.runtime.renderer.vertices.Vertex;
import com.k10.runtime.world.systems.Component;

public abstract class Body<V extends Vertex> extends Component implements Renderable<V>{

	public void update(float dt) {
		
	}
}
