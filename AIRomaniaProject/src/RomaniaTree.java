import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;


public class RomaniaTree {
	private Map<String, Node> RomanianMap;
	private Node root;
	private String startCity;
	private final String DELIMITER = ",";
	
	public RomaniaTree(File csv, String startCity) throws IOException {
		RomanianMap = new TreeMap<String, Node>();
		this.startCity = startCity;
		setRoot(csv);
	}

	@SuppressWarnings("unchecked")

	public Map<String, Node> parseCSV(File csv) throws IOException{
		BufferedReader fileReader = null;
		
		try{
			String cityLine = "";
			String cityLineTwo = "";
			String cityName = "";
			String cityNameTwo = "";
			String state = "";
			//String parent = "";
			Node parent;
			fileReader = new BufferedReader(new FileReader(csv));
			Action actions = new Action();
			
			
			while((cityLine = fileReader.readLine()) != null) {
				String[] tokens = cityLine.split(DELIMITER);
				cityName = tokens[1];
				cityNameTwo = tokens[1];
				actions.clear();
				while((cityLineTwo = fileReader.readLine()) != null && cityNameTwo.equals(cityName)) {
					String[] tokensTwo = cityLine.split(DELIMITER);
					cityNameTwo = tokensTwo[1];
					actions.addAction(tokensTwo[1]);
				}
			//	RomanianMap.put(tokens[0] + " " +tokens[1], new Node(tokens[1], , new Action(),Double.parseDouble(tokens[2])));
				/*
				if(!tokens[0].equals(startCity) && !tokens[0].equals(cityName)) {
					actions.clear();
					actions.addAction(tokens[1]);
					RomanianMap.put(tokens[0] + " " +tokens[1], new Node(tokens[1], tokens[0], new Action(),Double.parseDouble(tokens[2])));
				
				}else if(tokens[0].equals(cityName)) {
					
				}else {
					actions.addAction(tokens[1]);
				}
				cityName = tokens[0];
				*/
			}
			
		}catch(Exception e) {
			e.printStackTrace();

		}finally {
			try{
				fileReader.close();
			
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	
	public void setRoot(File csv) throws IOException{	
		BufferedReader fileReader = null;
		try{
			fileReader = new BufferedReader(new FileReader(csv));
			String cityAction = "";
			
			Action actions = new Action();
	
			while((cityAction = fileReader.readLine()) != null) {
				if(cityAction.split(DELIMITER)[0].equals(startCity)) {
					actions.addAction(cityAction.split(DELIMITER)[1]);
				}
			}
			root = new Node(startCity, actions, 0);
		
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally{
			try{
				fileReader.close();
			
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String printRoot() {
		return root.getState();
	}
	public String printRootActions() {
		String actions = "";
		for(String s : root.getAction().getActions()){
			actions += "\n" + s;
		}
		return actions;
	}
	public Map<String, Node> getRomanianMap() {
		return this.RomanianMap;
	}
}
