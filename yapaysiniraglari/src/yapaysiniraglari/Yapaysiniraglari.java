/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yapaysiniraglari;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author lenovo
 */
public class Yapaysiniraglari {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        YSA ysa=new YSA(1000);
        Scanner in=new Scanner(System.in);
        int sec=0;
        do{
            System.out.println("1.ağı eğit");
            System.out.println("2.ağı test et");
            System.out.println("3.min hata oranı");
            System.out.println("4.hatalar");
            System.out.println("5.çıkış");
            System.out.println("=>");
            sec=in.nextInt();
            switch(sec){
                case 1:
                    ysa.egit();
                    break;
                case 2:
                    System.out.println("x:");
                    double x=in.nextDouble();
                    System.out.println("y:");
                    double y=in.nextDouble();
                    double []z=ysa.test(x, y);
                    System.out.println("z:"+z[0]);
                    System.in.read();
                    break;
                case 3:
                    System.out.println("hata:"+ysa.hata());
                    break;
                case 4:
                    double []hatalar=ysa.hatalar();
                    int epoch=1;
                    for(double h:hatalar){
                        System.out.println(epoch+":"+h);
                        epoch++;
                    }
                    break;
            }
        }while(sec!=5);
    }
    
}
