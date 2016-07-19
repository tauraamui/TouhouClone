package graphics.ui.menu;

import com.sun.javafx.tk.FontMetrics;
import game.Game;
import game.stage.Stage;
import game.stage.StageManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import userinterface.Window;

import java.util.ArrayList;

public class StageMenu {

	private StageManager stageManager;
	private int selectionIndex = 0;
	private Font stageTitleFont = Font.font("Calibri");
	private FontMetrics fontMetrics;
	private Stage[] stages = new Stage[]{};
	private ArrayList<Stage> stageList = new ArrayList<Stage>();
	private long lastStageListIndexSwitch = 0;
	private int startIndex = 0;
	private int listTitleHeightSpacing = 30;
	private int listHeight = 0;


	public StageMenu(StageManager stageManager) {
		this.stageManager = stageManager;
		stages = new Stage[stageManager.getStages().size()];
		for (int i = 0; i < stages.length; i++) {
			stages[i] = stageManager.getStages().get(i);
		}
		lastStageListIndexSwitch = System.currentTimeMillis();
		populateListToRender();
	}

	public void update() {
		/*
		if (Game.gameinput.Keyboard.Down.Clicked) incListIndex();
		if (Game.gameinput.Keyboard.Up.Clicked) decListIndex();
		*/
		checkSelectionIndexBounds();
		populateListToRender();

		/*
		if (Game.gameinput.Keyboard.Enter.Clicked) {
			if (stages[selectionIndex].isUnlocked()) {
				stageManager.setStage(selectionIndex);
				stageManager.stageMenuOpen = false;
			} else {
				System.out.println(stages[selectionIndex].getTitle()+" (is locked)");
			}
		}
		*/
	}

	private void checkSelectionIndexBounds() {
		if (selectionIndex < startIndex) { if (startIndex == 0) selectionIndex = startIndex; }
		if (selectionIndex >= stageList.size()) selectionIndex = stageList.size()-1;
//		if (selectionIndex >= stages.length) selectionIndex = 0;
//		else if (selectionIndex < 0) selectionIndex = stages.length-1;
		lastStageListIndexSwitch = System.currentTimeMillis();
	}

	private void incListIndex() {
		selectionIndex++;
		populateListToRender();
		lastStageListIndexSwitch = System.currentTimeMillis();
	}

	private void decListIndex() {
		selectionIndex--;
		populateListToRender();
		lastStageListIndexSwitch = System.currentTimeMillis();
	}

	public void render(GraphicsContext graphicsContext) {

		graphicsContext.setFill(Color.DARKGREY);
		graphicsContext.fillRect(0, 0, graphicsContext.getCanvas().getWidth(), graphicsContext.getCanvas().getHeight());

		graphicsContext.setFont(stageTitleFont);
		/*
		fontMetrics = graphicsContext.getFontMetrics();
		int selectionBoxX = 0;
		int selectionBoxY = 0;
		int titleWidth = fontMetrics.stringWidth(stages[selectionIndex].getTitle());

		graphicsContext.setFill(Color.BLUE);
		if (stages[selectionIndex].isLocked()) graphicsContext.setColor(Color.RED);

		selectionBoxX = Window.Width/2-(fontMetrics.stringWidth(stages[selectionIndex].getTitle())/2);
		selectionBoxY = 30+(fontMetrics.getHeight()*(selectionIndex-1)+1);

		graphicsContext.fillRect(selectionBoxX-5, selectionBoxY+2, titleWidth+10, fontMetrics.getHeight()+2);
		graphicsContext.setColor(Color.WHITE);
		*/

//		for (int i = startListRender; i < endListRender; i++) {
//			gc.drawString(stages[i].getTitle(), Window.Width/2-(fontMetrics.stringWidth(stages[i].getTitle())/2), listTitleHeightSpacing+(fontMetrics.getHeight()*i));
//		}

		for (int i = 0; i < stageList.size(); i++) {
			Stage stage = stageList.get(i);
			//graphicsContext.drawString(stage.getTitle(), Window.Width/2-(fontMetrics.stringWidth(stage.getTitle())/2), listTitleHeightSpacing+(fontMetrics.getHeight()*i));
//			System.out.println(30+(fontMetrics.getHeight()*i));
		}

//		for (int i = 0; i < stages.length; i++) {
//			gc.drawString(stages[i].getTitle(), Window.Width/2-(fontMetrics.stringWidth(stages[i].getTitle())/2), listTitleHeightSpacing+(fontMetrics.getHeight()*i));
//			System.out.println(30+(fontMetrics.getHeight()*i));
//		}
	}
	
	public void populateListToRender() {
		stageList.clear();
		int stageListEnd = stages.length;
		int heightSize = listTitleHeightSpacing;
		listHeight = 0;
		if (fontMetrics != null) {
			for (int i = startIndex; i < stages.length; i++) {
				heightSize += 10;
				if (heightSize >= Window.Height-30) {
					stageListEnd = i;
					break;
				}
			}
			listHeight = heightSize;
		}
		for (int i = 0; i < stageListEnd; i++) {
			stageList.add(stages[i]);
		}
	}
	
	private int getMaxListElements() {
		
		if (fontMetrics == null) return 0;
		
		int total = 0;
		int heightTotal = listTitleHeightSpacing;
		int miny = 20;
		int maxy = Window.Height - miny;
		int fontHeight = 10;
		int totalToAdd = (fontHeight + listTitleHeightSpacing);
		
		while (true) {
			if (heightTotal >= maxy) break;
			heightTotal += fontHeight;
			total += 1;
		}
		return total;
	}
}
