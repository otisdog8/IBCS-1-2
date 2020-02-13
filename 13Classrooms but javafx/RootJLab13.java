import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Menu;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
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
        Classroom classroom = new Classroom();

        TableView<Student> studentview = generateTable(classroom);

        root = new Vbox(topbar, studentview);

        primaryStage.setScene(new Scene(root, 300, 300));
        primaryStage.show();
    }

    public TableView<Student> generateTable(Classroom classroom) {
        TableView<Student> studentview; //Note: we want to change the TableRow/TableColumn/Cell to make it work in a specific way
        //CheckBoxTableCell x
        //PropertyValueFactory

        TableColumn<Student, String> firstNameColumn, lastNameColumn, genderColumn;
        TableColumn<Student, Integer> idColumn, gradeColumn;
        TableColumn<Student, CheckBox> selectColumn;

        studentview.setItems(classroom.getStudents());

        Callback<TableColumn, TableCell> cellFactory = new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn c) {
                return new TextFieldTableCell();
            }
        };

        idColumn = new TableColumn<Student,Integer>("ID");
        idColumn.setSortable(true);
        idColumn.setCellValueFactory(new PropertyValueFactory("ID"));
        idColumn.setCellFactory(cellFactory);

        gradecolumn = new TableColumn<Student,Integer>("Grade");
        gradecolumn.setSortable(true);
        gradecolumn.setCellValueFactory(new PropertyValueFactory("grade"));
        gradecolumn.setCellFactory(cellFactory);

        firstNameColumn = new TableColumn<Student,String>("First Name");
        firstNameColumn.setSortable(true);
        firstNameColumn.setCellValueFactory(new PropertyValueFactory("firstName"));
        firstNameColumn.setCellFactory(cellFactory);

        lastNameColumn = new TableColumn<Student,String>("Last Name");
        lastNameColumn.setSortable(true);
        lastNameColumn.setCellValueFactory(new PropertyValueFactory("lastName"));
        lastNameColumn.setCellFactory(cellFactory);

        genderColumn = new TableColumn<Student,String>("Gender");
        genderColumn.setSortable(true);
        genderColumn.setCellValueFactory(new PropertyValueFactory("gender"));
        genderColumn.setCellFactory(cellFactory);

        return studentview;
    }
}

