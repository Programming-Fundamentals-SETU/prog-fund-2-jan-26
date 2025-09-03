public class Song {
    private int songId = 9999; //valid values - between 1000 and 9999 inclusive - default value is 9999
    private String name; // valid length is 20 - default to the first 20 characters of input.
    private Artist artist; // valid length is 15 - default to the first 15 characters
    private int length = 1; // length of song in seconds. Valid values are between 1 and 600 inclusive. Default to 1.
   // private boolean verified = false; // value of true means that this song has been correctly licenced on Songify platform, false otherwise. Default is false.



    public Song(int songId, String name, String  artistName,boolean verified, int length) {
        setSongId(songId);
        if (name.length() <= 20)
            this.name = name;
        else this.name = name.substring(0,20);
       artist = new Artist (artistName, verified);
       setLength(length);
    }


    public int getSongId() {
        return songId;
    }

    public String getName() {
        return name;
    }

    public Artist getArtist() {
        return artist;
    }

    public int getLength() {
        return length;
    }


    public void setSongId(int songId) {
        if ((songId >= 1000) && (songId <=9999))
            this.songId = songId;
    }

    public void setName(String name) {
        if  (name.length() <= 20)
            this.name = name;
    }

    public void setArtist(Artist artist) {

            this.artist = artist;
    }

    public void setLength(int length) {
        if ((length >= 1) && (length <=600))
            this.length = length;
    }


    @Override
    public String toString() {
        return "Song{" +
                "songId=" + songId +
                ", name= " + name + '\'' +
                ", artist= " + artist + '\'' +
                ", length=" + length +
                '}';
    }
}

