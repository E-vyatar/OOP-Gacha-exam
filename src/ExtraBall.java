import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ExtraBall extends Ball{

    private int extraDraws;

    public ExtraBall(String type, String colour, String description, int extraDraws) {
        super(type, colour, description);
        this.extraDraws = extraDraws;
    }

    public static List<Ball> read(String ballsToRead) {
        Scanner ballScanner = new Scanner(ballsToRead);

        Scanner lineScanner = new Scanner(ballScanner.nextLine());
        lineScanner.useDelimiter("EXTRA BALLS BALL \\[");
        String lineEnd = lineScanner.next();
        int numOfBalls = Integer.parseInt(lineEnd.substring(0,lineEnd.length()-1));

        String colour = ballScanner.nextLine();
        String description = ballScanner.nextLine();
        int extras =  Integer.parseInt(ballScanner.nextLine());

        ArrayList<Ball> finalList = new ArrayList<>();
        for (int i = 0; i < numOfBalls; ++i) {
            finalList.add(new ExtraBall("extra", colour, description, extras));
        }

        return finalList;
    }

    @Override
    public String toString() {
        return "Extra Ball Ball (" + getColour() + "), it gives " + extraDraws + " extra draws.";
    }

    public int getExtraDraws() {
        return extraDraws;
    }

    @Override
    public void stringWhenDrawn() {
        String result = "This ball is special, you get to pull a few more balls!\n" +
                "You get " + this.toString();
        System.out.println(result);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        if (!super.equals(other)) return false;
        ExtraBall extraBall = (ExtraBall) other;
        return extraDraws == extraBall.extraDraws;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), extraDraws);
    }
}
