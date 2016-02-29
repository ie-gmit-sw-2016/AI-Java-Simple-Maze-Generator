package ie.gmit.sw;

import java.awt.*;
import javax.swing.*;
import ie.gmit.sw.Node.NodePassage;
public class MazeRunner {
	private static final int MAZE_WIDTH = 40;
	private Node[][] maze;
	private MazeView mv;
	private boolean keepRunning = true;
	
	//Stats for measuring goodness of algorithm
	private int visitCount = 0;
	private long time;
	private int depth;
	
	public static void main(String[] args) {
		new MazeRunner();
	}
	
	public MazeRunner(){
		maze = new MazeGenerator(MAZE_WIDTH, MAZE_WIDTH).getMaze();
    	mv = new MazeView(maze);
    	
    	Dimension d = new Dimension(MazeView.DEFAULT_VIEW_SIZE, MazeView.DEFAULT_VIEW_SIZE);
    	mv.setPreferredSize(d);
    	mv.setMinimumSize(d);
    	mv.setMaximumSize(d);
    	
    	JFrame f = new JFrame("B.Sc. in Computing (Software Development)");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(mv);
        f.setSize(1000,1000);
        f.setLocation(100,100);
        f.pack();
        f.setVisible(true);
        
        //Start the clock
        long start = System.currentTimeMillis();
        
        dfs(maze[0][0], 0, 0); //Recursive DFS
        
        //Stop the clock
        time = System.currentTimeMillis() - start;
        
        //Output stats
        System.out.println("Visited " + visitCount + " nodes in " + time + "ms.");
        System.out.println("Found goal at a depth of " + depth);
        System.out.println("EBF = B* = k^(1/d) = " + String.format("%.2f", Math.pow((double)visitCount, 1/(double)depth)));    
	}
	
	
	private void dfs(Node next, int row, int col){
		if (next.isVisited() || !keepRunning) return; //Pop stack, by returning gracefully. Heap-based approach can skip this step.
		
		//Mark node as visited
		next.setVisited(true);
		mv.repaint();

		//Pre-process stats
		depth++;
		visitCount++;
		
		if (next.isGoalNode()) keepRunning = false; //Found goal node. Bail out

		
		try { //Simulate processing each expanded node
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		

		//Get the child nodes
		if (row - 1 >= 0 && next.getPassage() == NodePassage.North) dfs(maze[row - 1][col], row - 1, col);
		if (col - 1 >=0 && next.getPassage() == NodePassage.West) dfs(maze[row][col - 1], row, col - 1);
		if (row + 1 < maze.length && maze[row + 1][col].getPassage() == NodePassage.North) dfs(maze[row + 1][col], row + 1, col);
		if (col + 1 < maze[row].length && maze[row][col + 1].getPassage() == NodePassage.West) dfs(maze[row][col + 1], row, col + 1);
		
		//Post-process stats
		if (keepRunning) depth--;
	}
}
