package search;

import java.io.IOException;
import java.util.List;

import airomaniansearchcore.Node;
import airomaniansearchcore.Problem;

public class UniformCost extends GenericBreadthFirst{
	
	public List<Node> uniformCostSearch(Problem problem) throws IOException {
		return genericBFS(problem, (Node n1, Node n2) -> (n1.getPathCost() - n2.getPathCost()));
		
	}//End Uniform Cost Search
}
