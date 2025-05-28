package fisica.utils;

import fisica.controller.AlertController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AlertManager {
    public static void mostrar(String titulo, String mensaje) {
        try {
            FXMLLoader loader = new FXMLLoader(AlertManager.class.getResource("/fisica/proyectofisica/alert.fxml"));
            Parent root = loader.load();

            AlertController controller = loader.getController();
            controller.setContent(titulo, mensaje);

            Stage stage = new Stage();
            controller.setDialogStage(stage);
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
