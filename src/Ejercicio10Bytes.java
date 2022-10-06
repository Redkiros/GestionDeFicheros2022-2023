import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;

public class Ejercicio10Bytes {
    public static void main(String[] args) throws IOException {
        //Guardo los datos en el HashMap
        HashMap<Character, Integer> charnum = new HashMap<Character, Integer>();
        //Ruta del archivo a leer
        File fl = new File("src/Recursos/EJ10.txt");
        byte[] bytes = Files.readAllBytes(fl.toPath());
        contar(charnum, fl, bytes);
        mostrar(charnum);
    }

    private static void contar(HashMap<Character, Integer> charnum, File fl, byte[] bytes) {
        try {
            int valor = 0;
            FileReader fr = new FileReader(fl);

            while ((valor = fr.read()) != -1) {
                char entrada = (char) valor;
                for (byte caracter : bytes) {
                    if (caracter > 32) {

                        if (!charnum.containsKey((entrada))) {
                            charnum.put(entrada, 1);
                        } else {
                            charnum.put(entrada, charnum.get(entrada) + 1);
                        }
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void mostrar(HashMap<Character, Integer> charnum) {
        for (char entradaK : charnum.keySet()) {
            System.out.println("El cáracter  " + entradaK + "  Esta " + charnum.get(entradaK) + " veces");
        }
    }

}

