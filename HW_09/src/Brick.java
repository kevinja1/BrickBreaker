

import java.awt.*;

/**
 * A basic game object starting in the upper left corner of the game court. It is displayed as a
 * square of a specified color.
 */
public class Brick extends GameObj {
    public static final int HEIGHT = 40;
    public static final int WIDTH = 50;
    //public static final int INIT_POS_X = 0;
    //public static final int INIT_POS_Y = 0;
    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 0;

    public Color color;
    public int hits;

    /**
    * Note that, because we don't need to do anything special when constructing a Square, we simply
    * use the superclass constructor called with the correct parameters.
    */
    public Brick(int courtWidth, int courtHeight, Color color, int init_x, int init_y) {
        super(INIT_VEL_X, INIT_VEL_Y, init_x, init_y, WIDTH, HEIGHT, courtWidth, courtHeight);
        
        this.color = color;
        if(color == Color.yellow){
        	hits = 1;
        } else if(color == Color.red){
        	hits = 2;
        } else if(color == Color.blue){
        	hits = 3;
        } else if(color == Color.gray){
        	hits = 4;
        } else if(color == Color.orange){
        	hits = 5;
        } else if(color == Color.green){
        	hits = 6;
        }
        
    }

    @Override
    public void draw(Graphics g) {
        if(hits <= 0){
        	
        } else{
        	g.setColor(this.color);
        	g.fillRect(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
        }
        
    }
    
    public void lowerValue() {
    	if(color == Color.yellow){
        	color = Color.black;
        } else if(color == Color.red){
        	color = Color.yellow;
        } else if(color == Color.blue){
        	color = Color.red;
        } else if(color == Color.gray){
        	color = Color.blue;
        } else if(color == Color.orange){
        	color = Color.gray;
        } else if(color == Color.green){
        	color = Color.orange;
        }
    	
    	if(color == Color.black){
        	hits = 0;
        }
        else if(color == Color.yellow){
        	hits = 1;
        } else if(color == Color.red){
        	hits = 2;
        } else if(color == Color.blue){
        	hits = 3;
        } else if(color == Color.gray){
        	hits = 4;
        } else if(color == Color.orange){
        	hits = 5;
        } else if(color == Color.green){
        	hits = 6;
        }
        
    }
  
    /*
    @Override
    public boolean intersects(GameObj that) {
    	if(this.px + this.width >= that.px
            && this.py + this.height >= that.py
            && that.px + that.width >= this.px 
            && that.py + that.height >= this.py){
    		hits--;
    	}
    	return false;
    }*/
}