/* Caroline Lee
   9-27-2016
   Lab5a
   Stage One: 10X10 grid of clickable buttons. They don't do anything yet.
   Stage Two: 10X10 grid of randomly generated 0s and 1s in textFields.
 */


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Grids extends Application {

    //Stage One
    @Override
    public void start(Stage stageOne) {
        GridPane grid1 = new GridPane();
        grid1.setAlignment(Pos.CENTER);
        grid1.setPadding(new Insets(10, 10, 10, 10));
        //grid.setGridLinesVisible(true);//see grid1

        //Loop to populate button columns and rows
        int buttonLines = 10;
        int buttonColumns = 10;
        for (int r = 0; r < buttonLines; r++) {
            for (int c = 0; c < buttonColumns; c++) {
                Button btn = new Button("  ");
                btn.setPrefSize(50, 50);
                btn.setAlignment(Pos.CENTER);
                grid1.add(btn, c, r);
            }
        }

        Scene scene = new Scene(grid1);
        stageOne.setTitle("Click Board");
        stageOne.setScene(scene);
        stageOne.show();

        ////Stage Two////
        Stage stageTwo = new Stage();
        GridPane grid2 = new GridPane();
        grid2.setAlignment(Pos.CENTER);
        grid2.setPadding(new Insets(10, 10, 10, 10));

        //Loop to populate textField columns and rows
        int textLines = 10;
        int textColumns = 10;
        for (int r = 0; r < textLines; r++) {
            for (int c = 0; c < textColumns; c++) {
                TextField numField = new TextField();
                numField.setAlignment(Pos.CENTER);//put num in center of square
                numField.setPrefSize(40, 40);
                numField.setEditable(false);//makes text uneditable 
                grid2.add(numField, c, r);
                //randomly generate 0s and 1s
                numField.setText(Integer.toString((int) (Math.random() * 2)));

            }
        }

        Scene sceneTwo = new Scene(grid2);
        stageTwo.setTitle("Number Board");
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
