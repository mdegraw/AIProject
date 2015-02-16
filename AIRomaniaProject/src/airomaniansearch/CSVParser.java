package airomaniansearch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CSVParser {
	private String rootCity;
	private final String DELIMITER = ",";
	private File file;
	
	public CSVParser(File file, String rootCity) throws IOException {
		this.file = file;
		this.rootCity = rootCity;
	}
	
	public Node parseCSV(String cityName, Node parent) throws IOException {
		BufferedReader fileReader = null;
		
		try{
			fileReader = new BufferedReader(new FileReader(file));
			
			return (parent == null) ? generateRoot(cityName, fileReader) : generateNode(cityName, parent, fileReader);	
				
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
	
	public Node generateRoot(String cityName, BufferedReader fileReader) throws IOException{
		String cityAction = "";
		int heuristic = 0;
		Action actions = new Action();

		while((cityAction = fileReader.readLine()) != null) {
			if(cityAction.split(DELIMITER)[0].equals(cityName)) {
				heuristic = Integer.parseInt(cityAction.split(DELIMITER)[2]);
				actions.addAction(cityAction.split(DELIMITER)[1]);
			}
		}
		return new Node(cityName, actions, 0, heuristic);
	
	}
	
	public Node generateNode(String cityName, Node parent, BufferedReader fileReader) throws IOException{
		String cityAction = "";
		int heuristic = 0;
		Action actions = new Action();

		while((cityAction = fileReader.readLine()) != null) {
			if(cityAction.split(DELIMITER)[0].equals(cityName)) {
				heuristic = Integer.parseInt(cityAction.split(DELIMITER)[2]);
				actions.addAction(cityAction.split(DELIMITER)[1]);
			}
		}
		
		return new Node(cityName, parent, actions, getStepCost(cityName, parent.getState()), heuristic);
	}

	public int getStepCost(String cityName, String parentName) {
		BufferedReader fileReader = null;
		int stepCost = 0;
		try{
			
			fileReader = new BufferedReader(new FileReader(file));
			String cityAction = "";
			
			while((cityAction = fileReader.readLine()) != null) {
				if(cityAction.split(DELIMITER)[0].equals(parentName) && cityAction.split(DELIMITER)[1].equals(cityName)) {
					stepCost = Integer.parseInt(cityAction.split(DELIMITER)[2]);
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

		return stepCost;
	}
	
	public String getRootCity() {
		return rootCity;
	}

	public void setRootCity(String rootCity) {
		this.rootCity = rootCity;
	}
}
