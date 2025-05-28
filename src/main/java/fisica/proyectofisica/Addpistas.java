package fisica.proyectofisica;

import fisica.connection.JpaConnection;
import fisica.model.Ejercicio;
import fisica.persistence.service.EjercicioServiceImpl;
import jakarta.persistence.EntityManager;

import java.util.List;

public class Addpistas {
    public static void main(String[] args) {
        EjercicioServiceImpl service = new EjercicioServiceImpl();

        Ejercicio ejercicio = service.buscarPorId(3L);

        if (ejercicio != null) {

            ejercicio.setPistas(List.of("Usa la segunda ley de Newton: F=maF=ma.", "La fricción cinética se calcula con fk=μ⋅Nfk\u200B=μ⋅N, donde NN es la normal.", "La normal es igual al peso si el movimiento es horizontal: N= m⋅g."));
            service.actualizar(ejercicio);
        } else {
            System.out.println("ñaum");
        }

    }
}
