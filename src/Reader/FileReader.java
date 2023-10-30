package Reader;

import App.App;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileReader implements IReader {
    private String filePath;
    public FileReader(String filePath){
        this.filePath = filePath;
    }

    @Override
    public String readData() {
        StringBuilder content = new StringBuilder();
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                content.append(line);
                if(scanner.hasNextLine()) {
                    content.append("\n");
                }
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}