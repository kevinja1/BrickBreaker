/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

/**
 * GameCourt
 * 
 * This class holds the primary game logic for how different objects interact with one another. Take
 * time to understand how the timer interacts with the different methods and how it repaints the GUI
 * on every tick().
 */
@SuppressWarnings("serial")
public class InstructionCourt extends JPanel {

   
    private Instructions instruction;
    

    // Game constants
    public static final int COURT_WIDTH = 500;
    public static final int COURT_HEIGHT = 500;
    public static final int SQUARE_VELOCITY = 15;

    // Update interval for timer, in milliseconds
    public static final int INTERVAL = 35;

    public InstructionCourt(JLabel status) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // The timer is an object which triggers an action periodically with the given INTERVAL. We
        // register an ActionListener with this timer, whose actionPerformed() method is called each
        // time the timer triggers. We define a helper method called tick() that actually does
        // everything that should be done in a single timestep.
        Timer timer = new Timer(INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                	tick();
            }
        });
        timer.start(); // MAKE SURE TO START THE TIMER!
      
        // Enable keyboard focus on the court area.
        // When this component has the keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        // This key listener allows the square to move as long as an arrow key is pressed, by
        // changing the square's velocity accordingly. (The tick method below actually moves the
        // square.)
    }

    /**
     * (Re-)set the game to its initial state.
     */
    public void reset() {
    	
    	instruction = new Instructions(COURT_WIDTH, COURT_HEIGHT);
        // Make sure that this component has the keyboard focus
        requestFocusInWindow();
    }

    /**
     * This method is called every time the timer defined in the constructor triggers.
     */
    void tick() {
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
    		instruction.draw(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
}