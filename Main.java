package notessecured;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author royal
 * Main class of the application.
 * It starts the JavaFX application and loads the main window.
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the FXML file for the main view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/notessecured/views/main-view.fxml"));
            
            // Create a scene using the loaded FXML file
            Scene scene = new Scene(loader.load());

            
            // Set up the main window (Stage)
            primaryStage.setScene(scene);
            primaryStage.setTitle("Simple Notes App");
            primaryStage.show(); // Display the window
        } catch (Exception e) {
            e.printStackTrace(); // Print error if something goes wrong
        }
    }

    /**
     * Main method to launch the JavaFX application.
     */
    public static void main(String[] args) {
        launch(args); // Starts the application
    }
}
