package hangman;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;

import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;

import javafx.util.Duration;

/**
 *
 * @author cal31
 *
 * Hangman class for GameProject.java
 */
public class Hangman extends Pane {

    /**
     * *******************************************************************
     * BODY PARTS
     * *******************************************************************
     */
    public Circle head = new Circle();
    public Line body = new Line();
    public Circle legSocket = new Circle();
    public Line leftArm = new Line();
    public Circle leftHand = new Circle();
    public Line rightArm = new Line();
    public Circle rightHand = new Circle();
    public Circle armSocket = new Circle();
    public Line leftLeg = new Line();
    public Circle leftFoot = new Circle();
    public Line rightLeg = new Line();
    public Circle rightFoot = new Circle();

    public Circle leftEye = new Circle();
    public Circle rightEye = new Circle();
    public Arc mouth = new Arc();

    /**
     * *******************************************************************
     * POLYLINES FOR ANIMATIONS
     * *******************************************************************
     */
    public Polyline rHWavePL = new Polyline();
    public Polyline wordH = new Polyline();
    public Polyline wordE = new Polyline();
    public Polyline wordL = new Polyline();
    public Polyline wordP = new Polyline();
    public Polyline lineUp = new Polyline();
    public Polyline lineDown = new Polyline();

    public Polyline rFReactPL = new Polyline();
    public Polyline rHReactPL1 = new Polyline();
    public Polyline lHReactPL1 = new Polyline();
    public Polyline legSocketReactPL = new Polyline();
    public Polyline armSocketReactPL = new Polyline();

    public Polyline rHReactPL2 = new Polyline();
    public Polyline lHReactPL2 = new Polyline();
    public Polyline lFReactPL = new Polyline();

    /**
     * *******************************************************************
     * TRANSITIONS FOR ANIMATIONS
     * *******************************************************************
     */
    public ParallelTransition pT = new ParallelTransition();
    public PathTransition path1 = new PathTransition(Duration.millis(1000),
            rHReactPL1, rightHand);
    public PathTransition path2 = new PathTransition(Duration.millis(1000),
            lHReactPL1, leftHand);
    public PathTransition path3 = new PathTransition(Duration.millis(2000),
            rFReactPL, rightFoot);
    public PathTransition path4 = new PathTransition(Duration.millis(2000),
            legSocketReactPL, legSocket);
    public PathTransition path5 = new PathTransition(Duration.millis(2000),
            armSocketReactPL, armSocket);
    public PathTransition path6 = new PathTransition(Duration.millis(2000),
            lFReactPL, leftFoot);
    public PathTransition path7 = new PathTransition(Duration.millis(1000),
            lHReactPL2, leftHand);
    public PathTransition path8 = new PathTransition(Duration.millis(1000),
            rHReactPL2, rightHand);

