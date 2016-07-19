package graphics.ui;

import game.stage.Stage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import utils.Vector2;

public class StageInfoPanel {
	
	private Vector2 pos = new Vector2(Stage.Width, 0);
	private int Width;
	private int Height;
	private Stage stage;
	private Font stageTitleFont = new Font("Calibri", Font.BOLD, 12);
	private Font stageInfoTextFont = new Font("Calibri", Font.PLAIN, 12);
	
	private PercentageBar playerHealthBar = new PercentageBar();
	private PercentageBar stageProgressBar = new PercentageBar();

	public StageInfoPanel(Stage stage) {
		this.stage = stage;
		Width = Window.Width-Stage.Width;
		Height = Stage.Height;
		stageProgressBar.setPercentage(0);
	}
	
	public void update() {
		playerHealthBar.setPercentage(stage.player.healthManager.getHealthPercentage());
		stageProgressBar.setPercentage(stage.getStageProgress());
	}
	
	public void render(Graphics canvas) {
		//info panel itself
		canvas.setColor(Color.BLACK);
		canvas.fillRect((int)pos.X, (int)pos.Y, Width, Height);
		
		//stage title stuff
		canvas.setColor(Color.RED);
		canvas.setFont(stageTitleFont);
		canvas.drawString(stage.getTitle(), (int)pos.X+5, 15);
		
		//stage progress stuff
		canvas.setColor(Color.white);
		canvas.setFont(stageInfoTextFont);
		canvas.drawString("P", (int)pos.X+10, Height-93);
		stageProgressBar.render(canvas, (int)pos.X+canvas.getFontMetrics().stringWidth("P")+15, Height-100);
		
		//health stuff
		canvas.setColor(Color.WHITE);
		canvas.setFont(stageInfoTextFont);
		canvas.drawString("H", (int)pos.X+10, Height-73);
		playerHealthBar.render(canvas, (int)pos.X+canvas.getFontMetrics().stringWidth("H")+15, Height-80);
	}
}
