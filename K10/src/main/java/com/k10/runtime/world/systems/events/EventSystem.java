package com.k10.runtime.world.systems.events;

import java.util.ArrayList;
import java.util.List;

import com.k10.runtime.world.systems.Actor;
import com.k10.runtime.world.systems.events.Observer;

public class EventSystem {
	private static List<Observer> observers = new ArrayList<>();

	public static void addObserver(Observer observer) {
		observers.add(observer);
	}

	public static void notify(Actor obj, Event event) {
		for (Observer observer : observers) {
			observer.onNotify(obj, event);
		}
	}
}
