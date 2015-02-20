package searchFactory;

import java.util.HashMap;

import search.AStar;
import search.BreadthFirst;
import search.DepthLimited;
import search.IterativeDepthLimited;
import search.RecursiveBestFirst;
import search.Search;
import search.UniformCost;

public class SearchFactory {
	private static HashMap<String, Search> searchFactory;
	
	public SearchFactory() {
		searchFactory = new HashMap<String, Search>();
		searchFactory.put("Breadth First Search", new BreadthFirst());
		searchFactory.put("Uniform Cost Search", new UniformCost());
		searchFactory.put("A* Search", new AStar());
		searchFactory.put("Iterative Depth Limited Search", new IterativeDepthLimited());
		searchFactory.put("Depth Limited Search", new DepthLimited());
		searchFactory.put("Recursive Best First Search", new RecursiveBestFirst());
	
		
	}
	
	public Search getSearch(String searchType) {
		return searchFactory.get(searchType);
	}
}
