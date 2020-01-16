import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.shape.*;

public class RootJLab11 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Color and Scale");   

        Scale scale = new Scale();
        scale.setX(1);
        scale.setY(1);

        Circle circle = new Circle(100);

        Pane scalePane = new Pane();
        scalePane.getTransforms().add(scale);
        scalePane.getChildren().add(circle);


        Button scalebtn = new Button("Scale");
        scalebtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                scale.setX(scale.getX()*2);
                scale.setY(scale.getY()*2);
                if (scale.getX() > 1) {
                    scale.setX(scale.getX()/8);
                    scale.setY(scale.getY()/8);
                }
            }
        });

        Pane buttonPane = new Pane();
        buttonPane.getChildren().addAll(scalebtn);



        VBox root = new VBox();
        root.getChildren().addAll(scalePane,buttonPane);

        primaryStage.setScene(new Scene(root, 300, 300));
        primaryStage.show();
    }
}