package com.k10.runtime;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.Properties;

import com.k10.runtime.Instance;
import com.k10.runtime.events.Event;
import com.k10.runtime.events.EventSystem;
import com.k10.runtime.events.Observer;
import com.k10.runtime.windows.scenes.Scene;
import com.k10.runtime.world.systems.Actor;

public class Instance implements Observer {
	private static Instance instance = null;
	private HashSet<Scene> scenes;
//	protected Properties properties;

	private Instance() {
		scenes = new HashSet<Scene>();
		EventSystem.addObserver(this);

	}

	public void init() {
//		try {
//			properties.load(Files.newInputStream(Paths.get("config.properties", ""), StandardOpenOption.READ));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	public void initScene(Scene scene) {
		scenes.add(scene);
		scene.start();
	}

	public static Instance get() {
		if (Instance.instance == null) {
			Instance.instance = new Instance();
		}

		return Instance.instance;
	}

	@Override
	public void onNotify(Actor obj, Event event) {
		// TODO Auto-generated method stub

	}
}
