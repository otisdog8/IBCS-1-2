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
import javafx.util.StringConverter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class RootJLab13 extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        VBox root;
        MenuBar topbar;
        Classroom classroom = new Classroom();

        TableView<Student> studentview = generateTable(classroom);

        root = new VBox(studentview);

        primaryStage.setScene(new Scene(root, 300, 300));
        primaryStage.show();
    }

    public TableView<Student> generateTable(Classroom classroom) {
        TableView<Student> studentview = new TableView<Student>(); //Note: we want to change the TableRow/TableColumn/Cell to make it work in a specific way

        studentview.setEditable(true);
        //CheckBoxTableCell x
        //PropertyValueFactory

        TableColumn<Student, String> firstNameColumn, lastNameColumn, genderColumn;
        TableColumn<Student, Integer> idColumn, gradeColumn;
        TableColumn<Student, Boolean> selectColumn;

        studentview.setItems(classroom.getStudents());

        


        Callback<TableColumn<Student,String>, TableCell<Student,String>> cellFactoryString = new Callback<TableColumn<Student,String>, TableCell<Student,String>>() {
            @Override
            public TableCell<Student,String> call(TableColumn<Student,String> c) {
                TextFieldTableCell<Student,String> cell = new TextFieldTableCell<Student,String>();
                StringConverter<String> genericStringConverter = new StringConverter<String>() {
                    @Override
                    public String toString(String string) {
                        return string;
                    }

                    @Override
                    public String fromString(String string) {
                        return string;
                    }
                };
                cell.setEditable(true);
                cell.setConverter(genericStringConverter);
                return cell;
            }
        };

        Callback<TableColumn<Student,Integer>, TableCell<Student,Integer>> cellFactoryInteger = new Callback<TableColumn<Student,Integer>, TableCell<Student,Integer>>() {
            @Override
            public TableCell<Student,Integer> call(TableColumn<Student,Integer> c) {
                TextFieldTableCell<Student,Integer> cell = new TextFieldTableCell<Student,Integer>();
                StringConverter<Integer> genericStringConverter = new StringConverter<Integer>() {
                    @Override
                    public String toString(Integer integer) {
                        return Integer.toString(integer);
                    }

                    @Override
                    public Integer fromString(String string) {
                        return Integer.parseInt(string);
                    }
                };
                cell.setEditable(true);
                cell.setConverter(genericStringConverter);
                return cell;
            }
        };

        Callback<TableColumn<Student,Integer>, TableCell<Student,Integer>> cellFactorySelect = new Callback<TableColumn<Student,Integer>, TableCell<Student,Integer>>() {
            @Override
            public TableCell<Student,Integer> call(TableColumn<Student,Integer> c) {
                return new TextFieldTableCell<Student,Integer>();
            }
        };


        selectColumn = new TableColumn<Student,Boolean>();

        idColumn = new TableColumn<Student,Integer>("ID");
        idColumn.setSortable(true);
        idColumn.setEditable(true);
        idColumn.setCellValueFactory(new PropertyValueFactory<Student,Integer>("ID"));
        idColumn.setCellFactory(cellFactoryInteger);

        gradeColumn = new TableColumn<Student,Integer>("Grade");
        gradeColumn.setSortable(true);
        gradeColumn.setEditable(true);
        gradeColumn.setCellValueFactory(new PropertyValueFactory<Student,Integer>("grade"));
        gradeColumn.setCellFactory(cellFactoryInteger);

        firstNameColumn = new TableColumn<Student,String>("First Name");
        firstNameColumn.setSortable(true);
        firstNameColumn.setEditable(true);
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("firstName"));
        firstNameColumn.setCellFactory(cellFactoryString);

        lastNameColumn = new TableColumn<Student,String>("Last Name");
        lastNameColumn.setSortable(true);
        lastNameColumn.setEditable(true);
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("lastName"));
        lastNameColumn.setCellFactory(cellFactoryString);

        genderColumn = new TableColumn<Student,String>("Gender");
        genderColumn.setSortable(true);
        genderColumn.setEditable(true);
        genderColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("gender"));
        genderColumn.setCellFactory(cellFactoryString);

        studentview.getColumns().addAll(selectColumn, idColumn, gradeColumn, firstNameColumn, lastNameColumn, genderColumn);

        return studentview;
    }
}

