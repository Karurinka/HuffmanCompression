package logic;

public class HuffmanApp
{
    public static void main(String[] args) throws Exception
    {
        HuffmanManager manager = new HuffmanManager(new Logic());

        final String test =
        "Besten,\n" +
        "\n" +
        "Vanuit mijn perspectief had ik mijn best gedaan om iedereen feedback te geven en de laatste les had ik lege queque (niemand dus stond te wachten om nog feedback te vragen).\n" +
        "\n" +
        "Dus procesmatig gezien heb ik voldoende momenten gegeven voor het vragen van feedback - en de herkansing is dan de laatste moment om je leerdoelen aan te tonen.\n" +
        "\n" + "Ik wil even lief doen (extra afteken moment) met als tegen prestatie van jullie dat jullie echt voorbereid komen:\n" +
        "\n" + "-dubbelcheck (opgave(n) aandachtig lezen!) dat je de gevraagde stukken ook in jouw oplossing hebt staan. Uitgewerkte oplossing dus.\n" +
        "\n" + "-vroeg naar per review van jouw collega en vraag actief wat voor feedback dat ze hadden gekregen , verwerk hun feedback in jouw oplossing en wees voorbereid. als bewijs breng mee de genoteerde feedback die je had gekregen.\n" +
        "\n" + "-geen echte unit en/of performance tests == kijk ik niet naar de rest van je code (je hoeft dus niet te komen en ik zie je bij de herkansing).\n" +
        "\n" + "-je moet kunnen uitleggen hoe hash map en tree werken en waarom ze de bepaalde Big(o) hebben per operatie bv. hoe efficiënt is een nieuwe element toevoegen en hoe gebeurt het (stappen uitleggen)\n" +
        "\n" + "-als bewijs van dit bericht aandachtig gelezen te hebben geef index van de chars in dit announcements die nodig zijn om jouw achternaam te spellen. Neem dit ook mee naar de feedback sessie.\n" +
        "\n" + " 3 april 12:30-16:30 ik zal in de open ruimte op 2de verdiep zitten (english stream). pas op er zijn twee klassen die aftekenen dus wacht niet te lang.";

        final HuffmanEncodedResult result = manager.encode(test);

        System.out.println("encoded message: " + result.getEncodedData());
        System.out.println("original message: " + manager.decode(result));

//        B: 111000011
//        R: 01111
//        O: 1111
//        E: 001
//        N: 0101
//        S: 01110
    }
}
