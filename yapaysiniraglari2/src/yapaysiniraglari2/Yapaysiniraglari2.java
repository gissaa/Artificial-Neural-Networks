/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yapaysiniraglari2;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author lenovo
 */
public class Yapaysiniraglari2 {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
         
        Scanner in = new Scanner(System.in);
        int araKatmanNoron;
        double momentum,ok,error;
        int epoch,sec;
        
        YSA ysa = null;
        do {
            System.out.println("1. Eğit ve Test Et");
            System.out.println("2. Tek Test");
            System.out.println("3. Çıkış");
            System.out.println("=>");
            sec = in.nextInt();
            switch(sec){
                case 1:
                    System.out.print("Ara Katman Noron Sayısı : ");
                    araKatmanNoron = in.nextInt();
                    System.out.print("Momentum : ");
                    momentum = in.nextInt();
                    System.out.print("Öğrenme Katsayısı : ");
                    ok = in.nextInt();
                    System.out.print("Min Error : ");
                    error = in.nextInt();            
                    System.out.print("Maks Epoch Sayısı : ");
                    epoch = in.nextInt();
                    ysa = new YSA(araKatmanNoron, momentum, ok, error, epoch);
                    ysa.egit();
                    System.out.println("Eğitimdeki Hata Değeri : "+ ysa.egitimHata());
                    System.out.println("Test Verisindeki Hata Değeri : "+ ysa.egitimHata());
                    break;
                case 2:
                    double[] inputs = new double[8];
                    if(ysa == null)
                    {
                        System.out.println("Önce Eğitim");
                        System.in.read();
                    }
                    else
                    {

                        System.out.println("Silindir (4-8) : ");
                        inputs[0] = in.nextDouble();
                        System.out.println("Sürtünme (79-400) : ");
                        inputs[1] = in.nextDouble();
                        System.out.println("Beygir Gücü (60-175) : ");
                        inputs[2] = in.nextDouble();
                        System.out.println("Ağırlık (1760-4746): ");
                        inputs[3] = in.nextDouble();
                        System.out.println("Hızlanma (4-8) : ");
                        inputs[4] = in.nextDouble();
                        System.out.println("Model (4-8) : ");
                        inputs[5] = in.nextDouble();
                        System.out.println("Ülke : ");
                        String ulke = in.next();
                        switch(ulke){
                            case "America":
                                inputs[6] = 0;
                                inputs[7] = 0;
                                break;
                                case "Europa":
                                inputs[6] = 1;
                                inputs[7] = 0;
                                break;
                                case "Asia":
                                inputs[6] = 0;
                                inputs[7] = 1;
                                break;
                        }
                        inputs[6] =0;
                        inputs[7] =0;
                    }
                    System.out.println("Yakıt : "+ysa.tekTest(inputs));
                    System.in.read();
                    break;
            }
        } while (sec != 3);
    }
}
    
