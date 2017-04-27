

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/* Caroline Lee
   10-25-2016
   Mouse Event Click
   Stage: Create and remove points in a pane using a mouse click
        Left click adds a point
        Right click removes a point
 */
public class PanePoints extends Application {

    @Override
    public void start(Stage primaryStage) {

        Pane pane = new Pane();

        Scene scene = new Scene(pane, 300, 200);
        primaryStage.setTitle("Mouse Event Click");
        primaryStage.setScene(scene);
        primaryStage.show();

        //set event -- primary button is L, secondary button is R
        scene.setOnMousePressed(e -> {
            Circle circle = new Circle();
            if (e.isPrimaryButtonDown()) {

                circle.setRadius(3);
                circle.setFill(Color.BLACK);
                pane.getChildren().addAll(circle);
                circle.setCenterX(e.getX());
                circle.setCenterY(e.getY());
            } else if (e.isSecondaryButtonDown()) {
                pane.getChildren().remove(e.getTarget());//removes node where clicked
            }
        });

    }

    public static void main(String[] args) {
        launch(args);
    }
}
