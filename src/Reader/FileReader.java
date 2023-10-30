package Reader;

import App.App;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileReader implements IReader {
    private String filePath;
    public FileReader(){
        this.filePath = App.getReadFilePath();
        File file = new File(this.filePath);
    }

    @Override
    public String readData() {
        // TODO implement reading from file
        StringBuilder content = new StringBuilder();
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine());
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
