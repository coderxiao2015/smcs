package com.tianwen.common.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public enum ThreadPool {
	
	threadPool;

	private ExecutorService exec = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2 + 1);
	
	public ExecutorService getInstance(){
		return exec;
	}
}