    /**
     * *******************************************************************
     * HANGMAN CONSTRUCTOR
     * *******************************************************************
     * @param X
     * @param Y
     */
    public Hangman(double X, double Y) {

        head.setCenterX(X);
        head.setCenterY(Y);
        head.setRadius(20);
        head.setStroke(Color.YELLOW);
        head.setFill(Color.BLACK);

        leftEye.setCenterX(head.getCenterX() - 7);
        leftEye.setCenterY(head.getCenterY() - 6);
        leftEye.setRadius(2);
        leftEye.setFill(Color.YELLOW);

        rightEye.setCenterX(head.getCenterX() + 7);
        rightEye.setCenterY(head.getCenterY() - 6);
        rightEye.setRadius(2);
        rightEye.setFill(Color.YELLOW);

        mouth.setCenterX(head.getCenterX());
        mouth.setCenterY(head.getCenterY() - 30);
        mouth.setRadiusX(60);
        mouth.setRadiusY(41);
        mouth.setStartAngle(263);
        mouth.setLength(15);
        mouth.setStroke(Color.YELLOW);
        mouth.setType(ArcType.OPEN);

        body.setStartX(head.getCenterX());
        body.setStartY(head.getCenterY() + 20);
        body.setEndX(head.getCenterX());
        body.setEndY(head.getCenterY() + 90);
        body.setStrokeWidth(2);
        body.setStroke(Color.YELLOW);

        legSocket.setCenterX(head.getCenterX());
        legSocket.setCenterY(head.getCenterY() + 70);
        legSocket.setRadius(1);

        leftArm.setStartX(head.getCenterX());
        leftArm.setStartY(head.getCenterY() + 40);
        leftArm.setEndX(head.getCenterX() - 10);
        leftArm.setEndY(head.getCenterY() + 60);
        leftArm.setStrokeWidth(2);
        leftArm.setStroke(Color.YELLOW);

        leftHand.setCenterX(head.getCenterX() - 10);
        leftHand.setCenterY(head.getCenterY() + 60);
        leftHand.setRadius(2);
        leftHand.setFill(Color.YELLOW);

        rightArm.setStartX(head.getCenterX());
        rightArm.setStartY(head.getCenterY() + 40);
        rightArm.setEndX(head.getCenterX() + 10);
        rightArm.setEndY(head.getCenterY() + 60);
        rightArm.setStrokeWidth(2);
        rightArm.setStroke(Color.YELLOW);

        rightHand.setCenterX(head.getCenterX() + 10);
        rightHand.setCenterY(head.getCenterY() + 60);
        rightHand.setRadius(2);
        rightHand.setFill(Color.YELLOW);

        armSocket.setCenterX(head.getCenterX());
        armSocket.setCenterY(head.getCenterY() + 40);
        armSocket.setRadius(1);

        leftLeg.setStartX(head.getCenterX());
        leftLeg.setStartY(head.getCenterY() + 90);
        leftLeg.setEndX(head.getCenterX() - 10);
        leftLeg.setEndY(head.getCenterY() + 110);
        leftLeg.setStrokeWidth(2);
        leftLeg.setStroke(Color.YELLOW);

        leftFoot.setCenterX(head.getCenterX() - 10);
        leftFoot.setCenterY(head.getCenterY() + 110);
        leftFoot.setRadius(2);
        leftFoot.setFill(Color.YELLOW);

        rightLeg.setStartX(head.getCenterX());
        rightLeg.setStartY(head.getCenterY() + 90);
        rightLeg.setEndX(head.getCenterX() + 10);
        rightLeg.setEndY(head.getCenterY() + 110);
        rightLeg.setStrokeWidth(2);
        rightLeg.setStroke(Color.YELLOW);

        rightFoot.setCenterX(head.getCenterX() + 10);
        rightFoot.setCenterY(head.getCenterY() + 110);
        rightFoot.setRadius(2);
        rightFoot.setFill(Color.YELLOW);

        getChildren().addAll(head, body, rightEye, leftEye, mouth, rightArm,
                rightHand, leftArm, leftHand, rightLeg, rightFoot, leftLeg, leftFoot);
    }

