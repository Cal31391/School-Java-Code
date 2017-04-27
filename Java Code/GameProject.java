package hangman;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.util.Duration;

/* Caroline Lee
   10/29/2016 - 11/25/2016
   Game Project
   Hangman
   This program is based off of another program that was written to simulate 
   a hangman game. The game will be implemented using a GUI.  
 */
public class GameProject extends Application {

    /**
     * *******************************************************************
     * LOTS OF FIELDS
     * *******************************************************************
     */
    //Fields from original program//
    public static String word = "";
    public static char userChoice;
    public static String hiddenWord;
    public char[] hiddenWordArray;
    public static char userInput;
    public static int missCount = 0;
    public static boolean isCompleteWord;

    //Fields for initialization process//
    public static Label warnings = new Label();
    public static Label endDialogue = new Label();
    public static Label askToPlay = new Label();
    public static Label hiddenWordLabel = new Label();
    public static GridPane root = new GridPane();
    public static Pane hangmanPane = new Pane();
    public static GridPane warningMessageGrid = new GridPane();
    public static Pane hiddenWordPane = new Pane();
    public static Button yesBtn = new Button("Yes");
    public static Button noBtn = new Button("No");
    public static GridPane titleGrid = new GridPane();

    //Fields for creating grid of buttons for letter choices//
    public static GridPane letterTableGrid = new GridPane();
    public static String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
        "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x",
        "y", "z"};
    public static int iteration;

    //Fields for title screen//
    public static Label titleLabel = new Label("Hangman");
    public static Button playBtn = new Button("Play");
    public static Button exitBtn = new Button("Exit");

    //Fields for level choice screen//
    public static Label chooseLvlLabel = new Label("Choose Difficulty");
    public static Button easyBtn = new Button("Illiterate");
    public static Button normalBtn = new Button("Average");
    public static Button hardBtn = new Button("Orthographer");
    public static String normalBtnStyle;
    public static String hoveredBtnStyle;
    public static Stage playScreen = new Stage();

    //Fields for hangman stuff//
    public static Polyline tower = new Polyline();
    public static Polyline noose = new Polyline();
    public static Hangman titleMan = new Hangman(230, 60);
    public static Hangman exitMan = new Hangman(250, 60);
    public static Hangman gamePlayMan = new Hangman(100, 40);
    public static boolean stopKeyEvent;
    public static int scoreKeep = 0;
    public static Label scoreKeepLabel = new Label();
    public static int levelTracker = 0;

    Scene scene = new Scene(root, 600, 500);
    Stage choiceScreen = new Stage();

    /**
     * *******************************************************************
     * PRIMARY GAME EXECUTION
     * *******************************************************************
     */
    @Override
    public void start(Stage primaryStage) {

        initiateTitle();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void playGame(Stage s) {

        load(root);

        root.setAlignment(Pos.CENTER);
        s.setTitle("Hangman");
        s.setScene(scene);
        s.show();
    }

    /**
     * *******************************************************************
     * INITIATION METHOD
     * *******************************************************************
     */
    public void initiateTitle() {
        Stage titleScreen = new Stage();
        titleGrid.setStyle("-fx-background-color: black;");
        titleGrid.setAlignment(Pos.CENTER);

        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setFont(Font.font("Courier New", 30));
        titleLabel.setStyle("-fx-font-weight: bold");

        titleGrid.add(titleLabel, 0, 1);
        titleGrid.add(titleMan, 0, 0);
        titleGrid.add(playBtn, 1, 2);
        titleGrid.add(exitBtn, 2, 2);

        titleGrid.setColumnSpan(titleMan, 3);

        titleMan.setWriteHELP();
        titleMan.writeHELP();

        //http://fxexperience.com/2011/12/styling-fx-buttons-with-css/ << source
        setBtnStyle(playBtn, "000000", "yellow", "20", "bold");
        setBtnStyle(exitBtn, "000000", "yellow", "20", "bold");

        Scene titleScene = new Scene(titleGrid, 350, 350);
        titleScreen.setTitle("Hangman");
        titleScreen.setScene(titleScene);
        titleScreen.show();

        playBtn.setOnMouseClicked((MouseEvent e) -> {
            //clear pane and fill with buttons for level choice
            titleGrid.getChildren().clear();
            chooseLevel(titleGrid, titleScreen);
        });

        exitBtn.setOnMouseClicked((MouseEvent e2) -> {
            System.exit(0);
        });
    }

    /**
     * *******************************************************************
     * LEVEL CHOOSING METHOD
     *
     *
     * *******************************************************************
     * @param gp
     * @param s
     */
    public void chooseLevel(GridPane gp, Stage s) {
        gp.add(chooseLvlLabel, 1, 0);
        gp.add(easyBtn, 1, 1);
        gp.add(normalBtn, 1, 2);
        gp.add(hardBtn, 1, 3);

        gp.setHalignment(easyBtn, HPos.CENTER);
        gp.setHalignment(normalBtn, HPos.CENTER);
        gp.setHalignment(hardBtn, HPos.CENTER);

        chooseLvlLabel.setTextFill(Color.WHITE);
        chooseLvlLabel.setFont(Font.font("Courier New", 20));
        chooseLvlLabel.setStyle("-fx-font-weight: bold");

        setBtnStyle(easyBtn, "000000", "yellow", "20", "bold");
        setBtnStyle(normalBtn, "000000", "yellow", "20", "bold");
        setBtnStyle(hardBtn, "000000", "yellow", "20", "bold");

        //Choose level//
        easyBtn.setOnMouseClicked((MouseEvent e3) -> {
            word = getWordFromArrayEasy();
            gp.getChildren().clear();
            playGame(playScreen);
            s.close();
        });
        normalBtn.setOnMouseClicked((MouseEvent e3) -> {
            word = getWordFromArrayNormal();
            gp.getChildren().clear();
            playGame(playScreen);
            s.close();
        });
        hardBtn.setOnMouseClicked((MouseEvent e3) -> {
            word = getWordFromArrayHard();
            gp.getChildren().clear();
            playGame(playScreen);
            s.close();
        });
    }

    /**
     * *******************************************************************
     * LOAD METHOD
     *
     * @param gp
     * *******************************************************************
     */
    public void load(GridPane gp) {
        stopKeyEvent = false;
        tower.getPoints().clear();//make sure tower is cleared before reload
        noose.getPoints().clear();
        tower.getPoints().addAll(new Double[]{
            100.0, 20.0, 100.0, 10.0, 200.0, 10.0, 200.0, 200.0,
            140.0, 200.0, 220.0, 200.0
        });
        tower.setStrokeWidth(6);
        tower.setStroke(Color.YELLOW);

        noose.getPoints().addAll(new Double[]{
            100.0, 20.0, 100.0, 30.0, 90.0, 40.0, 87.0, 50.0, 88.0, 60.0,
            89.0, 62.0, 90.0, 63.0, 92.0, 64.0, 94.0, 65.0, 100.0, 66.0,
            106.0, 65.0, 108.0, 64.0, 110.0, 63.0, 110.0, 62.0, 111.0, 60.0,
            112.0, 50.0, 109.0, 40.0, 100.0, 30.0
        });
        noose.setStrokeWidth(2);
        noose.setStroke(Color.YELLOW);

        hangmanPane.setPrefSize(80, 100);
        hangmanPane.getChildren().addAll(tower, noose, gamePlayMan);
        gamePlayMan.setReactPLs();
        gamePlayMan.setReactions();
        gamePlayMan.InitialReact();

        //letterTableGrid//
        int buttonLines = 3;
        int buttonColumns = 9;
        for (int r = 0; r < buttonLines; r++) {
            for (int c = 0; c < buttonColumns; c++) {
                if (iteration < letters.length) {
                    Button btn = new Button(letters[iteration]);
                    btn.setPrefSize(40, 40);
                    btn.setStyle("-fx-background-color: #000000;"
                            + "-fx-background-insets: 0,0,2;"
                            + "-fx-background-radius: 3,2,1;"
                            + "-fx-padding: 3 3 3 3;"
                            + "-fx-text-fill: yellow;"
                            + "-fx-font-size: 14px;"
                            + "-fx-font-weight: bold;");
                    btn.setAlignment(Pos.CENTER);
                    btn.setId(letters[iteration]);
                    iteration++;
                    letterTableGrid.add(btn, c, r);

                    btn.setOnMouseClicked((MouseEvent e) -> {
                        userInput = btn.getId().charAt(0);
                        btn.setStyle("-fx-background-color: #000000;"
                                + "-fx-background-insets: 0,0,2;"
                                + "-fx-background-radius: 3,2,1;"
                                + "-fx-padding: 3 3 3 3;"
                                + "-fx-text-fill: black;"
                                + "-fx-font-size: 14px;"
                                + "-fx-font-weight: bold;");
                        processTurn();
                    });

                }
            }
            letterTableGrid.setPrefSize(200, 100);
        }

        /*make key presses possible for faster debugging//
        scene.setOnKeyPressed(e -> {
            for (String letter : letters) {
                if ((e.getText()).equals(letter) && !stopKeyEvent) {
                    userInput = (e.getText()).charAt(0);
                    processTurn();
                }
            }
        });*/
        //grid for warnings//
        warnings.setStyle("-fx-text-fill: white; -fx-font-size: 14px;"
                + "-fx-font-weight: bold;");
        warningMessageGrid.add(warnings, 0, 0);
        warningMessageGrid.setColumnSpan(warnings, 3);
        endDialogue.setStyle("-fx-text-fill: white; -fx-font-size: 14px;"
                + "-fx-font-weight: bold;");
        warningMessageGrid.add(endDialogue, 0, 1);
        warningMessageGrid.setColumnSpan(endDialogue, 3);
        askToPlay.setStyle("-fx-text-fill: white; -fx-font-size: 14px;"
                + "-fx-font-weight: bold;");
        warningMessageGrid.add(askToPlay, 0, 2);
        warningMessageGrid.setColumnSpan(askToPlay, 3);

        //hide word, prompt user for guess
        hiddenWord = hideWord(word); //"Hide" random word from user

        //display hiddenWord
        hiddenWordLabel.setText(hiddenWord);
        hiddenWordLabel.setStyle("-fx-font: 20 Courier; -fx-base: #000000; "
                + "-fx-text-fill: yellow;"
                + "-fx-font-weight: bold;");
        hiddenWordPane.setPadding(new Insets(25, 25, 25, 25));
        hiddenWordPane.getChildren().add(hiddenWordLabel);

        //keep score
        scoreKeepLabel.setText("Score: " + scoreKeep);
        scoreKeepLabel.setStyle("-fx-font: 14 Courier; -fx-base: #000000; "
                + "-fx-text-fill: white;"
                + "-fx-font-weight: bold;");

        //root pane
        root.setStyle("-fx-background-color: black;");
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(25, 25, 25, 25));
        root.add(hangmanPane, 1, 0);
        root.add(hiddenWordPane, 0, 1);
        root.add(letterTableGrid, 0, 4);
        root.add(warningMessageGrid, 0, 5);
        root.add(scoreKeepLabel, 0, 6);
    }

    /**
     * *******************************************************************
     * METHODS FOR GAME LOGIC
     *
     * @return
     * *******************************************************************
     */
    //separated into 3 levels of difficulty//
    public static String getWordFromArrayEasy() {
        String guessWords[] = {"easy", "cat", "dog", "food", "rest", "relax", "help",
            "eat", "good", "sound", "happy", "well", "dawn", "sun", "light", "bird", "bake",
            "book", "tree", "cook", "sheep", "living", "bulb", "model", "about", "people",
            "other", "the", "even", "only", "map", "game", "still", "best", "right", "think",
            "day", "air", "now", "does", "read", "start", "part", "year", "once", "down"};
        levelTracker = 1;
        return guessWords[(int) (Math.random() * guessWords.length)];
    }

    public static String getWordFromArrayNormal() {
        String guessWords[] = {"normal", "sausage", "message", "relaxation", "playing",
            "passage", "picture", "chair", "table", "horse", "naught", "haunt", "report",
            "kitchen", "drought", "dessert", "tissue", "cereal", "serial", "stomach", "sandwich",
            "device", "vegetable", "tablespoon", "because", "different", "history", "money",
            "information", "government", "important", "family", "example", "computer",
            "business", "between", "system", "number", "become", "process", "experience"};
        levelTracker = 2;
        return guessWords[(int) (Math.random() * guessWords.length)];
    }

    public static String getWordFromArrayHard() {
        String guessWords[] = {"instruction", "additional", "poultry", "cayenne",
            "zucchini", "bouillon", "occasion", "necessary", "definitely", "aberration",
            "abnegation", "antithesis", "apocryphal", "beguile", "candor", "caucus",
            "cavort", "cognizant", "complement", "demagogue", "duplicity", "eclectic",
            "egregious", "ephemeral", "evanescent", "exacerbate", "extant", "gratuitous",
            "maverick", "neophyte", "plethora", "proscribe", "quandary", "reprieve",
            "reprobate", "sanguine", "solicitous", "solipsism", "torpid", "travesty",
            "ubiquitous", "umbrage", "vicissitude", "virtuoso", "zephyr"};
        levelTracker = 3;
        return guessWords[(int) (Math.random() * guessWords.length)];
    }

    //method to take the randomly selected word and "hide" it from the user  
    public static String hideWord(String w) {
        String mysteryWord = "";
        for (int i = 0; i < w.length(); i++) {
            char ch = '_';
            mysteryWord += ch;
        }
        return mysteryWord;
    }

    /*method to turn the hiddenWord string into a char array. 
      (used in updateHiddenWord() method)
     */
    public static char[] hiddenWordToChar(String w) {
        return w.toCharArray();
    }

    //method to check to see if the word is completely guessed
    public static boolean isCompleteWord() {
        return !hiddenWord.contains("_");
    }

    //method to update the hiddenWord string to reflect the latest guess
    public static void updateHiddenWord() {
        for (int i = 0; i < hiddenWord.length(); i++) {
            if (userInput == word.charAt(i)) { //Turn hiddenWord string to char array
                char[] hiddenWordArray = hiddenWordToChar(hiddenWord);
                hiddenWordArray[i] = userInput; //Replace '_' with guess char
                hiddenWord = String.valueOf(hiddenWordArray); //Turn back to string
            }
        }
    }

    //method to check to see if user guess is correct
    public void checkForCorrectGuess() {//Check hiddenWord for guess char

        if (hiddenWord.contains(String.valueOf(userInput))) {
            warnings.setText(userInput + " is already in the word");
            missCount++;
            removeABodyPart();

        } //Must check original word for this step:
        else if (!word.contains(String.valueOf(userInput))) {
            warnings.setText(userInput + " is not in the word");
            missCount++;
            removeABodyPart();
        }
    }

    /**
     * *******************************************************************
     * PROCESS TURN
     * *******************************************************************
     */
    public void processTurn() {
        checkForCorrectGuess();//Is the guess correct or a miss?
        updateHiddenWord();//Changes hiddenWord if guess is correct
        hiddenWordLabel.setText(hiddenWord);

        /*If the word is complete or user has missed more than 3 guesses, word
             is printed out and user loses the game.
         */
        if (isCompleteWord() || missCount > 5) {
            stopKeyEvent = true;
            warnings.setText("The Word is " + word + ". ");
            letterTableGrid.getChildren().clear();
            hiddenWordPane.getChildren().clear();
            if (missCount > 5) { //User lost the game.
                endDialogue.setText("You killed him! You lose. ");
                scoreKeep = 0;
            } else if (missCount <= 5) { //User won the game.
                gamePlayMan.setMouth();
                switch (levelTracker) {
                    case 1:
                        scoreKeep += 20;
                        break;
                    case 2:
                        scoreKeep += 50;
                        break;
                    case 3:
                        scoreKeep += 100;
                        break;
                }
                endDialogue.setText("You won! You only missed " + missCount + " times ");

            }

            //Ask user if he/she wants to play again.
            askToPlay.setText("Would you like to play again?");
            setBtnStyle(yesBtn, "000000", "yellow", "20", "bold");
            setBtnStyle(noBtn, "000000", "yellow", "20", "bold");
            warningMessageGrid.add(yesBtn, 1, 4);
            warningMessageGrid.add(noBtn, 2, 4);

            //User says yes. Clear everything. Load the root again.
            yesBtn.setOnMouseClicked((MouseEvent e2) -> {
                warningMessageGrid.getChildren().clear();
                warnings.setText("");
                endDialogue.setText("");
                askToPlay.setText("");
                hangmanPane.getChildren().clear();
                hiddenWordPane.getChildren().clear();
                letterTableGrid.getChildren().clear();
                gamePlayMan.getChildren().clear();
                gamePlayMan.addBodyParts();
                root.getChildren().clear();
                missCount = 0;
                iteration = 0;
                levelTracker = 0;
                chooseLevel(root, choiceScreen);
            });
            //User says no. Load the closing screen and exit.
            noBtn.setOnMouseClicked((MouseEvent e3) -> {
                root.getChildren().clear();
                root.getChildren().add(warnings);
                warnings.setText("Goodbye!");
                warnings.setPrefSize(100, 100);

                root.getChildren().add(exitMan);

                exitMan.setGoodbyePL();
                exitMan.wave();

                //set the closing screen for 2 seconds, then exit program
                PauseTransition delay = new PauseTransition(Duration.seconds(2));
                delay.setOnFinished(e -> System.exit(0));
                delay.play();
            });
        }
    }

    /**
     * *******************************************************************
     * REMOVE BODY PARTS
     * *******************************************************************
     */
    public void removeABodyPart() {

        switch (missCount) {
            case 1:
                gamePlayMan.removeLeftLeg();
                gamePlayMan.React1();
                break;
            case 2:
                gamePlayMan.removeRightLeg();
                gamePlayMan.React2();
                break;
            case 3:
                gamePlayMan.removeRightArm();
                gamePlayMan.React3();
                break;
            case 4:
                gamePlayMan.removeLeftArm();
                gamePlayMan.React4();
                break;
            case 5:
                gamePlayMan.removeBody();
                break;
            case 6:
                gamePlayMan.removeHead();
                gamePlayMan.React5();
                break;
        }
    }

    /**
     * *******************************************************************
     * BUTTON METHODS
     * http://stackoverflow.com/questions/13074459/javafx-2-and-css-pseudo
     * -classes-setting-hover-attributes-in-setstyle-method << source
     * *******************************************************************
     * @param b
     * @param bgcolor
     * @param color
     * @param size
     * @param weight
     */
    //method to change title screen buttons when mouse hovers over them
    public void setBtnStyle(Button b, String bgcolor, String color,
            String size, String weight) {
        normalBtnStyle = "-fx-background-color: #" + bgcolor + ";"
                + "-fx-background-insets: 0,0,2;"
                + "-fx-padding: 10 10 10 10;"
                + "-fx-text-fill: " + color + ";"
                + "-fx-font-size: " + size + "px;"
                + "-fx-font-weight: " + weight + ";";
        hoveredBtnStyle = "-fx-background-color: #000000;"
                + "-fx-background-insets: 0,0,2;"
                + "-fx-padding: 10 10 10 10;"
                + "-fx-text-fill: white;"
                + "-fx-font-size: 20px;"
                + "-fx-font-weight: bold;";
        b.setStyle(normalBtnStyle);
        b.setOnMouseEntered((MouseEvent e) -> {
            b.setStyle(hoveredBtnStyle);
        });
        b.setOnMouseExited((MouseEvent e) -> {
            b.setStyle(normalBtnStyle);
        });
    }
}
