package fisica.model.simulaciones;

public class FactorySimulador {


    public static EjercicioSimulacion crearSimulador(int codigo) {
        return switch (codigo) {
            case 1 -> new CiclistaMruSimulacion();
            case 2 -> new NinoMruaSimulacion();
            case 3 -> new CajaFriccionSimulacion();
            default -> throw new RuntimeException("Codigo no valido");
        };
    }
}
