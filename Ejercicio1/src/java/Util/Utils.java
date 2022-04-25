/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daw1a
 */
public class Utils {

    public static void printRequestParamether(Map<String, String[]> mapa) {

        //primera forma
        System.out.println("* PRIMERA FORMA *");
        Set<Entry<String, String[]>> set = mapa.entrySet();
        Iterator<Entry<String, String[]>> it = set.iterator();
        while (it.hasNext()) {
            // entry es como cada linea del map
            Entry<String, String[]> entry = it.next();
            System.out.print("Key: " + entry.getKey());
            for (String i : entry.getValue()) {
                System.out.println(" Value: " + i);
            }
        }

        //Segubda forma
        System.out.println("* Segenda FORMA *");
        for (String key : mapa.keySet()) {
            String[] value = mapa.get(key);
            System.out.printf("KAY: %s, VALUE: %s %n", key, value[0]); // siendo %n un retorno de carro
        }

    }

    /**
     * createPropiertiesFile: crea el fichero donde vamos a pasar las
     * propiedades del cuestionario
     *
     * @param mapa
     * @param nombreFichero
     * @throws IOException
     */
    public static boolean createPropertiesFile(Map<String, String[]> mapa, String nombreFichero) throws IOException {
        Path prop = Paths.get(nombreFichero);

        if (Files.notExists(prop)) {
            try {
                System.out.println("[INFO] Creando fichero ya que no existe....");
                Files.createFile(prop);
                System.out.println("[INFO]Fichero" + nombreFichero + " creado");
            } catch (IOException ex) {
                System.err.println("[ERROR] No se ha podido crear el fichero de propiedades....");
                ex.printStackTrace();
                throw ex;
            }
        }
        if (prop != null) {
            BufferedWriter bw = null;
            try {

                bw = Files.newBufferedWriter(prop, Charset.forName("UTF-8"));
                for (String s : mapa.keySet()) {
                    String[] value = mapa.get(s);
                    System.out.println("Escribiendo es fichero....." + s + value);
                    bw.write(s + " = " + value[0]);
                    bw.newLine();

                }

            } catch (IOException ex) {
                System.out.println("No se a escrito en fichero");
            } finally {
                if (bw != null) {
                    try {
                        bw.close();
                    } catch (IOException ex) {
                        System.out.println("NO se hya cerrado BufferedWriter");
                    }

                }
            }
        }
        System.out.println("Terminado de pintar propieddes en archivo");

        return true;

    }
}
