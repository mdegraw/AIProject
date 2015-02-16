package airomaniansearch;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class RomaniaSearchMain {
	public static void main(String[] args) throws IOException {
		Problem problem;
		Scanner input = new Scanner(System.in);
		long startTime, endTime, duration1, duration2, duration3 = 0;
		
		System.out.print("Please enter start city: ");
		String startCity = input.nextLine();
		
		System.out.print("Please enter end city: ");
		String goal = input.nextLine();
		
		problem = new Problem(startCity, goal);
		
		Search search = new Search();
		
		startTime = System.nanoTime();
		List<Node> solution = search.breadthFirstSearch(problem);
		endTime = System.nanoTime();
		duration1 = TimeUnit.MILLISECONDS.convert(endTime-startTime, TimeUnit.NANOSECONDS);
		
		startTime = System.nanoTime();
		List<Node> solution2 = search.uniformCostSearch(problem);
		endTime = System.nanoTime();
		duration2 = TimeUnit.MILLISECONDS.convert(endTime-startTime, TimeUnit.NANOSECONDS);
		
		if(!goal.equals("Bucharest")){
		
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
		
		
		}else {
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
			
			startTime = System.nanoTime();
			List<Node> solution3 = search.aStarSearch(problem);
			endTime = System.nanoTime();
			duration3 = TimeUnit.MILLISECONDS.convert(endTime-startTime, TimeUnit.NANOSECONDS);
			
			System.out.print("\nA* Search Solution path is: ");
			for(Node n : solution3) {
				System.out.print(n.getState() + " ");
			}
			
			System.out.println("\nPath cost is " + solution3.get(solution3.size()-1).getPathCost());
			System.out.println("A* Search Time Taken: " + duration3 + " milliseconds");
		}
		
		
		
		input.close();
	}
}
