import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class RootJLab13 extends Application {   
    public static void main(String[] args) {
        launch(args);
    }

    private int currentGridIndex = 0;
    private int newGridIndex = 1;
    private boolean playing = true;
    private Rectangle[][] gridCells;
    private boolean[][][] cells;

    private void life(Stage primarystage, int rectSize, int gridSize) {
        gridCells = new Rectangle[gridSize][gridSize];
        cells = new boolean[2][gridSize][gridSize];
        String cellNum = getText("How many cells do you want? (default " + Integer.toString(1000 * (int) Math.round(gridSize / 150d)) + ")");
        int numCells;

        
        GridPane gridPane = new GridPane();
        Random rand = new Random();

        getText("Use Space to pause, click cells to change from alive to dead. Text input here does not matter");

        try {
            numCells = Integer.parseInt(cellNum);
        }
        catch (NumberFormatException e) {
            numCells = 1000 * (int) Math.round(gridSize / 150d);
        }

        final int newNumCells = numCells;

        //Initializing required arrays
        for (int x = 0; x < gridSize; x++) {
            for (int y = 0; y < gridSize; y++) {
                cells[0][x][y] = false;
                cells[1][x][y] = false;
                final Rectangle thisRect = new Rectangle(rectSize, rectSize);
                thisRect.setFill(Color.TRANSPARENT);
                final int x1 = x;
                final int y1 = y;
                gridCells[x][y] = thisRect;
                //Implement color change on click
                gridCells[x][y].setOnMouseClicked((event) -> {
                    thisRect.setFill((thisRect.getFill() == Color.TRANSPARENT) ? Color.BLACK : Color.TRANSPARENT);
                    cells[currentGridIndex][x1][y1] = ((thisRect.getFill() == Color.TRANSPARENT) ? false : true);
                });
                gridPane.add(gridCells[x][y], x, y);
            }
        }

        while (numCells != 0) {
            int x = rand.nextInt(gridSize);
            int y = rand.nextInt(gridSize);

            if (!cells[0][x][y]) {
                numCells--;
                gridCells[x][y].setFill(Color.BLACK);
                cells[0][x][y] = true;
                cells[1][x][y] = true;
            }
        }

        KeyFrame cellUpdate = new KeyFrame(new Duration(10), event -> {
            //HashSet<boolean[][]> pastcells =  new HashSet<boolean[][]>();
            for (int x = 0; x < gridSize; x++) {
                for (int y = 0; y < gridSize; y++) {
                    //Neighbors
                    int neighbors = 0;
                    for (int i = -1; i < 2; i++) {
                        for (int j = -1; j < 2; j++) {
                            if (cells[currentGridIndex][(x + i > 0) ? (x + i) % gridSize: (x + i + gridSize) % gridSize][(y + j > 0) ? (y + j) % gridSize: (y + j + gridSize) % gridSize] == true && (i != 0 || j != 0)) {
                                neighbors++;
                            }
                        }
                    }

                    if (neighbors == 3) {
                        cells[newGridIndex][x][y] = true;
                        gridCells[x][y].setFill(Color.BLACK);
                    }// We just do nothing if neighbors = 2
                    else if (neighbors == 2 && cells[currentGridIndex][x][y]) {
                        cells[newGridIndex][x][y] = true;
                    }
                    else {
                        cells[newGridIndex][x][y] = false;
                        gridCells[x][y].setFill(Color.TRANSPARENT);
                    }
                    /* if (pastcells.contains(cells[currentGridIndex])) {
                        int goodNumCells = newNumCells;
                        for (int i = 0; i < gridSize; i++) {
                            for (int j = 0; j < gridSize; j++) {
                                cells[0][i][j] = false;
                                cells[1][i][j] = false;
                            }
                        }
                        while (goodNumCells != 0) {
                            int x1 = rand.nextInt(gridSize);
                            int y1 = rand.nextInt(gridSize);
                            
                            if (!cells[0][x1][y1]) {
                                goodNumCells--;
                                gridCells[x1][y1].setFill(Color.BLACK);
                                cells[0][x1][y1] = true;
                                cells[1][x1][y1] = true;
                            }
                        }
                        pastcells = new HashSet<boolean[][]>();
                        System.out.println("x");
                    }
                    else {
                        final boolean[][] cellState = new boolean[gridSize][gridSize];
                        for (int i = 0; i < gridSize; i++) {
                            for (int j = 0; j < gridSize; j++) {
                                cellState[i][j] = cells[currentGridIndex][i][j];
                            }
                        }
                        pastcells.add(cellState);
                    } */

                }
            }
            currentGridIndex = newGridIndex;
            newGridIndex = (newGridIndex - 1) * (newGridIndex - 1);
        });




        
        Timeline timeLine = new Timeline(cellUpdate);
        timeLine.setCycleCount(Timeline.INDEFINITE);
        timeLine.play();

        Scene scene = new Scene(gridPane);

        scene.setOnKeyReleased(event->{
            if (event.getCode() == KeyCode.SPACE) {
                if (playing) {
                    timeLine.stop();                    
                    playing = false;
                }
                else {
                    timeLine.play();
                    playing = true;
                }
            }
        });

        primarystage.setTitle("Life");

        primarystage.setScene(scene);;

        primarystage.show();
    }

    public void start(Stage primarystage) {
        String gridSize = getText("How large do you want your grid (150 default)");
        String rectSize = getText("How large do you want your rectangles (3 default)");
        int rectSizeInt;
        int gridSizeInt;

        try {
            gridSizeInt = Integer.parseInt(gridSize);
        }
        catch (NumberFormatException e) {
            gridSizeInt = 150;
        }

        try {
            rectSizeInt = Integer.parseInt(rectSize);
        }
        catch (NumberFormatException e) {
            rectSizeInt =3;
        }

        this.life(primarystage, rectSizeInt, gridSizeInt);
    }   

    private String getText(String prompt) {
        TextInputDialog test = new TextInputDialog();
        test.setHeaderText(prompt);
        test.showAndWait();
        return test.getEditor().getText();
    }
}