import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class BallMachine0 {


    private List<Ball> ballsBaseList;

    private List<Ball> ballsList;

    private String textFile;

    private FileWriter logWriter;

    private int logLineNum;

    private String logLines;

    private boolean legendaryDrawn;

    public BallMachine0(Scanner input) throws IOException {
        System.out.println("Please enter file name of balls information (type txt): ");
        this.textFile = "resources/" + input.next() + ".txt";

        ballsBaseList = new ArrayList<>();
        ballsList = new ArrayList<>();
        logWriter = new FileWriter("resources/logs.txt");
        logLineNum = 0;
        logLines = "";
        legendaryDrawn = false;
    }

    public static BallMachine0 read() throws IOException {
        BallMachine0 ballMachine = new BallMachine0(new Scanner(System.in));

        Scanner ballsScanner = new Scanner(new File(ballMachine.getTextFile()));
        while (ballsScanner.hasNextLine()) {

            String next = ballsScanner.nextLine();
            addPrize(next,ballsScanner,ballMachine);

             if (next.startsWith("EMPTY")) {
                for (int i = 0; i < 2; ++i) {
                    next += "\n" + ballsScanner.nextLine();
                }
                ballMachine.addBalls("empty", next);
            }
            else if (next.startsWith("EXTRA")) {
                for (int i = 0; i < 3; ++i) {
                    next += "\n" + ballsScanner.nextLine();
                }
                ballMachine.addBalls("extra", next);
            }

        }

        return ballMachine;
    }

    public void add(Ball ball) {
        this.ballsBaseList.add(ball);
        this.ballsList.add(ball);
    }
    public static void addPrize(String next, Scanner ballsScanner, BallMachine0 ballMachine) {
        if (next.startsWith("NORMAL") |
                next.startsWith("RARE") |
                next.startsWith("EPIC") |
                next.startsWith("LEGENDARY")) {

            for (int i = 0; i < 3; ++i) {
                next += "\n" + ballsScanner.nextLine();
            }

            ballMachine.addBalls("prize", next);
        }
    }
    public void addBalls(String ballsType, String ballsToRead) {
        List<Ball> readBalls = null;

        switch (ballsType) {
            case "prize":
                readBalls = PrizeBall.read(ballsToRead);
                break;
            case "empty":
                readBalls = EmptyBall.read(ballsToRead);
                break;
            case "extra":
                readBalls = ExtraBall.read(ballsToRead);
        }

        if (readBalls != null) {
            for (Ball ball : readBalls) {
                this.add(ball);
            }
        }

    }

    public String getTextFile() {
        return textFile;
    }

    public void currentlyInMachine() {
        String summary = "";

        for (Ball ball : ballsList) {
            summary += "\n" + ball.toString();
        }

        System.out.println(summary);
    }

    public void legendaryChance() {
        if (!legendaryDrawn) {
            int chance = (int) ((1.0/ballsList.size()) * 100);
            System.out.println("You have " + chance + "% chance to win the legendary prize!");
        }
        else {
            System.out.println("Legendary ball already drawn, so there is no chance to draw it.");
        }
    }

    public void draw() throws IOException {
        int randomNum  = (int) (Math.random() * ballsList.size());
        Ball drawn = ballsList.remove(randomNum);
        drawn.stringWhenDrawn();

        logLineNum++;
        logLines += logLineNum + ". Drawn ball: " + drawn.toString() + "\n";

        if (drawn.getClass() == ExtraBall.class) {
            for (int i = 0; i < ((ExtraBall) drawn).getExtraDraws(); ++i) {
                this.draw();
            }
        }

        if (drawn.getClass() == PrizeBall.class
                && ((PrizeBall) drawn).getRarity().equals("Legendary")) {
            legendaryDrawn = true;
        }
    }

    public void appendRecentDraws() throws IOException {
        logWriter.write(logLines);
        logWriter.flush();
        logLines = "";
    }

    public void resetState() {
        ballsList.clear();
        ballsList.addAll(ballsBaseList);
    }

    public List<Ball> getBallsList() {
        return ballsList;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        BallMachine0 that = (BallMachine0) other;

        List<Ball> thatList = that.getBallsList();
        for (Ball ball : ballsList) {
            if (!thatList.contains(ball)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                ballsBaseList, ballsList, textFile, logWriter, logLineNum, logLines,legendaryDrawn);
    }
}
