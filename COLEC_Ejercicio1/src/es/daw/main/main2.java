/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.daw.main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author lrand
 */
public class main2 {

    static Scanner sc = new Scanner (System.in);
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // variables
       boolean salir=false;
       Collection <Integer> lista= new ArrayList<>();
       
       // completar coleccion
       while(!salir){
           int num= pedirNum();
           if(num >0) {
               lista.add(pedirNum());
           } else salir=true;
       }
       
       // metodos sobre coleccion
       valorPar(lista);
        quitarMultiplos(lista);
        collectionPrint(lista);
       
       
       
       
        
    }
    
    
    
    public static int pedirNum(){
        int num=0;
        boolean error=true;
        while(error){
            try{
                System.out.println("Introduce un numero no negativo. -1 para salir");
                num=sc.nextInt();
                if(num==-1 | num>=0)error=false;
            }catch(Exception ex){
                System.out.println("Dato introducido incorrecto");
            }
        }
        return num;
    }
    
    public static void valorPar( Collection<Integer> lista){
        for(int num : lista){
            if(num%2==0){
                System.out.println(num);
            }
        }
    }
    
    public static void collectionPrint(Collection <?> lista){
        for (Object ob : lista){
            System.out.println(ob);
        }
    }
    
    public static Collection<Integer> quitarMultiplos(Collection <Integer> lista){
        Iterator it = lista.iterator();
        while(it.hasNext()){
            if((Integer)it.next()%3==0){
                it.remove();
            }
        }
        return lista;
    }


    
}
