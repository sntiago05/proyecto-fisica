package fisica.persistence.service;

import fisica.model.Ejercicio;
import fisica.persistence.dao.EjercicioDAO;

import java.util.List;

public class EjercicioServiceImpl implements EjercicioService {

    private final EjercicioDAO ejercicioDAO = new EjercicioDAO();

    @Override
    public void guardar(Ejercicio ejercicio) {
        ejercicioDAO.guardar(ejercicio);
    }

    @Override
    public Ejercicio buscarPorId(Long id) {
        return ejercicioDAO.buscarPorId(id);
    }

    @Override
    public void actualizar(Ejercicio ejercicio) {
        ejercicioDAO.actualizar(ejercicio);
    }

    @Override
    public void eliminar(Ejercicio ejercicio) {
        ejercicioDAO.eliminar(ejercicio);
    }

    @Override
    public List<Ejercicio> listarTodos() {
        return ejercicioDAO.listarTodos();
    }

    @Override
    public Ejercicio buscarPorTitulo(String titulo) {
        return ejercicioDAO.buscarPorTitulo(titulo);
    }

}
