package com.k10.launcher;

import com.k10.runtime.TestInstance;
import com.k10.runtime.renderer.BatchedRenderer;
import com.k10.runtime.renderer.Renderer;
import com.k10.runtime.windows.GlfwWindow;

public class TestLauncher extends Launcher {
	public TestLauncher() {

	}
	@Override
	public void launch() {
		instance = new TestInstance();
		instance.init();
		Renderer r = new BatchedRenderer();
		instance.addWindow(new GlfwWindow(1920, 1080, "test", r));
//		instance.initScene(new );

	}

}
