package search;

import java.io.IOException;
import java.util.List;

import airomaniansearchcore.Node;
import airomaniansearchcore.Problem;

public class AStarSearch extends GenericBreadthFirstSearch{
	public List<Node> aStarSearch(Problem problem) throws IOException {
		//return genericBFS(problem, (Node n1, Node n2) -> (n1.getPathCost()+n1.getHeuristic()) - (n2.getPathCost() + n2.getHeuristic()));
		return genericBFS(problem, (Node n1, Node n2) -> (n1.getfCost() - n2.getfCost()));
		
	}//End aStarSearch
}
