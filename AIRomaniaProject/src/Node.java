import java.util.Map;

public class Node {
	private String state;
	private Node parent;
	private Action action;
	private double pathCost;
	
	public Node(String state, Node parent, Action action, double stepCost) {
		this.state = state;
		this.parent = parent;
		this.action = action;
		this.pathCost = parent.getPathCost() + stepCost;
	}
	public Node(String state, Action action, double stepCost) {
		this.state = state;
		this.parent = null;
		this.action = action;
		this.pathCost = 0;
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

	public double getPathCost() {
		return pathCost;
	}

	public void setPathCost(float pathCost) {
		this.pathCost = pathCost;
	}
	
	public String actionsToString() {
		String actions = "";
		for(String s : action.getActions()){
			actions += "\n" + s;
		}
		return actions;
	}

}
