/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.daw.web.Utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

/**
 *
 * @author daw1a
 */
public class utils {
    
    public static String listDir(String path,Boolean tipo)throws IOException{
        StringBuilder sb = new StringBuilder();
        //<br>
        
        if(tipo){
            // usamos java io
            File miCarpeta = new File(path);
            //comprbar si existe fircher........
            File[] listFicheros = miCarpeta.listFiles();
            for (File listFichero : listFicheros) {
                sb.append(listFichero.getName()).append("<br>");
            }
        }else{
            // usando java nio + arrow
            Path miPath = Paths.get(path);
            // ordenado
            Files.list(miPath).sorted(Comparator.reverseOrder()).forEach( f -> sb.append(f.getFileName()).append("<br>"));
            // sin ordenar
            Files.list(miPath).forEach( f -> sb.append(f.getFileName()).append("<br>"));
        
        }
        
        return sb.toString();
    }
}
