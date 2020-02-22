import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ClientUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        primaryStage.setMinHeight(500);
//        primaryStage.setMinWidth(500);
        Pane root = FXMLLoader.load(getClass().getResource("/Calculadora.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
