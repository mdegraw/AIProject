package search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import parser.CSVParser;
import airomaniansearchcore.Node;
import airomaniansearchcore.Problem;
import airomaniansearchcore.Result;

public class RecursiveBestFirst extends Search{
	public List<Node> returnPathFromRBFS(Problem problem) throws IOException{
		return getPathFromGoal(recursiveBestFirstSearch(problem), problem);
	
	}//End returnPathFromRBFS
	
	public Node recursiveBestFirstSearch(Problem problem) throws IOException {
		CSVParser nodeTree = new CSVParser(problem.getFile(), problem.getStartCity());
		
		return rBFS(problem, nodeTree.parseCSV(problem.getStartCity(), null), Integer.MAX_VALUE);
	
	}//End recursiveBestFirstSearch
	
	public Node rBFS(Problem problem, Node node, int f_limit) throws IOException {

		if(node.getState().equals(problem.getGoal())){
			node.setResult(Result.SUCCESS);
			return node;
		}
		ArrayList<Node> successors = new ArrayList<Node>();
		
		for(String s : node.getAction().getListOfActions()) {
			CSVParser nodeTree = new CSVParser(problem.getFile(), problem.getStartCity());
			successors.add(nodeTree.parseCSV(s, node));
		}
		
		if(successors.isEmpty()) {
		
			node.setResult(Result.FAILURE);
			node.setfLimit(Integer.MAX_VALUE);

			return node;
		}
	
		successors.forEach((Node s) -> s.setfCost(Math.max((s.getPathCost()+s.getHeuristic()), node.getfCost())));
	
		while(true) {
			
			Node best = getLowestFValue(successors);
			
			if(best.getfCost() > f_limit) {
				best.setResult(Result.FAILURE);
				best.setfLimit(best.getfCost());
				return best;
			}
	
			successors.sort(Comparator.comparing(Node::getfCost));
			
			int alternative = successors.get(successors.size()-2).getfCost();

			Node result =  rBFS(problem, best, Math.min(f_limit, alternative));
			best.setfCost(result.getfCost());
			
	
			if(!result.getResult().equals(Result.FAILURE)) {
				return result;
			}
			
		}
	
	}//End rBFS
	
	private Node getLowestFValue(List<Node> successors) {
		successors.sort((Node n1, Node n2) -> (n1.getfCost()-n2.getfCost()));
		Node lowest = successors.get(0);
	
		Iterator<Node> itr = successors.iterator();
		
		while(itr.hasNext()) {
			Node temp = itr.next();
			lowest = (temp.getfCost() < lowest.getfCost()) ? temp : lowest;
		}
		
		return lowest;
	}//End getLowestFValue
}
