package logic;

public class HuffmanApp
{
    public static void main(String[] args) throws Exception
    {
        HuffmanManager manager = new HuffmanManager(new Logic());

        final String test =
                "Een, twee, drie, vier\n" +
                "Hoedje van, hoedje van\n" +
                "Een, twee, drie, vier\n" +
                "Hoedje van papier\n" +
                "\n" +

                "Heb je dan geen hoedje meer\n" +
                "Maak er één van bordpapier\n" +
                "Eén, twee, drie, vier\n" +
                "Hoedje van papier\n" + "\n" +
                "Een, twee, drie, vier\n" +
                "Hoedje van, hoedje van\n" +
                "Een, twee, drie, vier\n" +
                "Hoedje van papier\n" +
                "\n" +

                "En als het hoedje dan niet past\n" +
                "Zetten we 't in de glazenkas\n" +
                "Een, twee, drie, vier\n" +
                "Hoedje van papier";

        final HuffmanEncodedResult result = manager.encode(test);

        System.out.println("encoded message =" + result.getEncodedData());
        System.out.println("original message = " + manager.decode(result));
    }
}
