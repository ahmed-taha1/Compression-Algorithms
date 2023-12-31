package Compression.VectorQuantization;

import IO.FileIO;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class VectorQuantizationDecompressionRW extends FileIO {
    public VectorQuantizationDecompressionRW(String readFilePath, String writeFilePath) {
        super(readFilePath, writeFilePath);
    }

    @Override
    public void print(String data) {
        String[] rows = data.split("\n");

        int[][] greyMatrix = new int[VectorQuantizationParser.getImageHeight(data)][VectorQuantizationParser.getImageWidth(data)];
        for(int i = 0; i < rows.length; i++){
            String[] col = rows[i].split(" ");
            for(int j = 0; j < col.length; j++){
                greyMatrix[i][j] = Integer.parseInt(col[j]);
            }
        }
        writeGrayscaleImage(greyMatrix, writeFilePath);
    }

    @Override
    public String readData() {
        String data = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(readFilePath);
            DataInputStream dataInputStream = new DataInputStream(fileInputStream);
            data += dataInputStream.readShort() + "\n";
            data += dataInputStream.readShort() + "\n";
            while (dataInputStream.available() > 0){
                data += dataInputStream.readUnsignedByte() + "\n";
            }
            dataInputStream.close();
            fileInputStream.close();
        } catch (IOException e) {}
        return data;
    }

    private static void writeGrayscaleImage(int[][] greyMatrix, String outputPath) {
        int height = greyMatrix.length;
        int width = greyMatrix[0].length;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int greyValue = greyMatrix[y][x];

                // Create a grayscale pixel
                int pixel = (greyValue << 16) | (greyValue << 8) | greyValue;
                image.setRGB(x, y, pixel);
            }
        }

        try {
            ImageIO.write(image, "jpg", new File(outputPath));
            System.out.println("Grayscale image written to: " + outputPath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: Unable to write the output image.");
        }
    }
}