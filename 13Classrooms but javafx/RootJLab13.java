import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableView;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Menu;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class RootJLab13 extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        Vbox root;
        MenuBar topbar;
        TableView<Student, CheckBox> studentview; //Note: we want to change the TableRow/TableColumn/Cell to make it work in a specific way
        //CheckBoxTableCell x


        root = new Vbox(topbar, studentview);

        primaryStage.setScene(new Scene(root, 300, 300));
        primaryStage.show();
    }
}