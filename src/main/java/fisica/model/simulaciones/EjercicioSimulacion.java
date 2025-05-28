package fisica.model.simulaciones;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public abstract class EjercicioSimulacion {
    protected Canvas canvas;
    protected boolean corriendo = false;

    public EjercicioSimulacion() {
    }

    public EjercicioSimulacion(Canvas canvas) {
        this.canvas = canvas;
    }

    public abstract void dibujar();

    public abstract void simular();

    public abstract void resetear();

    public abstract Image portada();

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public boolean isCorriendo() {
        return corriendo;
    }
    public void setCorriendo(boolean corriendo) {
        this.corriendo = corriendo;
    }
}
