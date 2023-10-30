package Reader;

import App.App;

import java.io.File;
import java.io.IOException;

public class FileReader implements IReader {
    private String fileName;
    public FileReader(){
        this.fileName = App.getReadFilePath();
        File file = new File(this.fileName);
    }

    @Override
    public String readData() {
        // TODO implement reading from file
        return "file";
    }
}
