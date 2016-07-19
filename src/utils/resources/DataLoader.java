package utils.resources;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;

import game.Game;
import game.stage.Stage;

public class DataLoader extends DataHandler {

	public static void loadData() throws IOException {
		if (dataFileName.exists()) {
			Stream<String> fileLines = Files.lines(dataFileName.toPath(), StandardCharsets.UTF_8);
			Iterator<String> fileLinesIterator = fileLines.iterator();
			while (fileLinesIterator.hasNext()) {
				String fileLine = fileLinesIterator.next();
				// if line starts with a instruction header
				for (String lineheader : lineheaders) {
					if (lineheader.length() == 0) continue;
					if (fileLine.contains(lineheader)) {
						String fileLinePart = fileLine.substring(0, lineheader.length());
						if (fileLinePart.equals(lineheader)) {
							// handle instruction
							handleHeaderAndParam(lineheader, fileLine);
						}
					}
				}
			}
		} else {
			System.out.println("Data file does not exist, creating");
			new File("data").mkdir();
			dataFileName.createNewFile();
		}
	}

	private static void handleHeaderAndParam(String lineheader, String fileLine) {
		switch (lineheader) {
		case "lastunlockedstage":
			int lastUnlockedStage = Integer.valueOf(getParamValue(fileLine));
			ArrayList<Stage> stages = Game.stageManager.getStages();
			for (int i = 0; i < stages.size(); i++) {
				Stage stage = stages.get(i);
				if (stage == null) continue;
				if (i <= lastUnlockedStage) {
					stage.setLocked(false);
				}
			}
			break;
		default:
			break;
		}
	}
	
	// does nothing
	private static void nothing() {}
	
	private static String getParamValue(String line) {
		return line.split("=")[1];
	}
}
