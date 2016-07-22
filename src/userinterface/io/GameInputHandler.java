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

	public GameKeyboard Keyboard;
	private Scene scene;

	public void tick() {
		if (handlekeyboard) Keyboard.Tick();
	}

	public GameInputHandler(boolean keys, Scene scene) {
		this.scene = scene;
		handlekeyboard = keys;
		if (keys) {
			scene.addEventHandler(KeyEvent.KEY_PRESSED, onPressed);
			scene.addEventHandler(KeyEvent.KEY_RELEASED, onReleased);
			Keyboard = new GameKeyboard();
		}
	}

	public void setHandlerKeys(boolean keys) {
		handlekeyboard = keys;
		if (keys) {
			scene.addEventHandler(KeyEvent.KEY_PRESSED, onPressed);
			scene.addEventHandler(KeyEvent.KEY_RELEASED, onReleased);
			Keyboard = new GameKeyboard();
		}
	}

	private void toggle(KeyCode kc, boolean pressed) {
		if (handlekeyboard) {
			switch (kc) {
				case W:				Keyboard.Up.toggle(pressed); break;
				case A:				Keyboard.Left.toggle(pressed);	break;
				case S:				Keyboard.Down.toggle(pressed);	break;
				case D:				Keyboard.Right.toggle(pressed); break;
				case ESCAPE:		Keyboard.Escape.toggle(pressed);break;
				case ENTER:			Keyboard.Enter.toggle(pressed); break;
				case SPACE:			Keyboard.Space.toggle(pressed); break;
				case P:				Keyboard.P.toggle(pressed);		break;
				case X:				Keyboard.X.toggle(pressed);		break;
			}
		}
	}
	
	public void reset() {
		for (Button button : Keyboard.getKeyset()) {
			button.toggle(false);
		}
	}

	public EventHandler<KeyEvent> onPressed = new EventHandler<KeyEvent>() {
		@Override
		public void handle(KeyEvent event) {
			toggle(event.getCode(), true);
		}
	};

	public EventHandler<KeyEvent> onReleased = new EventHandler<KeyEvent>() {
		@Override
		public void handle(KeyEvent event) {
			toggle(event.getCode(), false);
		}
	};
}