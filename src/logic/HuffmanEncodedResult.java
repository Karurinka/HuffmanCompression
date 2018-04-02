package logic;

public class HuffmanEncodedResult
{
    // fields
    private String encodedData;
    private Node root;

    // getters
    public Node getRoot()
    {
        return root;
    }

    public String getEncodedData()
    {
        return encodedData;
    }

    // constructor
    public HuffmanEncodedResult(String encodedData, Node root)
    {
        this.encodedData = encodedData;
        this.root = root;
    }
}
