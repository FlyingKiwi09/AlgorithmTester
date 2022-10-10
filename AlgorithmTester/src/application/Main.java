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
	public static ExperimentManager manager = ExperimentManager.getInstance();
	
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
		sortResultsList.setItems(this.manager.getResultsList());
		sortResultsList.setPrefWidth(500);
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
		testSortButton.setOnMouseClicked(event -> {
			int numRuns = Integer.parseInt(sortRunsTF.getText());
			int dataSize = Integer.parseInt(sortSizeTF.getText());
			TestSort testSort = new TestSort(); // testSort class extends Sorter class and can therefore be passed to the runSortTest method
			this.manager.runSortTest(testSort, numRuns, dataSize);				
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
		testSearchButton.setOnMouseClicked(event -> {
			int dataSize = Integer.parseInt(searchSizeTF.getText());
			int numRuns =  Integer.parseInt(searchRunsTF.getText());
			TestSearch testSearch = new TestSearch(); // testSearch extends Searcher
			this.manager.runSearchTest(testSearch, numRuns, dataSize);
			
		});
		
		
		// **** add buttons for new search algorithms here ****
		
		searchButtonsVBox.getChildren().addAll(searchRunsTF, searchSizeTF, testSearchButton);
				
		return searchButtonsVBox;
	}
	

	
	
	

	
	public static void main(String[] args) {
		launch(args);
	}
}
