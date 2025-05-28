package fisica.utils;

import fisica.controller.AlertController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.w3c.dom.Text;

public class FiltrosCampos {
    public static void restringirEntrada(TextField campo) {
        campo.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.matches("\"[a-zA-Z0-9]*\"")) {
                campo.setText(oldValue);
            }
        });
    }

    public static boolean camposVacios(TextField campo1, TextField campo2) {
        return campo1.getText().isBlank()||campo2.getText().isBlank();
    }

}
