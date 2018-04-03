package logic;

import java.io.*;
import java.util.BitSet;

public class Decode implements IDecode
{
    public HuffmanEncodedResult readFromFile(File fileName) throws InvalidClassException
    {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName)))
        {
            Object object = objectInputStream.readObject();
            if (object instanceof HuffmanEncodedResult)
            {
                return (HuffmanEncodedResult) object;
            }
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        throw new InvalidClassException("Object is not an instance off HuffmanEncodedResult");
    }

    /**
     * for each bit go down to the leaf
     * in order to determine where that portion of the message starts and ends
     * start at the root and go down to the leaf for each character in the message
     *
     * @param fileName is the file that is read from
     * @return the string builder to string
     * @throws IllegalAccessException when bit is not 1 or 0
     */
    @Override
    public String decode(File fileName) throws IOException
    {
        HuffmanEncodedResult result = readFromFile(fileName);
        StringBuilder stringBuilder = new StringBuilder();

        BitSet bitSet = result.getEncodedData();

        for (int i = 0; bitSet.length() - 1 > i; )
        {
            Node currentNode = result.getRoot();
            while (!currentNode.isLeaf())
            {
                if (bitSet.get(i))
                {
                    currentNode = currentNode.getLeftChild();
                }
                else
                {
                    currentNode = currentNode.getRightChild();
                }
                i++;
            }
            stringBuilder.append(currentNode.getCharacter());
        }
        return stringBuilder.toString();
    }
}
