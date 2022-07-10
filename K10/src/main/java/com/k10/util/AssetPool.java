package com.k10.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.k10.runtime.renderer.Texture;
import com.k10.runtime.renderer.shaders.Shader;
import com.k10.runtime.renderer.shaders.VertexShader;
import com.k10.runtime.renderer.vertices.Vertex;
import com.k10.util.AssetPool;

public class AssetPool {
	private static Map<String, VertexShader> shaders = new HashMap<>();
	private static Map<String, Texture> textures = new HashMap<>();

	public static VertexShader getShader(String resourceName, int[] params) {
		File file = new File(resourceName);
		if (AssetPool.shaders.containsKey(file.getAbsolutePath())) {
			return AssetPool.shaders.get(file.getAbsolutePath());
		} else {
			VertexShader shader = new VertexShader(resourceName, params);
			shader.compile();
			AssetPool.shaders.put(file.getAbsolutePath(), shader);
			return shader;
		}
	}

	public static Texture getTexture(String resourceName) {
		File file = new File(resourceName);
		if (AssetPool.textures.containsKey(file.getAbsolutePath())) {
			return AssetPool.textures.get(file.getAbsolutePath());
		} else {
			Texture texture = new Texture();
			texture.init(resourceName);
			AssetPool.textures.put(file.getAbsolutePath(), texture);
			return texture;
		}
	}
}