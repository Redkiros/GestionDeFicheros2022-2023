import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce la ruta del Directorio:");
        File file1 = new File(scanner.nextLine());
        String[] ficheros = file1.list();

        mostrar(ficheros);
    }

    private static void mostrar(String[] ficheros) {
        if (ficheros == null)
            System.out.println("No hay ficheros en el directorio especificado");
        else {
            for (int x = 0; x < ficheros.length; x++)
                System.out.println(ficheros[x]);
        }
    }
}
