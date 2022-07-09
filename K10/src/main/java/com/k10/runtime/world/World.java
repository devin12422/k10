package com.k10.runtime.world;

import java.util.ArrayList;
import java.util.List;

import com.k10.runtime.renderer.Camera;
import com.k10.runtime.renderer.Renderer;
import com.k10.runtime.windows.Window;
import com.k10.runtime.world.systems.Actor;

public abstract class World {
	protected Renderer renderer;
	protected Camera camera;
	private boolean isRunning = false;
	protected List<Actor> gameObjects = new ArrayList<>();

	public World() {
	}
	public void setRenderer(Renderer renderer) {
		this.renderer = renderer;
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

	public Camera camera() {
		return this.camera;
	}
}
