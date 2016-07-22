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

import java.util.ArrayList;
import java.util.List;

public class GameKeyboard {
	
	public boolean Swallow, AnyKey;
	public int KeysDown;
	public Button
	Left = new Button(),
	Right = new Button(),
	Up = new Button(),
	Down = new Button(),
	Escape = new Button(),
	Enter = new Button(),
	Space = new Button(),
	P = new Button(),
	X = new Button();
	
	private List<Button> keyset = new ArrayList<Button>();
	
	public GameKeyboard() {
		keyset.add(Left);
		keyset.add(Right);
		keyset.add(Up);
		keyset.add(Down);
		keyset.add(Escape);
		keyset.add(Space);
		keyset.add(Enter);
		keyset.add(P);
		keyset.add(X);
	}
	
	public List<Button> getKeyset() {
		return keyset;
	}
	
	public void Tick() {
		KeysDown = 0;
		if (!Swallow) for (int i=0; i < keyset.size(); i++) {
			keyset.get(i).tick();
			if (keyset.get(i).isDown) KeysDown++;
		}
		if (KeysDown > 0) AnyKey = true; else AnyKey = false;
		
		if (Escape.Clicked) {
			Game.stageManager.openStageMenu();
		}
		
		if (P.Clicked) {
			Game.togglePaused();
		}
		
		if (X.Clicked) {
			Game.debugMode = !Game.debugMode;
		}
	}
}
