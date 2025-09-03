import java.util.Objects;

public class Artist {

    private String artistName = ""; // valid length is 15 - default to the first 15 characters

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return verified == artist.verified && Objects.equals(artistName, artist.artistName);
    }



    private boolean verified = false;

    public Artist(String artistName, boolean verified) {
        if (artistName.length() <= 15)
            this.artistName = artistName;
        else this.artistName= artistName.substring(0,15);
        setVerified(verified);
    }
    public String getArtistName() {
        return artistName;
    }

    public boolean isVerified() {
        return verified;
    }
    public void setArtistName(String artistName) {
        if (artistName.length() <= 15)
            this.artistName = artistName;
    }
    public void setVerified(boolean verified) {
        this.verified = verified;
    }
    @Override
    public String toString() {
        String ver = " is a verified artist";
        if (!isVerified())  ver = "is not a verified artist";
        return
                "ArtistName='" + artistName + '\'' +
                ",and " + ver +
                '}';
    }

}