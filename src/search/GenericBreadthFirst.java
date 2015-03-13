package search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

import parser.CSVParser;
import airomaniansearchcore.Node;
import airomaniansearchcore.Problem;

public class GenericBreadthFirst extends Search {
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

			if(city.getState().equals(problem.getGoal()))
				return getPathFromGoal(city, problem);
	
			explored.add(city.getState());
			
			for(String s : city.getAction().getListOfActions()) {
				Node child = city.childNode(city, s, problem);
				
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
}
