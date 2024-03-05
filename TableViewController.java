package org.example.assignment1;

import javafx.collections.ObservableList;  // Importing for handling data lists
import javafx.fxml.FXML;  // Handling FXML elements
import javafx.scene.chart.PieChart;  // For accessing pie chart data
import javafx.scene.control.*;  // Importing various UI controls
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;  // Managing application windows

// Controller for managing the TableView scene
public class TableViewController {

    // Linking FXML elements for use in the code
    @FXML
    private TableView<DataItem> tableView;  // Table for displaying data

    @FXML
    private TableColumn<DataItem, String> nameColumn;  // Column for country names

    @FXML
    private TableColumn<DataItem, Double> valueColumn;  // Column for GDP values

    private Stage stage;  // Storing a reference to the main window

    // Setting the stage reference for use in scene switching
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    // Initializing the TableView with data
    public void initData(ObservableList<PieChart.Data> data) {
        // Making a clean slate for the table
        tableView.getItems().clear();

        // Populating the table with data from the pie chart
        for (PieChart.Data pieData : data) {
            String countryName = pieData.getName();
            double gdp2022 = pieData.getPieValue();
            // Creating a DataItem and adding it to the table
            tableView.getItems().add(new DataItem(countryName, gdp2022));
        }

        // Setting up the column factories to display data from DataItem properties
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
    }

    // Handling the "Go Back" button click
    @FXML
    private void goBack() {
        // Switching back to the Pie Chart scene using a method from HelloController
        HelloController.switchToPieChartScene(stage);
    }

    // Data model class for representing items in the TableView
    public static class DataItem {
        private final String name;
        private final double value;

        public DataItem(String name, double value) {
            this.name = name;
            this.value = value;
        }

        // Getters for name and value properties
        public String getName() {
            return name;
        }

        public double getValue() {
            return value;
        }
    }
}
