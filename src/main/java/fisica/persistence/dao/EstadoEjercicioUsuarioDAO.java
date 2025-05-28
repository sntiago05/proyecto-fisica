package fisica.persistence.dao;

import fisica.model.Ejercicio;
import fisica.model.EstadoEjercicioUsuario;
import fisica.model.User;

import java.util.List;

public abstract class EstadoEjercicioUsuarioDAO extends GenericDAO<EstadoEjercicioUsuario, Long> {

    public EstadoEjercicioUsuarioDAO() {
        super(EstadoEjercicioUsuario.class);
    }

    public abstract EstadoEjercicioUsuario buscarPorUsuarioYEjercicio(User usuario, Ejercicio ejercicio);

    public abstract List<EstadoEjercicioUsuario> listarPorUsuario(User usuario);

    public abstract List<EstadoEjercicioUsuario> leaderboardPorEjercicio(Ejercicio ejercicio);
    public abstract void guardarOActualizar(EstadoEjercicioUsuario estado) ;
}
