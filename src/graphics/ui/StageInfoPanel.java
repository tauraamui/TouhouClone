package graphics.ui;

import game.stage.Stage;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import userinterface.Window;
import utils.Vector2;

public class StageInfoPanel {
	
	private Vector2 pos = new Vector2(Stage.Width, 0);
	private int Width;
	private int Height;
	private Stage stage;
	private Font stageTitleFont = Font.getDefault();
	private Font stageInfoTextFont = Font.getDefault();
	
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
	
	public void render(GraphicsContext graphicsContext) {
		//info panel itself
		graphicsContext.setFill(Color.BLACK);
		graphicsContext.fillRect(pos.X, pos.Y, Width, Height);

		//stage title stuff
		graphicsContext.setFill(Color.RED);
		graphicsContext.setFont(Font.getDefault());
		graphicsContext.strokeText(stage.getTitle(), pos.X+5, 15);

		//stage progress stuff
		graphicsContext.setFill(Color.WHITE);
		graphicsContext.setFont(stageInfoTextFont);
		graphicsContext.strokeText("P", pos.X+10, Height-93);
		stageProgressBar.render(graphicsContext, (int)pos.X + (int)graphicsContext.getFont().getSize()+15, Height-100);

		//health stuff
		graphicsContext.setFill(Color.WHITE);
		graphicsContext.setFont(stageInfoTextFont);
		graphicsContext.strokeText("H", pos.X+10, Height-73);
		playerHealthBar.render(graphicsContext, (int)pos.X + (int)graphicsContext.getFont().getSize()+15, Height-80);
	}
}
