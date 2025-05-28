package fisica.controller;

import fisica.model.EstadoEjercicioUsuario;
import fisica.model.simulaciones.FactorySimulador;
import fisica.persistence.service.EjercicioServiceImpl;
import fisica.persistence.service.EstadoEjercicioUsuarioServiceImpl;
import fisica.utils.SessionManager;
import fisica.utils.WindowManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import fisica.model.Ejercicio;
import fisica.model.simulaciones.CiclistaMruSimulacion;


import java.io.IOException;
import java.util.List;

public class MainController {

    private EstadoEjercicioUsuarioServiceImpl service = new EstadoEjercicioUsuarioServiceImpl();


    @FXML
    private GridPane gridEjercicios;

    @FXML
    private Label lblTitulo;

    @FXML
    private Label lblTipo;

    @FXML
    private Label labelEstado;

    @FXML
    private void initialize() {
        limpiarInformacion();

        EjercicioServiceImpl ejercicioService = new EjercicioServiceImpl();


        List<Ejercicio> ejercicios = ejercicioService.listarTodos();


        for (Ejercicio ejercicio : ejercicios) {
            try {
                ejercicio.setSimulador(FactorySimulador.crearSimulador(ejercicio.getCodigoSimulador()));
            } catch (RuntimeException e) {
                System.out.println("Simulador no válido para el ejercicio: " + ejercicio.getTitulo());
            }
        }
        cargarEjercicios(ejercicios);
    }


    public void cargarEjercicios(List<Ejercicio> ejercicios) {
        gridEjercicios.getChildren().clear();

        int columnas = 2;
        int fila = 0;
        int columna = 0;

        for (Ejercicio e : ejercicios) {
            VBox card = crearCard(e);
            gridEjercicios.add(card, columna, fila);

            columna++;
            if (columna == columnas) {
                columna = 0;
                fila++;
            }
        }
    }

    private VBox crearCard(Ejercicio ejercicio) {
        VBox card = new VBox();
        card.setAlignment(Pos.CENTER);
        card.getStyleClass().add("card");
        card.setPrefWidth(200);
        card.setPrefHeight(250);

        Label titulo = new Label();
        titulo.setText(ejercicio.getTitulo());
        titulo.getStyleClass().add("card-title");

        ImageView portada = new ImageView();
        portada.setFitWidth(200);
        portada.setFitHeight(100);


        if (ejercicio.getSimulador() != null && ejercicio.getSimulador().portada() != null) {
            portada.setImage(ejercicio.getSimulador().portada());
        } else {
            System.out.println("imagen no cargada");
        }

        card.getChildren().addAll(portada, titulo);
        card.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                abrirVistaEjercicio(ejercicio);
            } else {
                lblTitulo.setText(ejercicio.getTitulo());
                lblTipo.setText(ejercicio.getTipo());
                EstadoEjercicioUsuario estado = service.buscarPorUsuarioYEjercicio(SessionManager.getInstance().getUsuarioLoggeado(), ejercicio);
                if (estado != null) {
                    labelEstado.setText(estado.getEstado().name());
                } else {
                    labelEstado.setText("Pendiente");
                }
            }
        });
        return card;
    }

    private void abrirVistaEjercicio(Ejercicio ejercicio) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fisica/proyectofisica/vistaEjercicio.fxml"));
            Parent root = loader.load();

            VistaEjercicioController controller = loader.getController();
            controller.setEjercicio(ejercicio);

            Stage stageActual = (Stage) gridEjercicios.getScene().getWindow();

            stageActual.setTitle(ejercicio.getTitulo());
            stageActual.setScene(new Scene(root));
            stageActual.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void limpiarInformacion() {
        lblTitulo.setText("Ninguno");
        lblTipo.setText("Ninguno");
        labelEstado.setText("Ninguno");
    }

    @FXML
    private void cerrarSesion() {
        WindowManager.cambiarVentana("/fisica/proyectofisica/login.fxml", gridEjercicios);
        if (SessionManager.getInstance().sesionActiva()) {
            SessionManager.getInstance().setUsuarioLoggeado(null);
        }
    }
}