    /**
     * *******************************************************************
     * SETTERS FOR POLYLINE COORDINATES
     * *******************************************************************
     */
    //method for setting goodbye screen wave animation polylines
    public void setGoodbyePL() {
        rHWavePL.getPoints().addAll(new Double[]{
            270.0, 79.0, 275.0, 81.0, 278.0, 84.0
        });
        rHWavePL.setStroke(Color.BLACK);
        rHWavePL.setOpacity(0);
        getChildren().add(rHWavePL);
    }
    //method for setting writeHELP animation polylines
    public void setWriteHELP() {
        lineUp.getPoints().addAll(new Double[]{
            220.0, 120.0, 180.0, 60.0
        });
        lineUp.setOpacity(0);

        wordH.getPoints().addAll(new Double[]{
            180.0, 60.0, 182.0, 70.0, 181.0, 65.0, 185.0, 66.0, 186.0, 59.0,
            188.0, 69.0, 187.0, 66.0});
        wordH.setOpacity(0);
        wordH.setStrokeWidth(1);
        wordH.setStroke(Color.YELLOW);

        wordE.getPoints().addAll(new Double[]{
            187.0, 66.0, 195.0, 64.0, 194.0, 62.0, 193.0, 62.0, 190.0, 62.0,
            187.0, 66.0, 193.0, 69.0, 194.0, 69.0, 195.0, 69.0, 198.0, 70.0});
        wordE.setOpacity(0);
        wordE.setStrokeWidth(1);
        wordE.setStroke(Color.YELLOW);

        wordL.getPoints().addAll(new Double[]{
            197.0, 58.0, 198.0, 70.0});
        wordL.setOpacity(0);
        wordL.setStrokeWidth(1);
        wordL.setStroke(Color.YELLOW);

        wordP.getPoints().addAll(new Double[]{
            200.0, 71.0, 201.0, 63.0, 202.0, 73.0, 201.0, 63.0, 204.0, 62.0,
            206.0, 62.0, 207.0, 68.0, 201.0, 68.0
        });
        wordP.setOpacity(0);
        wordP.setStrokeWidth(1);
        wordP.setStroke(Color.YELLOW);

        lineDown.getPoints().addAll(new Double[]{
            201.0, 68.0, 220.0, 120.0
        });
        lineDown.setOpacity(0);

        getChildren().addAll(lineUp, lineDown, wordH, wordE, wordL, wordP);
    }
    //method for setting reaction polylines
    public void setReactPLs() {
        rHReactPL1.getPoints().clear();
        lHReactPL1.getPoints().clear();
        rHReactPL2.getPoints().clear();
        lHReactPL2.getPoints().clear();
        rFReactPL.getPoints().clear();
        lFReactPL.getPoints().clear();
        legSocketReactPL.getPoints().clear();
        armSocketReactPL.getPoints().clear();

        rHReactPL1.getPoints().addAll(new Double[]{//right hand react PL 1
            110.0, 100.0, 113.0, 94.0
        });
        rHReactPL1.setOpacity(0);
        rHReactPL1.setStroke(Color.WHITE);

        lHReactPL1.getPoints().addAll(new Double[]{//left hand react PL 1
            90.0, 100.0, 86.0, 96.0
        });
        lHReactPL1.setOpacity(0);
        lHReactPL1.setStroke(Color.WHITE);

        rHReactPL2.getPoints().addAll(new Double[]{//right hand react PL 2
            110.0, 100.0, 113.0, 77.0,
            113.0, 100.0
        });
        rHReactPL2.setOpacity(0);
        rHReactPL2.setStroke(Color.WHITE);

        lHReactPL2.getPoints().addAll(new Double[]{// left hand react PL 2
            90.0, 100.0, 87.0, 77.0,
            87.0, 100.0
        });
        lHReactPL2.setOpacity(0);
        lHReactPL2.setStroke(Color.WHITE);

        rFReactPL.getPoints().addAll(new Double[]{//right foot react PL
            110.0, 150.0, 104.0, 149.0,
            110.0, 150.0
        });
        rFReactPL.setOpacity(0);
        rFReactPL.setStroke(Color.WHITE);

        lFReactPL.getPoints().addAll(new Double[]{//left foot react PL
            90.0, 150.0, 86.0, 149.0, 90.0, 150.0
        });
        lFReactPL.setOpacity(0);
        lFReactPL.setStroke(Color.WHITE);

        legSocketReactPL.getPoints().addAll(new Double[]{//leg socket react PL
            100.0, 135.0, 95.0, 134.0, 100.0, 135.0,
            105.0, 134.0, 100.0, 135.0
        });
        legSocketReactPL.setOpacity(0);
        legSocketReactPL.setStroke(Color.WHITE);

        armSocketReactPL.getPoints().addAll(new Double[]{//arm socket react PL
            100.0, 85.0, 98.0, 84.0, 100.0, 85.0,
            102.0, 84.0, 100.0, 85.0
        });
        armSocketReactPL.setOpacity(0);
        armSocketReactPL.setStroke(Color.WHITE);

        getChildren().addAll(rHReactPL1, rHReactPL2, lHReactPL1, lHReactPL2,
                rFReactPL, lFReactPL, legSocketReactPL, armSocketReactPL);
    }

