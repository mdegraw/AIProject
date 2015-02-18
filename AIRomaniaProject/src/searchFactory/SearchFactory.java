package searchFactory;

import java.util.HashMap;

import search.AStarSearch;
import search.BreadthFirstSearch;
import search.DepthLimitedSearch;
import search.IterativeDepthLimitedSearch;
import search.RecursiveBestFirstSearch;
import search.Search;
import search.UniformCostSearch;

public class SearchFactory {
	private static HashMap<String, Search> searchFactory;
	
	public SearchFactory() {
		searchFactory = new HashMap<String, Search>();
		searchFactory.put("Breadth First Search", new BreadthFirstSearch());
		searchFactory.put("Uniform Cost Search", new UniformCostSearch());
		searchFactory.put("A* Search", new AStarSearch());
		searchFactory.put("Iterative Depth Limited Search", new IterativeDepthLimitedSearch());
		searchFactory.put("Depth Limited Search", new DepthLimitedSearch());
		searchFactory.put("Recursive Best First Search", new RecursiveBestFirstSearch());
	
		
	}
	
	public Search getSearch(String searchType) {
		return searchFactory.get(searchType);
	}
}
