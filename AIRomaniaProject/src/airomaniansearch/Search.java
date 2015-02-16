package airomaniansearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
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
		
	}//End breadthFirstSearch
	
	public List<Node> genericBFS(Problem problem, Comparator<Node> comparator) throws IOException{
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(comparator);
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
		
			int g =  city.getPathCost();
			int h =  city.getHeuristic();
			int f = g + h;
			System.out.println("City: " + city.getState() + " g(n) = " + g + " + h(n) = " + h + " = " + f);
			
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
	}//End genericBFS
	
	public List<Node> uniformCostSearch(Problem problem) throws IOException {
		return genericBFS(problem, (Node n1, Node n2) -> (n1.getPathCost() - n2.getPathCost()));
		
	}//End Uniform Cost Search
	
	
	public List<Node> aStarSearch(Problem problem) throws IOException {
		return genericBFS(problem, (Node n1, Node n2) -> (n1.getPathCost()+n1.getHeuristic()) - (n2.getPathCost() + n2.getHeuristic()));
	
	}//End aStarSearch
	
	
	public List<Node>iterativeDLS(Problem problem) throws IOException{
		int depth = 0;
		System.out.println();
		while(true) {
			Node result_node = depthLimitedSearch(problem, depth);
			if(!result_node.getResult().equals(Result.CUTOFF)){
				int result_depth = depth+1;
				System.out.println("IterativeDLS Depth = " + result_depth);
				return getPathFromGoal(result_node, problem);
			}
			depth++;
		}
	}//End IterativeDLS
	
	public List<Node> returnPathFromDLS(Problem problem, int limit) throws IOException{
		return getPathFromGoal(depthLimitedSearch(problem, limit), problem);
	
	}//End returnPathFromDLS
	
	public Node depthLimitedSearch(Problem problem, int limit) throws IOException{
		CSVParser nodeTree = new CSVParser(problem.getFile(), problem.getStartCity());
		
		return recursiveDLS(nodeTree.parseCSV(problem.getStartCity(), null), problem, limit);
	
	}//End depthLimitedSearch
	
	public Node recursiveDLS(Node node, Problem problem, int limit) throws IOException {
		boolean cutoff_occurred;
		if(node.getState().equals(problem.getGoal())){
			node.setResult(Result.SUCCESS);
			return node;
			
		}else if(limit == 0) {
			node.setResult(Result.CUTOFF); 
			return node;
		
		}else {
			cutoff_occurred = false;
			for(String s : node.getAction().getListOfActions()) {
				CSVParser nodeTree = new CSVParser(problem.getFile(), problem.getStartCity());
				Node child = nodeTree.parseCSV(s, node);
				Node result_node = recursiveDLS(child, problem, limit-1);
			
				if(result_node.getResult().equals(Result.CUTOFF)){
					cutoff_occurred = true;
				
				}else if(!result_node.equals(Result.FAILURE)) {
					return result_node;
				}
			}
			if(cutoff_occurred) {
				node.setResult(Result.CUTOFF); 
				return node;
			}else {
				node.setResult(Result.FAILURE);
				return node;
			}
		}
	
	}//End recursiveDLS
	
	
	public List<Node> getPathFromGoal(Node n, Problem problem) {
		List<Node> solutionPath = new LinkedList<Node>();
		Node currentNode = n;
		while(!currentNode.getState().equals(problem.getStartCity())) {
			solutionPath.add(0, currentNode);
			currentNode = currentNode.getParent();
			
		}
		solutionPath.add(0, currentNode);
		return solutionPath;
		
	}//End getPathFromGoal
}
