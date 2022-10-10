package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class Main extends Application {
	
	public static ListView<String> sortResultsList, searchResultsList;
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Scene scene = this.setUpUIScene();
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private Scene setUpUIScene() {
		
		BorderPane root = new BorderPane();
		
		// create a title and add to the top of the root borderpane
		HBox top = new HBox();
		Text title = new Text("Algorithm Tester");
		top.setAlignment(Pos.CENTER);
		top.getChildren().add(title);
		root.setTop(top);
		
		// create hbox for the center of the root borderpane
		HBox center = new HBox();
		center.setAlignment(Pos.TOP_CENTER);
		root.setCenter(center);
		
		// create vboxes for the sort and search functions in the center hbox
		VBox sortVBox = new VBox();
		VBox searchVBox = new VBox();
		center.getChildren().addAll(sortVBox, searchVBox);
		
		// create sort test view
		Text sortTestTitle = new Text("Sort Test");
		HBox sortHBox = new HBox();
		sortVBox.getChildren().addAll(sortTestTitle, sortHBox);
		sortVBox.setAlignment(Pos.TOP_CENTER);
		
		// create search test view
		Text searchTestTitle = new Text("Search Test");
		HBox searchHBox = new HBox();
		searchVBox.getChildren().addAll(searchTestTitle, searchHBox);
		searchVBox.setAlignment(Pos.TOP_CENTER);
		
		// add buttonsVBox and ListVeiw to sortHBox
		sortResultsList = new ListView<String>();
		sortHBox.getChildren().addAll(this.setUpSortButtonsUI(), sortResultsList);
		
		// add buttonsVBox and ListVeiw to searchHBox
		searchResultsList = new ListView<String>();
		searchHBox.getChildren().addAll(this.setUpSearchButtonsUI(), searchResultsList);
		
		Scene scene = new Scene(root);
		return scene;
	}
	
	
	private VBox setUpSortButtonsUI() {
		VBox sortButtonsVBox = new VBox();
		
		// add textfields and buttons to sortButtonsVBox
		TextField sortRunsTF = new TextField();
		TextField sortSizeTF = new TextField();
		
		Button testSortButton = new Button("Test Sort");
		testSortButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				int numRuns = Integer.parseInt(sortRunsTF.getText());
				int dataSize = Integer.parseInt(sortSizeTF.getText());
				TestSort testSort = new TestSort(); // testSort class extends Sorter class and can therefore be passed to the runSortTest method
				runSortTest(testSort, numRuns, dataSize);				
			}
		});
		
		
		// **** add buttons for new sort algorithms here ****
		
		sortButtonsVBox.getChildren().addAll(sortRunsTF, sortSizeTF, testSortButton);
		
		return sortButtonsVBox;
	}
	
	
	private VBox setUpSearchButtonsUI() {
		VBox searchButtonsVBox = new VBox();
		
		// add textfields and buttons to searchButtonsVBox
		TextField searchRunsTF = new TextField();
		TextField searchSizeTF = new TextField();
		Button testSearchButton = new Button("Test Search");
		testSearchButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent arg0) {
				int dataSize = Integer.parseInt(searchSizeTF.getText());
				int numRuns =  Integer.parseInt(searchRunsTF.getText());
				TestSearch testSearch = new TestSearch(); // testSearch extends Searcher
				runSearchTest(testSearch, numRuns, dataSize);
				
			}
			
		});
		
		
		// **** add buttons for new search algorithms here ****
		
		searchButtonsVBox.getChildren().addAll(searchRunsTF, searchSizeTF, testSearchButton);
				
		return searchButtonsVBox;
	}
	
	// returns an array of random integers of length = quantity between 0 and 999,999
	public static int[] generateInts(int quantity) {
		int[] ints = new int[quantity];
		
		for (int i = 0; i < quantity; i++) {
			ints[i] = (int) Math.random()*1000000;
		}
		
		return ints;
	}
	
	// calls the sort method on the type of sorter passed through from the button click
	// calls this sort method the number of times specified by the user in the sortRuns textfield
	// records all the times and then finds the average
	// stores the average, and other data to the Observable list/ list view
	private void runSortTest(Sorter sorter, int numRuns, int dataSize) {
		
		double[] runTimes = new double[numRuns]; // to store the time each run takes
		
		for (int i = 0; i < numRuns; i++) { 					// for each trial that the user wants to do (sortRuns)
			int[] ints = generateInts(dataSize); 				// create a new array of the size the user wants to test
			double initialTime = System.currentTimeMillis();   	// take the initial time
			sorter.sort(ints); 									// run the sort with the int array
			double finalTime = System.currentTimeMillis(); 		// get the final time
			double elapsedTime = finalTime-initialTime; 		// calc and store the elapse time
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
		sortResultsList.getItems().add("Average " + average);
	}
	
	
	private void runSearchTest(Searcher searcher, int numRuns, int dataSize) {
		
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
		searchResultsList.getItems().add("Average " + average);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
