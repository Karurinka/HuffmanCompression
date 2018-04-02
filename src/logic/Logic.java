package logic;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Logic implements ILogic
{
    private static final int ALPHABET_SIZE = 256;

    @Override
    public int[] buildFrequencyTable(String data)
    {
        final int[] frequency = new int[ALPHABET_SIZE];
        for (char c : data.toCharArray())
        {
            frequency[c]++;
        }

        return frequency;
    }

    public Node buildHuffmanTree(int[] frequency)
    {
        PriorityQueue<Node> nodePriorityQueue = new PriorityQueue<>();

        for (char i = 0; i < ALPHABET_SIZE; i++)
        {
            if (frequency[i] > 0)
            {
                nodePriorityQueue.add(new Node(i, frequency[i], null, null));

            }
        }

        // if the message only contains one character
        if (nodePriorityQueue.size() == 1)
        {
            nodePriorityQueue.add(new Node('\0', 1, null, null));
        }

        // add all nodes together until only one remains
        while (nodePriorityQueue.size() > 1)
        {
            Node left = nodePriorityQueue.poll();
            Node right = nodePriorityQueue.poll();

            // merging left and right node
            // use 0 to indicate this is not a leaf character
            Node parent = new Node('\0', left.getFrequency() + right.getFrequency(), left, right);

            nodePriorityQueue.add(parent);
        }

        // this is the root node and is connected to all the previous nodes
        return nodePriorityQueue.poll();
    }

    // bind characters to a binary number
    public Map<Character, String> buildLookupTable(Node root)
    {
        Map<Character, String> lookupTable = new HashMap<>();
        addToLookupTable(root, "", lookupTable);

        return lookupTable;
    }

    public void addToLookupTable(Node node, String s, Map<Character, String> lookupTable)
    {
        // if the node is not a leaf
        // add the left and right child to the lookup table
        if (!node.isLeaf())
        {
            addToLookupTable(node.getLeftChild(), s + '0', lookupTable);
            addToLookupTable(node.getRightChild(), s + '1', lookupTable);
        }
        else
        {
            lookupTable.put(node.getCharacter(), s);
        }
    }

    public String generateEncodedData(String data, Map<Character, String> lookupTable)
    {
        StringBuilder stringBuilder = new StringBuilder();

        for (char c : data.toCharArray())
        {
            stringBuilder.append(lookupTable.get(c));

        }
        return stringBuilder.toString();
    }

    @Override
    public HuffmanEncodedResult compress(String data)
    {
        // build the table with the frequency of the characters
        final int[] frequency = buildFrequencyTable(data);
        Node root = buildHuffmanTree(frequency);
        // build the lookup table from the root node
        Map<Character, String> lookupTable = buildLookupTable(root);

        return new HuffmanEncodedResult(generateEncodedData(data, lookupTable), root);
    }

    /**
     * for each bit go down to the leaf
     * in order to determine where that portion of the message starts and ends
     * start at the root and go down to the leaf for each character in the message
     * @param result is the result of going down the whole tree
     * @return the string builder to string
     * @throws IllegalAccessException when bit is not 1 or 0
     */
    @Override
    public String decompress(HuffmanEncodedResult result) throws IllegalAccessException
    {
        StringBuilder stringBuilder = new StringBuilder();

        Node current = result.getRoot();
        int i = 0;

        while (i < result.getEncodedData().length())
        {
            //
            while (!current.isLeaf())
            {
                char bit = result.getEncodedData().charAt(i);
                if (bit == '1')
                {
                    current = current.getRightChild();
                }
                else if (bit == '0')
                {
                    current = current.getLeftChild();
                }
                else
                {
                    throw new IllegalAccessException("Invalid bit: " + bit);
                }
                i++;
            }
            stringBuilder.append(current.getCharacter());
            current = result.getRoot();
        }

        return stringBuilder.toString();
    }
}
