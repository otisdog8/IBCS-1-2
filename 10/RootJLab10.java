
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
 
public class RootJLab10 extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        Button java;
        Button onoff;
        Pane root;

        primaryStage.setTitle("Hello World!");
        
        java = new Button();
        java.setText("Java");
        java.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                Alert java = new Alert(AlertType.INFORMATION);
                java.setContentText("Make Java Great Again!");
                java.showAndWait();
            }
        });
        java.setLayoutX(60);
        java.setLayoutY(60);

        onoff = new Button();
        onoff.setText("On");
        onoff.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                if (onoff.getText().equals("On")) {
                    onoff.setText("Off");
                } else if (onoff.getText().equals("Off")) {
                    onoff.setText("On");
                }
            }
        });
        onoff.setLayoutX(60);
        onoff.setLayoutY(120);

        root = new Pane();
        root.getChildren().add(java);
        root.getChildren().add(onoff);

        primaryStage.setScene(new Scene(root, 300, 300));
        primaryStage.show();
    }
}