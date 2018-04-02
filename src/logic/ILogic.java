package logic;

import java.util.HashMap;
import java.util.Map;

public interface ILogic
{
    int[] buildFrequencyTable(final String data);
    HuffmanEncodedResult compress(final String data);
    String decompress(HuffmanEncodedResult result) throws IllegalAccessException;
    Node buildHuffmanTree(int[] frequency);
    Map<Character, String> buildBitCodeLookupTable(Node root);
    String generateEncodedData(String data, Map<Character, String> lookupTable);
}
