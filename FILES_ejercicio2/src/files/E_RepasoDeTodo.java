
package files;

import files.util.Utils;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author melola
 */
public class E_RepasoDeTodo {
    
    public static void main(String[] args) {
        //Creamos una ruta el directorio donde voy a crear un fichero
        Path dir = Paths.get("files");
        
        //Creamos una ruta para crear un fichero
        Path file = Paths.get("files", "fichero.txt");
        Utils.printInfo(file);
        
        if (Files.notExists(dir)) {
            System.out.println("El directorio padre no existe");
            try {
                //Files.createFile(p);
                Files.createDirectory(dir);
                System.out.println("Se ha creado el directorio padre");
                
                //Una vez creado el directorio padre, creo el fichero
                Files.createFile(file);
                System.out.println("Se ha creado el fichero");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        // ----------------------------------
        // Vamos a escribir en el fichero recién creado
        // FORMA 1: ESTILO TRADICIONAL
        BufferedWriter bw = null;
        try {
            //Pendiente controlar si el fichero existe y tiene contenido....????
            if (Files.exists(file)){
                System.out.println("\tEl fichero existe");
                List<String> lineas = Files.readAllLines(file);
                System.out.println("\tNúmero de líneas: "+lineas.size());

                //Controlar que si el fichero tiene contenido no se sobreescriba...
                if (lineas.isEmpty()){
                    //Estoy sobreescribiendo en el caso de que exista el fichero y tenga contenido
                    bw = Files.newBufferedWriter(file);
                    bw.write("LA REFINITAVA...Aprendiendo a usar java.nio en el módulo de Programación de DAW");
                    System.out.println("Se ha escrito correctamente en el fichero");
                }
                
            }
            
        } catch (IOException ex) {
            //Logger.getLogger(E_RepasoDeTodo.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } finally {
            if (bw != null){
                try{
                    bw.close();
                }catch(IOException ee){
                    ee.printStackTrace();
                }
            }
        }
        // -------------------- sin java.nio -------
            /*
                    
                    bw = new BufferedWriter(new FileWriter("quijote.txt"));
                    for (String s : quijote) {
                        bw.write(s);
                        bw.newLine(); //salto de línea
                    }
                

                */
        
        // -----------------------------------
        // FORMA 1: CON TRY CATCH CON RECURSOS
        try (BufferedWriter bw2 = Files.newBufferedWriter(file)){
            bw2.write("Nuevo texto escrito en el fichero en el try catch with resources");
            bw2.newLine();
            bw2.write("Otra nueva línea???");
            System.out.println("Se ha escrito correctamente en el fichero");
            
        }catch (IOException e){
            e.printStackTrace();
        }
        // -----------------------------------
        

        //Copiamos el fichero
        Path copia = Paths.get("files", "fichero_copiado.txt");
        try{
            Files.copy(file, copia, StandardCopyOption.REPLACE_EXISTING); //3 opciones REPLACE_EXISTING, 
                                                                          //ATOMIC_MOVE (evita problemas de sincronización), 
             System.out.println("Fichero copia creado correctamente");                                                            //COPY_ATTRIBUTES (fich destino mismos atributos que fichero origen)
        }catch(IOException e){
            e.printStackTrace();
        }
        
        
        // ------------
        
        //--------------
        //Recomendados para ficheros no muy grandes porque si no cargaríamos la memoria
        Path file2 = null;
        try{
            file2 = Paths.get("files","quijote.txt");
            Files.createFile(file2);
        }catch(IOException e){
            e.printStackTrace();
        }
        
        if (file2 != null && Files.exists(file2)){
            try (BufferedWriter bw3 = Files.newBufferedWriter(file2)){
                List<String> quijote = Arrays.asList(new String[]{"En un lugar de la Mancha,",
                "de cuyo nombre no quiero acordarme,", "no ha mucho tiempo que vivía un hidalgo",
                "de los de lanza en astillero,", "adarga antigua, rocín flaco y galgo corredor."});

                List<String> quijote2 = new ArrayList<>();
                quijote2.add("En un lugar de la Mancha,");
                quijote2.add("de cuyo nombre no quiero acordarme,");
                quijote2.add("no ha mucho tiempo que vivía un hidalgo");
                quijote2.add("bla bla bla");

                quijote2.forEach( s -> {
                    try {
                        bw3.write(s);
                        bw3.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //System.out.println(s);
                });
                
                /*
                for(String s: quijote2){
                    try{
                        bw3.write(s);
                        bw3.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }*/

            }catch(IOException e){
                e.printStackTrace();
            }
        } 
        
        //-----------------
        //Lo movemos fuera del directorio
        //Files.move(copia, Paths.get("files", "copiado.txt"), StandardCopyOption.REPLACE_EXISTING);

        //Lo eliminamos
        //Files.deleteIfExists(Paths.get("files", "copiado.txt"));
        
        
    }
    
}
