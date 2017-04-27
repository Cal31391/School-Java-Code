

/* Caroline Lee
   10-19-2016
   Calculator2
   Stage:Calculator2 - handles simple arithmetic functions such as add, subtract,
   multiply, divide
 */
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Calculator2 extends Application {

    //Buttons and Screen
    private TextField screen = new TextField();
    private Button num1 = new Button("1");
    private Button num2 = new Button("2");
    private Button num3 = new Button("3");
    private Button num4 = new Button("4");
    private Button num5 = new Button("5");
    private Button num6 = new Button("6");
    private Button num7 = new Button("7");
    private Button num8 = new Button("8");
    private Button num9 = new Button("9");
    private Button num0 = new Button("0");
    private Button decimalBtn = new Button(".");
    private Button additionBtn = new Button("+");
    private Button subtractionBtn = new Button("-");
    private Button multiplyBtn = new Button("*");
    private Button divideBtn = new Button("/");
    private Button equalsBtn = new Button("=");
    private Button clearBtn = new Button("C");

    //Stage
    @Override
    public void start(Stage primaryStage) {

        GridPane buttonBoard = new GridPane();
        buttonBoard.setHgap(5);
        buttonBoard.setVgap(5);
        buttonBoard.add(screen, 1, 0);
        screen.setPrefWidth(100);
        buttonBoard.setColumnSpan(screen, 4);//span 2 columns

        //row 1
        buttonBoard.add(clearBtn, 1, 1);
        buttonBoard.add(divideBtn, 2, 1);
        divideBtn.setPrefWidth(23);
        buttonBoard.add(multiplyBtn, 3, 1);
        multiplyBtn.setPrefWidth(23);
        buttonBoard.add(subtractionBtn, 4, 1);
        subtractionBtn.setPrefWidth(25);

        //row 2
        buttonBoard.add(num7, 1, 2);
        buttonBoard.add(num8, 2, 2);
        buttonBoard.add(num9, 3, 2);
        buttonBoard.add(additionBtn, 4, 2);
        additionBtn.setPrefHeight(55);
        buttonBoard.setRowSpan(additionBtn, 2);//span 2 rows

        //row 3
        buttonBoard.add(num4, 1, 3);
        buttonBoard.add(num5, 2, 3);
        buttonBoard.add(num6, 3, 3);

        //row 4
        buttonBoard.add(num1, 1, 4);
        buttonBoard.add(num2, 2, 4);
        buttonBoard.add(num3, 3, 4);
        buttonBoard.add(equalsBtn, 4, 4);
        equalsBtn.setPrefHeight(55);
        buttonBoard.setRowSpan(equalsBtn, 2);//span 2 rows

        //row 5
        buttonBoard.add(num0, 1, 5);
        num0.setPrefWidth(52);
        buttonBoard.setColumnSpan(num0, 2);//span 2 columns
        buttonBoard.add(decimalBtn, 3, 5);
        decimalBtn.setPrefWidth(23);
        
        //Handlers
        num0.setOnAction(new EventHandler<ActionEvent>() {
            @Override // Override the handle method
            public void handle(ActionEvent e) {
                pushNum(num0);
            }
        });
        num1.setOnAction(new EventHandler<ActionEvent>() {
            @Override // Override the handle method
            public void handle(ActionEvent e) {
                pushNum(num1);
            }
        });
        num2.setOnAction(new EventHandler<ActionEvent>() {
            @Override // Override the handle method
            public void handle(ActionEvent e) {
                pushNum(num2);
            }
        });
        num3.setOnAction(new EventHandler<ActionEvent>() {
            @Override // Override the handle method
            public void handle(ActionEvent e) {
                pushNum(num3);
            }
        });
        num4.setOnAction(new EventHandler<ActionEvent>() {
            @Override // Override the handle method
            public void handle(ActionEvent e) {
                pushNum(num4);
            }
        });
        num5.setOnAction(new EventHandler<ActionEvent>() {
            @Override // Override the handle method
            public void handle(ActionEvent e) {
                pushNum(num5);
            }
        });
        num6.setOnAction(new EventHandler<ActionEvent>() {
            @Override // Override the handle method
            public void handle(ActionEvent e) {
                pushNum(num6);
            }
        });
        num7.setOnAction(new EventHandler<ActionEvent>() {
            @Override // Override the handle method
            public void handle(ActionEvent e) {
                pushNum(num7);
            }
        });
        num8.setOnAction(new EventHandler<ActionEvent>() {
            @Override // Override the handle method
            public void handle(ActionEvent e) {
                pushNum(num8);
            }
        });
        num9.setOnAction(new EventHandler<ActionEvent>() {
            @Override // Override the handle method
            public void handle(ActionEvent e) {
                pushNum(num9);
            }
        });

        Scene scene = new Scene(buttonBoard);
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    //what happens when any number button is pushed
    private void pushNum(Button btn) {
        double num
                = Double.parseDouble(btn.getText());
        screen.setText(String.valueOf(num));
    }
    
    //handler method
    public void pushCalculateBtn(Button btn) {
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override // Override the handle method
            public void handle(ActionEvent e) {
                try {
                    double firstNum
                            = Double.parseDouble(screen.getText());
                    double secondNum
                            = Double.parseDouble(screen.getText());
                    //process according to button selection
                    if (btn == additionBtn) {
                        double answer
                                = firstNum + secondNum;
                        screen.setText(String.format("%.2f", answer));
                    } else if (btn == subtractionBtn) {
                        double answer
                                = firstNum - secondNum;
                        screen.setText(String.format("%.2f", answer));
                    } else if (btn == multiplyBtn) {
                        double answer
                                = firstNum * secondNum;
                        screen.setText(String.format("%.2f", answer));
                    } else if (btn == divideBtn) {
                        double answer
                                = firstNum / secondNum;
                        //check for divide by zero
                        if (Double.parseDouble(screen.getText()) == 0) {
                            screen.setText("Error. Divide by zero.");
                        } else {
                            screen.setText(String.format("%.2f", answer));
                        }
                    }
                } catch (Exception ex) {//if input field is empty, do nothing
                    if (screen.getText().isEmpty() || screen.getText().isEmpty()) {
                        screen.setText("");
                    } else if (!isNumber(screen.getText()) || !isNumber(screen.getText())) {
                        screen.setText("Error. Enter number.");//error message for not number
                    }
                }

            }
        });
    }
    
    //method to check if input is a number
    public boolean isNumber(String input) {
        try {
            Double.parseDouble(input);
            return true;

        } catch (Exception ex) {
            return false;
        }
    }

}
