import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.shape.*;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

public class RootJLab11 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Scale scale;
        Circle fillInCircle, transparentCircle;
        Text text;
        Line line;
        Rectangle transparentRectangle, fillInRectangle;
        Pane objectPane, buttonPane;
        Button scaleBtn, colorBtn;
        VBox root;
        
        primaryStage.setTitle("Color and Scale");   

        scale = new Scale(1,1);

        fillInCircle = new Circle(35, 35, 20);
        text = new Text(55,50,"This is drawn on the canvas");
        transparentCircle = new Circle(160,160,75);
        transparentCircle.setFill(null);
        transparentCircle.setStroke(Color.BLACK);
        line = new Line(5,5,150,150);
        transparentRectangle = new Rectangle(20, 160, 40, 160);
        transparentRectangle.setFill(null);
        transparentRectangle.setStroke(Color.BLACK);
        fillInRectangle = new Rectangle(85, 250, 225, 40);

        objectPane = new Pane();
        objectPane.getTransforms().add(scale);
        objectPane.getChildren().addAll(fillInCircle, text, transparentCircle, line, transparentRectangle, fillInRectangle);

        scaleBtn = new Button("Scale"); //Scale applies to the whole Pane, but color is different
        scaleBtn.setOnAction(new EventHandler<ActionEvent>(){
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

        scaleBtn.setLayoutX(100);
        scaleBtn.setLayoutY(0);

        colorBtn = new Button("Color");
        colorBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //figures out which color to fill
                Paint currentcolor =  ((Shape) objectPane.getChildren().get(0)).getFill();
                if (currentcolor.equals(Color.BLACK)) {
                    currentcolor = Color.RED;
                } else if (currentcolor.equals(Color.RED)) {
                    currentcolor = Color.BLUE;
                } else if (currentcolor.equals(Color.BLUE)) {
                    currentcolor = Color.GREEN;
                } else {
                    currentcolor = Color.BLACK;
                }

                int size = objectPane.getChildren().size();
                for (int i = 0; i < size; i++) {
                    Shape object = (Shape) objectPane.getChildren().get(i);
                    if (object.getFill() == null) {
                        object.setStroke(currentcolor);
                    } else {
                        object.setFill(currentcolor);
                    }
                }
            }
        });

        colorBtn.setLayoutX(200);
        colorBtn.setLayoutY(0);

        buttonPane = new Pane();
        buttonPane.getChildren().addAll(scaleBtn, colorBtn);

        root = new VBox();
        root.getChildren().addAll(objectPane,buttonPane);

        primaryStage.setScene(new Scene(root, 350, 350));
        primaryStage.show();
    }
}