import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



public class App extends Application {
    public static void main(String[] args) {
     launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(500);
        Pane root = FXMLLoader.load(getClass().getResource("/calculadora.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
