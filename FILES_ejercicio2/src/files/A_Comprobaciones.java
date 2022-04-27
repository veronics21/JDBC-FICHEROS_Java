/**
 * Practicando con ficheros usando los diferentes métodos de la clase de utilidades Files
 */
package files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 */
public class A_Comprobaciones {

    /**
     * @param args
     */
    public static void main(String[] args) {

        Path p = Paths.get("file.txt");
        
        Path dir = Paths.get("directorio");

        if (Files.notExists(p)) {
            System.out.println("La ruta no existe");
            try {
                Files.createFile(p);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // ???????????????????????????
        if (Files.exists(p)) {
            System.out.println("La ruta sí existe");
        }
        
        if (!Files.exists(p)){
            System.out.println("La ruta NO existe");
        }

        if (Files.notExists(p)) { //Qué diferencia hay???? !Files.exists(p)
                                  //Puede que ambas comprobaciones devuelvan false 
                                  //por problemas de acceso por temas de seguridad...
            System.out.println("La ruta no existe");
        }
        
        if (!Files.notExists(p)) { //Qué diferencia hay???? !Files.exists(p)
                                  //Puede que ambas comprobaciones devuelvan false 
                                  //por problemas de acceso por temas de seguridad...
            System.out.println("La ruta SÍ existe");
        }
        
        if (Files.isRegularFile(p)) {
            System.out.println("El fichero " + p.toString() + " es regular");
        }
        
        if (Files.isDirectory(dir))
            System.out.println("Es un directorio");
        
        if (Files.isRegularFile(dir))
            System.out.println("Dir como fichero regular");

        Path p2 = Paths.get("file.txt");

        try {
            if (Files.isSameFile(p, p2)) {
                //Ambos path están apuntando al mismo fichero
                System.out.println("Son el mismo fichero");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
