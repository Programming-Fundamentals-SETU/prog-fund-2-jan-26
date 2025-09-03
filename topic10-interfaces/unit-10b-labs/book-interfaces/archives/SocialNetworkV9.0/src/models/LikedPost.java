package models;

import utils.Utilities;

public abstract class LikedPost extends Post {

    private int likes = 0;

    public LikedPost(String author){
        super(author);
    }

    @Override
    public String displayCondensed() {
        if(likes > 0) {
            return super.getAuthor() + " (" + likes + " likes) ";
        }
        else {
            return super.getAuthor() + " (0 likes) ";
        }
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        if (Utilities.validRange(likes, 0, 10000))
            this.likes = likes;
    }

    public void likeAPost(){
        likes++;
    }

    public void unlikeAPost(){
        if (likes > 0) {
            likes--;
        }
    }

    public String display() {
        String str = super.display();

        if(likes > 0) {
            str += ("  -  " + likes + " people like this.\n");
        }
        else {
            str += "0 likes.\n";
        }

        return str;
    }

}