    /**
     * *******************************************************************
     * ANIMATION METHODS
     * *******************************************************************
     */
    //method for animation to write Help on board at beginning(titleScreen)
    public void writeHELP() {
        //path to raise arm up to start writing
        PathTransition pathLineUp = new PathTransition(Duration.millis(1250),
                lineUp, leftHand);
        pathLineUp.setCycleCount(1);
        //write an H
        ParallelTransition parT = new ParallelTransition();
        PathTransition p1 = new PathTransition(Duration.millis(1250), wordH, leftHand);
        p1.setCycleCount(1);
        FadeTransition fT = new FadeTransition(Duration.millis(1250), wordH);
        fT.setFromValue(0.0);
        fT.setToValue(1.0);
        fT.setCycleCount(1);
        parT.getChildren().addAll(p1, fT);
        //write an e
        ParallelTransition parT2 = new ParallelTransition();
        PathTransition p2 = new PathTransition(Duration.millis(650), wordE, leftHand);
        p2.setCycleCount(1);
        FadeTransition fT2 = new FadeTransition(Duration.millis(1300), wordE);
        fT2.setFromValue(0.0);
        fT2.setToValue(1.0);
        fT2.setCycleCount(1);
        parT2.getChildren().addAll(p2, fT2);
        //write an l
        ParallelTransition parT3 = new ParallelTransition();
        PathTransition p3 = new PathTransition(Duration.millis(750), wordL, leftHand);
        p3.setCycleCount(1);
        FadeTransition fT3 = new FadeTransition(Duration.millis(950), wordL);
        fT3.setFromValue(0.0);
        fT3.setToValue(1.0);
        fT3.setCycleCount(1);
        parT3.getChildren().addAll(p3, fT3);
        //write a p
        ParallelTransition parT4 = new ParallelTransition();
        PathTransition p4 = new PathTransition(Duration.millis(700), wordP, leftHand);
        p4.setCycleCount(1);
        FadeTransition fT4 = new FadeTransition(Duration.millis(1350), wordP);
        fT4.setFromValue(0.0);
        fT4.setToValue(1.0);
        fT4.setCycleCount(1);
        parT4.getChildren().addAll(p4, fT4);
        //put arm back down
        PathTransition pathLineDown = new PathTransition(Duration.millis(1250), 
                lineDown, leftHand);
        pathLineDown.setCycleCount(1);

        SequentialTransition sT = new SequentialTransition();
        PauseTransition pause = new PauseTransition(Duration.millis(2000));//wait to start
        sT.getChildren().addAll(pause, pathLineUp, parT, parT2, parT3, parT4, pathLineDown);
        sT.setCycleCount(1);
        sT.play();

        leftArm.endXProperty().bind(leftHand.centerXProperty()
                .add(leftHand.translateXProperty()));
        leftArm.endYProperty().bind(leftHand.centerYProperty()
                .add(leftHand.translateYProperty()));
    }

    //Wave on exit screen, at the end of the program//
    public void wave() {

        PathTransition pt = new PathTransition(Duration.millis(600), rHWavePL, rightHand);
        pt.setCycleCount(Timeline.INDEFINITE);
        pt.setAutoReverse(true);
        pt.play();
        rightArm.endXProperty().bind(rightHand.centerXProperty()
                .add(rightHand.translateXProperty()));
        rightArm.endYProperty().bind(rightHand.centerYProperty()
                .add(rightHand.translateYProperty()));
    }

    //Reaction method setters - connect path polylines to nodes//
    public void setReactions() {
        pT.getChildren().clear();

        setTransition(path1, rightHand, rightArm, armSocket, 2);
        setTransition(path2, leftHand, leftArm, armSocket, 2);
        setTransition(path3, rightFoot, rightLeg, legSocket, 1);
        setTransition(path4, legSocket, body, 1);
        setTransition(path5, armSocket, 1);
        setTransition(path6, leftFoot, leftLeg, legSocket, 1);
        setTransition(path7, leftHand, leftArm, armSocket, 2);
        setTransition(path8, rightHand, rightArm, armSocket, 2);

        pT.getChildren().addAll(path1, path2, path3, path4, path5, path6);
    }

