package TrabajandoConCSV;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


public class Menu {
    public static final String SEPARADOR = ",";

    private static Scanner l_Sc = new Scanner(System.in);

    private static int a_Opcion;
    // Las opciones del menu
    private static String[] menu =
            {" Leer Archivo",
                    " Escribir en Archivo",
                    " Salir"};

    public static void main(String[] args) {
        // Hacer mientras
        do {
            // Llamada al menu
            menu();
            // Si opcion = 0 termina, si es diferente vuelve a mostrar el menu
        } while (a_Opcion != 0);
    }

    /* Funcion que presenta un menu y ejecuta la opcion escogida */
    private static void menu() {
        System.out.println("");
        System.out.println("   ** Opciones del Menu **");
        System.out.println("   =======================");

        // Se muestran las opciones del menu
        for (a_Opcion = 0; a_Opcion < menu.length; a_Opcion++) {

            // Cada iteracion muestra una opcion del menu
            System.out.println(
                    (a_Opcion + 1) + menu[a_Opcion]);
        }

        System.out.print("Elija su opcion: ");

        a_Opcion = l_Sc.nextInt();

        // Bloque switch case, segun la opcion selecionada
        // se ejecuta el metodo que le corresponde
        switch (a_Opcion) {
            case 1:
                LeerArchivo();
                break;
            case 2:
               // ValidarletraNIF();
                break;
            case 3:
                System.exit(0);
                break;
        }
    }

    public static void PuntoInteres() throws IOException {

    }

    public static void LeerArchivo() {
        BufferedReader bufferLectura = null;
        try {
            // Abrir el .csv en buffer de lectura
            bufferLectura = new BufferedReader(new FileReader("src/TrabajandoConCSV/puntosdeinteres.csv"));

            // Leer una linea del archivo
            String linea = bufferLectura.readLine();

            while (linea != null) {
                // Sepapar la linea leída con el separador definido previamente
                String[] campos = linea.split(SEPARADOR);

                System.out.println(Arrays.toString(campos));

                // Volver a leer otra línea del fichero
                linea = bufferLectura.readLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            // Cierro el buffer de lectura
            if (bufferLectura != null) {
                try {
                    bufferLectura.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
