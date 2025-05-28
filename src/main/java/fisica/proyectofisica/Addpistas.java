package fisica.proyectofisica;

import fisica.connection.JpaConnection;
import fisica.model.Ejercicio;
import fisica.persistence.service.EjercicioServiceImpl;
import jakarta.persistence.EntityManager;

import java.util.List;

public class Addpistas {
    public static void main(String[] args) {
        EjercicioServiceImpl service = new EjercicioServiceImpl();

        Ejercicio ejercicio = service.buscarPorId(4L);
        Ejercicio ejercicio2 = service.buscarPorId(5L);
        Ejercicio ejercicio3 = service.buscarPorId(6L);

        if (ejercicio3 != null) {
            ejercicio.setPistas(List.of("Usa la descomposición del vector de velocidad inicial en sus componentes horizontal y vertical.", "Recuerda que la aceleración en el eje X es cero, pero en el eje Y es la gravedad.", "Usa la fórmula de posición vertical para hallar el tiempo total hasta que y = 0 (cuando la pelota toca el suelo).", "Luego con ese tiempo, usa la posición horizontal para encontrar la distancia recorrida."));
            ejercicio2.setPistas(List.of("Para la pregunta, puedes usar el teorema del trabajo y la energía o las ecuaciones de cinemática.", "La energía cinética depende de la masa y del cuadrado de la velocidad.", "En la simulación, observa cómo la fuerza constante produce una aceleración constante, lo que lleva a un aumento lineal de la velocidad y un aumento cuadrático de la energía cinética con el tiempo."));
            ejercicio3.setPistas(List.of("Calcula primero el momento de inercia y la aceleración angular.", "Usa las ecuaciones de cinemática rotacional para encontrar la velocidad angular en el tiempo dado.", "Momento de Inercia de un disco sólido (I): I = 0.5 * M * R^2", " La energía cinética rotacional depende del momento de inercia y del cuadrado de la velocidad angular.", "Energía Cinética Rotacional a los 5s (Kr(5s)): Kr(5s) = 0.5 * I * ω(5s)^2"));
            service.actualizar(ejercicio3);
            service.actualizar(ejercicio2);
            service.actualizar(ejercicio);
        } else {
            System.out.println("ñaum");
        }

    }
}
