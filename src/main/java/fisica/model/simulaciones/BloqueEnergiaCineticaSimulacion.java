package fisica.model.simulaciones;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.util.Objects;


public class BloqueEnergiaCineticaSimulacion extends EjercicioSimulacion {

    private double xBloque;
    private double tiempo;
    private double velocidadActual;
    private double energiaCineticaActual;
    private double distanciaRecorridaMetros;


    private final double masa = 2.0;
    private final double velocidadInicial = 5.0;
    private final double fuerzaAplicada = 10.0;
    private final double aceleracion = fuerzaAplicada / masa;


    private final double xInicialPx = 50;
    private final double yBloquePx;
    private final double escalaDistancia = 20;
    private final double distanciaMaximaSimulacion = 25;

    private Image bloqueImg;
    private Image fondoImg;
    private Timeline timeline;

    public BloqueEnergiaCineticaSimulacion() {
        super();
        try {
            bloqueImg = new Image(Objects.requireNonNull(getClass().getResource("/imgs/bloque.png")).toString());
            fondoImg = new Image(Objects.requireNonNull(getClass().getResource("/imgs/fondo-cinetica.jpg")).toString());
        } catch (NullPointerException e) {
            System.err.println("Error al cargar imágenes. Asegúrate de que las rutas son correctas: " + e.getMessage());
        }
        yBloquePx = 370;
        resetear();
    }

    @Override
    public void dibujar() {
        if (canvas == null) return;
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());


        if (fondoImg != null) {
            gc.drawImage(fondoImg, 0, 0, canvas.getWidth(), canvas.getHeight());
        } else {
            gc.setFill(Color.LIGHTGRAY);
            gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        }


        double altoBloquePx = 50;
        double anchoBloquePx = 50;
        if (bloqueImg != null) {
            gc.drawImage(bloqueImg, xBloque, yBloquePx - altoBloquePx, anchoBloquePx, altoBloquePx);
        } else {
            gc.setFill(Color.BLUE);
            gc.fillRect(xBloque, yBloquePx - altoBloquePx, anchoBloquePx, altoBloquePx);
        }


        gc.setFill(Color.BLACK);
        gc.setFont(new Font("Arial", 16));
        gc.fillText(String.format("Tiempo: %.2f s", tiempo), 10, 20);
        gc.fillText(String.format("Distancia: %.2f m", distanciaRecorridaMetros), 10, 40);
        gc.fillText(String.format("Velocidad: %.2f m/s", velocidadActual), 10, 60);
        gc.fillText("Pregunta: ¿Energía cinética a los 15 m?", 10, canvas.getHeight() - 60);
    }

    @Override
    public void simular() {
        if (canvas == null || (timeline != null && timeline.getStatus() == Timeline.Status.RUNNING)) {
            return;
        }
        setCorriendo(true);

        timeline = new Timeline(new KeyFrame(Duration.millis(40), e -> {
            tiempo += 0.040;

            velocidadActual = velocidadInicial + aceleracion * tiempo;
            distanciaRecorridaMetros = velocidadInicial * tiempo + 0.5 * aceleracion * tiempo * tiempo;
            xBloque = xInicialPx + distanciaRecorridaMetros * escalaDistancia;

            energiaCineticaActual = 0.5 * masa * velocidadActual * velocidadActual;

            dibujar();

            if (distanciaRecorridaMetros >= distanciaMaximaSimulacion || xBloque + 50 >= canvas.getWidth()) {
                timeline.stop();
                resetear();
                setCorriendo(false);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @Override
    public void resetear() {
        if (timeline != null) {
            timeline.stop();
        }
        tiempo = 0;
        xBloque = xInicialPx;
        velocidadActual = velocidadInicial;
        energiaCineticaActual = 0.5 * masa * velocidadInicial * velocidadInicial;
        distanciaRecorridaMetros = 0;
        setCorriendo(false);
        dibujar();
    }

    @Override
    public Image portada() {
        return fondoImg;
    }

}
