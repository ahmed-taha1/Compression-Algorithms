package Reader;

import static App.App.scanner;

public class ConsoleReader implements IReader {
    @Override
    public String readData() {
        System.out.print("please enter the data: ");
        scanner.nextLine();
        return scanner.nextLine();
    }
}
