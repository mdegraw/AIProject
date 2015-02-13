import java.util.ArrayList;
import java.util.List;


public class Action {
	private List<String> actions;
	
	public Action() {
		actions = new ArrayList<String>();
	}
	
	public void addAction(String cityName) {
		actions.add(cityName);
	}
	
	public List<String> getActions(){
		return actions;
	}
	
	public void clear() {
		actions.clear();
	}
}
