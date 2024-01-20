import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        BallMachine ballMachine = BallMachine.read();

        Scanner userInput = new Scanner(System.in);
        while (true) {
            System.out.println("Please make your choice: \n" +
                    "1 – Show all balls currently in the machine.\n" +
                    "2 – Show current chance to win the legendary prize.\n" +
                    "3 – Draw a ball.\n" +
                    "4 – Write debug output to file.\n" +
                    "5 – Reset machine state.\n" +
                    "6 – Quit the application.\n");

            int userChoice = userInput.nextInt();

            switch (userChoice) {
                case 1:
                    ballMachine.currentlyInMachine();
                    break;
                case 2:
                    ballMachine.legendaryChance();
                    break;
                case 3:
                    ballMachine.draw();
                    break;
                case 4:
                    ballMachine.appendRecentDraws();
                    break;
                case 5:
                    ballMachine.resetState();
                    break;
                case 6:
                    System.exit(0);
                    break;
            }
        }
    }
}
