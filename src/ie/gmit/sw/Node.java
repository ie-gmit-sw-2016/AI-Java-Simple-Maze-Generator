package ie.gmit.sw;

public class Node {
	public enum NodeType{Wall, Passage, Prisoner, Sword, Food};
	public enum NodePassage{North, West, None};
	private NodeType type = NodeType.Wall;
	private NodePassage passage = NodePassage.None;
	public boolean visited =  false;
	public boolean goal;

	public NodeType getType() {
		return type;
	}
	
	public void setType(NodeType type) {
		this.type = type;
	}

	public NodePassage getPassage() {
		return passage;
	}

	public void setPassage(NodePassage passage) {
		this.passage = passage;
	}

	public String toString() {
		if (passage == NodePassage.North){
			return "N ";
		}else{
			return "W ";
		}
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public boolean isGoalNode() {
		return goal;
	}

	public void setGoalNode(boolean goal) {
		this.goal = goal;
	}
}
