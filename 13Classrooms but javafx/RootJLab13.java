//So many imports... how many are actually used?
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
            //Might implement something where it has a different default startign file
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
        
        MenuItem add = new MenuItem("Add");
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Stage dialog = new Stage();

                GridPane grid = new GridPane();
                Label firstName = new Label("First Name:");
                grid.add(firstName, 0, 1);
                Label lastName = new Label("Last Name:");
                grid.add(lastName, 0, 2);
                Label grade = new Label("Grade:");
                grid.add(grade, 0, 3);
                Label studentID = new Label("Student ID:");
                grid.add(studentID, 0, 4);
                Label gender = new Label("Gender:");
                grid.add(gender, 0, 5);
                TextField fTextField = new TextField("John");
                grid.add(fTextField, 1, 1);
                TextField lTextField = new TextField("Smith");
                grid.add(lTextField, 1, 2);
                TextField grTextField = new TextField("11");
                grid.add(grTextField, 1, 3);
                TextField sTextField = new TextField("111111");
                grid.add(sTextField, 1, 4);
                TextField geTextField = new TextField("Male");
                grid.add(geTextField, 1, 5);
                Scene dialogScene = new Scene(grid, 300, 200);
                Button submitButton = new Button("Submit");
                submitButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        //Just generates the new student
                        classroom.add(new String[]{sTextField.getText(), grTextField.getText(), lTextField.getText(), fTextField.getText(), geTextField.getText()});
                        dialog.close();
                    }
                });
                grid.add(submitButton, 3, 6);
                dialog.setScene(dialogScene);
                dialog.show();            
            }
        });

        MenuItem delete = new MenuItem("Delete selected students"); //Call function on classroom
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                classroom.deleteSelected();
            }
        });

        MenuItem find = new MenuItem("Find"); //Op
        find.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Stage dialog = new Stage();
                Pane dialogPane = new Pane();
                //Makes a dialog and only searches if no empty prompt, also doesn't error if student not found, just returns a generic student (can change behavior)
                TextInputDialog alert = new TextInputDialog("123456");
                alert.setTitle("Search");
                alert.setContentText("Enter a student ID or student last/first name: ");
                Optional<String> result = alert.showAndWait();
                if (result.isPresent()) {
                    Student studentFound;
                    try {//Tries ID first, but goes to name if it's not a number
                        studentFound = classroom.search(Integer.parseInt(result.get()));
                    }
                    catch (NumberFormatException e1) {
                        studentFound = classroom.search(result.get());
                    }
                    dialogPane.getChildren().add(new Text(studentFound.toHumanReadableString()));
                    Scene dialogScene = new Scene(dialogPane, 200, 100);
                    dialog.setScene(dialogScene);
                    dialog.show();  
                }
           
            }
        });

        MenuItem sortID = new MenuItem("Sort backing array by ID"); //Op
        sortID.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                classroom.sortbyid();    
            }
        });

        MenuItem sortName = new MenuItem("Sort backing array by lastname"); //Op
        sortName.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                classroom.sortbyname();
            }
        });

        students.getItems().addAll(add, delete, find, sortID, sortName);

        Menu statistics = new Menu("Statistics");

        MenuItem gender = new MenuItem("Gender"); //Pie Chart
        gender.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Stage dialog = new Stage();
                Pane dialogPane = new Pane();
                PieChart genderChart = new PieChart();
                int[] genderStats = classroom.genderstatistics();
                String[] genders = new String[]{"Female ", "Male "};
                for (int i = 0; i < 2; i++) {
                    genderChart.getData().add(new PieChart.Data(genders[i] + Integer.toString(genderStats[i]) + " students", genderStats[i]));
                }
                dialogPane.getChildren().add(genderChart);
                Scene dialogScene = new Scene(dialogPane, 500, 400);
                dialog.setScene(dialogScene);
                dialog.show();            
            }
        });

        MenuItem name = new MenuItem("Name"); //Bar Graph (because 26 Vs. 2 or 4)
        name.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                CategoryAxis xAxis = new CategoryAxis();
                NumberAxis yAxis = new NumberAxis();
                xAxis.setLabel("First letter of last name");
                yAxis.setLabel("Number of people");
                BarChart<String, Number> lastNameChart = new BarChart<String, Number>(xAxis, yAxis);
                int[] namestats = classroom.namestatistics();
                XYChart.Series data = new XYChart.Series();
                data.setName("Data");
                for (int i =  0; i < 26; i++) {
                    data.getData().add(new XYChart.Data(Character.toString((char) (i + (int) 'A')), namestats[i]));
                }

                Stage dialog = new Stage();
                Pane dialogPane = new Pane();
                lastNameChart.getData().add(data);
                dialogPane.getChildren().add(lastNameChart);
                Scene dialogScene = new Scene(dialogPane, 500, 400);
                dialog.setScene(dialogScene);
                dialog.show();            
            }
        });

        MenuItem grade = new MenuItem("Grade"); //Pie Chart
        grade.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Stage dialog = new Stage();
                Pane dialogPane = new Pane();
                PieChart gradeChart = new PieChart();
                int[] gradeStats = classroom.gradestatistics();
                String[] grades = new String[]{"Freshmen ", "Sophomores ", "Juniors ", "Seniors "};
                for (int i = 0; i < 4; i++) {
                    gradeChart.getData().add(new PieChart.Data(grades[i] + Integer.toString(gradeStats[i]) + " students", gradeStats[i]));
                }
                dialogPane.getChildren().add(gradeChart);
                Scene dialogScene = new Scene(dialogPane, 500, 400);
                dialog.setScene(dialogScene);
                dialog.show();            
            }
        });

        statistics.getItems().addAll(gender, name, grade);
        
        Menu saving = new Menu("File I/O");


        MenuItem saveText = new MenuItem("Save as text"); //Op
        saveText.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Stage dialog = new Stage();
                File file = new FileChooser().showSaveDialog(dialog);
                classroom.save(file.getAbsolutePath(), true);
            }
        });

        MenuItem saveJava = new MenuItem("Save as object file"); //Op
        saveJava.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Stage dialog = new Stage();
                File file = new FileChooser().showSaveDialog(dialog);
                classroom.save(file.getAbsolutePath(), false);
            }
        });


        MenuItem load = new MenuItem("Load"); //Op
        load.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Stage dialog = new Stage();
                File file = new FileChooser().showOpenDialog(dialog);
                if (file != null) {
                    try {
                        classroom.load(file.getAbsolutePath());
                    }
                    catch (FileNotFoundException e1) {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error!");
                        alert.setHeaderText("File not found or formatted incorrectly");
                        alert.showAndWait();
                    }
                }
            }
        });

        saving.getItems().addAll(saveText, saveJava, load);

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

        //Improve code reuse

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

