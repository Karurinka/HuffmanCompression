package Unittests;

import logic.HuffmanManager;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class EncodeAndDecodeTest
{
    private HuffmanManager manager = new HuffmanManager();

    private File file1;
    private File file2;

    final String test = "Een, twee, drie, vier\n" + "Hoedje van, hoedje van\n" + "Een, twee, drie, vier\n" + "Hoedje van papier\n" + "\n" +

            "Heb je dan geen hoedje meer\n" + "Maak er één van bordpapier\n" + "Eén, twee, drie, vier\n" + "Hoedje van papier\n" + "\n" + "Een, twee, drie, vier\n" + "Hoedje van, hoedje van\n" + "Een, twee, drie, vier\n" + "Hoedje van papier\n" + "\n" +

            "En als het hoedje dan niet past\n" + "Zetten we 't in de glazenkas\n" + "Een, twee, drie, vier\n" + "Hoedje van papier";

    final String test2 = "Dit is mooie test text";

    @Before
    public void setUp()
    {
        file1 = new File("testFile1");
        file2 = new File("testFile2");
    }

    @Test
    public void buildFrequencyTable()
    {
        assertEquals(20, manager.buildFrequencyTable(test)[44]);
    }

    @Test
    public void buildHuffmanTree()
    {
        assertEquals(test.length(), manager.buildHuffmanTree(manager.buildFrequencyTable(test)).getFrequency());
        assertEquals('v', manager.buildHuffmanTree(manager.buildFrequencyTable(test)).getLeftChild().getLeftChild().getLeftChild().getLeftChild().getCharacter());
    }

    @Test
    public void buildBitCodeLookupTable()
    {
        assertEquals("1111", (manager.bitLookupTable(manager.buildHuffmanTree(manager.buildFrequencyTable(test))).get('v')));
    }

    @Test
    public void generateEncodedData()
    {
        assertEquals(1514, manager.generateEncodedData(test, manager.bitLookupTable(manager.buildHuffmanTree(manager.buildFrequencyTable(test)))).length());
    }

    @Test
    public void encode() throws IOException
    {
        manager.encode(test, file1);
        manager.encode(test2, file2);
    }

    @Test
    public void decode() throws IOException
    {
        String decodedText1 = manager.decode(file1);
        String decodedText2 = manager.decode(file2);

        assertEquals(test, decodedText1);
        assertEquals(test2, decodedText2);
    }
}