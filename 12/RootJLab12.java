import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
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
        VBox inputs, labels;
        HBox root;

        fahrenheit = new TextField();
        celcius = new TextField();
        kelvin = new TextField();

        
        
        fahrenheit.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                String textF, textC, textK;
                double tempF, tempC, tempK;
                textF = fahrenheit.getText();
                tempF = Double.parseDouble(textF);
                tempC = (tempF - 32.0) * 5.0 / 9.0;
                tempK = tempC + 273.15;
                tempC = Math.round(tempC * 10.0) / 10.0;
                tempK = Math.round(tempK * 10.0) / 10.0;
                textC = Double.toString(tempC);
                textK = Double.toString(tempK);
                celcius.setText(textC);
                kelvin.setText(textK);
            }
        });

        celcius.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                String textF, textC, textK;
                double tempF, tempC, tempK;
                textC = celcius.getText();
                tempC = Double.parseDouble(textC);
                tempF = tempC * 9.0 / 5.0 + 32.0;
                tempK = tempC + 273.15;
                tempF = Math.round(tempF * 10.0) / 10.0;
                tempK = Math.round(tempK * 10.0) / 10.0;
                textF = Double.toString(tempF);
                textK = Double.toString(tempK);
                fahrenheit.setText(textF);
                kelvin.setText(textK);
            }
        });

        kelvin.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                String textF, textC, textK;
                double tempF, tempC, tempK;
                textK = kelvin.getText();
                tempK = Double.parseDouble(textK);
                tempC = tempK - 273.15;
                tempF = tempC * 9.0 / 5.0 + 32.0;
                tempF = Math.round(tempF * 10.0) / 10.0;
                tempC = Math.round(tempC * 10.0) / 10.0;
                textF = Double.toString(tempF);
                textC = Double.toString(tempC);
                fahrenheit.setText(textF);
                celcius.setText(textC);
            }
        });

        inputs = new VBox(fahrenheit,celcius,kelvin);

        farenheitText = new Text("Farenheit");
        celciusText = new Text("Celcius");
        kelvinText = new Text("Kelvin");

        labels = new VBox(farenheitText, celciusText, kelvinText);

        root = new HBox(labels, inputs);

        primaryStage.setScene(new Scene(root, 300, 300));
        primaryStage.show();
    }
}