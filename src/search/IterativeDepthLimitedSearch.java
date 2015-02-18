package search;

import java.io.IOException;
import java.util.List;

import airomaniansearchcore.Node;
import airomaniansearchcore.Problem;
import airomaniansearchcore.Result;

public class IterativeDepthLimitedSearch extends DepthLimitedSearch{
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

}
