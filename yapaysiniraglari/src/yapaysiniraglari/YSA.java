/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yapaysiniraglari;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.Perceptron;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.util.TransferFunctionType;

/**
 *
 * @author lenovo
 */
public class YSA {
    private static final File dosya=new File(YSA.class.getResource("Data.txt").getPath());
    BackPropagation bp;
    double maksEpoch;
    double []eldeEdilenHatalar;
    public YSA(double maksEpoch){
        this.maksEpoch=maksEpoch;
        bp=new BackPropagation();
        eldeEdilenHatalar=new double[(int)maksEpoch];
    }
    public void egit() throws FileNotFoundException{
//tek katmanlı sinirsel ağ için
//        -NeuralNetwork sinirselAg=new Perceptron(2,1); 
//        -sinirselAg.learn(EgitimVeriSeti());
        MultiLayerPerceptron sinirselAg=new MultiLayerPerceptron(TransferFunctionType.TANH,2,3,1);
//        +BackPropagation bp=new BackPropagation();
        bp.setLearningRate(0.2);
        //bp.setMaxIterations(1000);
        bp.setMaxError(0.0000001);
        sinirselAg.setLearningRule(bp);
        for(int i=0;i<maksEpoch;i++){
            sinirselAg.getLearningRule().doOneLearningIteration(EgitimVeriSeti());
            if(i==0)eldeEdilenHatalar[i]=1;
            else eldeEdilenHatalar[i]=sinirselAg.getLearningRule().getPreviousEpochError();
        }
//        
//        sinirselAg.learn(EgitimVeriSeti());
        sinirselAg.save("ogrenenAg.nnet");
        System.out.println("Eğitim tamamlandı.");
    }
    public double[] hatalar(){
        return eldeEdilenHatalar;
    }
    public double hata(){
        return bp.getTotalNetworkError();  //ulaşılan en son hata
    }
    public DataSet EgitimVeriSeti()throws FileNotFoundException{
        Scanner oku=new Scanner(dosya);
        DataSet egitim=new DataSet(2,1);
        while(oku.hasNextDouble()){
            DataSetRow satir=new DataSetRow(new double[]{oku.nextDouble(),oku.nextDouble()},new double[]{oku.nextDouble()});
            egitim.add(satir);
        }
        oku.close();
        return egitim;
    }
    public double[] test(double x,double y){
        NeuralNetwork sinirselAg=NeuralNetwork.createFromFile("ogrenenAg.nnet");
        sinirselAg.setInput(x,y);
        sinirselAg.calculate();
        return sinirselAg.getOutput();
    }
}

