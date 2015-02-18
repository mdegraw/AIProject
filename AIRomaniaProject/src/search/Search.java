package search;

import java.util.LinkedList;
import java.util.List;

import airomaniansearchcore.Node;
import airomaniansearchcore.Problem;


public class Search {
	protected Node root;
	
	protected static List<Node> getPathFromGoal(Node n, Problem problem) {
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
