/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

// imports necessary libraries for Java swing
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
    public void run() {
        // NOTE : recall that the 'final' keyword notes immutability even for local variables.

        // Top-level frame in which game components live
        // Be sure to change "TOP LEVEL FRAME" to the name of your game
        final JFrame frame = new JFrame("Brick Breaker");
        frame.setLocation(500, 200);

        final JFrame instruction_window = new JFrame("Instructions");
        instruction_window.setLocation(500, 200);
        
        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Playing...");
        status_panel.add(status);

        // Main playing area
        final GameCourt court = new GameCourt(status);
        frame.add(court, BorderLayout.CENTER);

        // Reset button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);
        
        final JPanel instruction_panel = new JPanel();
        instruction_window.add(instruction_panel, BorderLayout.NORTH);
        
        // Note here that when we add an action listener to the reset button, we define it as an
        // anonymous inner class that is an instance of ActionListener with its actionPerformed()
        // method overridden. When the button is pressed, actionPerformed() will be called.
        final JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                court.reset();
            }
        });
        control_panel.add(reset);
        
        final JButton pause = new JButton("Pause");
        pause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                court.pause();
            }
        });
        control_panel.add(pause);
        
        final JButton play_1 = new JButton("Play");
        play_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                court.play();
            }
        });
        control_panel.add(play_1);
        
        final JButton start_over = new JButton("Start Over");
        start_over.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                court.startOver();
            }
        });
        control_panel.add(start_over);
        
        
        final JButton play = new JButton("Play Game");
        play.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 
                 frame.pack();
                 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                 frame.setVisible(true);
                 
                 instruction_window.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));;
                 court.reset();
            }
        });
        instruction_panel.add(play);
        
        final InstructionCourt incourt = new InstructionCourt(status);
        instruction_window.add(incourt, BorderLayout.CENTER);
        // Put the frame on the screen
        

        instruction_window.pack();
        //instruction_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        instruction_window.setVisible(true);
        // Start game
        incourt.reset();
       
       
        
    }

    /**
     * Main method run to start and run the game. Initializes the GUI elements specified in Game and
     * runs it. IMPORTANT: Do NOT delete! You MUST include this in your final submission.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}