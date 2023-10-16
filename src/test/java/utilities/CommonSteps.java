package utilities;

import pages.PageInitializer;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class CommonSteps extends PageInitializer {


    public static void writeToTxtFile(String filePath, String textToWrite)  {
        Path path = Paths.get(filePath);

        try {
            // Write the string to the file
            Files.write(path, textToWrite.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("Write successful!");
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

    }

    public static void main(String[] args)  {
        writeToTxtFile("src\\test\\java\\productDetails.txt", "Hello");
        writeToTxtFile("src\\test\\java\\productDetails.txt", "World");
    }

}
