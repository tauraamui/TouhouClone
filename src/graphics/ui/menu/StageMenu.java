package graphics.ui.menu;

import game.Game;
import game.stage.Stage;
import game.stage.StageManager;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;

public class StageMenu {
	
	private StageManager stageManager;
	private int selectionIndex = 0;
	private Font stageTitleFont = new Font("Calabri", Font.PLAIN, 12);
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
		if (Game.gameinput.Keyboard.Down.Clicked) incListIndex();
		if (Game.gameinput.Keyboard.Up.Clicked) decListIndex();
		
		checkSelectionIndexBounds();
		populateListToRender();
		
		if (Game.gameinput.Keyboard.Enter.Clicked) {
			if (stages[selectionIndex].isUnlocked()) {
				stageManager.setStage(selectionIndex);
				stageManager.stageMenuOpen = false;
			} else {
				System.out.println(stages[selectionIndex].getTitle()+" (is locked)");
			}
		}
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
	
	public void render(Graphics canvas) {
		canvas.setColor(Color.DARK_GRAY);
		canvas.fillRect(0, 0, Window.Width, Window.Height);
		
		canvas.setFont(stageTitleFont);
		fontMetrics = canvas.getFontMetrics();
		
		int selectionBoxX = 0;
		int selectionBoxY = 0;
		int titleWidth = fontMetrics.stringWidth(stages[selectionIndex].getTitle());
		
		canvas.setColor(Color.BLUE);
		if (stages[selectionIndex].isLocked()) canvas.setColor(Color.RED);
		
		selectionBoxX = Window.Width/2-(fontMetrics.stringWidth(stages[selectionIndex].getTitle())/2);
		selectionBoxY = 30+(fontMetrics.getHeight()*(selectionIndex-1)+1);
		
		canvas.fillRect(selectionBoxX-5, selectionBoxY+2, titleWidth+10, fontMetrics.getHeight()+2);
		canvas.setColor(Color.WHITE);
		
//		for (int i = startListRender; i < endListRender; i++) {
//			canvas.drawString(stages[i].getTitle(), Window.Width/2-(fontMetrics.stringWidth(stages[i].getTitle())/2), listTitleHeightSpacing+(fontMetrics.getHeight()*i));
//		}
		
		for (int i = 0; i < stageList.size(); i++) {
			Stage stage = stageList.get(i);
			canvas.drawString(stage.getTitle(), Window.Width/2-(fontMetrics.stringWidth(stage.getTitle())/2), listTitleHeightSpacing+(fontMetrics.getHeight()*i));
//			System.out.println(30+(fontMetrics.getHeight()*i));
		}
		
//		for (int i = 0; i < stages.length; i++) {
//			canvas.drawString(stages[i].getTitle(), Window.Width/2-(fontMetrics.stringWidth(stages[i].getTitle())/2), listTitleHeightSpacing+(fontMetrics.getHeight()*i));
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
				heightSize += fontMetrics.getHeight();
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
		int fontHeight = stageTitleFont.getSize();
		int totalToAdd = (fontHeight + listTitleHeightSpacing);
		
		while (true) {
			if (heightTotal >= maxy) break;
			heightTotal += fontHeight;
			total += 1;
		}
		return total;
	}
}
