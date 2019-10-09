package main.java.filepartreader;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FilePartReaderTest {
    private static FilePartReader filePartReader;

    @BeforeAll
    static void initAll() {
        filePartReader = new FilePartReader();
    }

    @Test
    void testReadEmptyFile() throws IOException {
        filePartReader.setup("src/main/resources/empty.txt", 1, 1);
        assertEquals("", filePartReader.read());
    }

    @Test
    void testReadOneLine() throws IOException {
        filePartReader.setup("src/main/resources/oneLine.txt", 1, 1);
        assertEquals("Lorem ipsum dolor sit amet, consectetur adipiscing elit.", filePartReader.read());
    }

    @Test
    void testReadMultipleLine() throws IOException {
        filePartReader.setup("src/main/resources/multipleLines.txt", 1, 1);
        assertEquals("Lorem ipsum dolor sit amet, consectetur adipiscing elit.\nNullam consequat nec diam quis vehicula.\nNullam nec ultrices eros, vel vestibulum ex.", filePartReader.read());
    }

    @Test
    void testReadInvalidRange() throws IOException {
        assertThrows(IllegalArgumentException.class, () -> filePartReader.setup("src/main/resources/multipleLines.txt", 10, 1));
    }

    @Test
    void testReadLinesEmptyFile() throws IOException {
        filePartReader.setup("src/main/resources/empty.txt", 1, 1);
        assertEquals("", filePartReader.readLines());
    }

    @Test
    void testReadLinesOneLine() throws IOException {
        filePartReader.setup("src/main/resources/oneLine.txt", 1, 1);
        assertEquals("Lorem ipsum dolor sit amet, consectetur adipiscing elit.", filePartReader.readLines());
    }

    @Test
    void testReadLinesMultipleLines() throws IOException {
        filePartReader.setup("src/main/resources/multipleLines.txt", 1, 3);
        assertEquals("Lorem ipsum dolor sit amet, consectetur adipiscing elit.\nNullam consequat nec diam quis vehicula.\nNullam nec ultrices eros, vel vestibulum ex.", filePartReader.readLines());
    }

    @Test
    void testReadLinesMultipleWhitespaceChars() throws IOException {
        filePartReader.setup("src/main/resources/multipleWhiteSpace.txt", 1, 3);
        assertEquals("Lorem ipsum dolor sit amet, consectetur adipiscing elit.\nNullam consequat nec diam quis vehicula.\nNullam nec ultrices eros, vel vestibulum ex.", filePartReader.readLines());
    }
}