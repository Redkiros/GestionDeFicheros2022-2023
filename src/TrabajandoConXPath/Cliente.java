package TrabajandoConXPath;

import java.sql.SQLData;

public class Cliente {
    String DNI;
    String apellido;
    Integer CP;

    public Cliente(String DNI,String apellido, Integer cp) {
        super();
        this.DNI = DNI;
        this.apellido = apellido;
        this.CP=cp;
    }

    public String getDNI() {
        return DNI;
    }

    public String getApellido() {
        return apellido;
    }

    public Integer getCP() {
        return CP;
    }
}
