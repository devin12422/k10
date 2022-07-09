package com.k10.runtime.world.systems.events;

import com.k10.runtime.world.systems.Actor;

public interface Observer {
	void onNotify(Actor obj, Event event);
}