    public void InitialReact() {
        //body swings, limbs move back and forth - contains all initial paths
        path7.setDuration(Duration.millis(1000));//reset left arm to move slower
        pT.play();

    }

    public void React1() {
        /*left leg is removed(path6), mouth turns to frown, initial paths for 
        arms are replaced with their reactPL2s
        */
        setMouth();
        pT.getChildren().removeAll(path1, path2);
        pT.getChildren().addAll(path7, path8);
        pT.play();

    }

    public void React2() {
        //right leg is removed(path3), mouth frowns more
        mouth.setStartAngle(82);
        mouth.setLength(15);
        pT.play();

    }

    public void React3() {
        //right arm is removed(path8), mouth frowns more
        mouth.setRadiusY(183);
        mouth.setStartAngle(80);
        mouth.setLength(20);
        path7.setDuration(Duration.millis(500));//left arm moves faster
        pT.play();

    }

    public void React4() {
        //left arm is removed(path7), mouth frowns and opens slightly
        mouth.setType(ArcType.CHORD);
        mouth.setFill(Color.YELLOW);
    }

    public void React5() {
        //body is removed, mouth and eyes fade
        mouth.setOpacity(0);
        leftEye.setOpacity(0);
        rightEye.setOpacity(0);
    }

    /**
     * *******************************************************************
     * TRANSITION SETTERS - overloaded
     * *******************************************************************
     * @param p
     * @param c
     * @param l
     * @param c2
     * @param i
     */
    public void setTransition(PathTransition p, Circle c, Line l, Circle c2, int i) {
        p.setCycleCount(i);
        p.setAutoReverse(true);
        l.startXProperty().bind(c2.centerXProperty().add(c2.translateXProperty()));
        l.startYProperty().bind(c2.centerYProperty().add(c2.translateYProperty()));
        l.endXProperty().bind(c.centerXProperty().add(c.translateXProperty()));
        l.endYProperty().bind(c.centerYProperty().add(c.translateYProperty()));
    }

    public void setTransition(PathTransition p, Circle c, Line l, int i) {
        p.setCycleCount(i);
        p.setAutoReverse(true);
        l.endXProperty().bind(c.centerXProperty().add(c.translateXProperty()));
        l.endYProperty().bind(c.centerYProperty().add(c.translateYProperty()));
    }

    public void setTransition(PathTransition p, Circle c, int i) {
        p.setCycleCount(i);
        p.setAutoReverse(true);
    }

    /**
     * *******************************************************************
     * REMOVE/RESET BODY PARTS METHODS
     * *******************************************************************
     */
    public void removeLeftLeg() {
        getChildren().removeAll(leftLeg, leftFoot);
    }

    public void removeRightLeg() {
        getChildren().removeAll(rightLeg, rightFoot);
    }

    public void removeRightArm() {
        getChildren().removeAll(rightArm, rightHand);
    }

    public void removeLeftArm() {
        getChildren().removeAll(leftArm, leftHand);
    }

    public void removeBody() {
        getChildren().remove(body);
    }

    public void removeHead() {
        getChildren().remove(head);
    }

    public void addBodyParts() {
        leftEye.setOpacity(1);
        rightEye.setOpacity(1);
        setMouth();
        getChildren().addAll(head, body, leftEye, rightEye, mouth, leftArm, leftHand, 
                rightArm, rightHand, rightLeg, rightFoot, leftLeg, leftFoot);
    }

    public void setMouth() {
        mouth.setCenterY(230);
        mouth.setRadiusY(180);
        mouth.setStartAngle(85);
        mouth.setLength(10);
        mouth.setOpacity(1);
        mouth.setType(ArcType.OPEN);
    }

}
