import java.io.File;

public class Ejercicio1 {
    public static void main(String[] args) {
        File archivo1 = new File("src/Recursos/prueba.txt");
        File archivo2 = new File("src/Recursos/prueba2.txt");
        File dir1 = new File("src/Recursos/dir");
        File dir2 = new File("src/Recursos/dir2");

        comprueba(archivo1);
        comprueba(archivo2);
        comprueba(dir1);
        comprueba(dir2);
    }

    static void comprueba(File a) {
        if (a.exists()) {
            System.out.println(a.getName() + " Existe ");
        }
        if (a.isFile()) {
            System.out.println(a.getName() + " Es un Archivo");
        }
        if (a.isDirectory()) {
            System.out.println(a.getName() + " Es un Directorio");
        } else {
            System.out.println(a.getName() + " No existe");
        }
    }
}
