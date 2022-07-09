package com.k10.runtime.renderer.batches;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import com.google.common.collect.Lists;
import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.SetMultimap;
import com.k10.runtime.renderer.BatchedRenderer;
import com.k10.runtime.renderer.Camera;
import com.k10.runtime.renderer.shaders.VertexShader;
import com.k10.runtime.renderer.vertices.ShaderData;
import com.k10.runtime.renderer.vertices.Vertex;
import com.k10.runtime.world.systems.components.RendererComponent;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class RenderBatch<V extends Vertex, S extends VertexShader<V>> {
	protected S shader;
	protected float[] data;
	protected FloatBuffer t;
	protected SetMultimap<Integer, ShaderData> datagroups;
	protected ArrayList<RendererComponent<S>> components;
	private boolean hasRoom;
	private int vaoID, vboID;
	protected Camera camera;
	protected BatchedRenderer renderer;

	public RenderBatch(S shader) {
		this.shader = shader;
		data = new float[BatchedRenderer.DATA_CAP * 3 * shader.getDataSize()];
		components = Lists.newArrayList();
		datagroups = MultimapBuilder.hashKeys().linkedHashSetValues().build();
		hasRoom = true;
	}

	public void start(BatchedRenderer renderer) {
		this.renderer = renderer;
		vaoID = glGenVertexArrays();
		glBindVertexArray(vaoID);

		vboID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboID);
		glBufferData(GL_ARRAY_BUFFER, datagroups.values().size() * Float.BYTES * shader.getDataSize(), GL_DYNAMIC_DRAW);

		// Create and upload indices buffer
		int eboID = glGenBuffers();
		int[] indices = generateIndices();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, eboID);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices, GL_STATIC_DRAW);

		shader.enableAttribPointers();
		
	}

	private int[] generateIndices() {
		// TODO Auto-generated method stub
		return null;
	}

	public void render() {
		shader.use();
		boolean rebufferData = false;
		for (int i = 0; i < components.size(); i++) {
			RendererComponent spr = components.get(i);
			if (spr.isDirty) {
				loadDataProperties(i);
				spr.setClean();
				rebufferData = true;
			}
		}
		if (rebufferData) {
			glBindBuffer(GL_ARRAY_BUFFER, vboID);
			glBufferSubData(GL_ARRAY_BUFFER, 0, t);
		}
		this.camera = renderer.getWindow().getWorld().camera();
		shader.uploadMat4f("uProjection", camera.getProjectionMatrix());
		shader.uploadMat4f("uView", camera.getViewMatrix());
		glBindVertexArray(vaoID);
		shader.enableAttribArrays();
		glDrawElements(GL_TRIANGLES, this.datagroups.values().size(), GL_UNSIGNED_INT, 0);
		shader.disableAttribArrays();
		glBindVertexArray(0);
		shader.detach();
	}

	private void loadDataProperties(int i) {
		int index = 0;
		for (int x = 0; x < i; x++) {
			index += components.get(x).lenShaderData() * shader.getDataSize();
		}
		FloatBuffer f = t.slice(index + components.get(i).lenShaderData() * shader.getDataSize(),
				t.remaining() - components.get(i).lenShaderData() * shader.getDataSize());
		for (int x = 0; x < components.get(i).lenShaderData(); x++) {
			if (components.get(i).getShaderData()[x].isDirty) {
				t.put(index, components.get(i).getShaderData()[x].getShaderData(), 0, shader.getDataSize());
			} else {
				index += components.get(i).lenShaderData() * shader.getDataSize();
				t.position(index);
			}
		}
		t.put(f);
		System.out.println(t);
	}

	public boolean canAdd(RendererComponent r) {
		return shader.equals(r.getShader()) && BatchedRenderer.DATA_CAP > datagroups.values().size();
	}

	public void add(RendererComponent<S> r) {

	}
}
