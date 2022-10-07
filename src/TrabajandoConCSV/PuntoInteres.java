package TrabajandoConCSV;

import java.util.Scanner;

public class PuntoInteres {
    String nombre, categoria, localidad, comarca;
    double altitud, latitud, longitud, UTMx, UTMy;

    public PuntoInteres() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Introduce el nombre: ");
            nombre = scanner.nextLine();
            System.out.println("Introduce la categoria: ");
            categoria = scanner.nextLine();
            System.out.println("Introduce la localidad: ");
            localidad = scanner.nextLine();
            System.out.println("Introduce la comarca: ");
            comarca = scanner.nextLine();
            System.out.println("Introduce la altitud: ");
            altitud = scanner.nextDouble();
            System.out.println("Introduce la latitud: ");
            latitud = scanner.nextDouble();
            System.out.println("Introduce la longitud: ");
            longitud = scanner.nextDouble();
            System.out.println("Introduce el UTMx: ");
            UTMx = scanner.nextDouble();
            System.out.println("Introduce el UTMy: ");
            UTMy = scanner.nextDouble();

        } catch (Exception e) {
            System.out.println("Error al introducir los datos.");
        }
    }
}
