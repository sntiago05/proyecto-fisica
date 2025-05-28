package fisica.persistence.service;

import fisica.model.Ejercicio;
import fisica.model.EstadoEjercicioUsuario;
import fisica.model.User;
import fisica.persistence.dao.EstadoEjercicioUsuarioDAOImpl;

import java.util.List;

public class EstadoEjercicioUsuarioServiceImpl implements EstadoEjercicioUsuarioService {

    private EstadoEjercicioUsuarioDAOImpl estadoEjercicioUsuarioDAO = new EstadoEjercicioUsuarioDAOImpl();

    public EstadoEjercicioUsuarioServiceImpl() {

    }

    @Override
    public EstadoEjercicioUsuario buscarPorUsuarioYEjercicio(User usuario, Ejercicio ejercicio) {
        return estadoEjercicioUsuarioDAO.buscarPorUsuarioYEjercicio(usuario, ejercicio);
    }


    @Override
    public List<EstadoEjercicioUsuario> listarPorUsuario(User usuario) {
        return estadoEjercicioUsuarioDAO.listarPorUsuario(usuario);
    }

    @Override
    public void guardar(EstadoEjercicioUsuario estadoEjercicioUsuario) {
        estadoEjercicioUsuarioDAO.guardar(estadoEjercicioUsuario);
    }

    @Override
    public void actualizar(EstadoEjercicioUsuario estadoEjercicioUsuario) {
        estadoEjercicioUsuarioDAO.actualizar(estadoEjercicioUsuario);
    }

    @Override
    public void eliminar(EstadoEjercicioUsuario estadoEjercicioUsuario) {
        estadoEjercicioUsuarioDAO.eliminar(estadoEjercicioUsuario);
    }

    public List<EstadoEjercicioUsuario> leaderboardPorEjercicio(Ejercicio ejercicio) {
        return estadoEjercicioUsuarioDAO.leaderboardPorEjercicio(ejercicio);
    }
}
