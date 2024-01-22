package com.nortexdev;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) {
		// run every 5 seconds
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		// wstępny delay 0, co 5 sekund
		executor.scheduleAtFixedRate(new DirectoryChecker(), 0, 5, TimeUnit.SECONDS);
	}
}
