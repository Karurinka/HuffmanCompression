package logic;

import java.io.File;
import java.io.IOException;
import java.util.BitSet;
import java.util.Map;

public class HuffmanManager implements IDecode, IEncode
{
    private Decode decode;
    private Encode encode;

    public HuffmanManager()
    {
        this.decode = new Decode();
        this.encode = new Encode();
    }

    @Override
    public int[] buildFrequencyTable(String data)
    {
        return encode.buildFrequencyTable(data);
    }

    @Override
    public void encode(String data, File fileLocation)
    {
        if (!fileLocation.exists())
        {
            try
            {
                fileLocation.createNewFile();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            return;

        }
        try
        {
            encode.encode(data, fileLocation);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public String decode(File fileName) throws IOException
    {
        return decode.decode(fileName);
    }

    @Override
    public Node buildHuffmanTree(int[] frequency)
    {
        return encode.buildHuffmanTree(frequency);
    }

    @Override
    public BitSet generateEncodedData(String data, Map<Character, String> lookupTable)
    {
        return encode.generateEncodedData(data, lookupTable);
    }

    @Override
    public Map<Character, String> bitLookupTable(Node root)
    {
        return encode.bitLookupTable(root);
    }
}
