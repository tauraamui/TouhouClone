/*
DON'T BE A DICK public static LICENSE TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION

Do whatever you like with the original work, just don't be a dick.

Being a dick includes - but is not limited to - the following instances:

1a. Outright copyright infringement - Don't just copy this and change the name.
1b. Selling the unmodified original with no work done what-so-ever, that's REALLY being a dick.
1c. Modifying the original work to contain hidden harmful content. That would make you a PROPER dick.

If you become rich through modifications, related works/services, or supporting the original work, share the love. Only a dick would make loads off this work and not buy the original work's creator(s) a pint.

Code is provided with no warranty. Using somebody else's code and bitching when it goes wrong makes you a DONKEY dick. Fix the problem yourself. A non-dick would submit the fix back.
*/

package utils;

import java.util.Random;

public class Random2 {

	private Random random = new Random();
	
	public int range(int min, int max) {
		return random.nextInt(max-min+1)+min;
	}
	
	public Random2 setSeed(long seed) {
		random.setSeed(seed);
		return this;
	}
	
	public int nextInt() {
		return random.nextInt();
	}
	
	public int nextInt(int n) {
		return random.nextInt(n);
	}
	
	public float nextFloat() {
		return random.nextFloat();
	}
	
	public float nextFloat(float n) {
		return random.nextFloat()*n;
	}
	
	public float rangef(float minX, float maxX) {
		return random.nextFloat() * (maxX - minX) + minX;
	}
	
	public float randchoice(float choiceone, float choicetwo) {
		return new float[] {choiceone, choicetwo}[random.nextInt(2)];
	}
	
	public boolean nextBoolean() {
		return random.nextBoolean();
	}
}
