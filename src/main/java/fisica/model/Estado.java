package fisica.model;

public enum Estado {

    PENDIENTE(0, "Pendiente"),

    Resuelto(1, "Resuelto");

    private int code;
    private String nombre;

    Estado(int code, String nombre) {
        this.code = code;
        this.nombre = nombre;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
