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

import game.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;

public class GameInputHandler implements KeyListener {

	private boolean handlekeyboard;

	public GameKeyboard Keyboard;
	private JComponent componentToHandle;

	public void tick() {
		if (handlekeyboard) Keyboard.Tick();
	}

	public GameInputHandler(boolean keys, JComponent game) {
		componentToHandle = game;
		handlekeyboard = keys;
		if (keys) {
			game.addKeyListener(this);
			Keyboard = new GameKeyboard();
		}
	}
	
	public void setHandlerKeys(boolean keys) {
		handlekeyboard = keys;
		if (keys) {
			componentToHandle.addKeyListener(this);
			Keyboard = new GameKeyboard();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent ke) {
		toggle(ke.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		toggle(ke.getKeyCode(), false);
	}

	@Override
	public void keyTyped(KeyEvent ke) {}

	private void toggle(int ke, boolean pressed) {
		if (handlekeyboard) {
			switch (ke) {
				case KeyEvent.VK_W:			Keyboard.Up.toggle(pressed);	break;
				case KeyEvent.VK_A: 		Keyboard.Left.toggle(pressed); 	break;
				case KeyEvent.VK_S:			Keyboard.Down.toggle(pressed);	break;
				case KeyEvent.VK_D: 		Keyboard.Right.toggle(pressed); break;
				case KeyEvent.VK_LEFT: 		Keyboard.Left.toggle(pressed); 	break;
				case KeyEvent.VK_UP:		Keyboard.Up.toggle(pressed);	break;
				case KeyEvent.VK_DOWN:		Keyboard.Down.toggle(pressed);	break;
				case KeyEvent.VK_RIGHT: 	Keyboard.Right.toggle(pressed); break;
				case KeyEvent.VK_ESCAPE:	Keyboard.Escape.toggle(pressed);break;
				case KeyEvent.VK_ENTER:		Keyboard.Enter.toggle(pressed); break;
				case KeyEvent.VK_SPACE:		Keyboard.Space.toggle(pressed);	break;
				case KeyEvent.VK_P:			Keyboard.P.toggle(pressed);		break;
				case KeyEvent.VK_X:			Keyboard.X.toggle(pressed);		break;
			}
		}
	}
	
	public void reset() {
		for (Button button : Keyboard.getKeyset()) {
			button.toggle(false);
		}
	}
}