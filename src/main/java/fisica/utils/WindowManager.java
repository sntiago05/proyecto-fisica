package fisica.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowManager {

    public static void cambiarVentana(String src, Node n) {
        FXMLLoader loader = new FXMLLoader(WindowManager.class.getResource(src));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stageActual = (Stage) n.getScene().getWindow();
        stageActual.setScene(new Scene(root));
        stageActual.show();
    }
}
