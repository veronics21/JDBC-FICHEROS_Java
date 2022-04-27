/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nio_ej1;

import files.Utils;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author lrand
 */
public class NIO_ej1 {

    /**
     * Ejemplo1 de cosas que se pueden hacer con el .nio
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Path dir= Paths.get("files");
        // estoy crreando un path a un fichero, que se encuentra en la ruta actual, en un subdirectorio llamado files
        // ./files/fichero.txt
        Path p = Paths.get("files","fichero.txt");
        
        
        // info del path , OJOOO no esta creado, solo info del path que estamos trabajando en el programa, 
        //no tiene por que existir en el sism¡tema
        Utils.getInfo(p);
        
        
        // ----------- 1º crear directotio y fichero -------------
        System.out.println("********1º CREANDO FICHERO & DIRECTORIO********");
        // intentamos crear fichero , pero no esta el directorio completo --> ERROR
        crearFichero(p);
        
        // intentamos crear el directorio
        crearDirectorio(dir);
        
        // volvemos a intentar crear el fichero
        crearFichero(p);
        
        
        // ------------ 2º escribir en fichero------------------
        // ESTILO TRADICIONAL  TRY - CATCH  - FINALLY
        BufferedWriter bw=escribirFichero1(p);
        
        
        // ESTILO  TRY - CATCH con close automatico(sin FINALLY) || with resources
        // machacando lo escrito anteriormente...
        escribirFichero2(p, bw);
        
        
        // ------------ 3º copiar fichero------------------
        copiaRemplazandoExistente(p);
        
        // ------------ 4º escribir recorriendo------------------
        List<String> quijote = Arrays.asList(new String[]{"En un lugar de la mancha,",
        "de cuyo nombre no quiero acordarme",
        "no ha mucho tiempo que vivía un hidalgo"});
        
        List<String> quijote2 =  new ArrayList<>();
        quijote2.add("En un lugar de la mancha,");
        quijote2.add("de cuyo nombre no quiero acordarme");
        quijote2.add("no ha mucho tiempo que vivía un hidalgo");
        
        
        try(BufferedWriter bw3=Files.newBufferedWriter(p)){
            // con lambdas
            quijote2.forEach((s)->{
                try{
                    bw3.write(s);
                    bw3.newLine();
                }catch(IOException ex){
                    ex.printStackTrace();
                }
            });
            // tradicional
            for(String s : quijote2){
                try{
                    bw3.write(s);
                    bw3.newLine();
                }catch(IOException ex){
                    ex.printStackTrace();
                }
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        

        
    }

    public static void copiaRemplazandoExistente(Path p) {
        // ------------ 3º copiar fichero------------------
        
        Path copia = Paths.get("files", "fichero_copia.txt");
        
        try{
            Files.copy(p, copia, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Fichero copia creado correctamente");
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public static void escribirFichero2(Path p, BufferedWriter bw) {
        // ESTILO  TRY - CATCH con close automatico(sin FINALLY) || with resources
        //BufferedWriter bw1=null;
        try (BufferedWriter bw1=Files.newBufferedWriter(p)){
            
            // estoy sobreescribiendo todo el contenido del fichero
            bw.write("Aprendiendo a usar java.nio 1º forma");
            bw1.newLine();
            bw.write("Otra linea");
            
            System.out.println("Si se ha escrito en el fichero");
            
            // comprobar que si tienen contenido no se escriba.....
            
        } catch (IOException ex ){
            System.out.println("NO se ha escrito en el fichero");
        }
    }

    public static BufferedWriter escribirFichero1(Path p) {
        
        // ESTILO TRADICIONAL TRY - CATCH - FINALLY
        BufferedWriter bw=null;
        try{
            bw=Files.newBufferedWriter(p);
            
            //comprobamos que no tiene contenido
            // si tubiera contenido y no lo comprobaramos, lo sobreescribiria TODO el contenido
            if(Files.exists(p)){
                List<String> lineas = Files.readAllLines(p);
                System.out.println("Num líneas :"+lineas.size());
                if(lineas.isEmpty()){
                    // estoy sobreescribiendo todo el contenido del fichero
                    bw.write("Aprendiendo a usar java.nio 1º forma");
                    System.out.println("Si se ha escrito en el fichero");
                }
            }
            
            
        } catch (IOException ex ){
            System.out.println("NO se ha escrito en el fichero");
        }finally{
            if(bw != null){
                try{
                    bw.close();
                }catch (IOException ex ){
                    ex.printStackTrace();
                }
            }
        }
        return bw;        
    }

    public static void crearDirectorio(Path dir) {
        if (Files.notExists(dir)) {
            System.out.println("La ruta no existe. Creando....");
            try{
                Files.createDirectory(dir);
            }catch (IOException e ){
                System.out.println("NO se ha podido crear el directorio");
            }
            
        }
    }

    public static void crearFichero(Path p) {
        if (Files.notExists(p)) {
            System.out.println("La ruta no existe");
            try{
                Files.createFile(p);
            }catch (IOException e ){
                System.out.println("No ha sido posible crear el fichero ya que no esta el deirectotio files");
            }
            
        }
    }

}
