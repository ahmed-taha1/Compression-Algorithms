package Printer;

import App.App;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FilePrinter implements IPrinter {
    String fileName;
    public FilePrinter(){
        this.fileName = App.getWriteFilePath();
        File file = new File(this.fileName);
    }
    @Override
    public void print(String data) throws IOException {
        FileWriter writer = new FileWriter(this.fileName);
        writer.write(data);
        writer.close();
    }
}