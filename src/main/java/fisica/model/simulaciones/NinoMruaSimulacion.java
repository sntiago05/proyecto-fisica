package fisica.model.simulaciones;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class NinoMruaSimulacion extends EjercicioSimulacion {

    private double x = 30;
    private double tiempo = 0;
    private final double v0 = 2 * 10;
    private final double vf = 6 * 10;
    private final double d = 15 * 10;
    private final double a = (vf * vf - v0 * v0) / (2 * d);

    private Image nino, fondo;
    private Timeline timeline;

    public NinoMruaSimulacion() {
        super();
        nino = new Image(getClass().getResource("/imgs/nino.png").toString());
        fondo = new Image(getClass().getResource("/imgs/fondo-nino.jpg").toString());
    }

    public NinoMruaSimulacion(Canvas canvas) {
        super(canvas);
    }

    @Override
    public void dibujar() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.drawImage(fondo, 0, 0, canvas.getWidth(), canvas.getHeight());
        gc.drawImage(nino, x, canvas.getHeight() / 2 + 50, 60, 60);
    }

    @Override
    public void simular() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        timeline = new Timeline(new KeyFrame(Duration.millis(50), e -> {
            double t = tiempo; // segundos
            double posicion = v0 * t + 0.5 * a * t * t;

            x = 30 + posicion;

            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            gc.drawImage(fondo, 0, 0, canvas.getWidth(), canvas.getHeight());
            gc.drawImage(nino, x, canvas.getHeight() / 2 + 50, 60, 60);

            tiempo += 0.1;

            if (x + 60 >= canvas.getWidth()) {
                timeline.stop();
                Timeline pausa = new Timeline(new KeyFrame(Duration.seconds(1), e2 -> resetear()));
                pausa.play();
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @Override
    public void resetear() {
        x = 30;
        tiempo = 0;
        dibujar();
    }

    @Override
    public Image portada() {
        return this.fondo;
    }
}
