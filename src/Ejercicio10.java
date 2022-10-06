import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Ejercicio10 {
    public static void main(String[] args) {
        //Guardo los datos en el HashMap
        HashMap<String, Integer> charnum = new HashMap<String, Integer>();
        //Ruta del archivo a leer
        File fl = new File("src/Recursos/EJ10.txt");
        contar(charnum, fl);
        mostrar(charnum);
    }

    private static void contar(HashMap<String, Integer> charnum, File fl) {
        try {
            int valor = 0;
            FileReader fr = new FileReader(fl);

            while ((valor = fr.read()) != -1) {
                String entrada = String.valueOf((char) valor);

                if (valor > 32) {

                    if (!charnum.containsKey((entrada))) {
                        charnum.put(entrada, 1);
                    } else {
                        charnum.put(entrada, charnum.get(entrada) + 1);
                    }
                }

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void mostrar(HashMap<String, Integer> charnum) {
        for (String entradaK : charnum.keySet()) {
            System.out.println("El cáracter  " + entradaK + "  Esta " + charnum.get(entradaK) + " veces");
        }
    }

}

