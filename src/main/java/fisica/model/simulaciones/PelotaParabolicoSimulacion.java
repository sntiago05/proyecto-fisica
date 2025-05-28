package fisica.model.simulaciones;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.util.Objects;

public class PelotaParabolicoSimulacion extends EjercicioSimulacion {
    private double x = 0;
    private double y = 0;
    private double tiempo = 0;

    private final double v0 = 20;
    private final double angulo = Math.toRadians(45);
    private final double v0x = v0 * Math.cos(angulo);
    private final double v0y = v0 * Math.sin(angulo);
    private final double g = 9.8;
    private final double escala = 5;
    private final double yBasePantalla = 120;
    private final double xInicioPantalla = 180;


    private Image pelota, fondo, nino;
    private Timeline timeline;

    public PelotaParabolicoSimulacion() {
        super();
        pelota = new Image(Objects.requireNonNull(getClass().getResource("/imgs/pelota.png")).toString());
        fondo = new Image(Objects.requireNonNull(getClass().getResource("/imgs/fondo-edificio.png")).toString());
        nino = new Image(Objects.requireNonNull(getClass().getResource("/imgs/estudiante.png")).toString());
    }

    @Override
    public void dibujar() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.drawImage(fondo, 0, 0, canvas.getWidth(), canvas.getHeight());
        gc.drawImage(nino, 130, yBasePantalla - 10 * escala - 15, 70, 70); // niño en la azotea
        gc.drawImage(pelota, xInicioPantalla, yBasePantalla - 10 * escala, 30, 30); // pelota justo desde y=20m
    }

    @Override
    public void simular() {
        if (timeline != null && timeline.getStatus() == Timeline.Status.RUNNING) {
            return;
        }
        GraphicsContext gc = canvas.getGraphicsContext2D();
        timeline = new Timeline(new KeyFrame(Duration.millis(40), e -> {
            double xFisico = v0x * tiempo;
            double yReal = 20 + v0y * tiempo - 0.5 * g * tiempo * tiempo;

            double xPantalla = xInicioPantalla + xFisico * escala;
            double yPantalla = yBasePantalla - yReal * escala;

            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            gc.drawImage(fondo, 0, 0, canvas.getWidth(), canvas.getHeight());
            gc.drawImage(nino, 130, yBasePantalla - 10 * escala - 15, 70, 70);
            gc.drawImage(pelota, xPantalla, yPantalla, 30, 30);

            tiempo += 0.1;

            if (yReal <= -60) {
                timeline.stop();
                resetear();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }


    @Override
    public void resetear() {
        x = 0;
        y = 0;
        tiempo = 0;
        dibujar();
    }

    @Override
    public Image portada() {
        return fondo;
    }
}
