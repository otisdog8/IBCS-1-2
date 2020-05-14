import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class RootJLab12 extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        TextField fahrenheit, celcius, kelvin;
        Text farenheitText, celciusText, kelvinText;
        GridPane root = new GridPane();

        fahrenheit = new TextField();
        celcius = new TextField();
        kelvin = new TextField();

        
        //These just do conversions. Round to 10s but I'll fix that before submitting.
        fahrenheit.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                String textF, textC, textK;
                int tempC, tempK;
                double tempF;
                textF = fahrenheit.getText();
                tempF = Double.parseDouble(textF);
                tempC = ((int) tempF - 32) * 5 / 9;
                tempK = tempC + 273;
                tempC = Math.round(tempC);
                tempK = Math.round(tempK);
                textC = Integer.toString(tempC);
                textK = Integer.toString(tempK);
                celcius.setText(textC);
                kelvin.setText(textK);
            }
        });

        celcius.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                String textF, textC, textK;
                int tempF, tempK;
                double tempC;
                textC = celcius.getText();
                tempC = Double.parseDouble(textC);
                tempF = (int) tempC * 9 / 5 + 32;
                tempK = (int) tempC + 273;
                tempF = Math.round(tempF);
                tempK = Math.round(tempK);
                textF = Integer.toString(tempF);
                textK = Integer.toString(tempK);
                fahrenheit.setText(textF);
                kelvin.setText(textK);
            }
        });

        kelvin.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                String textF, textC, textK;
                int tempF, tempC;
                double tempK;
                textK = kelvin.getText();
                tempK = Double.parseDouble(textK);
                tempC = (int) tempK - 273;
                tempF = tempC * 9 / 5 + 32;
                tempF = Math.round(tempF);
                tempC = Math.round(tempC);
                textF = Integer.toString(tempF);
                textC = Integer.toString(tempC);
                fahrenheit.setText(textF);
                celcius.setText(textC);
            }
        });

        root.add(fahrenheit, 2, 1);
        root.add(celcius, 2, 2);
        root.add(kelvin, 2, 3);



        farenheitText = new Text("Farenheit: ");
        celciusText = new Text("Celcius: ");
        kelvinText = new Text("Kelvin: ");

        root.add(farenheitText, 1, 1);
        root.add(celciusText, 1, 2);
        root.add(kelvinText, 1, 3);

        primaryStage.setScene(new Scene(root, 300, 300));
        primaryStage.show();
    }
}