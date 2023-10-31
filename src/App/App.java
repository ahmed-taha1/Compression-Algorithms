package App;

import Compression.Factory.CompressionFactory;
import Compression.ICompression;
import Printer.*;
import Reader.*;

import java.io.IOException;
import java.util.Scanner;

public class App {
    private static int COMPRESSION_CHOICE = 1;
    private static int DECOMPRESSION_CHOICE = 2;
    public static Scanner scanner = new Scanner(System.in);
    private static final CompressionFactory compressionFactoryInstance = CompressionFactory.getCompressionFactoryInstance();
    private static final String[] algorithmsList = compressionFactoryInstance.getAvailableCompressionAlgorithms();
    private static final IPrinter printer = new FilePrinter(getWriteFilePath());
    private static final IReader reader = new FileReader(getReadFilePath());

    public void run() throws IOException {
        while (true){
            final int algorithmChoice = AlgorithmListView();
            final String algorithmName = algorithmsList[algorithmChoice-1];
            final ICompression compressionAlgorithm = compressionFactoryInstance.createCompression(algorithmName);
            if(compressionAlgorithm == null){
                System.out.println("wrong input,Algorithm doesn't exist. Terminating program");
                break;
            }
            final String data = reader.readData();

            // TODO Refactor to switch or mapping
            final int compressionChoice = CompressionListView();
            if (compressionChoice == COMPRESSION_CHOICE) {
                final String compressedWord = compressionAlgorithm.compress(data);
                System.out.println("Compressed successfully.");
                printer.print(compressedWord);
            }else if (compressionChoice == DECOMPRESSION_CHOICE) {
                final String decompressedData = compressionAlgorithm.decompress(data);
                System.out.println("Decompressed successfully.");
                printer.print(decompressedData);
            }else{
                System.out.println("wrong input program has been terminated");
                break;
            }
        }
    }
    private static int AlgorithmListView(){
        System.out.println("Choose Compression Algorithm:");
        for (int i =0 ;i<algorithmsList.length;i++) {
            System.out.println((i+1)+"- " + algorithmsList[i]);
        }
        System.out.print(">> ");
        return scanner.nextInt();
    }
    private static int CompressionListView(){
        System.out.println("Choose Operation:");
        System.out.println("1- compress");
        System.out.println("2- decompress");
        System.out.print(">> ");
        return scanner.nextInt();
    }
    private static String getWriteFilePath(){
        System.out.print("please enter the file path that you want to write the data in: ");
        return scanner.nextLine();
    }

    private static String getReadFilePath(){
        System.out.print("please enter the file path that you want to read the data from: ");
        return scanner.nextLine();
    }
}