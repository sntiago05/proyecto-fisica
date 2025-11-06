package fisica.model.simulaciones;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.Objects;


public class DiscoRotacionalSimulacion extends EjercicioSimulacion {

    private double anguloActualRad;
    private double velocidadAngularActual;
    private double energiaCineticaRotacionalActual;
    private double tiempo;


    private final double masaDisco = 1.5;
    private final double radioDisco = 0.5;
    private final double torqueAplicado = 2.0;
    private final double momentoInercia = 0.5 * masaDisco * radioDisco * radioDisco;
    private final double aceleracionAngular = torqueAplicado / momentoInercia;

    private final double centroXPx;
    private final double centroYPx;
    private final double radioDiscoPx = 100;
    private final double tiempoMaxSimulacion = 10.0;

    private Image discoImg;
    private Image fondoImg;
    private Timeline timeline;

    public DiscoRotacionalSimulacion() {
        super();
        try {
            discoImg = new Image(Objects.requireNonNull(getClass().getResource("/imgs/disco.png")).toString());
            fondoImg = new Image(Objects.requireNonNull(getClass().getResource("/imgs/fondo-disco.png")).toString());
        } catch (NullPointerException e) {
            System.err.println("Error al cargar imágenes para DiscoRotacionalSimulacion: " + e.getMessage());
        }
        centroXPx = 250;
        centroYPx = 200;
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
            gc.setFill(Color.LIGHTSKYBLUE);
            gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        }

        gc.save();
        Rotate r = new Rotate(Math.toDegrees(anguloActualRad), centroXPx, centroYPx);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());

        if (discoImg != null) {
            gc.drawImage(discoImg, centroXPx - radioDiscoPx, centroYPx - radioDiscoPx, 2 * radioDiscoPx, 2 * radioDiscoPx);
        } else {
            gc.setFill(Color.ORANGE);
            gc.fillOval(centroXPx - radioDiscoPx, centroYPx - radioDiscoPx, 2 * radioDiscoPx, 2 * radioDiscoPx);
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(3);
            gc.strokeLine(centroXPx, centroYPx, centroXPx + radioDiscoPx, centroYPx);
        }
        gc.restore();

        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Arial", 16));
        gc.fillText(String.format("Tiempo: %.2f s", tiempo), 10, 20);
        gc.fillText(String.format("Ángulo: %.2f rad (%.1f°)", anguloActualRad, Math.toDegrees(anguloActualRad)), 10, 40);
        gc.fillText(String.format("Vel. Angular: %.2f rad/s", velocidadAngularActual), 10, 60);
    }

    @Override
    public void simular() {
        if (canvas == null || (timeline != null && timeline.getStatus() == Timeline.Status.RUNNING)) {
            return;
        }
        setCorriendo(true);

        timeline = new Timeline(new KeyFrame(Duration.millis(40), e -> {
            tiempo += 0.040;

            velocidadAngularActual = aceleracionAngular * tiempo;
            anguloActualRad = 0.5 * aceleracionAngular * tiempo * tiempo;
            energiaCineticaRotacionalActual = 0.5 * momentoInercia * velocidadAngularActual * velocidadAngularActual;

            dibujar();

            if (tiempo >= tiempoMaxSimulacion) {
                timeline.stop();
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
        anguloActualRad = 0;
        velocidadAngularActual = 0;
        energiaCineticaRotacionalActual = 0;
        setCorriendo(false);
        dibujar();
    }

    @Override
    public Image portada() {
        return fondoImg;
    }

    @Override
    public void setCanvas(Canvas canvas) {
        super.setCanvas(canvas);
        if (canvas != null) {
            resetear();
        }
    }
}
