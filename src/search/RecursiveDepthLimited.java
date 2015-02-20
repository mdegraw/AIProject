package search;

import java.io.IOException;

import parser.CSVParser;
import airomaniansearchcore.Node;
import airomaniansearchcore.Problem;
import airomaniansearchcore.Result;

public class RecursiveDepthLimited extends Search{
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
	
}
