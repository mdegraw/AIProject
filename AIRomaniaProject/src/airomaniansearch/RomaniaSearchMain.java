package airomaniansearch;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class RomaniaSearchMain {
	public static void main(String[] args) throws IOException {
		Problem problem;
		Scanner input = new Scanner(System.in);
		
		System.out.print("Please enter start city: ");
		String startCity = input.nextLine();
		
		System.out.print("Please enter end city: ");
		String goal = input.nextLine();
		
		problem = new Problem(startCity, goal);
		
		Search search = new Search();
		List<Node> solution = search.breadthFirstSearch(problem);
		List<Node> solution2 = search.uniformCostSearch(problem);
		if(!goal.equals("Bucharest")){
		
		System.out.print("\nBreadth First Search Solution path is: ");
		
		for(Node n : solution) {
			System.out.print(n.getState() + " ");
		}
		System.out.println("\nPath cost is " + solution.get(solution.size()-1).getPathCost());
		
		System.out.print("\nUniform-Cost-Search Solution path is: ");
		for(Node n : solution2) {
			System.out.print(n.getState() + " ");
		}
		System.out.println("\nPath cost is " + solution2.get(solution2.size()-1).getPathCost());
		
		}else {
			System.out.print("\nBreadth First Search Solution path is: ");
			
			for(Node n : solution) {
				System.out.print(n.getState() + " ");
			}
			System.out.println("\nPath cost is " + solution.get(solution.size()-1).getPathCost());
			
			System.out.print("\nUniform-Cost-Search Solution path is: ");
			for(Node n : solution2) {
				System.out.print(n.getState() + " ");
			}
			System.out.println("\nPath cost is " + solution2.get(solution2.size()-1).getPathCost());
			
			List<Node> solution3 = search.aStarSearch(problem);	
			System.out.print("\nA* Search Solution path is: ");
			for(Node n : solution3) {
				System.out.print(n.getState() + " ");
			}
			System.out.println("\nPath cost is " + solution3.get(solution3.size()-1).getPathCost());
		}
		
		
		
		input.close();
	}
}
