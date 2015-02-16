package airomaniansearch;

public class Node {
	private String state;
	private Node parent;
	private Action action;
	private int pathCost;
	private int heuristic;
	
	public Node(String state, Node parent, Action action, int stepCost) {
		this.state = state;
		this.parent = parent;
		this.action = action;
		this.heuristic = 0;
		this.pathCost = parent.getPathCost() + stepCost;
	}
	public Node(String state, Node parent, Action action, int stepCost, int heuristic) {
		this.state = state;
		this.parent = parent;
		this.action = action;
		this.heuristic = heuristic;
		this.pathCost = parent.getPathCost() + stepCost;
	}
	public Node(String state, Action action, int stepCost, int heuristic) {
		this.state = state;
		this.parent = null;
		this.action = action;
		this.pathCost = 0;
		this.heuristic = heuristic;
	}
	public Node(String state){
		this(state, null, null, 0);
	}
	/*
	public Node childNode(Node parent, String action) {
		return new NodeParser();
	}
	*/
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public int getPathCost() {
		return pathCost;
	}

	public void setPathCost(int pathCost) {
		this.pathCost = pathCost;
	}
	
	public String actionsToString() {
		String actions = "";
		for(String s : action.getListOfActions()){
			actions += "\n" + s;
		}
		return actions;
	}
	public int getHeuristic() {
		return heuristic;
	}
	public void setHeuristic(int heuristic) {
		this.heuristic = heuristic;
	}

}
