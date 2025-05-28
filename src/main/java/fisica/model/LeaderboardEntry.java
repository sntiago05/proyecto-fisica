package fisica.model;

import javafx.beans.property.*;

public class LeaderboardEntry {
    private final StringProperty nombre;
    private final IntegerProperty puntos;
    private final IntegerProperty intentos;
    private final IntegerProperty posicion;

    public LeaderboardEntry(String nombre, int puntos, int intentos, int posicion) {
        this.nombre = new SimpleStringProperty(nombre);
        this.puntos = new SimpleIntegerProperty(puntos);
        this.intentos = new SimpleIntegerProperty(intentos);
        this.posicion = new SimpleIntegerProperty(posicion);
    }

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public int getPuntos() {
        return puntos.get();
    }

    public IntegerProperty puntosProperty() {
        return puntos;
    }

    public int getIntentos() {
        return intentos.get();
    }

    public IntegerProperty intentosProperty() {
        return intentos;
    }

    public int getPosicion() {
        return posicion.get();
    }

    public IntegerProperty posicionProperty() {
        return posicion;
    }
}
