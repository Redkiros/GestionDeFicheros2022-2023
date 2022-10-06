import java.io.*;

public class Ejercicio11 {
    public static void main(String[] args) {
        // Le indicamos la ruta del data y la del backup.
        File rutaData = new File("src\\data");
        File rutaBackup = new File("src\\Backups\\Backup");
        File rutaBackupMod = new File("src\\Backups\\Backup\\BackupFinal");
        // Indicamos la lista de archivos de la carpeta de origen.
        File[] archivos = rutaData.listFiles();
        try {
            if (!rutaBackup.exists()) {
                rutaBackup.mkdir();
            }
            for (File archivo : archivos) { // Inicia el bucle para la lectura de los archivos.
                if (cambiararchivo(rutaBackup, archivo)) { // Decimos que es un archivo.
                    if (!rutaBackupMod.exists()) {
                        rutaBackupMod.mkdir();
                    }
                    cont(archivo);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void cont(File archivo) {
        try (FileInputStream fileInputStream = new FileInputStream("src\\data\\" + archivo.getName());
             FileOutputStream fileOutputStream = new FileOutputStream("src\\Backups\\Backup\\BackupFinal\\" + archivo.getName());) {
            // Leer el tamaño de los archivos.
            byte[] tamaño = fileInputStream.readAllBytes();

            // Escribe el archivo.
            fileOutputStream.write(tamaño);

            // Cerramos streams.
            fileOutputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean cambiararchivo(File rutaBackup, File archivoOrigen) throws IOException {
        if (!rutaBackup.exists()) {
            return true;
        } else if (archivoOrigen.lastModified() > rutaBackup.lastModified()) {
            return true;
        } else return false;
    }
}