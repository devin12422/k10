package com.k10.runtime.windows.scenes;

import java.util.ArrayList;
import java.util.List;

import com.k10.runtime.graphics.Camera;
import com.k10.runtime.graphics.renderer.Renderer;
import com.k10.runtime.windows.Window;
import com.k10.runtime.world.systems.Actor;

public abstract class Scene {

	protected Renderer renderer;
	protected Window window;
	protected Camera camera;
	private boolean isRunning = false;
	protected List<Actor> gameObjects = new ArrayList<>();

	public Scene(Window window) {
		this.window = window;
	}

	public void start() {
		for (Actor go : gameObjects) {
			go.start();
		}
		isRunning = true;
	}

	public void addGameObjectToScene(Actor go) {
		if (!isRunning) {
			gameObjects.add(go);
		} else {
			gameObjects.add(go);
			go.start();
		}
	}

	public abstract void update(float dt);

	public void render() {
		if (isRunning) {
			renderer.render();
		}
	}

	public Camera camera() {
		return this.camera;
	}
}
