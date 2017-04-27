

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/* Caroline Lee
   10-25-2016
   Rectangle Generator
   Stage: User gives coordinates of top left corner, and h & w, for two rectangles
        Rectangles are displayed on a pane
        Application reports whether rectangles intersect or not
 */
public class RectanglesInPane extends Application {

    double x1 = 0;
    double y1 = 0;
    double w1 = 0;
    double h1 = 0;

    double x2 = 0;
    double y2 = 0;
    double w2 = 0;
    double h2 = 0;

    @Override
    public void start(Stage primaryStage) {

        //for user input
        GridPane inputScreen = new GridPane();
        inputScreen.setAlignment(Pos.CENTER);
        inputScreen.setHgap(10);
        inputScreen.setVgap(10);
        inputScreen.setPadding(new Insets(25, 25, 25, 25));

        ///Input for Rectangle 1///
        Label rectangle1 = new Label("Rectangle 1");
        inputScreen.add(rectangle1, 0, 0);
        inputScreen.setColumnSpan(rectangle1, 2);

        Label rectangle1X = new Label("Start X:");
        inputScreen.add(rectangle1X, 0, 1);

        TextField inputrect1X = new TextField();
        inputrect1X.setMaxSize(40, 40);
        inputScreen.add(inputrect1X, 1, 1);

        Label rectangle1Y = new Label("Start Y:");
        inputScreen.add(rectangle1Y, 0, 2);

        TextField inputrect1Y = new TextField();
        inputrect1Y.setMaxSize(40, 40);
        inputScreen.add(inputrect1Y, 1, 2);

        Label rectangle1W = new Label("Width:");
        inputScreen.add(rectangle1W, 0, 3);

        TextField inputrect1W = new TextField();
        inputrect1W.setMaxSize(40, 40);
        inputScreen.add(inputrect1W, 1, 3);

        Label rectangle1H = new Label("Height:");
        inputScreen.add(rectangle1H, 0, 4);

        TextField inputrect1H = new TextField();
        inputrect1H.setMaxSize(40, 40);
        inputScreen.add(inputrect1H, 1, 4);

        ///Input for Rectangle 2///
        Label rectangle2 = new Label("Rectangle 2");
        inputScreen.add(rectangle2, 3, 0);
        inputScreen.setColumnSpan(rectangle2, 2);

        Label rectangle2X = new Label("Start X:");
        inputScreen.add(rectangle2X, 3, 1);

        TextField inputrect2X = new TextField();
        inputrect2X.setMaxSize(40, 40);
        inputScreen.add(inputrect2X, 4, 1);

        Label rectangle2Y = new Label("Start Y:");
        inputScreen.add(rectangle2Y, 3, 2);

        TextField inputrect2Y = new TextField();
        inputrect2Y.setMaxSize(40, 40);
        inputScreen.add(inputrect2Y, 4, 2);

        Label rectangle2W = new Label("Width:");
        inputScreen.add(rectangle2W, 3, 3);

        TextField inputrect2W = new TextField();
        inputrect2W.setMaxSize(40, 40);
        inputScreen.add(inputrect2W, 4, 3);

        Label rectangle2H = new Label("Height:");
        inputScreen.add(rectangle2H, 3, 4);

        TextField inputrect2H = new TextField();
        inputrect2H.setMaxSize(40, 40);
        inputScreen.add(inputrect2H, 4, 4);

        //rectangle generation button
        Button generate = new Button("Generate");
        inputScreen.add(generate, 3, 5);

        //pane for rectangles
        Pane rectanglePane = new Pane();
        Label intersectYorN = new Label();

        generate.setOnMouseClicked((MouseEvent e) -> {
            try {//catch letters entered instead of numbers
                x1 = Double.parseDouble(inputrect1X.getText());
                y1 = Double.parseDouble(inputrect1Y.getText());
                w1 = Double.parseDouble(inputrect1W.getText());
                h1 = Double.parseDouble(inputrect1H.getText());

                x2 = Double.parseDouble(inputrect2X.getText());
                y2 = Double.parseDouble(inputrect2Y.getText());
                w2 = Double.parseDouble(inputrect2W.getText());
                h2 = Double.parseDouble(inputrect2H.getText());

                //Rectangles
                Rectangle r1 = new Rectangle(x1, y1, w1, h1);
                r1.setFill(Color.GREEN);
                Rectangle r2 = new Rectangle(x2, y2, w2, h2);
                r2.setFill(Color.PURPLE);
                rectanglePane.getChildren().addAll(r1, r2);

                //label to tell user if rectangles intersect - check edges
                if (((x1 + w1) < x2) || ((x2 + w2) < x1) || ((y1 + h1) < y2) || ((y2 + h2) < y1)) {
                    intersectYorN.setText("Rectangles do not intersect.");
                } else {
                    intersectYorN.setText("Rectangles intersect.");
                }

            } catch (Exception ex) {
                intersectYorN.setText("Enter numbers only.");
            }
        });

        //root node//
        BorderPane root = new BorderPane();
        root.setLeft(inputScreen);
        root.setCenter(rectanglePane);
        root.setBottom(intersectYorN);

        Scene scene = new Scene(root, 550, 280);
        primaryStage.setTitle("Rectangle Generator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
