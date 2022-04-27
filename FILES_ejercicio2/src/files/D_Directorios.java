/**
 * Manejando directorios y subdirectorios
 * Comprobando que existen para crearlos y recorrerlos
 */
package files;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class D_Directorios {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        Path dir = Paths.get("files");
        if (Files.notExists(dir)) {
            Files.createDirectory(dir);
        }
        Path subdir = Paths.get(dir.toString(),"subdir2");
        if (Files.notExists(subdir)) {
            Files.createDirectory(subdir);
        }

        //Path complete = dir.resolve(subdir);

        //DirectoryStream: nos permite iterar el contenido de un directorio
        DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get("files"));

        for (Path p : directoryStream) {
            System.out.println(p.getFileName());
        }

        // Para recorrer el contenido de un directorio vamos a realizar el uso de Stream... próximamente!!!!
        
    }

}
