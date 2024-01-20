import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class PrizeBall extends Ball{

    private String rarity;

    private String prize;

    public PrizeBall(String type, String colour, String description, String rarity, String prize) {
        super(type, colour, description);
        this.rarity = rarity;
        this.prize = prize;
    }

    public static List<Ball> read(String ballsToRead) {
        Scanner ballScanner = new Scanner(ballsToRead);
        Scanner lineScanner = new Scanner(ballScanner.nextLine());
        String rarityCase = lineScanner.next();
        String rarity = rarityCase.charAt(0) + rarityCase.substring(1).toLowerCase();

        lineScanner.next();
        lineScanner.next();
        String lineEnd = lineScanner.next();
        int numOfBalls = Integer.parseInt(lineEnd.substring(1,lineEnd.length()-1));

        String colour = ballScanner.nextLine();
        String description = ballScanner.nextLine();

        String prizesLine = ballScanner.nextLine();
        prizesLine = prizesLine.substring(1,prizesLine.length()-1);
        String[] prizesList = prizesLine.split(", ");

        ArrayList<Ball> finalList = new ArrayList<>();
        int prizeNum = 0;
        for (int i = 0; i < numOfBalls; ++i) {
            finalList.add(new PrizeBall("prize", colour, description, rarity,prizesList[prizeNum]));
            if (++prizeNum >= prizesList.length) {
                prizeNum = 0;
            }
        }

        return finalList;
    }

    @Override
    public String toString() {
        return rarity + " Prize Ball (" + getColour() + "), it contains " + prize + ".";
    }

    @Override
    public void stringWhenDrawn() {
        String result = "This ball contains an ultra fabulous animal figurine!\n" +
                "You get " + this.toString();
        System.out.println(result);
    }

    public String getRarity() {
        return rarity;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        if (!super.equals(other)) return false;
        PrizeBall prizeBall = (PrizeBall) other;
        return Objects.equals(rarity, prizeBall.rarity) && Objects.equals(prize, prizeBall.prize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), rarity, prize);
    }
}