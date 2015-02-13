import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class NodeParser {
	private String rootCity;
	private final String DELIMITER = ",";
	private File file;
	
	public NodeParser(File file, String rootCity) throws IOException {
		this.file = file;
		this.setRootCity(rootCity);

	}
	
	public Node generateNode(String cityName, Node parent) throws IOException{
		BufferedReader fileReader = null;
		
		try{
			fileReader = new BufferedReader(new FileReader(file));
			String cityAction = "";
			Action actions = new Action();
	
			while((cityAction = fileReader.readLine()) != null) {
				if(cityAction.split(DELIMITER)[0].equals(cityName)) {
					actions.addAction(cityAction.split(DELIMITER)[1]);
				}
			}
			//return (cityName.equals(rootCity)) ? new Node(rootCity, actions, 0): new Node(cityAction, parent, actions, 0);
			return new Node(cityName, parent, actions, getStepCost(cityName, parent.getState()));
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally{
			try{
				fileReader.close();
			
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public double getStepCost(String cityName, String parentName) {
		BufferedReader fileReader = null;
		double stepCost = 0;
		try{
			fileReader = new BufferedReader(new FileReader(file));
			String cityAction = "";
			
			while((cityAction = fileReader.readLine()) != null) {
				if(cityAction.split(DELIMITER)[0].equals(parentName) && cityAction.split(DELIMITER)[1].equals(cityName)) {
					stepCost = Double.parseDouble(cityAction.split(DELIMITER)[2]);
				}
			}
			return stepCost;
		
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally{
			try{
				fileReader.close();
			
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("boom");
		return stepCost;
	}
	
	public Node generateRoot(String cityName) throws IOException{
		BufferedReader fileReader = null;
		try{
			fileReader = new BufferedReader(new FileReader(file));
			String cityAction = "";
			
			Action actions = new Action();
	
			while((cityAction = fileReader.readLine()) != null) {
				if(cityAction.split(DELIMITER)[0].equals(cityName)) {
					actions.addAction(cityAction.split(DELIMITER)[1]);
				}
			}
			return new Node(cityName, actions, 0);
		
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally{
			try{
				fileReader.close();
			
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public String getRootCity() {
		return rootCity;
	}

	public void setRootCity(String rootCity) {
		this.rootCity = rootCity;
	}
}
