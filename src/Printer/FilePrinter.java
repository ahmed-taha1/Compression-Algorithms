package Printer;

import App.App;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FilePrinter implements IPrinter {
    String filePath;
    public FilePrinter(String filePath){
        this.filePath = filePath;
        File file = new File(this.filePath);
    }
    @Override
    public void print(String data) throws IOException {
        FileWriter writer = new FileWriter(this.filePath);
        writer.write(data);
        writer.close();
    }
}