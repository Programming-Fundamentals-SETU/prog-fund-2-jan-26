import controllers.NewsFeed;
import models.MessagePost;
import models.PhotoPost;

public class Test {

    public static void main(String args[])
    {
        // Create 2 MessagePost objects.
        MessagePost messagePost1 = new MessagePost("Mary", "Hi there.");
        MessagePost messagePost2 = new MessagePost("John", "I'm on my way.");

        // Create 2 PhotoPosts objects.
        PhotoPost photoPost1 = new PhotoPost("Larry", "img1.jpg", "First day in college");
        PhotoPost photoPost2 = new PhotoPost("Moe", "img2.jpg", "Graduation day");

        // Create 1 NewsFeed object.
        NewsFeed newsFeed = new NewsFeed();

        System.out.println("-----------------Adding Message and Photo Posts------------------");
        // Add 1 PhotoPost object to the NewsFeed object.
        if (newsFeed.addPhotoPost(photoPost1)){
            System.out.println("Photo Post 1 Added Successfully ("
                    + photoPost1.getAuthor() + ": "
                    + photoPost1.getCaption() + ","
                    + photoPost1.getFilename() + ")");
        }

        // Add 1 MessagePost object to the NewsFeed object.

        if (newsFeed.addMessagePost(messagePost1)){
            System.out.println("Message Post 1 Added Successfully ("
                    + messagePost1.getAuthor() + ": "
                    + messagePost1.getMessage() + ")");
        }

        // Add another PhotoPost object to the NewsFeed object.
        if (newsFeed.addPhotoPost(photoPost2)){
            System.out.println("Photo Post 2 Added Successfully ("
                    + photoPost2.getAuthor() + ": "
                    + photoPost2.getCaption() + ","
                    + photoPost2.getFilename() + ")");
        }

        // Add another MessagePost object to the NewsFeed object.
        if (newsFeed.addMessagePost(messagePost2)){
            System.out.println("Message Post 2 Added Successfully ("
                    + messagePost2.getAuthor() + ": "
                    + messagePost2.getMessage() + ")");
        }

        // List all messagePost and photoPosts from the NewsFeed object.
        System.out.println("-----------------Newsfeed - show() method contents------------------");
        System.out.println(newsFeed.show());
    }
}
