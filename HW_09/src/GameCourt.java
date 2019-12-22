/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

import java.awt.*;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
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
public class GameCourt extends JPanel {

    // the state of the game logic
    private Paddle square; // the Black Square, keyboard control
    private Circle snitch; // the Golden Snitch, bounces
    private Instructions instruction;
    private FileReader fr; 
    private BufferedReader br;
    private FileWriter fw; 
    private BufferedWriter bw;
    
    //private Brick brick;
    private Brick[][] bricksetup;
    private int lives;
    
   
    private int numPlayers = 0;
    
    //TreeMap that stores a player number to their highest score.
    private Map<String, Integer> scores = new TreeMap<>();

    public boolean playing = false; // whether the game is running 
    public boolean instructions_on = false;
    private JLabel status; // Current status text, i.e. "Running..."

    // Game constants
    public static final int COURT_WIDTH = 500;
    public static final int COURT_HEIGHT = 500;
    public static final int SQUARE_VELOCITY = 15;

    // Update interval for timer, in milliseconds
    public static final int INTERVAL = 35;

    public GameCourt(JLabel status) {
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
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    square.setVx(-SQUARE_VELOCITY);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    square.setVx(SQUARE_VELOCITY);
                }
            }

            public void keyReleased(KeyEvent e) {
                square.setVx(0);
                //square.setVy(0);
            }
        });

        this.status = status;
    }

    /**
     * (Re-)set the game to its initial state.
     */
    public void reset() {
    	
	    numPlayers++;
    	lives = 3;
        square = new Paddle(COURT_WIDTH, COURT_HEIGHT, Color.BLACK);
        //poison = new Poison(COURT_WIDTH, COURT_HEIGHT);
        snitch = new Circle(COURT_WIDTH, COURT_HEIGHT, Color.BLACK);
        //brick = new Brick(COURT_WIDTH, COURT_HEIGHT, Color.RED);
        String line = null;
        try {
			fr = new FileReader("files/game_state.txt");
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException();
		} catch (NullPointerException e){
			throw new IllegalArgumentException();
		}
	    br = new BufferedReader(fr); 
	    
	    String[] hitvals = new String[50];
	    
	    try {
	    	//System.out.println(br.readLine());
			line = br.readLine();
			System.out.println(line);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
		if(!(line == null)){
			hitvals = line.split(" ");
		}
	   
	    
	    
        Color[] colors = {Color.YELLOW, Color.RED, Color.BLUE, Color.GRAY, Color.ORANGE, Color.GREEN};
        int color = 0;
        bricksetup = new Brick[10][5];
   
        if(line == null){
	    	for(int i = 0; i<10; i++){
	        	for(int j = 0; j<5; j++){
	            	bricksetup[i][j]=new Brick(COURT_WIDTH, COURT_HEIGHT, colors[color%6], 50*i, 40*j);
	            	color++;
	            }
	        }
	    } else{
	    	
	    	for (int i = 0; i < 50; i++){
	    		Integer key = Integer.parseInt(hitvals[i]);
	    		int a = i/5;
	    		int b = i%5;
	    		if(key <= 0){
        			key = 1;
        			bricksetup[a][b]= new Brick(COURT_WIDTH, COURT_HEIGHT, colors[key - 1], 50*a, 40*b);
        			bricksetup[a][b].lowerValue();
        		} else{
        			bricksetup[a][b]= new Brick(COURT_WIDTH, COURT_HEIGHT, colors[key - 1], 50*a, 40*b);
        		}
	    		
	    	}
	    }
        
     
        
        //instructions_on = true;
        playing = true;
        instruction = new Instructions(COURT_WIDTH, COURT_HEIGHT);
    	
        
        status.setText("Lives Left: " + lives);

        // Make sure that this component has the keyboard focus
        requestFocusInWindow();
    }
    
public void startOver() {
    	
	    numPlayers++;
    	lives = 3;
        square = new Paddle(COURT_WIDTH, COURT_HEIGHT, Color.BLACK);
        //poison = new Poison(COURT_WIDTH, COURT_HEIGHT);
        snitch = new Circle(COURT_WIDTH, COURT_HEIGHT, Color.BLACK);
        //brick = new Brick(COURT_WIDTH, COURT_HEIGHT, Color.RED);
        
	    
	   
	    
	   
	   
	    
	    
        Color[] colors = {Color.YELLOW, Color.RED, Color.BLUE, Color.GRAY, Color.ORANGE, Color.GREEN};
        int color = 0;
        bricksetup = new Brick[10][5];
   
        if(true){
	    	for(int i = 0; i<10; i++){
	        	for(int j = 0; j<5; j++){
	            	bricksetup[i][j]=new Brick(COURT_WIDTH, COURT_HEIGHT, colors[color%6], 50*i, 40*j);
	            	color++;
	            }
	        }
	    } 
        
     
        
        //instructions_on = true;
        playing = true;
        instruction = new Instructions(COURT_WIDTH, COURT_HEIGHT);
    	
        
        status.setText("Lives Left: " + lives);

        // Make sure that this component has the keyboard focus
        requestFocusInWindow();
    }

    public void pause(){
    	//snitch.setVx(0);
    	//snitch.setVy(0);
    	playing = false;
    	try {
			fw = new FileWriter("files/game_state.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    bw = new BufferedWriter(fw); 
	    for(int i = 0; i<10; i++){
        	for(int j = 0; j<5; j++){
            	try {
					bw.write(bricksetup[i][j].hits + " ");
					//System.out.println("poo");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }
	    try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void play(){
    	/*try {
			fr = new FileReader("files/game_state.txt");
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException();
		} catch (NullPointerException e){
			throw new IllegalArgumentException();
		}
	    br = new BufferedReader(fr); 
	    
	    String[] hitvals = br.readLine().split(" ");
	    for(int i = 0; i<10; i++){
        	for(int j = 0; j<5; j++){
            	try {
					bw.write(bricksetup[i][j].hits + " ");
					//System.out.println("poo");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }*/
    	//snitch.setVx(5);
    	//snitch.setVy(5);
    	requestFocusInWindow();
    	playing = true;
    	//setFocusable(true);
    }

    /**
     * This method is called every time the timer defined in the constructor triggers.
     */
    void tick() {
        if (playing) {
            // advance the square and snitch in their current direction.
    
            square.move();
            snitch.move();
            snitch.bounce(snitch.hitObj(square));
            
            if(snitch.lostLife()){
            	lives--;
            	status.setText("Lives Left: " + lives);
            	snitch = new Circle(COURT_WIDTH, COURT_HEIGHT, Color.BLACK);
            	try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            
            // make the snitch bounce off walls...
            snitch.bounce(snitch.hitWall());
            //snitch.bounce(snitch.hitObj(bricksetup));
           
           
            
            for(int i = 0; i<10; i++){
            	for(int j = 0; j<5; j++){
                	Brick that = bricksetup[i][j];
                	if(snitch.hitObj(that)!=null){
                		if (that.hits > 0){
                			snitch.bounce(snitch.hitObj(that));
                		}
                		that.lowerValue();
                	}
                	
                }
            }
            
            boolean done = false;
            
            for(int i = 0; i<10; i++){
            	for(int j = 0; j<5; j++){
                	Brick that = bricksetup[i][j];
                	if(that.hits != 0){
                		done = false;
                		break;
                	}
                	done = true;
                }
   
            }
            
            
            
            if(lives == 0){
            	playing = false;
                status.setText("Sorry you lost, Your ID is Player " + numPlayers + ". Try Again!");
                scores.put("Player " + numPlayers, 0);
                //System.out.println(scores.toString());
                try {
        			fw = new FileWriter("files/game_state.txt");
        		} catch (IOException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
        	    bw = new BufferedWriter(fw); 
        	    for(int i = 0; i<10; i++){
                	for(int j = 0; j<5; j++){
                    	try {
        					bw.write("");
        					//System.out.println("poo");
        				} catch (IOException e) {
        					// TODO Auto-generated catch block
        					e.printStackTrace();
        				}
                    }
                }
        	    try {
        			bw.close();
        		} catch (IOException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
        	    
        	    try {
        			fw = new FileWriter("files/scores.txt");
        		} catch (IOException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
        	    bw = new BufferedWriter(fw); 
        	 
                try {
        			bw.write(scores.toString());
        					//System.out.println("poo");
        		} catch (IOException e) {
        					// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
                   
        	    try {
        			bw.close();
        		} catch (IOException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
            }
            
            if(done){
            	playing = false;
                status.setText("You win! Your ID is Player " + numPlayers);
                if(scores.get("Player " + numPlayers) < lives){
                	scores.put("Player " + numPlayers, lives);
                }
                //System.out.println(scores.toString());
                try {
        			fw = new FileWriter("files/game_state.txt");
        		} catch (IOException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
        	    bw = new BufferedWriter(fw); 
        	    for(int i = 0; i<10; i++){
                	for(int j = 0; j<5; j++){
                    	try {
        					bw.write("");
        					//System.out.println("poo");
        				} catch (IOException e) {
        					// TODO Auto-generated catch block
        					e.printStackTrace();
        				}
                    }
                }
        	    try {
        			bw.close();
        		} catch (IOException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
        	    try {
        			fw = new FileWriter("files/scores.txt");
        		} catch (IOException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
        	    
        	    bw = new BufferedWriter(fw); 
        	 
                try {
        			bw.write(scores.toString());
        					//System.out.println("poo");
        		} catch (IOException e) {
        					// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
                   
        	    try {
        			bw.close();
        		} catch (IOException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
            }
            
            
            // check for the game end conditions
            /*if (square.intersects(snitch)) {
                //playing = false;
                //status.setText("You win!");
            }*/
            
            //playing = false;
            //status.setText("" + bricksetup[4][4].hits);

            // update the display
           
        }
        repaint(); 
    }

    @Override
    public void paintComponent(Graphics g) {
    	if(instructions_on){
    		instruction.draw(g);
    	} else{
    		super.paintComponent(g);
            square.draw(g);
            //poison.draw(g);
            snitch.draw(g);
            //instruction.draw(g);
            for(int i = 0; i<10; i++){
            	for(int j = 0; j<5; j++){
                	if(bricksetup[i][j].hits > 0){
                		bricksetup[i][j].draw(g);
                	}
                }
            }
    	}
        
    }
    

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
}