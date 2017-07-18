package barol.revival.controller;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Arc;

import java.net.URL;
import java.util.ResourceBundle;

import barol.revival.service.MainService;

public class MainController implements Initializable {


    private Scene scene;

    private boolean upArrow;
    private boolean downArrow;
    private boolean leftArrow;
    private boolean rightArrow;

    @FXML
    AnchorPane mainPane;


    private MainService mainService;

    public MainController() {
        System.out.println("creating controller...");

    }





    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("initializing controller...");

    }

    public void initController(Scene scene) {
        System.out.println("init method...");
        this.scene = scene;
        this.mainService = new MainService(this.mainPane, scene);

        this.setSceneKeyListeners();
        this.runAnimationTimer();
    }



    private void setSceneKeyListeners() {
        this.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    upArrow = true; break;
                    case DOWN:  downArrow = true; break;
                    case LEFT:  leftArrow  = true; break;
                    case RIGHT: rightArrow  = true; break;
                }
            }
        });

        this.scene.setOnKeyReleased(new EventHandler<KeyEvent>() {

            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    upArrow = false; break;
                    case DOWN:  downArrow = false; break;
                    case LEFT:  leftArrow  = false; break;
                    case RIGHT: rightArrow  = false; break;
                }
            }
        });
    }



    private void runAnimationTimer() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {


                int power = 0;
                int dr = 0;

                if (upArrow) {
                    power = 5;
                }
                if (downArrow) {
                    System.out.println("Shoot!");
                }
                if (rightArrow)  {
                    dr += 4;
                }
                if (leftArrow)  {
                    dr -= 4;
                }


                mainService.moveAndRotateShip(power, dr);
                mainService.gravity(power);
            }
        };
        timer.start();
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public AnchorPane getMainPane() {
        return mainPane;
    }

    public void setMainPane(AnchorPane mainPane) {
        this.mainPane = mainPane;
    }

    public MainService getMainService() {
        return mainService;
    }

    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }
}
