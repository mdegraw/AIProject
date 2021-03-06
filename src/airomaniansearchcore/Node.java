package airomaniansearchcore;

import java.io.IOException;

import parser.CSVParser;

public class Node {
	private String state;
	private Node parent;
	private Action action;
	private Result result;
	private int pathCost;
	private int heuristic;
	private int fCost;
	private int fLimit;
	
	public Node(String state, Action action, int pathCost, int heuristic){
		this.state = state;
		this.action = action;
		this.pathCost = pathCost;
		this.heuristic = heuristic;
		this.fLimit = 0;
		this.result = Result.INITIAL;
	}
	
	
	public Node(String state, Node parent, Action action, int stepCost, int heuristic) {
		this(state, action, parent.getPathCost() + stepCost, heuristic);
		this.parent = parent;
		//this.pathCost = parent.getPathCost() + stepCost;
		this.fCost = this.pathCost + this.heuristic;
	
	}
	public Node(String state, Action action, int heuristic) {
		this(state, action, 0, heuristic);
		this.parent = null;
		this.fCost = heuristic;
		this.pathCost = 0;
	
	}

	
	public Node childNode(Node parent, String action, Problem problem) throws IOException {
		CSVParser nodeTree = new CSVParser(problem.getFile(), problem.getStartCity());
		
		return nodeTree.parseCSV(action, parent);
	}
	
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
	
	public int getfCost() {
		return this.fCost;
	}
	
	public void setfCost(int fCost) {
		this.fCost = fCost;
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
	
	public int getfLimit() {
		return fLimit;
	}
	
	public void setfLimit(int fLimit) {
		this.fLimit = fLimit;
	}
	
	public int getHeuristic() {
		return heuristic;
	}
	
	public void setHeuristic(int heuristic) {
		this.heuristic = heuristic;
	}
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}

}
