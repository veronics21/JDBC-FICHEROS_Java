/**
 * Formas de crear un Path
 */
package path;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class A_Crear {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
            // No tiene por qué existir. 
            // Es la identificación de una ruta que puede existir o no....
            // directorio java que dentro tiene el temario.txt
            //Path p1 = Paths.get("java", "temario.txt");
            Path dir = Paths.get("java");
            Path p1 = Paths.get("temario.txt");
            
            
//            try{
//                //crea un fichero si aún no existe...
//                if (Files.notExists(dir)) Files.createFile(dir);
//                if (Files.notExists(p1)) Files.createFile(p1);
//
//                
//                
//                // FORMA TRADICIONAL
//                if (!dir.exists()) dir.mkdir();
//                File f = new File("./prueba/", "nuevo.txt"); 
//                f.createNewFile();
//
//                
//                
//            }catch(IOException e){
//                e.printStackTrace();
//            }
            
            
            //Otra forma de obtener el path menos habitual
            Path p2 = FileSystems.getDefault().getPath("java", "temario.txt");
            
            //Usando propiedades del sistema
            Path p3 = Paths.get(System.getProperty("user.home"),"documents", "java", "temario.txt");
		
            System.out.println(p1.toAbsolutePath().toString());
            System.out.println(p2.toAbsolutePath().toString());
            System.out.println(p3.toAbsolutePath().toString());

	}

}
