package logic;

import java.io.Serializable;
import java.util.BitSet;

public class HuffmanEncodedResult implements Serializable
{
    // fields
    private BitSet encodedData;
    private Node root;

    // getters
    public Node getRoot()
    {
        return root;
    }

    public BitSet getEncodedData()
    {
        return encodedData;
    }

    // constructor
    public HuffmanEncodedResult(BitSet encodedData, Node root)
    {
        this.encodedData = encodedData;
        this.root = root;
    }
}
