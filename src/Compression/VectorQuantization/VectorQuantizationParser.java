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
        vectors.setSize((dataHeight* dataHeight) / (vectorHeight * vectorWidth));

        for(int i = 0; i < dataHeight; i+=vectorHeight){    // TODO fill the vectors
            for(int j = 0; j < dataWidth; j+= vectorWidth){

            }
        }

        return vectors;
    }
}
