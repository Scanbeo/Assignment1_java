package org.example.assignment1;

import javafx.application.Application; // Extending this class makes the program a JavaFX application
import javafx.fxml.FXMLLoader; // Used to load the FXML file for the UI
import javafx.scene.Parent; // Represents the root node of the scene graph
import javafx.scene.Scene; // Represents the scene to be displayed on the stage
import javafx.scene.image.Image; // Used to load the application icon
import javafx.stage.Stage; // Represents the main window of the application

import java.io.IOException; // This exception might be thrown when loading the FXML file

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Loading the FXML file and creating the scene
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 600, 400); // Setting the scene size

        // Setting the stage title and icon
        stage.setTitle("Pie Chart Application");
        stage.getIcons().add(new Image(HelloApplication.class.getResourceAsStream("icon.png"))); // Setting the application icon

        // Setting the scene on the stage and showing the stage
        stage.setScene(scene);
        stage.show();

        // Providing the stage reference to the controller
        HelloController controller = fxmlLoader.getController();
        controller.setStage(stage);
    }

    public static void main(String[] args) {
        launch(); // Launching the application using the JavaFX Launcher
    }
}
