package fisica.controller;

import fisica.model.Ejercicio;
import fisica.model.EstadoEjercicioUsuario;
import fisica.persistence.dao.EstadoEjercicioUsuarioDAOImpl;
import fisica.model.LeaderboardEntry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.List;

public class LeaderBoardController {


    private Stage dialogStage;


    @FXML
    private Label lbltitulo;
    @FXML
    private TableView<LeaderboardEntry> leaderboard;
    @FXML
    private TableColumn<LeaderboardEntry, String> colNombre;
    @FXML
    private TableColumn<LeaderboardEntry, Integer> colPuntos;
    @FXML
    private TableColumn<LeaderboardEntry, Integer> colIntentos;
    @FXML
    private TableColumn<LeaderboardEntry, Integer> colPosicion;

    private final EstadoEjercicioUsuarioDAOImpl estadoDAO = new EstadoEjercicioUsuarioDAOImpl();

    public void cargarLeaderboard(Ejercicio ejercicio) {
        lbltitulo.setText("Leaderboard - " + ejercicio.getTitulo());

        List<EstadoEjercicioUsuario> resultados = estadoDAO.leaderboardPorEjercicio(ejercicio);
        int puntosBase = 100;

        ObservableList<LeaderboardEntry> entries = FXCollections.observableArrayList();
        for (int i = 0; i < resultados.size(); i++) {
            EstadoEjercicioUsuario estado = resultados.get(i);
            String nombre = estado.getUsuario().getNickName();
            int intentos = estado.getIntentos();
            int puntos = Math.max(puntosBase - intentos * 10, 10);
            int posicion = i + 1;

            entries.add(new LeaderboardEntry(nombre, puntos, intentos, posicion));
        }


        colNombre.setCellValueFactory(cell -> cell.getValue().nombreProperty());
        colPuntos.setCellValueFactory(cell -> cell.getValue().puntosProperty().asObject());
        colIntentos.setCellValueFactory(cell -> cell.getValue().intentosProperty().asObject());
        colPosicion.setCellValueFactory(cell -> cell.getValue().posicionProperty().asObject());

        leaderboard.setItems(entries);
    }

    public void setDialogStage(Stage stage) {
        this.dialogStage = stage;
    }

    @FXML
    private void cerrar() {
        this.dialogStage.close();
    }
}

