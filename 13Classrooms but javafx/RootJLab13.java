import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.event.EventType;

public class RootJLab13 extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        VBox root;
        MenuBar topbar;
        Classroom classroom;
        try {
            classroom = new Classroom("classlist.txt");
        }
        catch (FileNotFoundException e) {
            classroom = new Classroom();
        }

        //Make top bar to handle add, delete, find: distributions: save/loading
        MenuBar menu = generateMenu(classroom);
        TableView<Student> studentview = generateTable(classroom);

        root = new VBox(menu, studentview);

        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }

    private MenuBar generateMenu(Classroom classroom) {
        MenuBar menuBar = new MenuBar();

        Menu students = new Menu("Students");
        
        MenuItem add = new MenuItem("Add"); //Open popup window
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                final Stage dialog = new Stage();
                VBox dialogVbox = new VBox(20);
                dialogVbox.getChildren().add(new Text("This is a Dialog"));
                Scene dialogScene = new Scene(dialogVbox, 300, 200);
                dialog.setScene(dialogScene);
                dialog.show();            
            }
        });

        MenuItem delete = new MenuItem("Delete selected"); //Call function on classroom
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                classroom.deleteSelected();
            }
        });

        MenuItem find = new MenuItem("Find"); //Op
        find.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                final Stage dialog = new Stage();
                VBox dialogVbox = new VBox(20);
                dialogVbox.getChildren().add(new Text("This is a Dialog"));
                Scene dialogScene = new Scene(dialogVbox, 300, 200);
                dialog.setScene(dialogScene);
                dialog.show();            
            }
        });

        students.getItems().addAll(add, delete, find);

        Menu statistics = new Menu("Statistics");

        MenuItem gender = new MenuItem("Gender"); //Op
        gender.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                final Stage dialog = new Stage();
                VBox dialogVbox = new VBox(20);
                dialogVbox.getChildren().add(new Text("This is a Dialog"));
                Scene dialogScene = new Scene(dialogVbox, 300, 200);
                dialog.setScene(dialogScene);
                dialog.show();            
            }
        });

        MenuItem name = new MenuItem("Name"); //Op
        name.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                final Stage dialog = new Stage();
                VBox dialogVbox = new VBox(20);
                dialogVbox.getChildren().add(new Text("This is a Dialog"));
                Scene dialogScene = new Scene(dialogVbox, 300, 200);
                dialog.setScene(dialogScene);
                dialog.show();            
            }
        });

        MenuItem grade = new MenuItem("Grade"); //Op
        grade.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                final Stage dialog = new Stage();
                VBox dialogVbox = new VBox(20);
                dialogVbox.getChildren().add(new Text("This is a Dialog"));
                Scene dialogScene = new Scene(dialogVbox, 300, 200);
                dialog.setScene(dialogScene);
                dialog.show();            
            }
        });

        statistics.getItems().addAll(gender, name, grade);
        
        Menu saving = new Menu("Saving");


        MenuItem save = new MenuItem("Save"); //Op
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                final Stage dialog = new Stage();
                VBox dialogVbox = new VBox(20);
                dialogVbox.getChildren().add(new Text("This is a Dialog"));
                Scene dialogScene = new Scene(dialogVbox, 300, 200);
                dialog.setScene(dialogScene);
                dialog.show();            
            }
        });

        MenuItem load = new MenuItem("Load"); //Op
        load.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                final Stage dialog = new Stage();
                VBox dialogVbox = new VBox(20);
                dialogVbox.getChildren().add(new Text("This is a Dialog"));
                Scene dialogScene = new Scene(dialogVbox, 300, 200);
                dialog.setScene(dialogScene);
                dialog.show();            
            }
        });

        saving.getItems().addAll(save, load);

        menuBar.getMenus().addAll(students, statistics, saving);
        return menuBar;
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

        selectColumn = generateSelectColumn(classroom);

        idColumn = generateIntColumn("ID", "ID", classroom);

        gradeColumn = generateIntColumn("Grade", "grade", classroom);

        firstNameColumn = generateStringColumn("First Name", "firstName", classroom);

        lastNameColumn = generateStringColumn("Last Name", "lastName", classroom);

        genderColumn = generateStringColumn("Gender", "gender", classroom);

        studentview.getColumns().addAll(selectColumn, idColumn, gradeColumn, firstNameColumn, lastNameColumn, genderColumn);

        return studentview;
    }

    private TableColumn<Student, Boolean> generateSelectColumn(Classroom classroom) {
        TableColumn<Student, Boolean> selectColumn;

        Callback<Integer, ObservableValue<Boolean>> studentSelectInfo = new Callback<Integer,ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(Integer i) {
                return classroom.getStudents().get(i).selectedProperty();
            }
        };

        Callback<TableColumn<Student,Boolean>, TableCell<Student,Boolean>> cellFactorySelect = new Callback<TableColumn<Student,Boolean>, TableCell<Student,Boolean>>() {
            @Override
            public TableCell<Student,Boolean> call(TableColumn<Student,Boolean> c) {
                return new CheckBoxTableCell<Student,Boolean>(studentSelectInfo);
            }
        };


        selectColumn = new TableColumn<Student,Boolean>("");
        selectColumn.setCellFactory(cellFactorySelect);

        return selectColumn;
    }

    private TableColumn<Student, Integer> generateIntColumn(String name, String property, Classroom classroom) {
        TableColumn<Student,Integer> intColumn;

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

        intColumn = new TableColumn<Student,Integer>(name);
        intColumn.setSortable(true);
        intColumn.setEditable(true);
        intColumn.setCellValueFactory(new PropertyValueFactory<Student,Integer>(property));
        intColumn.setCellFactory(cellFactoryInteger);
        EventHandler<CellEditEvent<Student,Integer>> intColumnEvent = intColumn.getOnEditCommit();
        intColumn.setOnEditCommit( new EventHandler<CellEditEvent<Student,Integer>>() {
            @Override
            public void handle(CellEditEvent<Student,Integer> event) {
                intColumnEvent.handle(event);
                classroom.save();
            }
        });
        return intColumn;
    }

    private TableColumn<Student, String> generateStringColumn(String name, String property, Classroom classroom) {
        TableColumn<Student, String> strColumn;

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

        strColumn = new TableColumn<Student,String>(name);
        strColumn.setSortable(true);
        strColumn.setEditable(true);
        strColumn.setCellValueFactory(new PropertyValueFactory<Student,String>(property));
        strColumn.setCellFactory(cellFactoryString);
        EventHandler<CellEditEvent<Student,String>> strColumnEvent = strColumn.getOnEditCommit();
        strColumn.setOnEditCommit( new EventHandler<CellEditEvent<Student,String>>() {
            @Override
            public void handle(CellEditEvent<Student,String> event) {
                strColumnEvent.handle(event);
                classroom.save();
            }
        });

        return strColumn;
    }

    public static void launch(String[] args) {
        Application.launch(args);
    }
}

