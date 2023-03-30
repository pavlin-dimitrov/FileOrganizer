import java.util.Random;
import java.util.Scanner;

public class Shelter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Dog Balkan = Balkan();
        Dog Sharo = Sharo();
        Dog Rex = Rex();
        Dog Betoven = Betoven();

        Dog[] allDogs = {Balkan, Sharo, Rex, Betoven};
        int[] notAdoptedDogsIndexes = new int[allDogs.length / 2];
        int[] adoptedDogsIndexes = new int[allDogs.length / 2];

        Random rnd = new Random();
        int dogIndex = rnd.nextInt(0, 1);

        printAllDogs(allDogs);
        adoptTwoDogs(sc, allDogs);
        separateAdoptedFromNotAdopted(allDogs, notAdoptedDogsIndexes, adoptedDogsIndexes);

        System.out.println("This dog is adopted: ");
        printOneAdoptedOrNotAdoptedDog(allDogs, adoptedDogsIndexes[dogIndex]);

        System.out.println("This dog still wait to be adopted: ");
        printOneAdoptedOrNotAdoptedDog(allDogs, notAdoptedDogsIndexes[dogIndex]);
    }

    private static void printOneAdoptedOrNotAdoptedDog(Dog[] allDogs, int arrayIndex) {
        System.out.println("Name: " + allDogs[arrayIndex].getName());
        System.out.println("Profile picture: " + "\n" + allDogs[arrayIndex].getProfilePicture());
        System.out.println("Color: " + allDogs[arrayIndex].getColor());
        System.out.println("EGN: " + allDogs[arrayIndex].getEgn());
        System.out.println("Adopted: " + allDogs[arrayIndex].isAdopted());
        System.out.println();
    }

    private static void separateAdoptedFromNotAdopted(Dog[] allDogs, int[] notAdoptedDogsIndexes, int[] adoptedDogsIndexes) {
        for (int i = 0; i < allDogs.length; i++) {
            if (!allDogs[i].isAdopted()) {
                notAdoptedDogsIndexes[i % 2] = i;
            } else if (allDogs[i].isAdopted()) {
                adoptedDogsIndexes[i % 2] = i;
            }
        }
    }

    private static void adoptTwoDogs(Scanner sc, Dog[] allDogs) {
        int dogsToAdopt = 0;
        System.out.println("Adopt 2 dogs by entering their names:");
        while (dogsToAdopt < 2) {
            System.out.print("Dog name: ");
            String dogName = sc.nextLine();
            for (Dog allDog : allDogs) {
                if (allDog.getName().equalsIgnoreCase(dogName)) {
                    allDog.setAdopted();
                    dogsToAdopt++;
                }
            }
        }
    }

    private static void printAllDogs(Dog[] allDogs) {
        for (Dog allDog : allDogs) {
            System.out.println("Name: " + allDog.getName());
            System.out.println("Profile picture: " + "\n" + allDog.getProfilePicture());
            System.out.println();
        }
    }

    private static Dog Balkan() {
        Dog Balkan = new Dog();
        Balkan.setName("Balkan");
        Balkan.setProfilePicture("""
                   / \\__
                  (    @\\___
                  /         O
                 /   (_____/
                /_____/   U""");
        Balkan.setColor("Brown");
        Balkan.setEgn(1234567);
        return Balkan;
    }

    private static Dog Sharo() {
        Dog Sharo = new Dog();
        Sharo.setName("Sharo");
        Sharo.setProfilePicture("""
                             .--~~,__
                :-....,-------`~~'._.'
                 `-,,,  ,_      ;'~U'
                  _,-' ,'`-__; '--.
                 (_/'~~      ''''(;""");
        Sharo.setColor("Black");
        Sharo.setEgn(7654321);
        return Sharo;
    }

    private static Dog Rex() {
        Dog Rex = new Dog();
        Rex.setName("Rex");
        Rex.setProfilePicture("""
                    ___
                 __/_  `.  .-""\"-.
                 \\_,` | \\-'  /   )`-')
                  "") `"`    \\  ((`"`
                 ___Y  ,    .'7 /|
                (_,___/...-` (_/_/\s""");
        Rex.setColor("Black/Brown");
        Rex.setEgn(7890123);
        return Rex;
    }

    private static Dog Betoven() {
        Dog Betoven = new Dog();
        Betoven.setName("Betoven");
        Betoven.setProfilePicture("""
                     |\\_/|                 \s
                     | @ @   Woof!\s
                     |   <>              _ \s
                     |  _/\\------____ ((| |))
                     |               `--' |  \s
                 ____|_       ___|   |___.'\s
                /_/_____/____/_______|""");
        Betoven.setColor("Black/Brown/White");
        Betoven.setEgn(3210987);
        return Betoven;
    }
}
