package main.java.filepartreader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import main.java.filepartreader.FilePartReader;

public class FileWordAnalyzer {
    private FilePartReader filePartReader;

    public FileWordAnalyzer(FilePartReader filePartReader) {
        this.filePartReader = filePartReader;
    }

    public List getWordsOrderedAlphabetically() throws IOException {
        String lines = filePartReader.readLines();
        String[] words = lines.split("\\s+");
        Arrays.sort(words);
        return Arrays.asList(words);
    }

    public List getWordsContainingSubstring (String subString) throws IOException {
        String lines = filePartReader.readLines();
        String[] words = lines.split("\\s+");
        return Arrays.stream(words).filter(word -> word.contains(subString)).collect(Collectors.toList());
    }

    public List getStringsWhichPalindromes () throws IOException {
        ArrayList<String> palindromes = new ArrayList<>();
        String lines = filePartReader.readLines();
        List<String> words = Arrays.asList(lines.split("\\s+"));
        return words.stream().filter(word -> word.equals((new StringBuffer(word).reverse()).toString())).collect(Collectors.toList());
    }
}
