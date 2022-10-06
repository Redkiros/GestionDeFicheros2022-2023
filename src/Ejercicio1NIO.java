import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class Ejercicio1NIO {
    public static void main(String[] args) {
        Path path1 = Path.of("src/Recursos/prueba.txt");
        Path path2 = Path.of("src/Recursos/prueba2.txt");
        Path path3 = Path.of("src/Recursos/dir");
        Path path4 = Path.of("src/Recursos/dir2");
        try {
            comprueba(path1);
            comprueba(path2);
            comprueba(path3);
            comprueba(path4);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void comprueba(Path p) throws IOException {
        if (Files.exists(p)) {
            System.out.println(p.getFileName() + " Existe");
            if (Files.isDirectory(p)) {
                System.out.println(" y es un directorio");
            } else {
                System.out.println(" y es un archivo");
                BasicFileAttributes bfa = Files.readAttributes(p, BasicFileAttributes.class);
                System.out.println(bfa.lastAccessTime());
                System.out.println(bfa.lastModifiedTime());
                System.out.println(bfa.size());
                System.out.println(bfa.isSymbolicLink());
            }
        }
    }
}
