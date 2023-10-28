package Printer;

public class ConsolePrinter implements Printer{
    @Override
    public void print(String data) {
        System.out.println(data);
    }
}
