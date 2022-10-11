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
	
	
	// calls the sort method on the type of sorter passed through from the button click
		// calls this sort method the number of times specified by the user in the sortRuns textfield
		// records all the times and then finds the average
		// stores the average, and other data to the Observable list/ list view
	public void runSortTest(Sorter sorter, int numRuns, int dataSize) {
		
		double[] runTimes = new double[numRuns]; // to store the time each run takes
		long[] counts = new long[numRuns];
		
		for (int i = 0; i < numRuns; i++) { 					// for each trial that the user wants to do (sortRuns)
			int[] ints = generateInts(dataSize); 				// create a new array of the size the user wants to test
			double initialTime = System.currentTimeMillis();   	// take the initial time
			sorter.sort(ints); 									// run the sort with the int array
			double finalTime = System.currentTimeMillis(); 		// get the final time
			double elapsedTime = finalTime-initialTime; 		// calc and store the elapse time
			runTimes[i] = elapsedTime;
			counts[i] = sorter.stepCount;
		}
		
		// calculate the average time
		double totalTime = 0;
		for (double time : runTimes){
			totalTime += time;
		}
		double averageTime = totalTime/numRuns;
		
		// calculate the average count
		long totalCount = 0;
		for (long count : counts) {
			totalCount += count;
		}
		long averageCount = totalCount/numRuns;
		
		// output result
		System.out.println(averageTime);
		resultsList.add(sorter.testName + " Results:    Trials: " + numRuns + ",    Date Size: " + dataSize + ",    Average Time: " + averageTime + ",    Average Count: " + averageCount);
	}
	

	// returns an array of random integers of length = quantity between 0 and 999,999
	public static int[] generateInts(int quantity) {
		int[] ints = new int[quantity];
		
		for (int i = 0; i < quantity; i++) {
			ints[i] = (int) Math.floor(Math.random()*(1000000)+0);
		}
		
		return ints;
	}
	
	
	public void runSearchTest(Searcher searcher, int numRuns, int dataSize) {
		
		double[] runTimes = new double[numRuns]; // to store the time each run takes
		
		for (int i = 0; i < numRuns; i++) { 					// for each trial that the user wants to do (sortRuns)
			int[] ints = generateInts(dataSize); 				// generate a random set of ints
			int target = (int) Math.random()*1000000;			// generate a random target
			double initialTime = System.currentTimeMillis();   	// take the initial time
			searcher.search(ints, target); 						// run the search with the int array and target
			double finalTime = System.currentTimeMillis(); 		// record final time
			double elapsedTime = finalTime-initialTime; 		// calc and store the elapse time
			System.out.println(elapsedTime);
			runTimes[i] = elapsedTime;
		}
		
		// calculate the average
		double totalTime = 0;
		for (double time : runTimes){
			totalTime += time;
		}
		double average = totalTime/numRuns;
		
		// output result
		System.out.println(average);
		resultsList.add("Average " + average);
	}
	
}
