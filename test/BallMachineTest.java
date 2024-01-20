import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class BallMachineTest {

//    @Test
//    void constructor() throws IOException {
//        Scanner scanner = new Scanner("resources/test.txt");
//        BallMachine ballMachine = new BallMachine(scanner);
//        BallMachine.read();
//        ballMachine.add(new Ball("a","b","c"));
//        ballMachine.addBalls("d","e");
//
//    }

    @Test
    void read() throws IOException {
        Scanner scanner = new Scanner("resources/test.txt");
        BallMachine ballMachine = BallMachine.read();
    }

    @Test
    void write() throws IOException {
        BallMachine x = new BallMachine(new Scanner(""));
        x.addBalls("","");
        String input = "PRIZE BALL";
        assertTrue(x.getBallsList().size() > 0);
//        assertEquals(x.getBallsList().get(0).ball);
    }

    @Test
    void add() {
    }

    @Test
    void addBalls() {
    }

    @Test
    void getTextFile() {
    }

    @Test
    void currentlyInMachine() {
    }

    @Test
    void legendaryChance() {
    }

    @Test
    void draw() {
    }

    @Test
    void appendRecentDraws() {
    }

    @Test
    void resetState() {
    }

    @Test
    void getBallsList() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
    }
}