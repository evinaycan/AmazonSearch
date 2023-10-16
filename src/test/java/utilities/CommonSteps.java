package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pages.PageInitializer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonSteps extends PageInitializer {


    public static byte[] takeScreenshot(String filename) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        byte[] picBytes = ts.getScreenshotAs(OutputType.BYTES);

        File file = ts.getScreenshotAs(OutputType.FILE);
        // create destination as : filepath + filename + timestamp + .png
        String destination = System.getProperty("user.dir") + "/test-output/Screenshots/" + filename + date + ".png";

        try {
            FileUtils.copyFile(file, new File(destination));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return picBytes;
    }
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
