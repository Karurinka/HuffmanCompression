package logic;

import java.util.HashMap;
import java.util.Map;

public interface ILogic
{
    int[] buildFrequencyTable(final String data);
    HuffmanEncodedResult encode(final String data);
    String decode(HuffmanEncodedResult result) throws IllegalAccessException;
    Node buildHuffmanTree(int[] frequency);
    Map<Character, String> buildBitCodeLookupTable(Node root);
    String generateEncodedData(String data, Map<Character, String> lookupTable);
}
