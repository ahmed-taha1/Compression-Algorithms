import Compression.LZ77.LZ77Compression;
import Compression.LZ77.LZ77Tag;

import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    private static Scanner scanner;
    private static final String algorithm = {"lz77"};
    public Controller(){
        scanner = new Scanner(System.in);
    }
    public void run(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("welcome to compression & decompression");
        System.out.println("please select operation");
        System.out.println("1- compress");
        System.out.println("2- decompress");
        System.out.print(">> ");
        String option = scanner.nextLine();

        switch (option){
            case "1":
                compressWindow();
            case "2":
                decompressWindow();
                // ************************* empty ****************************
            default:
                System.out.println("wrong input program has been terminated");
        }
    }
    private static void displayAlgorithmsPage(){
        for(int i = 0 ;i<)
    }
    private static void compressWindow(){
        System.out.print("please enter string: ");
        String data = scanner.nextLine();

        String compressedData = new LZ77Compression().compress(data);
        System.out.println("this is the data after compress");
        System.out.println(compressedData);
    }
    private static void decompressWindow(){
        // ************************* empty ****************************

    }
}