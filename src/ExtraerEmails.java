import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

public class ExtraerEmails {
    public static void main(String[] args) {
        Path Path = java.nio.file.Path.of("src/Recursos/emails.txt");

        try {
            String text = Files.readAllLines(Path).toString();
            String[] strings = text.split(" ");
            for (String cadena : strings) {
                //Patrón para validar el email
                Pattern pattern = Pattern.compile("([a-zA-Z0-9]+(?:[._+-][a-zA-Z0-9]+)*)@([a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*[.][a-zA-Z]{2,})");

                if (cadena.matches(String.valueOf(pattern))) {
                    System.out.println(cadena);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
