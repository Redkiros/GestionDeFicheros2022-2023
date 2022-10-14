package TrabajandoConXPath;

public class Alumno {
    private String nombre;
    private Integer edad;

    public Alumno(String nombre, Integer edad) {
        super();
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }
}
