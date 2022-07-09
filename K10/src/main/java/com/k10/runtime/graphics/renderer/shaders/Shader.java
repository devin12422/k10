package com.k10.runtime.graphics.renderer.shaders;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_INFO_LOG_LENGTH;
import static org.lwjgl.opengl.GL20.GL_LINK_STATUS;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glGetProgramInfoLog;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;
import static org.lwjgl.opengl.GL20.glGetShaderi;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glShaderSource;
import static org.lwjgl.opengl.GL20.glUniform1f;
import static org.lwjgl.opengl.GL20.glUniform1i;
import static org.lwjgl.opengl.GL20.glUniform2f;
import static org.lwjgl.opengl.GL20.glUniform3f;
import static org.lwjgl.opengl.GL20.glUniform4f;
import static org.lwjgl.opengl.GL20.glUniformMatrix3fv;
import static org.lwjgl.opengl.GL20.glUniformMatrix4fv;
import static org.lwjgl.opengl.GL20.glUseProgram;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.BufferUtils;

import com.k10.runtime.graphics.renderer.batches.RenderBatch;
import com.k10.runtime.graphics.renderer.shaders.Shader;
import com.k10.runtime.graphics.renderer.vertices.ShaderData;

public abstract class Shader<S extends ShaderData> {
	protected int shaderProgramID;
	protected boolean beingUsed = false;
	protected String[] source;
	protected int[] ids;
	protected String filepath;
	protected int[] sizes;
	protected int dataSize;

	public Shader(String filepath, int[] sizes) {
		this.filepath = filepath;
		this.sizes = sizes;
		for (int i : sizes) {
			dataSize += i;
		}
		try {
			String source = new String(Files.readAllBytes(Paths.get(filepath)));
			this.source = source.split("(#type)( )+([a-zA-Z]+)");
//			int index = source.indexOf("#type") + 6;
//			int eol = source.indexOf("\r\n", index);
//			String firstPattern = source.substring(index, eol).trim();
//			index = source.indexOf("#type", eol) + 6;
//			eol = source.indexOf("\r\n", index);
//			String secondPattern = source.substring(index, eol).trim();
		} catch (IOException e) {
			e.printStackTrace();
			assert false : "Error: Could not open file for shader: '" + filepath + "'";
		}
	}

	public boolean equals(Shader<S> o) {
		return o.getFilepath() == this.filepath;
//		public boolean equals(Object obj) {
//		    if (obj instanceof Integer) {
//		        return value == ((Integer)obj).intValue();
//		    }
//		    return false;
//		}
	}

	public String getFilepath() {
		return this.filepath;
	}
	public int getDataSize() {
		return this.dataSize;
	}
	public abstract void start();

	protected void compileSource() {
		ids[0] = glCreateShader(GL_FRAGMENT_SHADER);
		glShaderSource(ids[0], source[0]);
		glCompileShader(ids[0]);
		int success = glGetShaderi(ids[0], GL_COMPILE_STATUS);
		if (success == GL_FALSE) {
			int len = glGetShaderi(ids[0], GL_INFO_LOG_LENGTH);
			System.out.println("ERROR: '" + filepath + "'\n\tFragment shader compilation failed.");
			System.out.println(glGetShaderInfoLog(ids[0], len));
			assert false : "";
		}
	}

	public void compile() {
		compileSource();
		shaderProgramID = glCreateProgram();
		for (int i : ids) {
			glAttachShader(shaderProgramID, i);
		}
		glLinkProgram(shaderProgramID);
		int success = glGetProgrami(shaderProgramID, GL_LINK_STATUS);
		if (success == GL_FALSE) {
			int len = glGetProgrami(shaderProgramID, GL_INFO_LOG_LENGTH);
			System.out.println("ERROR: '" + filepath + "'\n\tLinking of shaders failed.");
			System.out.println(glGetProgramInfoLog(shaderProgramID, len));
			assert false : "";
		}
	}

	public void use() {
		if (!beingUsed) {
			glUseProgram(shaderProgramID);
			beingUsed = true;
		}
	}

	public void detach() {
		glUseProgram(0);
		beingUsed = false;
	}

	public void uploadMat4f(String varName, Matrix4f mat4) {
		int varLocation = glGetUniformLocation(shaderProgramID, varName);
		use();
		FloatBuffer matBuffer = BufferUtils.createFloatBuffer(16);
		mat4.get(matBuffer);
		glUniformMatrix4fv(varLocation, false, matBuffer);
	}

	public void uploadMat3f(String varName, Matrix3f mat3) {
		int varLocation = glGetUniformLocation(shaderProgramID, varName);
		use();
		FloatBuffer matBuffer = BufferUtils.createFloatBuffer(9);
		mat3.get(matBuffer);
		glUniformMatrix3fv(varLocation, false, matBuffer);
	}

	public void uploadVec4f(String varName, Vector4f vec) {
		int varLocation = glGetUniformLocation(shaderProgramID, varName);
		use();
		glUniform4f(varLocation, vec.x, vec.y, vec.z, vec.w);
	}

	public void uploadVec3f(String varName, Vector3f vec) {
		int varLocation = glGetUniformLocation(shaderProgramID, varName);
		use();
		glUniform3f(varLocation, vec.x, vec.y, vec.z);
	}

	public void uploadVec2f(String varName, Vector2f vec) {
		int varLocation = glGetUniformLocation(shaderProgramID, varName);
		use();
		glUniform2f(varLocation, vec.x, vec.y);
	}

	public void uploadFloat(String varName, float val) {
		int varLocation = glGetUniformLocation(shaderProgramID, varName);
		use();
		glUniform1f(varLocation, val);
	}

	public void uploadInt(String varName, int val) {
		int varLocation = glGetUniformLocation(shaderProgramID, varName);
		use();
		glUniform1i(varLocation, val);
	}

	public void enableAttribPointers() {
		int offset = 0;
		for (int i = 0; i < sizes.length; i++) {
			glVertexAttribPointer(i, sizes[i], GL_FLOAT, false, dataSize * Float.BYTES, offset);
			glEnableVertexAttribArray(i);
			offset += sizes[i];
		}
	}

	public void enableAttribArrays() {
		for (int i = 0; i < sizes.length; i++) {
			glEnableVertexAttribArray(i);
		}
	}
	public void disableAttribArrays() {
		for (int i = 0; i < sizes.length; i++) {
			glDisableVertexAttribArray(i);
		}
	}
}
