package graphics.ui.menu;

import com.sun.javafx.tk.FontMetrics;
import com.sun.javafx.tk.Toolkit;
import game.Game;
import game.stage.Stage;
import game.stage.StageManager;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import userinterface.Window;

import java.util.ArrayList;

public class StageMenu {

	private StageManager stageManager;

	public boolean open = false;
	private int selectionIndex = 0;
	private Font stageTitleFont = Font.font("Calibri", 20);
	private FontMetrics fontMetrics;
	private Stage[] stages = new Stage[]{};
	private ArrayList<Stage> stageList = new ArrayList<Stage>();
	private long lastStageListIndexSwitch = 0;
	private int startIndex = 0;
	private int listTitleHeightSpacing = 35;
	private int listHeight = 0;


	public StageMenu(StageManager stageManager) {

		fontMetrics = Toolkit.getToolkit().getFontLoader().getFontMetrics(stageTitleFont);

		this.stageManager = stageManager;
		stages = new Stage[stageManager.getStages().size()];
		for (int i = 0; i < stages.length; i++) {
			stages[i] = stageManager.getStages().get(i);
		}
		lastStageListIndexSwitch = System.currentTimeMillis();
		populateListToRender();
	}

	public void update() {

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

	private EventHandler<KeyEvent> keyEventHandler = new EventHandler<KeyEvent>() {
		@Override
		public void handle(KeyEvent event) {
			if (event.getCode() == KeyCode.S || event.getCode() == KeyCode.DOWN) incListIndex();
			if (event.getCode() == KeyCode.W || event.getCode() == KeyCode.UP) decListIndex();
			if (event.getCode() == KeyCode.ENTER) {
				if (stages[selectionIndex].isUnlocked()) {
					System.out.println("loading "+stages[selectionIndex].getTitle());
					stageManager.setStage(selectionIndex);
					stageManager.startCurrentStage();
				} else {
					System.out.println(stages[selectionIndex].getTitle()+" (is locked)");
				}
			}
		}
	};

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

		graphicsContext.setFill(Color.BLACK);
		graphicsContext.fillRect(0, 0, graphicsContext.getCanvas().getWidth(), graphicsContext.getCanvas().getHeight());

		graphicsContext.setFont(stageTitleFont);

		int selectionBoxX = 0;
		int selectionBoxY = 0;
		int titleWidth = (int)fontMetrics.computeStringWidth(stages[selectionIndex].getTitle());

		graphicsContext.setFill(Color.BLUE);
		if (stages[selectionIndex].isLocked()) graphicsContext.setFill(Color.RED);

		selectionBoxX = Window.Width/2-((int)fontMetrics.computeStringWidth(stages[selectionIndex].getTitle())/2);
		selectionBoxY = 36+((int)fontMetrics.getLineHeight()*(selectionIndex-1)+1);

		graphicsContext.fillRect(selectionBoxX-5, selectionBoxY+2, titleWidth+10, fontMetrics.getLineHeight()+2);
		graphicsContext.setFill(Color.WHITE);


		for (int i = 0; i < stageList.size(); i++) {
			Stage stage = stageList.get(i);
			graphicsContext.fillText(stage.getTitle(), Window.Width/2-(fontMetrics.computeStringWidth(stage.getTitle())/2), listTitleHeightSpacing+(fontMetrics.getLineHeight()*i));
		}
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

	private void checkKeyEventHandler() {
		if (isOpen()) {
			Game.input.setOnKeyPressedEventHandler(keyEventHandler);
		} else {
			Game.input.setOnKeyPressedEventHandler(null);
		}
	}



	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
		checkKeyEventHandler();
	}


}
