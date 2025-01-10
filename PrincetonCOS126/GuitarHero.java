import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GuitarHero extends JFrame implements KeyListener {
    private static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private GuitarString[] strings = new GuitarString[37];

    public GuitarHero() {
        for (int i = 0; i < strings.length; i++) {
            double frequency = 440.0*Math.pow(2, (i - 24) / 12.0);
            strings[i] = new GuitarString(frequency);
        }
        setTitle("Guitar Hero");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(this);
        setFocusable(true);
        setVisible(true);
    }


    public void keyTyped(KeyEvent e) {
        //not used
    }


    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        int index = KEYBOARD.indexOf(key);
        if (index >= 0 && index < strings.length) {
            strings[index].pluck();
            System.out.println("Plucked string for key: " + key);
        }
    }


    public void keyReleased(KeyEvent e) {
        // Not used
    }

    public void play() {
        while (true) {
            double sample = 0.0;
            for (GuitarString string : strings) {
                sample += string.sample();
            }

            if (sample != 0) {
                StdAudio.play(sample);
            }

            for (GuitarString string : strings) {
                string.tic();
            }
        }
    }

    public static void main(String[] args) {
        GuitarHero hero = new GuitarHero();
        hero.play();
    }
}
