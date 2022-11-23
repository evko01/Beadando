package grafikus;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
public class AdatbázisAlkalmazás extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdatbázisAlkalmazás.class.getResource("grafikus.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 620, 640);
        stage.setTitle("Beadandó");
        stage.setScene(scene);
        stage.show();

        }
    public static void main(String[] args) {
        launch();
    }
}
