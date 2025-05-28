package fisica.controller;

import fisica.model.Estado;
import fisica.model.EstadoEjercicioUsuario;
import fisica.model.User;
import fisica.persistence.service.EstadoEjercicioUsuarioServiceImpl;
import fisica.persistence.service.UsuarioServiceImpl;
import fisica.utils.AlertManager;
import fisica.utils.SessionManager;
import fisica.utils.WindowManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import fisica.model.Ejercicio;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class VistaEjercicioController {

    private EstadoEjercicioUsuarioServiceImpl service = new EstadoEjercicioUsuarioServiceImpl();
    private UsuarioServiceImpl usuarioService = new UsuarioServiceImpl();

    int indicePistas = 0;
    int pistasVistas = 0;

    private Ejercicio ejercicio;
    @FXML
    private Canvas canvasEjercicio;
    @FXML
    private Label txtTitle;

    @FXML
    private TextField txtRespuesta;

    @FXML
    private Button btnVerPistas;

    @FXML
    private void initialize() {
        Rectangle clip = new Rectangle(
                canvasEjercicio.getWidth(),
                canvasEjercicio.getHeight()
        );
        clip.setArcWidth(40);
        clip.setArcHeight(40);
        canvasEjercicio.setClip(clip);
    }

    public void setEjercicio(Ejercicio ejercicio) {
        this.ejercicio = ejercicio;
        this.ejercicio.getSimulador().setCanvas(canvasEjercicio);
        this.ejercicio.getSimulador().dibujar();
        txtTitle.setText(this.ejercicio.getEnunciado());
    }

    @FXML
    private void simular() {
        ejercicio.getSimulador().simular();
    }


    @FXML
    private void verPista() {
        int totalPistas = this.ejercicio.getPistas().size();

        if (indicePistas < totalPistas) {
            AlertManager.mostrar("pista numero " + (++pistasVistas) + " de " + totalPistas,
                    this.ejercicio.getPistas().get(indicePistas));
            indicePistas++;
        } else {
            indicePistas = 0;
            pistasVistas = 0;
        }
    }

    @FXML
    private void verificar() {
        User usuario = SessionManager.getInstance().getUsuarioLoggeado();
        String respuesta = txtRespuesta.getText().trim().toLowerCase();

        if (respuesta.isBlank()) {
            AlertManager.mostrar("Campo respuesta vacío", "Debes escribir una respuesta");
            return;
        }

        boolean esCorrecta = respuesta.equalsIgnoreCase(this.ejercicio.getSolucion());

        EstadoEjercicioUsuario estadoActual = service.buscarPorUsuarioYEjercicio(usuario, ejercicio);
        boolean yaEstabaResuelto = estadoActual != null && estadoActual.getEstado() == Estado.Resuelto;

        if (estadoActual == null) {
            estadoActual = new EstadoEjercicioUsuario(usuario, ejercicio,
                    esCorrecta ? Estado.Resuelto : Estado.PENDIENTE);
            estadoActual.setIntentos(1);
            service.guardar(estadoActual);
        } else if (!yaEstabaResuelto) {
            estadoActual.setIntentos(estadoActual.getIntentos() + 1);
            if (esCorrecta) {
                estadoActual.setEstado(Estado.Resuelto);
            }
            service.actualizar(estadoActual);
        }

        if (esCorrecta) {
            if (yaEstabaResuelto) {
                AlertManager.mostrar("Respuesta correcta", "¡Felicidades! Ya habías resuelto este ejercicio.");
            } else {
                AlertManager.mostrar("Respuesta correcta", "¡Felicidades! Tu respuesta es acertada");
            }
        } else {
            AlertManager.mostrar("Respuesta incorrecta", "Inténtalo de nuevo");
        }
    }

    @FXML
    private void verLeader() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fisica/proyectofisica/leaderBoard.fxml"));
            Parent root = loader.load();
            LeaderBoardController controller = loader.getController();
            controller.cargarLeaderboard(ejercicio);
            Stage stage = new Stage();
            controller.setDialogStage(stage);
            stage.setTitle("Leaderboard");
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            AlertManager.mostrar("Error", "No se pudo cargar la ventana de leaderboard.");
        }
    }

    @FXML
    private void irAtras() {
        WindowManager.cambiarVentana("/fisica/proyectofisica/main.fxml",txtTitle);
    }

}

