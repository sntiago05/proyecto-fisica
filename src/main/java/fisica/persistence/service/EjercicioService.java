package fisica.persistence.service;

import fisica.model.Ejercicio;

import java.util.List;

public interface EjercicioService {
    void guardar(Ejercicio ejercicio);
    Ejercicio buscarPorId(Long id);
    void actualizar(Ejercicio ejercicio);
    void eliminar(Ejercicio ejercicio);
    List<Ejercicio> listarTodos();
    Ejercicio buscarPorTitulo(String titulo);
}
