import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class PlayerGUI extends JFrame {
    //color configs
    public static final Color FRAME_COLOR = Color.BLACK;
    public static final Color TEXT_COLOR = Color.WHITE;

    public PlayerGUI() {
        super("Melodi"); // calls JFrame constructor and names header to Melodi

        setSize(400, 600); // size

        setDefaultCloseOperation(EXIT_ON_CLOSE); //end process on close

        setLocationRelativeTo(null); // launch app on center of screen

        setResizable(false); // self explanatory

        setLayout(null); //allows for x,y control for components and height and width control

        // change frame color
        getContentPane().setBackground(FRAME_COLOR);

        addGuiComponets();
    }

    private void addGuiComponets() {
        // load toolbar
        addToolbar();

        //load record image
        JLabel songImage = new JLabel(loadImage("src/assets/record.png"));
        songImage.setBounds(0, 50, getWidth() - 20, 225);
        add(songImage);

        // song title
        JLabel songTitle = new JLabel("Song Title");
        songTitle.setBounds(0, 285, getWidth() - 10, 30);
        songTitle.setFont(new Font("Dialog", Font.BOLD, 24));
        songTitle.setForeground(TEXT_COLOR);
        songTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(songTitle);

        // song artist
        JLabel songArtist = new JLabel("Artist");
        songArtist.setBounds(0, 315, getWidth() - 10, 30);
        songArtist.setFont(new Font("Dialog", Font.PLAIN, 24));
        songArtist.setForeground(TEXT_COLOR);
        songArtist.setHorizontalAlignment(SwingConstants.CENTER);
        add(songArtist);

        // playback slider
        JSlider playbackSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        playbackSlider.setBounds(getWidth()/2 - 300/2, 365, 300, 40);
        playbackSlider.setBackground(null);
        add(playbackSlider);

        // playback buttons
        addPlayBackBtns();

    }

    private void addToolbar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setBounds(0,0, getWidth(), 20);

        toolBar.setFloatable(false); // no moving the toolbar

        JMenuBar menuBar = new JMenuBar(); // drop down menu
        toolBar.add(menuBar);

        // song menu to place loading song option
        JMenu songMenu = new JMenu("Song");
        menuBar.add(songMenu);

        // load song item in songMenu
        JMenuItem loadSong = new JMenuItem("Load Song");
        songMenu.add(loadSong);

        // playlist menu
        JMenu playlistMenu = new JMenu("Playlist");
        menuBar.add(playlistMenu);

        // add itmes to the playlist menu
        JMenuItem createPlaylist = new JMenuItem("Create Playlist");
        playlistMenu.add(createPlaylist);

        JMenuItem loadPlaylist = new JMenuItem("Load Playlist");
        playlistMenu.add(loadPlaylist);

        add(toolBar);
    }

    private void addPlayBackBtns() {
        JPanel playbackBtns = new JPanel();
        playbackBtns.setBounds(0, 435, getWidth() - 10, 80);
        playbackBtns.setBackground(null);


        // previous button
        JButton prevButton = new JButton(loadImage("src/assets/previous.png"));
        prevButton.setBorderPainted(false);
        prevButton.setBackground(null);
        playbackBtns.add(prevButton);

        // play button
        JButton playButton = new JButton(loadImage("src/assets/play.png"));
        playButton.setBorderPainted(false);
        playButton.setBackground(null);
        playbackBtns.add(playButton);

        // pause button
        JButton pauseButton = new JButton(loadImage("src/assets/pause.png"));
        pauseButton.setBorderPainted(false);
        pauseButton.setBackground(null);
        pauseButton.setVisible(false);
        playbackBtns.add(pauseButton);

        // next button
        JButton nextButton = new JButton(loadImage("src/assets/next.png"));
        nextButton.setBorderPainted(false);
        nextButton.setBackground(null);
        playbackBtns.add(nextButton);

        add(playbackBtns);
    }

    private ImageIcon loadImage(String imagePath) {
        try {
            // read the image from the given path
            BufferedImage image = ImageIO.read(new File(imagePath));

            // returns image icon
            return new ImageIcon(image);
        } catch(Exception e) {
            e.printStackTrace();
        }

        // cant find image
        return null;
    }
}
