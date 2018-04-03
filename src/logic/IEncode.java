package logic;

import java.io.File;
import java.io.IOException;
import java.util.BitSet;
import java.util.Map;

public interface IEncode
{
    int[] buildFrequencyTable(String data);
    void encode(String data, File fileLocation) throws IOException;
    BitSet generateEncodedData(String data, Map<Character, String> lookupTable);
    Map<Character, String> bitLookupTable(Node root);
    Node buildHuffmanTree(int[] frequency);
}
