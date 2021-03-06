package barol.revival;

import barol.revival.controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/programGui.fxml"));

        primaryStage.setTitle("Barol Revival 1.0");
        Scene scene = new Scene((Parent) fxmlLoader.load());

        primaryStage.setScene(scene);

        MainController controller =
                fxmlLoader.<MainController>getController();
        controller.initController(scene);

        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
