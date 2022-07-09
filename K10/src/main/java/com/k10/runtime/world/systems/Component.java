package com.k10.runtime.world.systems;

import com.k10.runtime.world.systems.Actor;

public abstract class Component {
	private static int ID_COUNTER = 0;
	private int uid = -1;
	public transient Actor gameObject = null;

	public void start() {

	}

	public void update(float dt) {
		if (gameObject == null) {
			System.out.println("component not bound");
			return;
		}
	}

	public void generateId() {
		if (this.uid == -1) {
			this.uid = ID_COUNTER++;
		}
	}
	public int getUid() {
        return this.uid;
    }

    public static void init(int maxId) {
        ID_COUNTER = maxId;
    }	
}
