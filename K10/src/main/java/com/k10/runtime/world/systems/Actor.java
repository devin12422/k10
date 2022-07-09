package com.k10.runtime.world.systems;

import java.util.ArrayList;
import java.util.List;

import com.k10.runtime.world.systems.Component;
import com.k10.runtime.world.systems.components.Transform;

public class Actor {
	private static int ID_COUNTER = 0;
	private int uid = -1;
	private int z;
	private String name;
	private List<Component> components;
	public Transform transform;

	public Actor(String name, int z) {
		this.name = name;
		this.z = z;
		this.components = new ArrayList<>();
		this.transform = new Transform();
		this.uid = ID_COUNTER++;
	}
	
	public int getZIndex() {
		return this.z;
	}
	public <T extends Component> T getComponent(Class<T> componentClass) {
		for (Component c : components) {
			if (componentClass.isAssignableFrom(c.getClass())) {
				try {
					return componentClass.cast(c);
				} catch (ClassCastException e) {
					e.printStackTrace();
					assert false : "Error: Casting component.";
				}
			}
		}

		return null;
	}

	public <T extends Component> void removeComponent(Class<T> componentClass) {
		for (int i = 0; i < components.size(); i++) {
			Component c = components.get(i);
			if (componentClass.isAssignableFrom(c.getClass())) {
				components.remove(i);
				return;
			}
		}
	}

	public void addComponent(Component c) {
		this.components.add(c);
		c.gameObject = this;
	}

	public void update(float dt) {
		for (int i = 0; i < components.size(); i++) {
			components.get(i).update(dt);
		}
	}

	public void start() {
		for (int i = 0; i < components.size(); i++) {
			components.get(i).start();
		}
	}
}