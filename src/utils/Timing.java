package utils;

import game.Game;

public class Timing {
	
	private static double time = 0L;
	
	public static void update() {
		if (Game.isNotPaused()) {
			time += 1;
		}
	}
	
	public static long getCurrentTimeSeconds() {
		return (long)time/100;
	}
	
	public static long getCurrentTimeMillis() {
		return System.currentTimeMillis();
//		return (long)time*10;
	}
}
