/* Caroline Lee
   9-27-2016
   Lab5a
   Stage One: Chessboard grid with one piece placed on board.
   Stage Two: Three hieroglyphic images pulled from files using random number
   generation. Three pics are displayed side by side. 
 */
package gameboards;

import java.io.FileInputStream;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class GameBoards extends Application {

    @Override //Stage One(chessboard)
    public void start(Stage stageOne) {
        GridPane grid1 = new GridPane();
        grid1.setMaxWidth(500);
        grid1.setMaxHeight(500);
        grid1.setAlignment(Pos.CENTER);//align to center of stage
        grid1.setPadding(new Insets(10, 10, 10, 10));

        //create chessboard
        int boardRows = 8;
        int boardColumns = 8;

        for (int r = 0; r < boardRows; r++) {
            for (int c = 0; c < boardColumns; c++) {
                HBox boardSquare = new HBox();
                boardSquare.setPrefSize(120, 120);
                if ((r + c) % 2 == 0) {
                    boardSquare.setStyle("-fx-background-color: white;");
                } else if ((r + c) % 2 != 0) {
                    boardSquare.setStyle("-fx-background-color: black;");
                }
                grid1.add(boardSquare, r, c);
            }

        }
        //chess piece image
        try {
            HBox picFrame = new HBox();
            FileInputStream f = new FileInputStream("images/clipart-knight.png");
            Image image = new Image(f);
            //set image for viewing
            ImageView iv = new ImageView(image);
            iv.setImage(image);
            //bound to grid1 width and height for proportion
            iv.fitWidthProperty().bind(grid1.widthProperty().divide(8));
            iv.fitHeightProperty().bind(grid1.heightProperty().divide(8));
            picFrame.getChildren().add(iv);
            grid1.add(picFrame, 1, 7);//piece placed on grid at (1, 7)
        } catch (Exception ex) {
            ex.getMessage();
        }
        HBox gridContainer = new HBox();//container for whole grid1, aids with sizing
        gridContainer.setMaxSize(500, 500);
        gridContainer.getChildren().add(grid1);
        gridContainer.setAlignment(Pos.CENTER);
        Scene sceneOne = new Scene(gridContainer, 500, 500);
        stageOne.setWidth(500);
        stageOne.setHeight(500);
        stageOne.setTitle("Chessboard");
        stageOne.setScene(sceneOne);
        stageOne.show();

        ////Stage Two////
        Stage stageTwo = new Stage();
        GridPane grid2 = new GridPane();
        grid2.setMaxSize(250, 200);
        grid2.setAlignment(Pos.CENTER);//align to center of stage
        grid2.setPadding(new Insets(20, 20, 20, 20));

        //switch case - get pic based on random number
        try {
            int picNum = 3;
            for (int i = 0; i < picNum; i++) {
                HBox picBox = new HBox();
                int numGeneration = (int) (Math.random() * 5);
                switch (numGeneration) {
                    case 0: {
                        FileInputStream f = new FileInputStream("images/cup.png");
                        Image image = new Image(f);
                        //set image for viewing
                        ImageView iv = new ImageView(image);
                        iv.setImage(image);
                        iv.fitWidthProperty().bind(grid2.widthProperty().divide(3));
                        iv.fitHeightProperty().bind(grid2.heightProperty().divide(3));
                        picBox.getChildren().add(iv);
                        grid2.add(picBox, i, 0);
                        break;
                    }
                    case 1: {
                        FileInputStream f = new FileInputStream("images/Eye_of_Horus.png");
                        Image image = new Image(f);
                        //set image for viewing
                        ImageView iv = new ImageView(image);
                        iv.setImage(image);
                        iv.fitWidthProperty().bind(grid2.widthProperty().divide(3));
                        iv.fitHeightProperty().bind(grid2.heightProperty().divide(3));
                        picBox.getChildren().add(iv);
                        grid2.add(picBox, i, 0);
                        break;
                    }
                    case 2: {
                        FileInputStream f = new FileInputStream("images/fish.png");
                        Image image = new Image(f);
                        //set image for viewing
                        ImageView iv = new ImageView(image);
                        iv.setImage(image);
                        iv.fitWidthProperty().bind(grid2.widthProperty().divide(3));
                        iv.fitHeightProperty().bind(grid2.heightProperty().divide(3));
                        picBox.getChildren().add(iv);
                        grid2.add(picBox, i, 0);
                        break;
                    }
                    case 3: {
                        FileInputStream f = new FileInputStream("images/Hieroglyphics_L.png");
                        Image image = new Image(f);
                        //set image for viewing
                        ImageView iv = new ImageView(image);
                        iv.setImage(image);
                        iv.fitWidthProperty().bind(grid2.widthProperty().divide(3));
                        iv.fitHeightProperty().bind(grid2.heightProperty().divide(3));
                        picBox.getChildren().add(iv);
                        grid2.add(picBox, i, 0);
                        break;
                    }
                    case 4: {
                        FileInputStream f = new FileInputStream("images/Hieroglyphics_W_or_U.png");
                        Image image = new Image(f);
                        //set image for viewing
                        ImageView iv = new ImageView(image);
                        iv.setImage(image);
                        iv.fitWidthProperty().bind(grid2.widthProperty().divide(3));
                        iv.fitHeightProperty().bind(grid2.heightProperty().divide(3));
                        picBox.getChildren().add(iv);
                        grid2.add(picBox, i, 0);
                        break;
                    }
                    default:
                        break;
                }
            }
        } catch (Exception ex) {
            ex.getMessage();
        }

        Scene sceneTwo = new Scene(grid2, 300, 200);
        stageTwo.setTitle("Hieroglyphics");
        stageTwo.setScene(sceneTwo);
        stageTwo.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
