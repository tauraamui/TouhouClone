/*
DON'T BE A DICK PUBLIC LICENSE TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION

Do whatever you like with the original work, just don't be a dick.

Being a dick includes - but is not limited to - the following instances:

1a. Outright copyright infringement - Don't just copy this and change the name.
1b. Selling the unmodified original with no work done what-so-ever, that's REALLY being a dick.
1c. Modifying the original work to contain hidden harmful content. That would make you a PROPER dick.

If you become rich through modifications, related works/services, or supporting the original work, share the love. Only a dick would make loads off this work and not buy the original work's creator(s) a pint.

Code is provided with no warranty. Using somebody else's code and bitching when it goes wrong makes you a DONKEY dick. Fix the problem yourself. A non-dick would submit the fix back.
*/

package userinterface.io;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GameInputHandler {

	private boolean handlekeyboard;

	private Scene scene;

	public GameInputHandler(Scene scene) {
		this.scene = scene;
	}


	public void setOnKeyPressedEventHandler(EventHandler<KeyEvent> keyEventHandler) {
		this.scene.setOnKeyPressed(keyEventHandler);
	}

	public void setOnKeyReleasedEventHandler(EventHandler<KeyEvent> keyEventHandler) {
		this.scene.setOnKeyReleased(keyEventHandler);
	}
}