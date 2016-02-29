package ie.gmit.sw;

import java.util.*;
public class MazeGenerator {
	private Node[][] maze;

	public MazeGenerator(int rows, int cols) {
		maze = new Node[rows][cols];
		generateMaze();
		setGoalNode();
	}
	
	//Binary tree algorithm for creating a maze. Adds a bias into the generated structure
	//For each node in the maze (2D array), randomly create a passage either north or west, but not both
	private void generateMaze(){
		for (int row = 0; row < maze.length; row++){
			for (int col = 0; col < maze[row].length; col++){
				maze[row][col] = new Node();
				int num = (int) (Math.random() * 10);
				if (col > 0 && (row == 0 || num >= 5)){
					maze[row][col].setPassage(Node.NodePassage.West);
				}else{
					maze[row][col].setPassage(Node.NodePassage.North);
					
				}
			}
		}
	}
	
	//Pick a goal node
	public void setGoalNode(){
		Random generator = new Random();
		int randRow = generator.nextInt(maze.length);
		int randCol = generator.nextInt(maze[0].length);
		maze[randRow][randCol].setGoalNode(true);
	}
	
	public Node[][] getMaze(){
		return maze;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		for (int row = 0; row < maze.length; row++){
			for (int col = 0; col < maze[row].length; col++){
				sb.append(maze[row][col]);
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
