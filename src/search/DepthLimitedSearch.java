package search;

import java.io.IOException;
import java.util.List;

import parser.CSVParser;
import airomaniansearchcore.Node;
import airomaniansearchcore.Problem;

public class DepthLimitedSearch extends RecursiveDepthLimitedSearch{
	
	public  List<Node> returnPathFromDLS(Problem problem, int limit) throws IOException{
		return getPathFromGoal(depthLimitedSearch(problem, limit), problem);
	
	}//End returnPathFromDLS
	
	public Node depthLimitedSearch(Problem problem, int limit) throws IOException{
		CSVParser nodeTree = new CSVParser(problem.getFile(), problem.getStartCity());
		
		return recursiveDLS(nodeTree.parseCSV(problem.getStartCity(), null), problem, limit);
	
	}//End depthLimitedSearch
	
}
