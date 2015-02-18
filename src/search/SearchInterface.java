package search;

import search.Search;

@FunctionalInterface
public interface SearchInterface {
	public Search getSearch(String searchType);
}
