package Descargar;

import java.io.IOException;
import java.net.MalformedURLException;

public class PruebaDescargador
{

    // Método principal de la aplicación auxiliar.
    public static void main(String[] args)
    {

        // Creamos un objeto de la clase que pide crear la tarea..
        DescargadorDeRecursos l_Descargador = new DescargadorDeRecursos();

        // Cargamos los tipos MIME que queremos ser capaces de procesar.
        l_Descargador.anadirTipoRecurso("text/plain.*", "DescargadorRecursos/csv");


        // Creamos la estructura de carpetas en el disco local.
        try
        {
            l_Descargador.crearArbolCarpetas();
            lanzarDescarga("https://raw.githubusercontent.com/sandravelap/ficheros/main/puntosdeinteres.csv", l_Descargador);
        }
        catch (MalformedURLException l_Excepcion)
        {
            System.out.println("ERROR: La estructura de la URL no es correcta.");
            // Logger.getLogger(PruebaDescargadorRecursos.class.getName()).log(Level.SEVERE, null, l_Excepcion);
        }
        catch (IOException l_Excepcion)
        {
            System.out.println("ERROR: Se ha producido un error al descargar el recurso.");
            // Logger.getLogger(PruebaDescargadorRecursos.class.getName()).log(Level.SEVERE, null, l_Excepcion);
        }

    }   // main()


    // Invoca el método de descarga del recurso de la clase que pide crear la tarea y trata las posibles excepciones.
    public static void lanzarDescarga(String p_Recurso, DescargadorDeRecursos p_Descargador)
    {

        try
        {
            p_Descargador.salvarRecurso(p_Recurso);
        }
        catch (MalformedURLException l_Excepcion)
        {
            System.out.println("ERROR: Recurso desconocido.");
        }
        catch (IOException l_Excepcion)
        {
            System.out.println("ERROR: Se ha producido un error al descargar el recurso.");
        }
        catch (Exception l_Excepcion)
        {
            System.out.println("ERROR: Se ha producido un error de tipo desconocido.");
        }

    }   // lanzarDescarga()

}   // PruebaDescargadorRecursos