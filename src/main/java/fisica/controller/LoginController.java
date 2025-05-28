package fisica.controller;

import fisica.persistence.service.UsuarioService;
import fisica.persistence.service.UsuarioServiceImpl;
import fisica.utils.AlertManager;
import fisica.utils.SessionManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import fisica.model.User;

import java.io.IOException;

import static fisica.utils.FiltrosCampos.camposVacios;

public class LoginController {
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtNombreR;

    @FXML
    private Label labelNombre;
    @FXML
    private Label labelNombreR;

    @FXML
    private PasswordField txtPassword;
    @FXML
    private PasswordField txtPasswordR;

    @FXML
    private Label labelPassword;
    @FXML
    private Label labelPasswordR;

    @FXML
    private TabPane tabPaneLogin;
    private UsuarioService usuarioService = new UsuarioServiceImpl();

    @FXML
    public void initialize() {
        setLabelFocus(txtPassword, labelPassword);
        setLabelFocus(txtPasswordR, labelPasswordR);
        setLabelFocus(txtNombreR, labelNombreR);
        setLabelFocus(txtNombre, labelNombre);
        tabPaneLogin.getSelectionModel().selectedItemProperty().addListener((obs, oldTab, newTab) -> {
            clearTextFields(tabPaneLogin);
        });

    }


    @FXML
    private void login() {
        String nombre = txtNombre.getText().trim();
        String password = txtPassword.getText().trim();
        if (camposVacios(txtNombre, txtPassword)) {
            AlertManager.mostrar("Campos vacíos", "Por favor ingrese nombre y contraseña.");

            return;
        }
        User usuario = usuarioService.autenticar(nombre, password);
        if (usuario != null) {
            SessionManager.getInstance().setUsuarioLoggeado(usuario);
            AlertManager.mostrar("Bienvenido!", "Inicio de sesión exitoso para: " + usuario.getNickName());

            redirigir();
        } else {
            AlertManager.mostrar("Error", "Nombre o contraseña incorrectos.");
        }
    }

    @FXML
    private void register() {
        String nombre = txtNombreR.getText().trim();
        String password = txtPasswordR.getText().trim();

        if (camposVacios(txtNombreR, txtPasswordR)) {
            AlertManager.mostrar("Campos vacios", "debes llenar todos los campos");
            return;
        }

        UsuarioService usuarioService = new UsuarioServiceImpl();
        boolean registrado = usuarioService.registrar(nombre, password);

        if (registrado) {
            AlertManager.mostrar("Registro exitoso", "Usuario guardado exitosamente");
            txtNombreR.clear();
            txtPasswordR.clear();
            tabPaneLogin.getSelectionModel().select(0);
        } else {
            AlertManager.mostrar("Error", "El nombre de usuario ya está en uso.");
        }
    }


    private void setLabelFocus(TextField campo, Label etiqueta) {
        campo.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal || !campo.getText().isEmpty()) {
                etiqueta.setStyle("-fx-text-fill:#2196f3 ;");
            } else {
                etiqueta.setStyle("-fx-text-fill:#000000 ;");
            }
        });
    }


    private void clearTextFields(Parent root) {
        for (Node node : root.lookupAll(".text-field")) {
            if (node instanceof TextField textField) {
                textField.clear();
            }
        }
    }

    private void redirigir() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fisica/proyectofisica/main.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stageActual = (Stage) txtNombre.getScene().getWindow();
        stageActual.setScene(new Scene(root));
        stageActual.show();
    }
}
