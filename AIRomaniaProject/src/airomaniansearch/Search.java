package airomaniansearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
		CSVParser nodeTree = new CSVParser(problem.getFile(), problem.getStartCity());
		
		root = nodeTree.parseCSV(problem.getStartCity(), null);
		
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
				Node child = nodeTree.parseCSV(s, city);
				
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
		
		PriorityQueue<Node> frontier = new PriorityQueue<Node>((Node n1, Node n2) -> (n1.getPathCost()+n1.getHeuristic()) - (n2.getPathCost()+n1.getHeuristic()));
		HashMap<Node, String> frontier_elements = new HashMap<Node, String>();
		HashSet<String> explored = new HashSet<String>();
		List<Node> solutionPath = new ArrayList<Node>();
		CSVParser nodeTree = new CSVParser(problem.getFile(), problem.getStartCity());

		root = nodeTree.parseCSV(problem.getStartCity(), null);
		
		frontier.add(root);
		frontier_elements.put(root, root.getState());
		solutionPath.add(root);
		
		if(root.getState().equals(problem.getGoal())){return solutionPath;}
		
		while(true) {
			if(frontier.isEmpty()){
				solutionPath.clear();
				return solutionPath;
			}
			Node city = frontier.poll();
			frontier_elements.remove(city);
			
			if(city.getState().equals(problem.getGoal()))
				return getPathFromGoal(city, problem);
	
			explored.add(city.getState());
			
			for(String s : city.getAction().getListOfActions()) {
				Node child = nodeTree.parseCSV(s, city);
				
				if(!(explored.contains(child.getState()) || frontier_elements.containsValue(child.getState()))) {
		
					frontier.add(child);
					frontier_elements.put(child, child.getState());
				
				}else if(frontier_elements.containsValue(child.getState())){
					
					frontier.add(child);
					frontier_elements.put(child, child.getState());
					
				}
			}
		}
	}//End Uniform Cost Search
	
	public List<Node> aStarSearch(Problem problem) throws IOException {
		
		PriorityQueue<Node> frontier = new PriorityQueue<Node>((Node n1, Node n2) -> n1.getPathCost() - n2.getPathCost());
		HashMap<Node, String> frontier_elements = new HashMap<Node, String>();
		HashSet<String> explored = new HashSet<String>();
		List<Node> solutionPath = new ArrayList<Node>();
		CSVParser nodeTree = new CSVParser(problem.getFile(), problem.getStartCity());

		root = nodeTree.parseCSV(problem.getStartCity(), null);
		
		frontier.add(root);
		frontier_elements.put(root, root.getState());
		solutionPath.add(root);
		
		if(root.getState().equals(problem.getGoal())){return solutionPath;}
		
		while(true) {
			if(frontier.isEmpty()){
				solutionPath.clear();
				return solutionPath;
			}
			
			Node city = frontier.poll();
			frontier_elements.remove(city);
			
			if(city.getState().equals(problem.getGoal()))
				return getPathFromGoal(city, problem);
	
			explored.add(city.getState());
			
			for(String s : city.getAction().getListOfActions()) {
				Node child = nodeTree.parseCSV(s, city);
				
				if(!(explored.contains(child.getState()) || frontier_elements.containsValue(child.getState()))) {
		
					frontier.add(child);
					frontier_elements.put(child, child.getState());
				
				}else if(frontier_elements.containsValue(child.getState())){
					
					frontier.add(child);
					frontier_elements.put(child, child.getState());
					
				}
			}
		}
	}//End aStarSearch
	
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
