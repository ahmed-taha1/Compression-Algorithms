package Compression.VectorQuantization;

import java.util.ArrayList;
import java.util.Vector;

public class VectorQuantizationParser {
    public static Vector<Vector<Double>> extractVectorsFromData(String data){
        Vector<Vector<Double>> doubleData = new Vector<>();
        String[] dataList = data.split("\n");
        doubleData.setSize(dataList.length);

        // convert strings to doubles
        for(int i = 0; i < dataList.length; i++){
            String[] stringArray = dataList[i].split(" ");
            Vector<Double> doubleArray = new Vector<>();
            doubleArray.setSize(stringArray.length);
            for (int j = 0; j < stringArray.length; j++) {
                doubleArray.set(j, Double.parseDouble(stringArray[j]));
            }
            doubleData.set(i, doubleArray);
        }

        int dataWidth = doubleData.get(0).size(), dataHeight = doubleData.size();
        int vectorWidth = 2, vectorHeight = 2;
        Vector<Vector<Double>> vectors = new Vector<>();
        vectors.setSize((dataHeight* dataWidth) / (vectorHeight * vectorWidth));

        int vecNum = 0;
        for(int i = 0; i < dataHeight; i+=vectorHeight){
            for(int j = 0; j < dataWidth; j+= vectorWidth){
                Vector<Double> singleVector = new Vector<>();
                for(int h = 0; h < vectorHeight; h++){
                    for(int w = 0; w < vectorWidth; w++){
                        singleVector.add(doubleData.get(i + h).get(w + j));
                    }
                }
                vectors.set(vecNum, singleVector);
                vecNum++;
            }
        }
        return vectors;
    }
}
