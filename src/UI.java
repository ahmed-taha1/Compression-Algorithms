import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {
    public static Scanner scanner;

    public UI(){
        scanner = new Scanner(System.in);
    }
    public void run(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("welcome to lz77 compressor & decompressor");
        System.out.println("please select operation");
        System.out.println("1- compress");
        System.out.println("2- decompress");
        System.out.print(">> ");
        String option = scanner.nextLine();

        switch (option){
            case "1":
                compressWindow();
            case "2":
                decompressWindow();        // ************************* empty ****************************
            default:
                System.out.println("wrong input program has been terminated");
        }
    }

    private static void compressWindow(){
        String data;
        ArrayList<tag> compressedData;

        System.out.print("please enter string: ");
        data = scanner.nextLine();

        compressedData = lz77.compress(data);
        System.out.println("this is the data after compress");
        printTags(compressedData);
    }

    private static void decompressWindow(){

        // ************************* empty ****************************

    }

    private static void printTags(ArrayList<tag> data){
        for (tag t : data) {
            System.out.println("<" + t.position + ", " + t.length + ", " + t.next + ">");
        }
    }
}