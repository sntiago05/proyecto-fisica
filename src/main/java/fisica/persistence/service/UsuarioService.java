package fisica.persistence.service;

import fisica.model.User;

import java.util.List;

public interface UsuarioService {
    boolean registrar(String nombre, String password);

    User autenticar(String nombre, String password);

    User buscarPorId(Long id);

    List<User> listarUsuarios();

    User buscarPornNombre(String nombre);
}
