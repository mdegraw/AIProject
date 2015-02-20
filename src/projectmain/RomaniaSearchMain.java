package projectmain;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import airomaniansearchcore.Node;
import airomaniansearchcore.Problem;
import search.*;
import searchFactory.SearchFactory;

public class RomaniaSearchMain {
	public static void main(String[] args) throws IOException {
		Problem problem;
		SearchFactory searchFactory = new SearchFactory();
		Scanner input = new Scanner(System.in);
		long startTime, endTime, duration1, duration2, duration3, duration4, duration5, duration6 = 0;
		
		System.out.print("Please enter start city: ");
		String startCity = input.nextLine();
		
		System.out.print("Please enter end city: ");
		String goal = input.nextLine();
		
		problem = new Problem(startCity, goal);
		
		
		Search bfs = searchFactory.getSearch("Breadth First Search");
		startTime = System.nanoTime();
		List<Node> solution = ((BreadthFirst)bfs).breadthFirstSearch(problem);
		endTime = System.nanoTime();
		duration1 = TimeUnit.MILLISECONDS.convert(endTime-startTime, TimeUnit.NANOSECONDS);
		
		Search ufs = searchFactory.getSearch("Uniform Cost Search");
		startTime = System.nanoTime();
		List<Node> solution2 = ((UniformCost)ufs).uniformCostSearch(problem);
		endTime = System.nanoTime();
		duration2 = TimeUnit.MILLISECONDS.convert(endTime-startTime, TimeUnit.NANOSECONDS);
		
		if(goal.equals("Bucharest") && startCity.equals("Arad")){
			
			Search astar = searchFactory.getSearch("A* Search");
			startTime = System.nanoTime();
			List<Node> solution3 = ((AStar)astar).aStarSearch(problem);
			endTime = System.nanoTime();
			duration3 = TimeUnit.MILLISECONDS.convert(endTime-startTime, TimeUnit.NANOSECONDS);
			
			System.out.print("\nA* Search Solution path is: ");
			for(Node n : solution3) {
				System.out.print(n.getState() + " ");
			}
			
			System.out.println("\nPath cost is " + solution3.get(solution3.size()-1).getPathCost());
			
			System.out.println("A* Search Time Taken: " + duration3 + " milliseconds");
			
			Search rbfs = searchFactory.getSearch("Recursive Best First Search");
			startTime = System.nanoTime();
			List<Node> solution6 = ((RecursiveBestFirst)rbfs).returnPathFromRBFS(problem);
			endTime = System.nanoTime();
			duration6 = TimeUnit.MILLISECONDS.convert(endTime-startTime, TimeUnit.NANOSECONDS);
			System.out.print("\nRecursive Best-First-Search  Solution path is: ");
			
			for(Node n : solution6){
				System.out.print(n.getState() + " ");
			}
		
			System.out.println("\nRecursive Best-First-Search Time Taken: " + duration6 + " milliseconds");
		}
		
		System.out.print("\nBreadth First Search Solution path is: ");
		
		for(Node n : solution) {
			System.out.print(n.getState() + " ");
		}
		System.out.println("\nPath cost is " + solution.get(solution.size()-1).getPathCost());
		System.out.println("Breadth First Search Time Taken: " + duration1 + " milliseconds");
		
		System.out.print("\nUniform-Cost-Search Solution path is: ");
		for(Node n : solution2) {
			System.out.print(n.getState() + " ");
		}
		System.out.println("\nPath cost is " + solution2.get(solution2.size()-1).getPathCost());
		System.out.println("Uniform-Cost-Search Time Taken: " + duration2 + " milliseconds");
		System.out.println();
		
		Search dls = searchFactory.getSearch("Depth Limited Search");
		startTime = System.nanoTime();
		List<Node> solution4 = ((DepthLimited)dls).returnPathFromDLS(problem, 9);
		endTime = System.nanoTime();
		duration4 = TimeUnit.MILLISECONDS.convert(endTime-startTime, TimeUnit.NANOSECONDS);
		System.out.print("\nDepth Limited Search  Solution path is: ");
		
		for(Node n : solution4) {
			System.out.print(n.getState() + " ");
		}
	
		System.out.println("\nDepth Limited Search Time Taken: " + duration4 + " milliseconds");
		
		Search idls = searchFactory.getSearch("Iterative Depth Limited Search");
		startTime = System.nanoTime();
		List<Node> solution5 = ((IterativeDepthLimited)idls).iterativeDLS(problem);
		endTime = System.nanoTime();
		duration5 = TimeUnit.MILLISECONDS.convert(endTime-startTime, TimeUnit.NANOSECONDS);
		System.out.print("iterativeDLS Solution path is: ");
		
		for(Node n : solution5){
			System.out.print(n.getState() + " ");
		}
	
		System.out.println("\nIterative DLS Time Taken: " + duration5 + " milliseconds");
	
		
		input.close();
	}
}
