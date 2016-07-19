package game;

import game.stage.Stage;
import game.stage.StageManager;
import graphics.Renderer;

import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.canvas.GraphicsContext;
import utils.Profiler;
import utils.Profiler.TimingBar;
import utils.Timing;

public class Game extends TimerTask implements Runnable {
	
	public static float DELTATIME = 0.16F;
	public static StageManager stageManager = new StageManager();
	//public static GameInputHandler gameinput = new GameInputHandler(true, Renderer);
	public static Renderer Renderer = new Renderer();
	//	private static long tStart;
//	private long lastTime = tStart = System.nanoTime();
	private double unprocessed = 0;
	private float targetFPS = 60F;
	//	private double nsPerTick = 1000000000.0 / currentFPS;
//	private static float frames = 0;
//	private static float ticks = 0;
	private static long lastTimer = System.currentTimeMillis();
	private static boolean shouldRender = false;
	private static long now;
	public static boolean debugMode = false;
	private static boolean paused = false;
	private static boolean running = true;
	private static boolean gameOver = false;
	private static int tps = 0;
	private static int fps = 0;

	public Game() {
		startgame();
	}
	
	public void startgame() {
		new Timer().scheduleAtFixedRate(this, 0, 16);
	}
	
	@Override
	public void run() {
		Timing.update();
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / targetFPS;
		int frames = 0;
		int ticks = 0;
		long lastTimer = System.currentTimeMillis();
		float deltaTime = 0;
		
		while (running) {
			long now = System.nanoTime();
			deltaTime += (now - lastTime) / nsPerTick;
			Game.DELTATIME = deltaTime;
			lastTime = now;
			boolean shouldRender = false;
			
			while (deltaTime >= 1) {
				ticks++;
				//TODO: Need to replace old input method
				//gameinput.tick();
				stageManager.update(deltaTime);
				deltaTime -= 1;
				shouldRender = true;
			}
			
			if (shouldRender) {
				frames++;
				if (debugMode) {
					Stage currentStage = stageManager.getCurrentStage();
					TimingBar timingBar = new TimingBar();
					timingBar.setRenderTiming((short)(Renderer.totalRenderTime/Profiler.scale));
					if (currentStage != null) timingBar.setWorldTickTiming((short)(currentStage.totalTickTime/Profiler.scale));
					Profiler.newFrame(timingBar);
				}
				Renderer.render();
			}
			
			try { Thread.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
			
			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				tps = frames;
				fps = ticks;
				frames = 0;
				ticks = 0;
			}
		}
	}
	
	public static boolean isPaused() {
		return paused;
	}
	
	public static boolean isNotPaused() {
		return !paused;
	}
	
	public static void setPaused(boolean paused) {
		Game.paused = paused;
	}
	
	public static void togglePaused() {
		paused = !paused;
	}
	
	public static boolean isGameOver() {
		return gameOver;
	}
	
	public static boolean isNotGameOver() {
		return !gameOver;
	}
	
	public static void setGameOver(boolean gameOver) {
		Game.gameOver = gameOver;
	}
	
	public static void render(GraphicsContext canvas) {
		stageManager.render(canvas);
		if (Game.debugMode) {
			Profiler.render(canvas);
		}
//		stageMenu.render(canvas);
	}
	
	public static void quit() {
		System.exit(0);
	}
}
