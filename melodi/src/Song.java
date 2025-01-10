import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

import java.io.File;

// used to describe song
public class Song {
    public String songTitle;
    private String songArtist;
    private String songLength;
    private String filePath;

    public Song(String filePath) {
        this.filePath = filePath;
        try{
            // use the jaudiotagger lib to create an audiofile obj to read the mp3 files info\
            AudioFile audioFile = AudioFileIO.read(new File(filePath));

            // read through the meta data of the audio file
            Tag tag = audioFile.getTag();
            if(tag != null) {
                songTitle = tag.getFirst(FieldKey.TITLE);
                songArtist = tag.getFirst(FieldKey.ARTIST);
            } else {
                // could not read through mp3 files meta data
                songTitle = "N/A";
                songArtist = "N/A";
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // getters

    public String getSongTitle() {
        return songTitle;
    }

    public String getSongArtist() {
        return songArtist;
    }

    public String getSongLength() {
        return songLength;
    }

    public String getFilePath() {
        return filePath;
    }
}
