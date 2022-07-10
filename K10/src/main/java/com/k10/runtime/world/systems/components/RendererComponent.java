package com.k10.runtime.world.systems.components;

import java.util.List;

import com.k10.runtime.renderer.Renderable;
import com.k10.runtime.renderer.shaders.Shader;
import com.k10.runtime.renderer.shaders.VertexShader;
import com.k10.runtime.renderer.vertices.ShaderData;
import com.k10.runtime.renderer.vertices.Vertex;
import com.k10.runtime.world.systems.Component;
import com.k10.util.AssetPool;

/**
 * 
 * @author Devin
 *
 * @param <S>
 */
public class RendererComponent extends Component {
	private Renderable target;
	private Shader shader;
	public boolean isDirty;
	public RendererComponent(Renderable target, Shader shader) {
		this.shader = shader;
		this.target = target;
		this.isDirty = true;
	}

	public void setClean() {
		this.isDirty = false;
	}

	public int lenShaderData() {
		return target.lenShaderData();
	}

	public ShaderData[] getShaderData() {
		return target.getRenderData();
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub

	}

	public Shader getShader() {
		return this.shader;
	}

	public int getZIndex() {
		return gameObject.getZIndex();
	}

}
