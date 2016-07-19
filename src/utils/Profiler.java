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

package utils;

import java.awt.Color;
import java.awt.Graphics;

public class Profiler {
	
	private static int x = 0;
	private static int setX = 0;
	private static int setY = 0;
	
    public static class TimingBar {
        short timings[] = new short[3];
        
        public void setRenderTiming(short time) {
            timings[0] = time;
        }

        public void setPhysicsTiming(short time) {
            timings[1] = time;
        }

        public void setWorldTickTiming(short time) {
            timings[2] = time;
        }
    }

    public static final long scale = 1000000;
    private static final TimingBar[] timings = new TimingBar[50];
    private static final Color[] timingColours = {Color.yellow, Color.red, Color.green, Color.blue, Color.magenta, Color.orange};
    private static short currentTiming = 0;
    
    static  {
        for (short i=0; i<timings.length; i++) 
            timings[i] = new TimingBar();
    }
    
    public static TimingBar getCurrentTimings() {
        return timings[currentTiming];
    }
    
    public static void setX(int x) {
    	setX = x;
    }
    
    public static void setY(int y) {
    	setY = y;
    }
    
    public static void newFrame(TimingBar tb) {
        timings[currentTiming] = tb;
        currentTiming++;
        currentTiming %= timings.length;
    }
    
    public static void render(Graphics canvas) {
    	x = setX;
        for (short i=0; i<timings.length; i++) {
            int 
            y = setY,
            timing = 0;
            if (i>currentTiming) x++;
            
            for (short time : timings[i].timings) {
                canvas.setColor(timingColours[timing++]);
                canvas.drawLine(x, y, x, y+time);
                y += time+1;
            }
            x = setX+i;
        }
    }
}