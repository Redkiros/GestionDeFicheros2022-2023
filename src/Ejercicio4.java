import java.io.*;
import java.util.Scanner;

public class Ejercicio4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        File file = new File("src/Recursos/EJ4.txt");

        try {
            FileWriter escribir = new FileWriter(file);
            System.out.println("Introduce la frase: ");
            String frase = sc.nextLine();
            while (!frase.equals("fin")) {
                escribir.write(frase + "\n");
                frase = sc.nextLine();
            }
            escribir.close();

        } catch (IOException e) {
            System.out.println("Error en la Escritura");
        }

        try {
            FileReader leer = new FileReader(file);
            int ch = leer.read();
            while (ch != -1) {
                System.out.print((char) ch);
                ch = leer.read();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error en la lectura");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}