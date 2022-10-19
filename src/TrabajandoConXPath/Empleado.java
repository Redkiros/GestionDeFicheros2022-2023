package TrabajandoConXPath;

public class Empleado {
    String DNI;
    String Nombre;
    String Apellido;
    String Sueldo;
    String Base;
    String IRPF;

    public Empleado(String dni, String nombre, String apellido, String sueldo, String base, String irpf) {
        super();
        this.DNI = dni;
        this.Nombre = nombre;
        this.Apellido = apellido;
        this.Sueldo = sueldo;
        this.Base = base;
        this.IRPF = irpf;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getSueldo() {
        return Sueldo;
    }

    public void setSueldo(String sueldo) {
        Sueldo = sueldo;
    }

    public String getBase() {
        return Base;
    }

    public void setBase(String base) {
        Base = base;
    }

    public String getIRPF() {
        return IRPF;
    }

    public void setIRPF(String IRPF) {
        this.IRPF = IRPF;
    }
}
