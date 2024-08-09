package game2;

import javax.swing.*;
import java.awt.*;


class MapGenerator {
	public int map [][];
	public int brickWidth;
	public int brickHeight;
	
	public int stoneWidth=60;
	public int stoneHeight=20;
	static int row1;
    static int col1;
	// this creates the brick of size 3x7
	public MapGenerator(int row, int col) {
		map = new int [row][col];
		for (int i = 0; i < map.length; i++) { 
			for (int j=0; j< map[0].length;j++) {
				map[i][j] = 1;
			}
		}
		brickWidth = 540/col;
		brickHeight = 150/row;
		
		//for random brick to appear (increases difficlty)
		// row1 = (int) (Math.random() * map.length);
		// col1 = (int) (Math.random() * map[0].length);
	}
	
	// this draws the bricks
	public void draw(Graphics2D g) {
		for (int i = 0; i < map.length; i++) {
			for (int j=0; j< map[0].length;j++) {
				if(map[i][j] > 0) {
					g.setColor(new Color(0XFF8787)); // brick color
					g.fillRect(j*brickWidth + 80, i*brickHeight + 50, brickWidth, brickHeight);
					
					g.setStroke(new BasicStroke(4));
					g.setColor(Color.BLACK);
					g.drawRect(j*brickWidth + 80, i*brickHeight + 50, brickWidth, brickHeight);
					
					//level 2 Stones
					g.drawRect(1*stoneWidth + 100, 1*stoneHeight + 300, stoneWidth, stoneHeight);
					g.drawRect(1*stoneWidth + 400, 1*stoneHeight + 300, stoneWidth, stoneHeight);
					g.setColor(Color.GRAY); // stone color
					g.fillRect(1*stoneWidth + 100, 1*stoneHeight + 300, stoneWidth, stoneHeight);
					g.fillRect(1*stoneWidth + 400, 1*stoneHeight + 300, stoneWidth, stoneHeight);
					
					//for random brick to appear (increases difficlty)
					//  if (i == row1 && j == col1) { // Check if it's a stone		                                    
					// 	 g.setStroke(new BasicStroke(5));
					// 		g.setColor(Color.BLACK);
		            //         g.drawRect(j*brickWidth + 80, i*brickHeight + 50, brickWidth, brickHeight);
		            //         g.setColor(Color.GRAY);
		            //         g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
					//  }					  
				}
			}			
		}	
		
		
	}
	
	// this sets the value of brick to 0 if it is hit by the ball
	public void setBrickValue(int value, int row, int col) {
		map[row][col] = value;	
			}
}