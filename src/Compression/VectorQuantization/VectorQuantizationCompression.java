package Compression.VectorQuantization;

import Compression.ICompression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class VectorQuantizationCompression implements ICompression {
    private final int codeBookSize = 4;     // 2^n only numbers
    @Override
    public String compress(String data) {
        Vector<Vector<Double>> extractedVectors = VectorQuantizationParser.extractVectorsFromData(data);
        Vector<Double> avgVector = getAvgVector(extractedVectors);
        Vector<Vector<Double>> codeBook = createCodeBook(extractedVectors, avgVector);

        Vector<Vector<Double>>[] indexedVectors = getIndexedVectors(extractedVectors, codeBook);

//         for debug
//        for(int i = 0; i < indexedVectors.length; i++){
//            System.out.println(indexedVectors[i] + " ->" + i);
//        }

        return null;
    }

    @Override
    public String decompress(String data) {
        return null;
    }


    private Vector<Vector<Double>> createCodeBook(Vector<Vector<Double>> vectors, Vector<Double> avgVector){
        Vector<Vector<Double>> codeBook = new Vector<>();
        codeBook.add(avgVector);
        Vector<Vector<Double>>[] indexedVectors;

        int numberOfLevels = (int) (Math.log(codeBookSize) / Math.log(2));

        // splitting
        for(int l = 1; l <= numberOfLevels; l++){
            Vector<Vector<Double>> splittedVectors = new Vector<>();
            for (int i = 0; i < codeBook.size(); i++) {
                ArrayList<Vector<Double>> splittedVector = split(codeBook.get(i));
                splittedVectors.add(splittedVector.get(0));
                splittedVectors.add(splittedVector.get(1));
            }

            // mapping each vector to it's nearest code vector index
            indexedVectors = getIndexedVectors(vectors, splittedVectors);

            Vector<Vector<Double>> newCodeBook = new Vector<>();
            for(int i = 0; i < indexedVectors.length; i++){
                newCodeBook.add(getAvgVector(indexedVectors[i]));
            }
            codeBook = newCodeBook;
        }

        // check if there is a change to stop or complete the iterations
        while (true){
            indexedVectors = getIndexedVectors(vectors, codeBook);
            Vector<Vector<Double>> newCodeBook = new Vector<>();
            for(int i = 0; i < indexedVectors.length; i++){
                newCodeBook.add(getAvgVector(indexedVectors[i]));
            }

            if(areVectorsIdentical(codeBook, newCodeBook)){
                break;
            } else {
                codeBook = newCodeBook;
            }
        }
        return codeBook;
    }


    private Vector<Vector<Double>>[] getIndexedVectors(Vector<Vector<Double>> vectors, Vector<Vector<Double>> codeBook){
        Vector<Vector<Double>>[] indexedVectors = new Vector[codeBook.size()];
        for (int i = 0; i < indexedVectors.length; i++) {
            indexedVectors[i] = new Vector<>();
        }

        for(int i = 0; i < vectors.size(); i++){
            int nearestIndex = findNearestCodeVectorIndex(vectors.get(i), codeBook);
            indexedVectors[nearestIndex].add(vectors.get(i));
        }
        return  indexedVectors;
    }

    private static boolean areVectorsIdentical(Vector<Vector<Double>> vectors1, Vector<Vector<Double>> vectors2) {
        if (vectors1.size() != vectors2.size()) {
            return false;
        }
        for (int i = 0; i < vectors1.size(); i++) {
            Vector<Double> vector1 = vectors1.get(i);
            Vector<Double> vector2 = vectors2.get(i);
            if (vector1.size() != vector2.size()) {
                return false;
            }
            for (int j = 0; j < vector1.size(); j++) {
                if (!vector1.get(j).equals(vector2.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    private Vector<Double> getAvgVector(Vector<Vector<Double>> vectors){
        Vector<Double> avgVector = new Vector<>();
        avgVector.setSize(vectors.get(0).size());
        avgVector.replaceAll(ignored -> 0.0);

        for(int i = 0; i < vectors.size(); i++){
            for(int j = 0; j < vectors.get(i).size(); j++){
                // accumulate all vectors into avgVector , each element index to it's corresponding there
                avgVector.set(j, avgVector.get(j) + vectors.get(i).get(j));
            }
        }

        for(int i = 0; i < avgVector.size(); i++){
            avgVector.set(i, avgVector.get(i) / vectors.size());
        }
        return avgVector;
    }

    private static int findNearestCodeVectorIndex(Vector<Double> vector, Vector<Vector<Double>> codebook) {
        int nearestIndex = 0;
        double minDistance = Double.MAX_VALUE;

        for (int i = 0; i < codebook.size(); i++) {
            double distance = calculateDistance(vector, codebook.get(i));
            if (distance < minDistance) {
                minDistance = distance;
                nearestIndex = i;
            }
        }
        return nearestIndex;
    }

    private static double calculateDistance(Vector<Double> vector1, Vector<Double> vector2) {
        double sum = 0;
        for (int i = 0; i < vector1.size(); i++) {
            sum += Math.abs(vector1.get(i) - vector2.get(i));
        }
        return sum;
    }

    private ArrayList<Vector<Double>> split(Vector<Double> v){
        Vector<Double> v1 = new Vector<>(), v2 = new Vector<>();
        ArrayList<Vector<Double>> splittedVectors = new ArrayList<>();
        for(int i = 0; i < v.size(); i++){
            if(v.get(i) == v.get(i).intValue()){
                v1.add((double) (v.get(i).intValue() -1));
                v2.add((double) (v.get(i).intValue() +1));
            }
            else{
                v1.add(Math.floor(v.get(i)));
                v2.add(Math.ceil(v.get(i)));
            }
        }

        splittedVectors.add(v1);
        splittedVectors.add(v2);
        return splittedVectors;
    }
}