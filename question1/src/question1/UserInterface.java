package question1;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//timing stuff for reference
//http://www.java-gaming.org/index.php?topic=24220.0
//going to use VARIABLE timesteps

public class UserInterface extends JPanel implements Runnable{
	
	//main frame
	static JFrame frame;
	
	//frame size and block size
	static final Dimension frameSize = new Dimension(1020,760);
	static final Dimension blockSize = new Dimension(20,20);
	
	//grid # of blocks 
	static final int gridHeight = frameSize.height/blockSize.height;
	static final int gridWidth = frameSize.width/blockSize.width;
	
	//stores the block color 0= black 1=white
	static int[][] blockColor = new int[gridWidth][gridHeight];
	
	//blockdelay from 1-1938. delay is calculated later
	static int[][] blockDelay = new int[gridWidth][gridHeight];
	
	//counter array to compare to blockDelay
	static int[][] blockCounter = new int[gridWidth][gridHeight];
	
	//debugging array to track how many times a block has changed
	static int[][] timesChanged = new int[gridWidth][gridHeight];
	
	
	//---TIMING STUFF---
	
	//setting the refresh rate to 60 hz (roughly)
	static final int TARGET_FPS = 60;
	static final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;   
	
	//keeps track of the last FPS (HZ)
	private long lastFpsTime;
	
	//tracks # of frames recorded
	private int fps;
    
	public UserInterface(){
		//initialize blocks array to all 0 (black)
		//and initialize blockFreq array to freq values
		//initialize counter array
		//initially update all blocks
		int counter=2;
		for (int j=0;j<gridHeight;j++)
    	{	
			for (int i=0;i<gridWidth;i++)
	    	{
	    		blockColor[i][j]=0;
	    		blockDelay[i][j]=counter;
	    		blockCounter[i][j]=0;
	    		
	    		//debugging
	    		timesChanged[i][j]=0;
	    		counter++;
        	}
    	}
		
		this.setPreferredSize(frameSize);
		//start thread
		Thread thread = new Thread(this);
		thread.start();
	}
	
    //main "game" loop run all the code and stuff :)
	public void run() {
		
		long lastLoopTime = System.nanoTime();
		
		while (true){
			// work out how long its been since the last update
		    long now = System.nanoTime();
		    long updateLength = now - lastLoopTime;
		    lastLoopTime = now;
	
		    // update the frame counter
		    lastFpsTime += updateLength;
		    fps++;
		      
		    // update our FPS counter if a second has passed since we last recorded
		    if (lastFpsTime >= 1000000000)
		    {
		    	frame.setTitle("Question 1--(FPS: "+fps+")");
		        lastFpsTime = 0;
		        fps = 0;
		    }
		    
		    //update blockColorArray
		    updateColor();
		    
		    //update jpanel drawing
		    repaint();
		    
		    //debug print how much things changes
		    //printArray(this.timesChanged);
		    
		    // we want each frame to take 10 milliseconds, to do this
		    // we've recorded when we started the frame. We add 10 milliseconds
		    // to this and then factor in the current time to give 
		    // us our final value to wait for
		    // remember this is in ms, whereas our lastLoopTime etc. vars are in ns.
		    try{
		    	Thread.sleep( (lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000 );
		    }catch(Exception e){
		    	e.printStackTrace();
		    }  
		}
	}
	
	//update graphics
    public void paintComponent(Graphics g) {
    	int startX=0;
    	int startY=0;
    	
    	for (int j=0;j<gridHeight;j++)
    	{	
			for (int i=0;i<gridWidth;i++)
	    	{
	        		Color newColor = (blockColor[i][j]==0) ? Color.BLACK : Color.WHITE;
	        		g.setColor(newColor);
	        		g.fillRect(startX, startY, blockSize.width,blockSize.height);
        		startX+=blockSize.width;
        	}
    		startY+=blockSize.height;
    		startX=0;
    	}
    }
	
	//update the counter and color array
	public void updateColor(){
		for (int j=0;j<gridHeight;j++)
    	{	
			for (int i=0;i<gridWidth;i++)
	    	{
        		if(blockCounter[i][j] >= blockDelay[i][j])
        		{
        			//reset counter
        			blockCounter[i][j]=0;
        			//toggle color
        			blockColor[i][j] ^=1;
        			
        			//increment timesChanged
        			timesChanged[i][j]++;
        		}
        		else
        		{
        			blockCounter[i][j]++;
        		}
        	}
    	}
	}

	public void createImage(){
		BufferedImage bi = new BufferedImage(frameSize.width, frameSize.height, BufferedImage.TYPE_INT_ARGB); 
		Graphics g = bi.createGraphics();
		this.paint(g);  //this == jpanel
		g.dispose();
		try{ImageIO.write(bi,"png",new File("savedImages//test.png"));}catch (Exception e) {}
	}
	
	//debugging functions
	public void printArray(int[][] array){
		for (int i=0;i<array[0].length;i++){
			for (int j=0;j<array.length;j++){
				System.out.print(array[j][i]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	//main
	public static void main(String[] args) {
		frame = new JFrame();
		Container container = frame.getContentPane();
		container.add(new UserInterface());
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
