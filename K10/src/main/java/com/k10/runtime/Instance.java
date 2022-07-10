package com.k10.runtime;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;

import com.google.common.collect.Sets;
import com.k10.runtime.Instance;
import com.k10.runtime.windows.Window;
import com.k10.runtime.world.World;
import com.k10.runtime.world.systems.Actor;
import com.k10.runtime.world.systems.events.Event;
import com.k10.runtime.world.systems.events.EventSystem;
import com.k10.runtime.world.systems.events.Observer;

public abstract class Instance implements Observer {

	private static Instance instance = null;
	private HashSet<Window> windows;

	public Instance() {
		windows = new HashSet<Window>();
		EventSystem.addObserver(this);
	}

	protected void setInstance(Instance i) {
		if (instance != null) {
			instance.destroy();
		}
		instance = i;
	}

	public void init() {
		setInstance(this);
	}

	public void addWindow(Window window) {
		windows.add(window);
	}

	@Override
	public void onNotify(Actor obj, Event event) {
		// TODO Auto-generated method stub

	}

	public static Instance getInstance() {
		return instance;
	}

	public void destroy() {
		for (Window w : windows) {
			w.destroy();
		}
	}
}
