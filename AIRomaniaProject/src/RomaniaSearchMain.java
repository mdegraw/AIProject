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
		System.out.println("Solution path is:");
		
		for(Node n : solution) {
			System.out.print(n.getState() + " ");
		}
		/*
		NodeParser rTree = new NodeParser(new File(System.getProperty("user.dir")+"/"+"Romania.csv"), startCity);
		Node root = rTree.generateRoot(startCity);
		Node child = rTree.generateNode(root.getAction().getActions().get(0), root);
	
		Node child2 = rTree.generateNode(child.getAction().getActions().get(1), child);
		Node child3 = rTree.generateNode(child2.getAction().getActions().get(1), child2);
		Node child4 = rTree.generateNode(child3.getAction().getActions().get(1), child3);
		Node child5 = rTree.generateNode(child4.getAction().getActions().get(1), child4);
		Node child6 = rTree.generateNode(child5.getAction().getActions().get(2), child5);
		Node child7 = rTree.generateNode(child6.getAction().getActions().get(2), child6);
		System.out.println("Root City: " + root.getState());
		System.out.println("Available actions: " + root.actionsToString());
		System.out.println("Child City: " + child.getState() + " Path cost: " + child.getPathCost());
		System.out.println("Available actions: " + child.actionsToString());
		
		System.out.println("Child City: " + child2.getState() + " Path cost: " + child2.getPathCost());
		System.out.println("Available actions: " + child2.actionsToString());
		System.out.println("Child City: " + child3.getState() + " Path cost: " + child3.getPathCost());
		System.out.println("Available actions: " + child3.actionsToString());
		System.out.println("Child City: " + child4.getState() + " Path cost: " + child4.getPathCost());
		System.out.println("Available actions: " + child4.actionsToString());
		System.out.println("Child City: " + child5.getState() + " Path cost: " + child5.getPathCost());
		System.out.println("Available actions: " + child5.actionsToString());
		System.out.println("Child City: " + child6.getState() + " Path cost: " + child6.getPathCost());
		System.out.println("Available actions: " + child6.actionsToString());
		System.out.println("Child City: " + child7.getState() + " Path cost: " + child7.getPathCost());
		System.out.println("Available actions: " + child7.actionsToString());
		*/
		input.close();
	}
}
