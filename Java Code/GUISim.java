
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/* Caroline Lee
   10-25-2016
   Color Field Manipulation Tool
   Stage: Uses color display field
        Four sliders to manipulate rgb and opacity
        Numeric values cooresponding to current slider state displayed dynamically
 */
public class GUISim extends Application {

    int r = 0;
    int g = 0;
    int b = 0;
    double o = 0.5;

    @Override
    public void start(Stage primaryStage) {

        //slider for red  
        Slider redSlider = new Slider();
        redSlider.setShowTickLabels(true);
        redSlider.setShowTickMarks(true);
        redSlider.setMajorTickUnit(130);
        redSlider.setMax(255);
        redSlider.setOrientation(Orientation.VERTICAL);
        //slider for green
        Slider greenSlider = new Slider();
        greenSlider.setShowTickLabels(true);
        greenSlider.setShowTickMarks(true);
        greenSlider.setMax(255);
        greenSlider.setMajorTickUnit(130);
        greenSlider.setOrientation(Orientation.VERTICAL);
        //slider for blue
        Slider blueSlider = new Slider();
        blueSlider.setShowTickLabels(true);
        blueSlider.setShowTickMarks(true);
        blueSlider.setMax(255);
        blueSlider.setMajorTickUnit(130);
        blueSlider.setOrientation(Orientation.VERTICAL);
        //slider for opacity -horizontal
        Slider opacSlider = new Slider();
        opacSlider.setShowTickLabels(true);
        opacSlider.setShowTickMarks(true);
        opacSlider.setMajorTickUnit(0.5);
        opacSlider.setValue(0.5);
        opacSlider.setMax(1.0);

        //labels for current state of sliders
        Label rCurrent = new Label(Double.toString(redSlider.getValue()));
        Label gCurrent = new Label(Double.toString(greenSlider.getValue()));
        Label bCurrent = new Label(Double.toString(blueSlider.getValue()));
        Label oCurrent = new Label(Double.toString(opacSlider.getValue()));

        //color
        Color c = Color.rgb(r, g, b, o);

        //textfield to fill with color
        Rectangle colorField = new Rectangle(300, 150);
        colorField.setFill(c);

        //titles for sliders
        Label redTitle = new Label("R");
        Label greenTitle = new Label("G");
        Label blueTitle = new Label("B");
        Label opacTitle = new Label("Opacity");

        //pane to hold sliders
        GridPane colorSliderPane = new GridPane();
        colorSliderPane.add(redTitle, 0, 0);
        colorSliderPane.add(greenTitle, 1, 0);
        colorSliderPane.add(blueTitle, 2, 0);
        colorSliderPane.add(redSlider, 0, 1);
        colorSliderPane.add(greenSlider, 1, 1);
        colorSliderPane.add(blueSlider, 2, 1);
        colorSliderPane.add(rCurrent, 0, 2);
        colorSliderPane.add(gCurrent, 1, 2);
        colorSliderPane.add(bCurrent, 2, 2);

        //box to hold opacSlider and oCurrent
        VBox opacBox = new VBox();
        opacBox.getChildren().add(opacTitle);
        opacBox.getChildren().add(opacSlider);
        opacBox.getChildren().add(oCurrent);

        //listeners for slider' current values
        redSlider.valueProperty().addListener((ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) -> {
            rCurrent.setText(String.format("%.1f", new_val));
            r = new_val.intValue();
            colorField.setFill(Color.rgb(r, g, b, o));
        });
        greenSlider.valueProperty().addListener((ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) -> {
            gCurrent.setText(String.format("%.1f", new_val));

            g = new_val.intValue();
            colorField.setFill(Color.rgb(r, g, b, o));
        });
        blueSlider.valueProperty().addListener((ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) -> {
            bCurrent.setText(String.format("%.1f", new_val));
            b = new_val.intValue();
            colorField.setFill(Color.rgb(r, g, b, o));
        });
        opacSlider.valueProperty().addListener((ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) -> {
            oCurrent.setText(String.format("%.2f", new_val));
            o = new_val.doubleValue();
            colorField.setFill(Color.rgb(r, g, b, o));
        });

        //root pane
        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(5, 5, 5, 5));
        pane.setRight(colorSliderPane);
        pane.setBottom(opacBox);
        pane.setCenter(colorField);

        Scene scene = new Scene(pane, 450, 250);
        primaryStage.setTitle("Color Changer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
