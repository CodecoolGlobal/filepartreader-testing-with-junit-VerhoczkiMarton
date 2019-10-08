package main.java.filepartreader;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class FilePartReader {
    private String filePath;
    private int fromLine;
    private int toLine;
    private File file;
    private BufferedReader reader;

    public FilePartReader() {
    }

    public void setup(String filePath, int fromLine, int toLine) throws FileNotFoundException {
        if ((toLine < fromLine) || fromLine < 1) throw new IllegalArgumentException();
        this.filePath = filePath;
        this.fromLine = fromLine;
        this.toLine = toLine;
        this.file = new File(filePath);
        if (!this.file.exists()) throw new FileNotFoundException();
        this.reader = new BufferedReader(new FileReader(this.file));
    }

    public String read() throws IOException {
        return new String(Files.readAllBytes(Paths.get(this.filePath)));
    }

    public String readLines() throws IOException {
        return String.join("\\n", new ArrayList<String>(Arrays.asList(read().split("\\r?\\n"))).subList(this.fromLine, this.toLine));
    }
}
