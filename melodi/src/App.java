import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() { // ensures the GUI is executed on the event dispatch thread
            @Override
            public void run () {
                new PlayerGUI().setVisible(true);

                /*Song song = new Song("src/assets/Wind Riders - Asher Fulero.mp3");
                System.out.println(song.getSongTitle());
                System.out.println(song.getSongArtist());*/
            }
        });
    }
}
