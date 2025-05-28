package fisica.persistence.service;

import fisica.model.Ejercicio;
import fisica.model.EstadoEjercicioUsuario;
import fisica.model.User;

import java.util.List;

public interface EstadoEjercicioUsuarioService {

    EstadoEjercicioUsuario buscarPorUsuarioYEjercicio(User usuario, Ejercicio ejercicio);

    List<EstadoEjercicioUsuario> listarPorUsuario(User usuario);

    void guardar(EstadoEjercicioUsuario estadoEjercicioUsuario);

    void actualizar(EstadoEjercicioUsuario estadoEjercicioUsuario);

    void eliminar(EstadoEjercicioUsuario estadoEjercicioUsuario);
}
