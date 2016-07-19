package utils.resources;

import java.io.File;

public abstract class DataHandler {
	protected static final File dataFileName = new File("data/snapshot.dat");
	protected static final String lineheaders[] = {"lastunlockedstage"};
}
