package com.k10.runtime.renderer.shaders;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

import com.k10.runtime.renderer.batches.RenderBatch;
import com.k10.runtime.renderer.vertices.Vertex;

public class VertexShader extends Shader {

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

	@Override
	public boolean equals(Shader o) {
		if (!(o instanceof VertexShader))
			return false;
		return o.getFilepath() == this.filepath;
	}

}