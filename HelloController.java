package org.example.assignment1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    @FXML
    private PieChart pieChart;  // This pie chart will display the data

    @FXML
    private Button tableViewButton;  // This button allows switching to the table view

    private Stage stage;  // This variable stores the reference to the main stage

    public void setStage(Stage stage) {
        this.stage = stage;  // Assigning the stage reference
    }

    public void initialize() {
        // Creating a database connector and fetching data to populate the pie chart
        DatabaseConnector dbConnector = new DatabaseConnector();
        dbConnector.fetchData(pieChart);

        // Adding an action listener to the "tableViewButton"
        tableViewButton.setOnAction(event -> {
            // Calling the method to switch to the table view scene
            switchToTableViewScene();
        });
    }

    private void switchToTableViewScene() {
        try {
            // Loading the FXML file for the table view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("tableView.fxml"));
            Parent tableViewRoot = loader.load();

            // Getting the controller from the loaded FXML
            TableViewController controller = loader.getController();

            // Setting the stage reference and data in the TableView controller
            controller.setStage(stage);
            controller.initData(pieChart.getData());

            // Creating a new scene for the table view and setting it on the stage
            Scene tableViewScene = new Scene(tableViewRoot);
            stage.setScene(tableViewScene);

        } catch (IOException e) {
            e.printStackTrace();  // Printing any errors encountered during loading the FXML
        }
    }

    public static void switchToPieChartScene(Stage stage) {
        try {
            // Loading the FXML file for the pie chart view
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Parent root = fxmlLoader.load();

            // Creating a scene for the pie chart view and setting it on the stage
            Scene scene = new Scene(root, 600, 400);
            stage.setTitle("Pie Chart Application");
            stage.setScene(scene);

            // Getting the controller from the loaded FXML and setting the stage reference
            HelloController controller = fxmlLoader.getController();
            controller.setStage(stage);

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();  // Printing any errors encountered during loading the FXML
        }
    }
}
