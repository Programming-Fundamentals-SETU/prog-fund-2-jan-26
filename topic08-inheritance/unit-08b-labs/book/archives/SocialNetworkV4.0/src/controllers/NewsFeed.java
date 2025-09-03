package controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.MessagePost;
import models.PhotoPost;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class NewsFeed {

    private ArrayList<MessagePost> messagePosts;
    private ArrayList<PhotoPost> photoPosts;

    public NewsFeed() {
        messagePosts = new ArrayList<MessagePost>();
        photoPosts = new ArrayList<PhotoPost>();
    }

    public boolean addMessagePost(MessagePost messagePost) {
        return messagePosts.add(messagePost);
    }

    public boolean addPhotoPost(PhotoPost photoPost) {
        return photoPosts.add(photoPost);
    }

    public String show() {
        String str = "";

        for(MessagePost messagePost : messagePosts) {
            str += messagePosts.indexOf(messagePost) + ": " + messagePost.display() + "\n";
        }

        for(PhotoPost photoPost : photoPosts) {
            str += photoPosts.indexOf(photoPost) + ": " + photoPost.display() + "\n";
        }

        if (str.isEmpty()){
            return "No Posts";
        }
        else {
            return str;
        }
    }

    public String showPhotoPosts() {
        String str = "";

        for(PhotoPost photoPost : photoPosts) {
            str += photoPosts.indexOf(photoPost) + ": " + photoPost.display() + "\n";
        }

        if (str.isEmpty()){
            return "No Photo Posts";
        }
        else {
            return str;
        }
    }

    public String showMessagePosts() {
        String str = "";

        for(MessagePost messagePost : messagePosts) {
            str += messagePosts.indexOf(messagePost) + ": " + messagePost.display() + "\n";
        }

        if (str.isEmpty()){
            return "No Message Posts";
        }
        else {
            return str;
        }
    }

    public MessagePost deleteMessagePost(int indexToDelete) {
        if (isValidMessagePostIndex(indexToDelete)) {
            return messagePosts.remove(indexToDelete);
        }
        return null;
    }

    public PhotoPost deletePhotoPost(int indexToDelete) {
        if (isValidPhotoPostIndex(indexToDelete)) {
            return photoPosts.remove(indexToDelete);
        }
        return null;
    }

    public boolean updateMessagePost(int indexToUpdate, String author, String message) {
        //find the object by the index number
        MessagePost foundMessage = findMessagePost(indexToUpdate);

        //if the object exists, use the details passed in the parameters to
        //update the found object in the ArrayList.
        if (foundMessage != null) {
            foundMessage.setAuthor(author);
            foundMessage.setMessage(message);
            return true;
        }

        //if the object was not found, return false, indicating that the update was not successful
        return false;
    }

    public boolean updatePhotoPost(int indexToUpdate, String author, String caption, String filename) {
        //find the object by the index number
        PhotoPost foundPost = findPhotoPost(indexToUpdate);

        //if the object exists, use the details passed in the parameters to
        //update the found object in the ArrayList.
        if (foundPost != null) {
            foundPost.setAuthor(author);
            foundPost.setCaption(caption);
            foundPost.setFilename(filename);
            return true;
        }

        //if the object was not found, return false, indicating that the update was not successful
        return false;
    }

    public MessagePost findMessagePost(int index) {
        if (isValidMessagePostIndex(index)) {
            return messagePosts.get(index);
        }
        return null;
    }

    public PhotoPost findPhotoPost(int index) {
        if (isValidPhotoPostIndex(index)) {
            return photoPosts.get(index);
        }
        return null;
    }

    public int numberOfMessagePosts() {
        return messagePosts.size();
    }

    public int numberOfPhotoPosts() {
        return photoPosts.size();
    }

    /**
     * The load method uses the XStream component to read all the models.MessagePost objects from the posts.xml
     * file stored on the hard disk.  The read objects are loaded into the posts ArrayList
     *
     * @throws Exception  An exception is thrown if an error occurred during the load e.g. a missing file.
     */
    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        //list of classes that you wish to include in the serialisation, separated by a comma
        Class<?>[] classes = new Class[] { MessagePost.class, PhotoPost.class};

        //setting up the xstream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        //doing the actual serialisation to an XML file
        ObjectInputStream inMessages = xstream.createObjectInputStream(new FileReader("messagePosts.xml"));
        messagePosts = (ArrayList<MessagePost>) inMessages.readObject();
        inMessages.close();

        ObjectInputStream inPhotos = xstream.createObjectInputStream(new FileReader("photoPosts.xml"));
        photoPosts = (ArrayList<PhotoPost>) inPhotos.readObject();
        inPhotos.close();
    }

    /**
     * The save method uses the XStream component to write all the objects in the posts ArrayList
     * to the posts.xml file stored on the hard disk.
     *
     * @throws Exception  An exception is thrown if an error occurred during the save e.g. drive is full.
     */
    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream outMessages = xstream.createObjectOutputStream(new FileWriter("messagePosts.xml"));
        outMessages.writeObject(messagePosts);
        outMessages.close();
        ObjectOutputStream outPosts = xstream.createObjectOutputStream(new FileWriter("photoPosts.xml"));
        outPosts.writeObject(photoPosts);
        outPosts.close();
    }

    public boolean isValidMessagePostIndex(int index) {
        return (index >= 0) && (index < messagePosts.size());
    }

    public boolean isValidPhotoPostIndex(int index) {
        return (index >= 0) && (index < photoPosts.size());
    }

}
