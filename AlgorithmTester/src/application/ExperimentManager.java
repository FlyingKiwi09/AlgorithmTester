package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ExperimentManager {
	
	private static ExperimentManager instance;
	private ObservableList<String> resultsList;
	
	// private constructor for singleton pattern
	private ExperimentManager() {
		this.resultsList = FXCollections.observableArrayList();
	}
	
	// getInstance used to return the one and only instance of this class so that only one can be created.
	public static ExperimentManager getInstance() {
		if (instance == null) {
			instance = new ExperimentManager();
		}
		return instance;
	}
	
	public ObservableList<String> getResultsList() {
		return this.resultsList;
	}
	
	

}
