import java.util.ArrayList;

public class Playlist {
    private String playlistName; // valid length is 20 - default to the first 20 characters of input.
    private ArrayList<Song> songs = new ArrayList<Song>();  // should start empty
    private String description; // valid length is 30 - default to the first 30 characters of input.
    private String coverPicture; // this is the name of the cover picture on the playlist. Validation - max length = 10
    private int likes = 0;  // No need to set this in the constructor as it should always start at 0.

    public Playlist(String playlistnane, String description, String coverPicture) {
        this.playlistName = playlistnane;
        this.description = description;
        this.coverPicture = coverPicture;
    }

    public boolean addSong(Song song){
        return songs.add(song);
    }
    public Song deleteSong(int i) {
        if (isValidIndex(i)){
            return songs.remove(i);}
        else return null;
        }


    public String getPlaylistName() {
        return playlistName;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }
    public String getDescription() {
        return description;
    }
    public String getCoverPicture() {
        return coverPicture;
    }
    public int getLikes() {
        return likes;
    }
    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void setCoverPicture(String coverPicture) {
        this.coverPicture = coverPicture;
    }
    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void addLike(){
        this.likes ++;
    }
public String listSongs(){
    if (songs.isEmpty()) {
        return "No songs in playlist.";
    } else {
        String listOfSOngs = "";
        for (int i = 0; i < songs.size(); i++) {
            listOfSOngs += i + ": " + songs.get(i) + "\n";
        }
        return listOfSOngs;
    }
}
public String listVerifiedSongs(){
    if (songs.isEmpty()) {
        return "No songs in playlist.";
    } else {
        String listOfSongs = "";
        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).getArtist().isVerified())
                listOfSongs += i + ": " + songs.get(i) + "\n";
        }
        if (listOfSongs == "") return "There are no verified songs on this playlist";
        else return listOfSongs;
    }
}
    public String listSongsLongerThan(int length){
        if (songs.isEmpty()) {
            return "No songs in playlist.";
        } else {
            String listOfSongs = "";
            for (int i = 0; i < songs.size(); i++) {
                if (songs.get(i).getLength() > length)
                    listOfSongs += i + ": " + songs.get(i) + "\n";
            }
            if (listOfSongs == "") return "There are no verified songs on this playlist";
            else return listOfSongs;
        }
    }
    public String listOfSongsOfArtist(String artist){
        if (songs.isEmpty()) {
            return "No songs in playlist.";
        } else {
            String listOfSongs = "";
            for (int i = 0; i < songs.size(); i++) {
                if (songs.get(i).getArtist().equals(artist))
                    listOfSongs += i + ": " + songs.get(i) + "\n";
            }
            if (listOfSongs == "") return "There are no  songs on this playlist by " + artist;
            else return listOfSongs;
        }
    }
    public int getAverageSongLength(){
        int total = 0;
        for( Song song : songs){
            total += song.getLength();
        }
        return total / songs.size();
    }
    public Song findSong(int index) {
        if (isValidIndex(index)) {
            return songs.get(index);
        }
        return null;
    }
    public boolean isValidIndex(int index) {
        return (index >= 0) && (index < songs.size());
    }
    @Override
    public String toString() {
        return "Playlist{" +
                "playlistname='" + playlistName + '\'' +
                ", songs=" + listSongs() +
                ", description='" + description + '\'' +
                ", coverPicture='" + coverPicture + '\'' +
                '}';
    }
}
