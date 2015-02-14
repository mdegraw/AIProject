package airomaniansearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Search {
	private Node root;
	
	public List<Node> breadthFirstSearch(Problem problem) throws IOException {
		LinkedList<Node> frontier = new LinkedList<Node>();
		HashSet<String> explored = new HashSet<String>();
		List<Node> solutionPath = new ArrayList<Node>();
		NodeParser nodeTree = new NodeParser(problem.getFile(), problem.getStartCity());
		root = nodeTree.generateRoot(problem.getStartCity());
		
		frontier.add(root);
		solutionPath.add(root);
		
		if(root.getState().equals(problem.getGoal())){return solutionPath;}
		
		while(true) {
			if(frontier.isEmpty()){
				solutionPath.clear();
				return solutionPath;
			}
			Node city = frontier.pop();
			explored.add(city.getState());
			
			for(String s : city.getAction().getListOfActions()) {
				Node child = nodeTree.generateNode(s, city);
				
				if(!(explored.contains(child.getState()) || frontier.contains(child))) {
					if(child.getState().equals(problem.getGoal())){ 
						return getPathFromGoal(child, problem);
					}
					
					frontier.add(child);
				}
			}
		}
		
	}
	/*public List<Node> depthFirstSearch() {
		
	}*/
	public List<Node> uniformCostSearch(Problem problem) throws IOException {
		PriorityQueue<Node> frontier = new PriorityQueue<Node>((Node n1, Node n2) -> Math.min(n1.getPathCost(), n2.getPathCost()));
		HashSet<String> explored = new HashSet<String>();
		List<Node> solutionPath = new ArrayList<Node>();
		NodeParser nodeTree = new NodeParser(problem.getFile(), problem.getStartCity());
		root = nodeTree.generateRoot(problem.getStartCity());
		
		frontier.add(root);
		solutionPath.add(root);
		
		if(root.getState().equals(problem.getGoal())){return solutionPath;}
		
		while(true) {
			if(frontier.isEmpty()){
				solutionPath.clear();
				return solutionPath;
			}
			Node city = frontier.poll();
			if(city.getState().equals(problem.getGoal()))
				return getPathFromGoal(city, problem);
			explored.add(city.getState());
			
			for(String s : city.getAction().getListOfActions()) {
				Node child = nodeTree.generateNode(s, city);
				
				if(!(explored.contains(child.getState()) || frontier.contains(child))) {
				
					frontier.add(child);
				
				}else if(frontier.contains(child)) {
					System.out.println("Sheet");
				}
			}
		}
	}
	
	//public N
	
	
	public List<Node> getPathFromGoal(Node n, Problem problem) {
		List<Node> solutionPath = new LinkedList<Node>();
		Node currentNode = n;
		while(!currentNode.getState().equals(problem.getStartCity())) {
			solutionPath.add(0, currentNode);
			currentNode = currentNode.getParent();
			
		}
		solutionPath.add(0, currentNode);
		return solutionPath;
		
	}
}
