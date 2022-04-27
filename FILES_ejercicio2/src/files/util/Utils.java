package files.util;


import java.nio.file.Path;

/*
 * Mi clase de utilidades para trabajar con java.nio
 */

/**
 *
 * @author melola
 */
public class Utils {
    
    public static void printInfo(Path path){
        System.out.format("toString: %s%n", path.toString());
        System.out.format("getFileName: %s%n", path.getFileName());
        System.out.format("getName(0): %s%n", path.getName(0));
        System.out.format("getNameCount: %d%n", path.getNameCount());
        
        // Para realizar un subpath tengo que tener presente el getNameCount()
        //System.out.format("subpath(0,2): %s%n", path.subpath(0,2));
        //System.out.format("subpath(3,5): %s%n", path.subpath(3,5));
        
        System.out.format("getParent: %s%n", path.getParent());
        System.out.format("getRoot: %s%n", path.getRoot());        
    }    
    
}
