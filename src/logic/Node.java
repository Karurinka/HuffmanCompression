package logic;

import java.util.Comparator;

public class Node implements Comparable<Node>
{
    // fields
    private char character;
    private int frequency;
    private Node leftChild;
    private Node rightChild;

    // getters
    public char getCharacter()
    {
        return character;
    }

    public int getFrequency()
    {
        return frequency;
    }

    public Node getLeftChild()
    {
        return leftChild;
    }

    public Node getRightChild()
    {
        return rightChild;
    }

    // constructor
    public Node(char character, int frequency, Node leftChild, Node rightChild)
    {
        this.character = character;
        this.frequency = frequency;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    // methods
    boolean isLeaf()
    {
        return this.leftChild == null && this.rightChild == null;
    }

    @Override
    public int compareTo(Node other)
    {
        int frequencyComparer = Integer.compare(this.frequency, other.frequency);
        if (frequencyComparer != 0)
        {
            return frequencyComparer;
        }

        return Integer.compare(this.character, other.character);
    }
}

