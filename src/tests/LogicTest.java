package tests;

import logic.HuffmanEncodedResult;
import logic.HuffmanManager;
import logic.Logic;
import org.junit.Test;

import static org.junit.Assert.*;

public class LogicTest
{
    private HuffmanManager manager = new HuffmanManager(new Logic());

    final String test =
            "Een, twee, drie, vier\n" +
                    "Hoedje van, hoedje van\n" +
                    "Een, twee, drie, vier\n" +
                    "Hoedje van papier\n" +
                    "\n" +

                    "Heb je dan geen hoedje meer\n" +
                    "Maak er één van bordpapier\n" +
                    "Eén, twee, drie, vier\n" +
                    "Hoedje van papier\n" + "\n" +
                    "Een, twee, drie, vier\n" +
                    "Hoedje van, hoedje van\n" +
                    "Een, twee, drie, vier\n" +
                    "Hoedje van papier\n" +
                    "\n" +

                    "En als het hoedje dan niet past\n" +
                    "Zetten we 't in de glazenkas\n" +
                    "Een, twee, drie, vier\n" +
                    "Hoedje van papier";

    @Test
    public void buildFrequencyTable()
    {
        assertEquals(20,manager.buildFrequencyTable(test)[44]);
    }

    @Test
    public void buildHuffmanTree()
    {
        assertEquals(test.length(),manager.buildHuffmanTree(manager.buildFrequencyTable(test)).getFrequency());
        assertEquals('v',manager.buildHuffmanTree(manager.buildFrequencyTable(test)).getLeftChild().getLeftChild().getLeftChild().getLeftChild().getCharacter());

    }

    @Test
    public void buildBitCodeLookupTable()
    {
        assertEquals("1111",(manager.buildBitCodeLookupTable(manager.buildHuffmanTree(manager.buildFrequencyTable(test))).get('v')));
    }

    @Test
    public void generateEncodedData()
    {
        assertEquals(1513,manager.generateEncodedData(test,manager.buildBitCodeLookupTable(manager.buildHuffmanTree(manager.buildFrequencyTable(test)))).length());
    }

    @Test
    public void encode()
    {
        assertEquals(1513,manager.encode(test).getEncodedData().length());
    }

    @Test
    public void decode() throws IllegalAccessException
    {
        HuffmanEncodedResult result = manager.encode(test);

        assertEquals(test,manager.decode(result));
    }
}