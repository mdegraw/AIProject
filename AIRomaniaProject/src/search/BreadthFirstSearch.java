package search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import parser.CSVParser;
import airomaniansearchcore.Node;
import airomaniansearchcore.Problem;

public class BreadthFirstSearch extends Search{
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
						return super.getPathFromGoal(child, problem);
					}
					
					frontier.add(child);
				}
			}
		}
		
	}//End breadthFirstSearch
}
