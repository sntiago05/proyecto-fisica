package fisica.model.simulaciones;

public class FactorySimulador {


    public static EjercicioSimulacion crearSimulador(int codigo) {
        return switch (codigo) {
            case 1 -> new CiclistaMruSimulacion();
            case 2 -> new NinoMruaSimulacion();
            case 3 -> new CajaFriccionSimulacion();
            case 4 -> new PelotaParabolicoSimulacion();
            case 5 -> new BloqueEnergiaCineticaSimulacion();
            case 6 -> new DiscoRotacionalSimulacion();
            default -> throw new RuntimeException("Codigo no valido");
        };
    }
}
