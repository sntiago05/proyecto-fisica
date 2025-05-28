package fisica.model;

import fisica.model.simulaciones.EjercicioSimulacion;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ejercicios")
public class Ejercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 1000)
    private String enunciado;
    private String tipo;
    private String titulo;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> pistas;
    private String solucion;
    @Column(name = "codigo_simulador")
    private int codigoSimulador;

    @Transient
    private EjercicioSimulacion simulador;

    public Ejercicio() {
    }

    public Ejercicio(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public List<String> getPistas() {
        return pistas;
    }

    public void setPistas(List<String> pistas) {
        this.pistas = pistas;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    public EjercicioSimulacion getSimulador() {
        return simulador;
    }

    public void setSimulador(EjercicioSimulacion simulador) {
        this.simulador = simulador;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCodigoSimulador() {
        return codigoSimulador;
    }

    public void setCodigoSimulador(int codigoSimulador) {
        this.codigoSimulador = codigoSimulador;
    }
}
