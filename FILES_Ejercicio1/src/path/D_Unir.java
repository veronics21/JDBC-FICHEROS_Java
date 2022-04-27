/**
 *
 */
package path;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class D_Unir {

    /**
     * @param args
     */
    public static void main(String[] args){ //throws IOException{

        Path basePath = Paths.get(System.getProperty("user.home"), "documents", "java");
        Path file = Paths.get("temario.txt");

        //bucle para 100 ficheros
        Path complete = basePath.resolve(file);
        //los voy creando....
        //if (Files.notExists(complete)) Files.createFile(complete);

        System.out.println(complete.toString());

    }

}
