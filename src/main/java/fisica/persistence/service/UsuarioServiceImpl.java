package fisica.persistence.service;


import fisica.persistence.dao.UsuarioDAO;
import fisica.model.User;

import java.util.List;

public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    public boolean registrar(String nombre, String password) {
        if (usuarioDAO.buscarPorNombre(nombre) != null) {
            return false;
        }
        User nuevo = new User(nombre, password);
        usuarioDAO.guardar(nuevo);
        return true;
    }

    @Override
    public User autenticar(String nombre, String password) {
        User usuario = usuarioDAO.buscarPorNombre(nombre);
        if (usuario != null && usuario.getPassword().equals(password)) {
            return usuario;
        }
        return null;
    }

    @Override
    public User buscarPorId(Long id) {
        return usuarioDAO.buscarPorId(id);
    }

    @Override
    public List<User> listarUsuarios() {
        return usuarioDAO.listarTodos();
    }

    @Override
    public User buscarPornNombre(String nombre) {
        return usuarioDAO.buscarPorNombre(nombre);
    }
}
