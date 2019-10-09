package main.java.filepartreader;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        this.fromLine = fromLine - 1;
        this.toLine = toLine;
        this.file = new File(filePath);
        if (!this.file.exists()) throw new FileNotFoundException();
        this.reader = new BufferedReader(new FileReader(this.file));
    }

    public String read() throws IOException {
        String lines = "";
        String line;
        while ((line = reader.readLine()) != null) {
            lines += line.trim() + "\n";
        }
        return lines.trim();
    }

    public String readLines() throws IOException {
        String[] lines = read().split("\\r?\\n");
        if (lines.length <= 1 && lines[0] == "") return "";
        List<String> notEmptyLines = Arrays.asList(lines).stream().filter(item-> !item.isEmpty()).collect(Collectors.toList());
        List<String> subLines = notEmptyLines.subList(this.fromLine, this.toLine);
        return String.join("\n", subLines);
    }
}
