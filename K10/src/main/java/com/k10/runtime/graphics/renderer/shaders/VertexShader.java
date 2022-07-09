package com.k10.runtime.graphics.renderer.shaders;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

import com.k10.runtime.graphics.renderer.batches.RenderBatch;
import com.k10.runtime.graphics.renderer.shaders.Shader;
import com.k10.runtime.graphics.renderer.vertices.Vertex;

public class VertexShader<V extends Vertex> extends Shader<V> {
	protected String vertexSource;

	public VertexShader(String filepath, int[] sizes) {
		super(filepath, sizes);
	}

	public VertexShader(String filepath) {
		super(filepath, new int[] { 2 });
	}

	public void start() {

	}

	protected void compileSource() {
		super.compileSource();
		ids[1] = glCreateShader(GL_VERTEX_SHADER);
		glShaderSource(ids[1], source[1]);
		glCompileShader(ids[1]);
		int success = glGetShaderi(ids[1], GL_COMPILE_STATUS);
		if (success == GL_FALSE) {
			int len = glGetShaderi(ids[1], GL_INFO_LOG_LENGTH);
			System.out.println("ERROR: '" + filepath + "'\n\tVertex shader compilation failed.");
			System.out.println(glGetShaderInfoLog(ids[1], len));
			assert false : "";
		}
	}

	public RenderBatch<V, ? extends VertexShader<V>> createRenderBatch() {
		return new RenderBatch(this);
	}

	public void uploadTexture(String varName, int slot) {
		int varLocation = glGetUniformLocation(shaderProgramID, varName);
		use();
		glUniform1i(varLocation, slot);
	}

	public void uploadIntArray(String varName, int[] array) {
		int varLocation = glGetUniformLocation(shaderProgramID, varName);
		use();
		glUniform1iv(varLocation, array);
	}

	@Override
	public boolean equals(Shader<V> o) {
		if (!(o instanceof VertexShader))
			return false;
		return o.getFilepath() == this.filepath;
	}

}