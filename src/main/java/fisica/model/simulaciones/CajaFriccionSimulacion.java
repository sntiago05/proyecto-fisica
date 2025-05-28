package fisica.model.simulaciones;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class CajaFriccionSimulacion extends EjercicioSimulacion {

    private double x = 30;
    private double tiempo = 0;

    private final double masa = 10; // kg
    private final double fuerza = 50; // N
    private final double mu = 0.2;
    private final double g = 9.8;
    private final double friccion = mu * masa * g;
    private final double a = (fuerza - friccion) / masa;

    private Image caja, fondo;
    private Timeline timeline;

    public CajaFriccionSimulacion() {
        super();
        caja = new Image(getClass().getResource("/imgs/caja.png").toString());
        fondo = new Image(getClass().getResource("/imgs/fondo-trabajador.jpg").toString());
    }

    public CajaFriccionSimulacion(Canvas canvas) {
        super(canvas);
    }

    @Override
    public void dibujar() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.drawImage(fondo, 0, 0, canvas.getWidth(), canvas.getHeight());
        gc.drawImage(caja, x, canvas.getHeight() / 2 + 80, 90, 90);
    }

    @Override
    public void simular() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        timeline = new Timeline(new KeyFrame(Duration.millis(50), e -> {
            double posicion = 0.5 * a * tiempo * tiempo;
            x = 30 + posicion;

            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            gc.drawImage(fondo, 0, 0, canvas.getWidth(), canvas.getHeight());
            gc.drawImage(caja, x, canvas.getHeight() / 2 + 80, 90, 90);

            tiempo += 0.1;

            if (x + 80 >= canvas.getWidth()) {
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
        return fondo;
    }
}
