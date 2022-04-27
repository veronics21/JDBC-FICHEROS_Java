/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package files;

import java.nio.file.Path;

/**
 *
 * @author lrand
 */
public class Utils {
    
    public static void getInfo(Path path){
         System.out.format("toString: %s%n", path.toString());   
         System.out.format("getFileName: %s%n", path.getFileName());   
         System.out.format("getName(0): %s%n", path.getName(0));   
         System.out.format("getNameCount: %s%n", path.getNameCount()); 
         // para realizar el ubpath, tengo que tener presente el getNameCount()
//         System.out.format("toString: %s%n", path.subpath(0, 0));   
//         System.out.format("toString: %s%n", path.subpath(0, 0));   
         System.out.format("toString: %s%n", path.getParent());   
         System.out.format("toString: %s%n", path.getRoot());   
        
    }
}
