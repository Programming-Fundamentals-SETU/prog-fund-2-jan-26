package models;

import utils.Utilities;

public class PhotoPost {

    private String author = "";
    private String caption = "";
    private String filename = "";
    private int likes = 0;

    public PhotoPost(String author, String caption, String filename) {
        this.author = Utilities.truncateString(author, 10);
        this.caption = Utilities.truncateString(caption, 100);
        this.filename = Utilities.truncateString(filename, 40);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (Utilities.validateStringLength(author, 10)) {
            this.author = author;
        }
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        if (Utilities.validateStringLength(caption, 100)) {
            this.caption = caption;
        }
    }

    public void setFilename(String filename) {
        if (Utilities.validateStringLength(filename, 40)) {
            this.filename = filename;
        }
    }

    public String getFilename() {
        return filename;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String display() {
        String str = "";

        str += (author + "\n");

        if(likes > 0) {
            str += ("  -  " + likes + " people like this.\n");
        }
        else {
            str += "0 likes.\n";
        }

        if (!caption.isEmpty()){
            str += "\t" + caption + "\n";
        }

        if (!filename.isEmpty()){
            str += "\t" + filename + "\n";
        }

        return str;
    }
}
