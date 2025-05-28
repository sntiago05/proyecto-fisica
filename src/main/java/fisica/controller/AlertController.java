package fisica.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AlertController {
    @FXML
    private Label titleLabel;
    @FXML
    private Label messageLabel;
    @FXML
    private Button okButton;

    private Stage dialogStage;

    public void setDialogStage(Stage stage) {
        this.dialogStage = stage;
    }

     @FXML
    private void closeAlert() {
        dialogStage.close();
    }
    public void setContent(String title, String message) {
        titleLabel.setText(title);
        messageLabel.setText(message);
    }
    
}
