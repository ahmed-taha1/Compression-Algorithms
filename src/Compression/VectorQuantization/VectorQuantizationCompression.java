package Compression.VectorQuantization;

import Compression.ICompression;

import java.util.Vector;

public class VectorQuantizationCompression implements ICompression {
    @Override
    public String compress(String data) {
        Vector<Vector<Double>> extractedVectors = VectorQuantizationParser.extractVectorsFromData(data);

        return null;
    }

    @Override
    public String decompress(String data) {
        return null;
    }
}
