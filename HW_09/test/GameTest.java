import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.Color;

import org.junit.Test;

/** 
 *  You can use this file (and others) to test your
 *  implementation.
 */

public class GameTest {
  
    @Test
	public void testYellowBrick() {
    	Brick brick = new Brick(0, 0, Color.yellow, 0, 0);
		assertEquals(1, brick.hits);
	}
    
    @Test
	public void testRedBrick() {
    	Brick brick = new Brick(0, 0, Color.red, 0, 0);
		assertEquals(2, brick.hits);
	}
    
    @Test
	public void testBlueBrick() {
    	Brick brick = new Brick(0, 0, Color.blue, 0, 0);
		assertEquals(3, brick.hits);
	}
    
    @Test
	public void testGrayBrick() {
    	Brick brick = new Brick(0, 0, Color.gray, 0, 0);
		assertEquals(4, brick.hits);
	}
    
    @Test
	public void testOrangeBrick() {
    	Brick brick = new Brick(0, 0, Color.orange, 0, 0);
		assertEquals(5, brick.hits);
	}
    
    @Test
	public void testGreenBrick() {
    	Brick brick = new Brick(0, 0, Color.green, 0, 0);
		assertEquals(6, brick.hits);
	}
    
    @Test
	public void testHitYellowBrick() {
    	Brick brick = new Brick(0, 0, Color.yellow, 0, 0);
    	brick.lowerValue();
		assertEquals(0, brick.hits);
	}
    
    @Test
	public void testHitRedBrick() {
    	Brick brick = new Brick(0, 0, Color.red, 0, 0);
    	brick.lowerValue();
		assertEquals(1, brick.hits);
	}
    
    @Test
	public void testHitBlueBrick() {
    	Brick brick = new Brick(0, 0, Color.blue, 0, 0);
    	brick.lowerValue();
		assertEquals(2, brick.hits);
	}
    
    @Test
	public void testHitGrayBrick() {
    	Brick brick = new Brick(0, 0, Color.gray, 0, 0);
    	brick.lowerValue();
		assertEquals(3, brick.hits);
	}
    
    @Test
	public void testHitOrangeBrick() {
    	Brick brick = new Brick(0, 0, Color.orange, 0, 0);
    	brick.lowerValue();
		assertEquals(4, brick.hits);
	}
    
    @Test
	public void testHitGreenBrick() {
    	Brick brick = new Brick(0, 0, Color.green, 0, 0);
    	brick.lowerValue();
		assertEquals(5, brick.hits);
	}
    
    @Test
   	public void testEliminateBrick() {
       	Brick brick = new Brick(0, 0, Color.yellow, 0, 0);
       	brick.lowerValue();
   		assertEquals(Color.black, brick.color);
   	}
    
    @Test
   	public void testHitBrickMultipleTimes() {
       	Brick brick = new Brick(0, 0, Color.yellow, 0, 0);
       	brick.lowerValue();
       	brick.lowerValue();
   		assertEquals(Color.black, brick.color);
   	}
    

}
