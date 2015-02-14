package airomaniansearch;

import java.io.File;


public class Problem {
	private String startCity;
	private String goal;
	private File file;
	
	public Problem(String startCity, String goal) {
		this.startCity = startCity;
		this.goal = goal;
		setFile(new File(System.getProperty("user.dir")+"/"+"Romania.csv"));
	}

	public String getStartCity() {
		return startCity;
	}

	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
}
