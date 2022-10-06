import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ejercicio3 {
    public static void main(String[] args) throws IOException {
        Path path1 = Path.of("src/Recursos/prueba.txt");
        mostrar(path1);
    }

    static void mostrar(Path p) throws IOException {

            if (Files.exists(p)) {
                System.out.println("Leyendo archivo de texto.......");
                //Files.readAllLines(p).forEach(System.out::println);
                for (Path ruta:Files.newDirectoryStream(p)){
                    Path linea = ruta.getName(p.getNameCount());
                    System.out.println(linea);
                }
            } else {
                System.out.println("Ruta no encontrada / No se a podido leer el archivo");
            }
        }
    }
