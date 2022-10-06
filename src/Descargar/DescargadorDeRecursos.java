package Descargar;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

    public class DescargadorDeRecursos {

        //Creamos nuestro HashMap
        HashMap<String, String> a_HasMap = new HashMap<>();

        //Añadimos todos los recursos que vamos a utilizar para la creacion de las carpetas y la descarga de los archivos.
        public void anadirTipoRecurso(String l_MIME, String l_Carpeta) {

            a_HasMap.put(l_MIME, l_Carpeta);
        }

        public void crearArbolCarpetas() throws IOException {

            //Recorremos el hashmap
            for (Map.Entry l_Entry : a_HasMap.entrySet()) {
                String l_carpeta = (String) l_Entry.getValue();

                File l_File = new File(l_carpeta);
                // Creamos las carpetas y cualquier error nos mostrara un mensaje.
                try {
                    l_File.mkdirs();

                } catch (Exception e) {
                    System.out.println("Error");
                }
            }
        }

        public void salvarRecurso(String p_Recurso) throws Exception {
            //Definimos todas las variables.
            int l_LeerByte;

            URL l_Url = new URL(p_Recurso);
            URLConnection l_Conexion = (l_Url).openConnection();

            for (String l_string : a_HasMap.keySet()) {

                if (Pattern.matches(l_string, l_Conexion.getContentType()) || (Pattern.matches(l_string, URLConnection.guessContentTypeFromName(p_Recurso)))) {

                    if (l_Conexion.getContentType().matches(l_string)) {
                        //Indicamos la direccion en la que guardaremos los archivos.
                        int l_Direccion = l_Url.getPath().lastIndexOf("/");

                        String l_Archivo = l_Url.getPath().substring(l_Direccion);

                        String l_Archivo2 = a_HasMap.get(l_string);
                        //Guardamos los datos de los archivos.
                        File l_File = new File(l_Archivo2, l_Archivo);
                        byte[] l_Byte = new byte[1024];
                        InputStream l_Ips = l_Conexion.getInputStream();
                        FileOutputStream l_Fos = new FileOutputStream(l_File);

                        //Mostramos el nombre y el tamaño del archivo.
                        if (l_Conexion.getContentLength() >= 0) {

                            System.out.println("El Archivo: " + l_File.getName() + " tiene un tamaño de: " + l_Conexion.getContentLength() + " bytes");
                        } else {

                            System.out.println("Error.");
                        }

                        while ((l_LeerByte = l_Ips.read(l_Byte)) != -1) {
                            l_Fos.write(l_Byte, 0, l_LeerByte);
                        }
                    }
                }
            }
        }
    }
