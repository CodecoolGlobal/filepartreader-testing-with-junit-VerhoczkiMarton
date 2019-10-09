package main.java.filepartreader;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import main.java.filepartreader.FileWordAnalyzer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileWordAnalyzerTest {
    private static FilePartReader filePartReader;

    @BeforeAll
    static void initAll() {
        filePartReader = new FilePartReader();
    }


    @Test
    void testGetWordsOrderedAlphabeticallySingleWord() throws IOException {
        List<String> expected = List.of("abc");
        filePartReader.setup("src/main/resources/alphabeticalSingle.txt", 1, 1);
        FileWordAnalyzer ana = new FileWordAnalyzer(filePartReader);
        assertEquals(expected, ana.getWordsOrderedAlphabetically());
    }

    @Test
    void testGetWordsOrderedAlphabeticallyMultipleWords() throws IOException {
        List<String> expected = List.of("abc", "def", "ghi", "jkl");
        filePartReader.setup("src/main/resources/alphabetical.txt", 1, 1);
        FileWordAnalyzer ana = new FileWordAnalyzer(filePartReader);
        assertEquals(expected, ana.getWordsOrderedAlphabetically());
    }

    @Test
    void testGetWordsContainingSubstringNoneContaining() throws IOException {
        List<String> expected = List.of();
        filePartReader.setup("src/main/resources/substring.txt", 1, 1);
        FileWordAnalyzer ana = new FileWordAnalyzer(filePartReader);
        assertEquals(expected, ana.getWordsContainingSubstring("jkl"));
    }

    @Test
    void testGetWordsContainingSubstringOneContaining() throws IOException {
        List<String> expected = List.of("abcdef");
        filePartReader.setup("src/main/resources/substring.txt", 1, 1);
        FileWordAnalyzer ana = new FileWordAnalyzer(filePartReader);
        assertEquals(expected, ana.getWordsContainingSubstring("def"));
    }

    @Test
    void testGetWordsContainingSubstringMultipleContaining() throws IOException {
        List<String> expected = List.of("abcdef", "abcghi");
        filePartReader.setup("src/main/resources/substring.txt", 1, 1);
        FileWordAnalyzer ana = new FileWordAnalyzer(filePartReader);
        assertEquals(expected, ana.getWordsContainingSubstring("abc"));
    }

    @Test
    void testGetStringsWhichPalindromesOne() throws IOException {
        List<String> expected = List.of("roor");
        filePartReader.setup("src/main/resources/palindromeOne.txt", 1, 1);
        FileWordAnalyzer ana = new FileWordAnalyzer(filePartReader);
        assertEquals(expected, ana.getStringsWhichPalindromes());
    }

    @Test
    void testGetStringsWhichPalindromeMultiple() throws IOException {
        List<String> expected = List.of("asddsa", "feef");
        filePartReader.setup("src/main/resources/palindromeMultiple.txt", 1, 1);
        FileWordAnalyzer ana = new FileWordAnalyzer(filePartReader);
        assertEquals(expected, ana.getStringsWhichPalindromes());
    }

    @Test
    void testGetStringsWhichPalindromesNoPalindromes() throws IOException {
        List<String> expected = List.of();
        filePartReader.setup("src/main/resources/palindromeNone.txt", 1, 1);
        FileWordAnalyzer ana = new FileWordAnalyzer(filePartReader);
        assertEquals(expected, ana.getStringsWhichPalindromes());
    }
}