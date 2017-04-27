
/* Caroline Lee
   10-18-2016
   ReloadableHieroglyphics
   Stage:Hieroglyphics game from GameBoards.java, but with added event handler
        Event Handler: button to loadGrid screen with new random pic generation
 */

import java.util.Scanner;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ReloadableHieroglypics extends Application {

    @Override
    public void start(Stage primaryStage) {
        initialize(primaryStage);//initialize method to load stage and scene

    }

    public static void main(String[] args) {
        launch(args);
    }

    //method for executing main program
    public void initialize(Stage s) {

        Stage stage = new Stage();
        GridPane grid = new GridPane();
        grid.setMaxSize(250, 200);
        grid.setAlignment(Pos.CENTER);//align to center of stage
        grid.setPadding(new Insets(20, 20, 20, 20));

        //method to load grid with images
        loadGrid(grid);

        //create reload button
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        Button btReload = new Button("Reload");
        hBox.getChildren().addAll(btReload);
        grid.add(hBox, 1, 3);

        //converted to lambda because Netbeans
        btReload.setOnAction((ActionEvent e) -> {
            loadGrid(grid);
        }
        );

        Scene scene = new Scene(grid, 300, 300);
        stage.setWidth(300);
        stage.setHeight(300);
        stage.setTitle("Hieroglyphics");
        stage.setScene(scene);
        stage.show();

    }

    //method for loading the grid on the stage
    public void loadGrid(GridPane g) {
        int picNum = 3;
        for (int i = 0; i < picNum; i++) {
            int n = (int) (Math.random() * 44);//pick one of the images from site
            String extractedImage = getImage("http://www.pdclipart.org/displayimage.php?album=45&pos=" + n);
            HBox picBox = new HBox();
            Image image = new Image(extractedImage);
            //set image for viewing
            ImageView iv = new ImageView(image);
            iv.setImage(image);
            iv.fitWidthProperty().bind(g.widthProperty().divide(3));
            iv.fitHeightProperty().bind(g.heightProperty().divide(3));
            picBox.getChildren().add(iv);
            g.add(picBox, i, 0);
        }
    }

    //method for getting images from clipart site
    public String getImage(String s) {
        String imageURL = "";
        try {
            java.net.URL url = new java.net.URL(s);
            Scanner input = new Scanner(url.openStream());
            int current = 0;

            while (input.hasNext()) {
                String line = input.nextLine();
                current = line.indexOf("albums/Hieroglyphics", current);
                while (current > 0) {
                    int endIndex = line.indexOf(".png", current);
                    if (endIndex > 0) { // Ensure that a correct URL is found
                        imageURL = "http://www.pdclipart.org/" + line.substring(current, endIndex) + ".png";
                        current = line.indexOf("http:", endIndex);
                    } else {
                        current = -1;
                    }
                }
            }

        } catch (IOException ex) {
            ex.getMessage();
        }

        return imageURL;
    }

}
