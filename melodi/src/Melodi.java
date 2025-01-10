import javazoom.jl.player.advanced.AdvancedPlayer;

public class Melodi {
    // way to store song details
    private Song currentSong;

    // JLayer lib to create AdvancedPlayer obj to hnadle playing music
    private AdvancedPlayer advancedPlayer;
    // constructor
    public Melodi() {

    }

    public void loadSong(Song song) {
        currentSong = song;

        if(currentSong != null) {
            playCurrentSong();
        }
    }

    public void playCurrentSong() {
        //25:53
    }
}
