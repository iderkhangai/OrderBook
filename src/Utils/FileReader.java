package Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class FileReader {

    private final FileInputStream inputStream;
    private final Scanner scanner;

    public FileReader(String path) throws FileNotFoundException {
        inputStream = new FileInputStream(path);
        scanner = new Scanner(inputStream);
    }

    public boolean hasNext() {
        return scanner.hasNext();
    }

    public String nextLine() {
        return scanner.nextLine();
    }

    public void close() {
        try {
            inputStream.close();
        } catch (IOException e) {
            System.out.println("Error during closing the resource:" + e);
        }
        scanner.close();
    }

}
