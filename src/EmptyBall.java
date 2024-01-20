import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmptyBall extends Ball{

    public EmptyBall(String type, String colour, String description) {
        super(type, colour, description);
    }

    public static List<Ball> read(String ballsToRead) {
        Scanner ballScanner = new Scanner(ballsToRead);

        Scanner lineScanner = new Scanner(ballScanner.nextLine());
        lineScanner.useDelimiter("EMPTY BALL \\[");
        String lineEnd = lineScanner.next();
        int numOfBalls = Integer.parseInt(lineEnd.substring(0,lineEnd.length()-1));

        String colour = ballScanner.nextLine();
        String description = ballScanner.nextLine();

        ArrayList<Ball> finalList = new ArrayList<>();
        for (int i = 0; i < numOfBalls; ++i) {
            finalList.add(new EmptyBall("empty", colour, description));
        }

        return finalList;
    }

    @Override
    public String toString() {
        return "Empty Ball (" + getColour() + "), it contains... nothing.";
    }

    @Override
    public void stringWhenDrawn() {
        String result = "Oh no! This ball is completely empty. Better luck on the next one!" +
                "\nYou get " + this.toString();
        System.out.println(result);
    }
}
