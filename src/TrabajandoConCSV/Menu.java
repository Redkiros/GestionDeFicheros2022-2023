package TrabajandoConCSV;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Scanner;


public class Menu {
    public static final String SEPARADOR = ",";
    public static final Descargador descargador = new Descargador();
    private static Scanner l_Sc = new Scanner(System.in);

    private static int a_Opcion;
    // Las opciones del menu
    private static String[] menu =
            {" Leer Archivo",
                    " Escribir en Archivo",
                    " Descargar Archivo",
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
                EscribirArchivo();
                break;
            case 3:
                Descargar();
                break;
            case 4:
                System.exit(0);
                break;
        }
    }

    public static void EscribirArchivo() {


    }

    public static void Descargar() {
        String url = "https://raw.githubusercontent.com/sandravelap/ficheros/main/puntosdeinteres.csv"; //Dirección url del recurso a descargar
        String nombre = "puntosdeinteres.csv"; //Nombre del archivo destino

        //Directorio destino para las descargas
        String DireccionDestino = "src/TrabajandoConCSV/";

        try {

            //Crea el directorio de destino en caso de que no exista
            File CrearDestino = new File(DireccionDestino);

            if (!CrearDestino.exists())
                if (!CrearDestino.mkdir())
                    return; //No se pudo crear la carpeta de destino

            File file = new File(DireccionDestino + nombre);

            URLConnection conn = new URL(url).openConnection();
            conn.connect();
            System.out.println("\nEmpezando descarga: \n");
            System.out.println(">> URL: " + url);
            System.out.println(">> Nombre: " + nombre);
            System.out.println(">> Tamaño: " + conn.getContentLength() + " bytes");

            InputStream in = conn.getInputStream();
            OutputStream out = new FileOutputStream(file);

            int b = 0;
            while (b != -1) {
                b = in.read();
                if (b != -1)
                    out.write(b);
            }
            out.close();
            in.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
        } catch (IOException e) {
            System.out.println("Error al leer, comprueba que la direccion introducida es correcta");
        } finally {
            // Cierro el buffer de lectura
            if (bufferLectura != null) {
                try {
                    bufferLectura.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
