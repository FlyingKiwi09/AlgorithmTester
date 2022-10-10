package application;
	
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
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
			VBox sortButtonsVBox = new VBox();
			ListView<String> sortResultsList = new ListView<String>();
			sortHBox.getChildren().addAll(sortButtonsVBox, sortResultsList);
			
			// add buttonsVBox and ListVeiw to searchHBox
			VBox searchButtonsVBox = new VBox();
			ListView<String> searchResultsList = new ListView<String>();
			searchHBox.getChildren().addAll(searchButtonsVBox, searchResultsList);
			
			// add textfields and buttons to sortButtonsVBox
			TextField sortRunsTF = new TextField();
			TextField sortSizeTF = new TextField();
			Button testSortButton = new Button("Test Sort");
			sortButtonsVBox.getChildren().addAll(sortRunsTF, sortSizeTF, testSortButton);
			
			// add textfields and buttons to searchButtonsVBox
			TextField searchRunsTF = new TextField();
			TextField searchSizeTF = new TextField();
			Button testSearchButton = new Button("Test Search");
			searchButtonsVBox.getChildren().addAll(searchRunsTF, searchSizeTF, testSearchButton);
			
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
