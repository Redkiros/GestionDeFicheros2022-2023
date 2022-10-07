package TrabajandoConCSV;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class Descargador {
    public static void main(String[] args) throws IOException {

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

            System.exit(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
