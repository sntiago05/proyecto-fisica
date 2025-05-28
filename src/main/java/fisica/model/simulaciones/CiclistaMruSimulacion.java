package fisica.model.simulaciones;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;


public class CiclistaMruSimulacion extends EjercicioSimulacion {

    private double x = 30;
    private final double velocidad = 4 * 10;
    private Image ciclista, fondo;
    private Timeline timeline;

    public CiclistaMruSimulacion() {
        super();
        ciclista = new Image(getClass().getResource("/imgs/ciclista.png").toString());
        fondo = new Image(getClass().getResource("/imgs/fondo.jpg").toString());
    }


    @Override
    public void dibujar() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.drawImage(fondo, 0, 0, canvas.getWidth(), canvas.getHeight());
        gc.drawImage(ciclista, x, canvas.getHeight() / 2 + 10, 60, 60);
    }

    @Override
    public void simular() {

        GraphicsContext gc = canvas.getGraphicsContext2D();

        timeline = new Timeline(new KeyFrame(Duration.millis(50), e -> {
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            gc.drawImage(fondo, 0, 0, canvas.getWidth(), canvas.getHeight());
            gc.drawImage(ciclista, x, canvas.getHeight() / 2 + 10, 60, 60);

            x += velocidad * 0.1;

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
        dibujar();
    }

    @Override
    public Image portada() {
        return this.fondo;
    }

}
