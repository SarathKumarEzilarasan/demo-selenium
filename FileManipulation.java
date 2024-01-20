package tests;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class FileManipulation {

    public static void main(String[] args) throws IOException {
        //File manipulations -> create write read delete

//        Path path = Path.of("demo.txt");
//        Path path = Path.of("demo");
//        Files.createFile(path);
//        Files.writeString(path, "hello world", StandardOpenOption.APPEND);
//        List<String> allLines = Files.readAllLines(path);
//
//        for (String line : allLines) {
//            System.out.println(line);
//        }
//        Files.delete(path);

        File folder = new File("demo");
        File[] files = folder.listFiles();


        for (File file : files) {
            Path path = Path.of(file.getPath());
            Files.readAllLines(path);
        }

    }
}
