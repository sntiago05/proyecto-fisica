package fisica.model;

import jakarta.persistence.*;

@Entity
@Table(name = "estado_ejercicio_usuario")
public class EstadoEjercicioUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private User usuario;

    private int intentos;

    @ManyToOne(optional = false)
    private Ejercicio ejercicio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado;

    public EstadoEjercicioUsuario() {

    }

    public EstadoEjercicioUsuario(User usuario, Ejercicio ejercicio, Estado estado) {
        this.usuario = usuario;
        this.ejercicio = ejercicio;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public User getUsuario() {
        return usuario;
    }


    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }
}
