package logic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Encode implements IEncode
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

    public Map<Character, String> bitLookupTable(Node root)
    {
        HashMap<Character, String> bitSetHashMap = new HashMap<>();
        addToLookupTable(bitSetHashMap, root, "");
        return bitSetHashMap;
    }

    private void addToLookupTable(HashMap<Character, String> bitSetHashMap, Node currentNode, String code)
    {
        if (!currentNode.isLeaf())
        {
            addToLookupTable(bitSetHashMap, currentNode.getLeftChild(), code + "1");
            addToLookupTable(bitSetHashMap, currentNode.getRightChild(), code + "0");
        }
        else
        {
            bitSetHashMap.put(currentNode.getCharacter(), code);
        }
    }

    public BitSet generateEncodedData(String data, Map<Character, String> lookupTable)
    {
        BitSet bitSet = new BitSet();
        int index = 0;

        for (char c : data.toCharArray())
        {
            String character = lookupTable.get(c);
            for (char lookupCharacter : character.toCharArray())
            {
                if (lookupCharacter == '1')
                {
                    bitSet.set(index);
                }
                index++;
            }

        }
        bitSet.set(index);
        return bitSet;
    }

    /**
     * builds a frequency table for every letter in the data
     * builds a huffman tree for all the characters in the data
     * this huffman tree returns one node that contains all of the other nodes
     * this root node is then set into a lookup table where the characters are bound to a binary number
     *
     * @param data
     * @return
     */
    public void encode(String data, File fileLocation) throws IOException
    {
        // build the table with the frequency of the characters
        final int[] frequency = buildFrequencyTable(data);
        Node root = buildHuffmanTree(frequency);
        // build the lookup table from the root node
        Map<Character, String> lookupTable = bitLookupTable(root);
        HuffmanEncodedResult result = new HuffmanEncodedResult(generateEncodedData(data, lookupTable), root);

        writeToFile(result, fileLocation);
    }

    public void writeToFile(HuffmanEncodedResult result, File file) throws IOException
    {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file)))
        {
            objectOutputStream.writeObject(result);
        }
    }
}
