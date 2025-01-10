
/**
 * Write a description of class TowerOfHanoi here.
 *
 * @author (Andrew Bae)
 * @version (9/3/24)
 */

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class TowerOfHanoi extends JPanel {
    private static final int discs = 3; //number of discs
    private Stack<Integer>[] pegs = new Stack[3]; //because there are 3 pegs
    private int moveDelay = 800; //movement delay

    public TowerOfHanoi() {
        for (int i = 0; i < 3; i++) { //initialize the pegs
            pegs[i] = new Stack<>();
        }

        for (int i = discs; i > 0; i--) { //add disc to the first peg
            pegs[0].push(i);
        }

        //JFrame window setup
        JFrame frame = new JFrame("Tower of Hanoi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.add(this);
        frame.setVisible(true);

        //thread because the recursive formula could be taxing with a larger disc count
        new Thread(() -> {
            try {
                solve(discs, 0, 2, 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void solve(int n, int fromPeg, int toPeg, int tempPeg) throws InterruptedException { //interruptedException because moveDisc has it
        if (n == 1) { //terminate when the first disc goes to the last peg
            moveDisc(fromPeg, toPeg);
        } else { //keep moving the discs to different pegs
            solve(n - 1, fromPeg, tempPeg, toPeg);
            moveDisc(fromPeg, toPeg);
            solve(n - 1, tempPeg, toPeg, fromPeg);
        }
    }

    private void moveDisc(int fromPeg, int toPeg) throws InterruptedException { //interruptedException for if while sleeping it is interrupted
        int disc = pegs[fromPeg].pop(); //move the disc
        pegs[toPeg].push(disc);

        repaint(); //refresh the screen to show the new disc locations

        Thread.sleep(moveDelay); //wait for teh delay
    }

    @Override
    protected void paintComponent(Graphics g) { //from the JPanel class
        super.paintComponent(g);
        int pegWidth = getWidth() / 3;
        int baseHeight = getHeight() - 30;
        int discHeight = 20;

        for (int peg = 0; peg < 3; peg++) {
            g.fillRect(peg * pegWidth + pegWidth / 2 - 5, 100, 10, baseHeight - 100); //draw the pegs
            
            Stack<Integer> discs = pegs[peg]; //draw the discs
            for (int i = 0; i < discs.size(); i++) {
                int disc = discs.get(i);
                int discWidth = disc * 20;
                g.fillRect(peg * pegWidth + pegWidth / 2 - discWidth / 2, baseHeight - (i + 1) * discHeight, discWidth, discHeight);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TowerOfHanoi::new);
        //swingutilities schedules task to be executed on the event dispatch thread
        //invoke.later to ensure the task runs on the EVT b/c swing is not thread safe
        //TowerofHanoi::new is a method reference
    }
}

