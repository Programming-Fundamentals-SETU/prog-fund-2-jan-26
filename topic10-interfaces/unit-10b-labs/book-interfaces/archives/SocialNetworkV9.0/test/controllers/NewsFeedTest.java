package controllers;

import models.EventPost;
import models.MessagePost;
import models.PhotoPost;
import models.Post;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NewsFeedTest {

    private MessagePost messagePostBelow, messagePostExact, messagePostAbove, messagePostZero;
    private EventPost eventPostBelow, eventPostExact, eventPostAbove, eventPostZero;
    private PhotoPost photoPostBelow, photoPostExact, photoPostAbove, photoPostZero;
    private NewsFeed newsFeed = new NewsFeed();
    private NewsFeed emptyNewsFeed = new NewsFeed();

    @BeforeEach
    void setUp() {
        //author 9 chars, message 39 chars
        messagePostBelow = new MessagePost("Mairead M", "Programming fundamentals assignment due");
        //author 10 chars, message 40 chars
        messagePostExact = new MessagePost("SiobhanDro", "Objects and Classes Lecture starting now");
        //author 11 chars, message 41 chars
        messagePostAbove = new MessagePost("SiobhanRoch", "Computing and Maths Centre open from 9am.");
        //author 0 chars, message 0 chars
        messagePostZero = new MessagePost("", "");

        //Event - author (max 10), eventname (max 35) event cost (0 - 99999)
        //author 9 chars, event 34 chars
        eventPostBelow = new EventPost("Mairead M", "Programming Hackathon : Room IT101", -1);
        //author 10 chars, event 35 chars
        eventPostExact = new EventPost("SiobhanDro", "Programming Fundamentals Quiz Night", 99999);
        //author 11 chars, event 36 chars
        eventPostAbove = new EventPost("SiobhanRoch", "Programming Fundamentals Study Group", 100000);
        //author 0 chars, event 0 chars, cost 0
        eventPostZero = new EventPost("", "", 0);

        //Photo - author (max 10), caption (max 100) filename (40)
        //author 9 chars, caption 99 chars, filename 39
        photoPostBelow = new PhotoPost("Mairead M",
                "Programming Hackathon : Room IT101 : group photo with all participants from BSc (Hons) Applied Yr 1",
                "Hackathon IT101-BSc (Hons) Applied Yr 1");
        //author 10 chars, caption 100 chars, filename 40
        photoPostExact = new PhotoPost("SiobhanDro",
                "Programming Fundamentals Quiz Night 2021- Podium photo of the winning team BSc (Hons) Applied Year 1",
                "Prog Fund Quiz 2021-Applied Winning Team");
        //author 11 chars, caption 101 chars, filename 41
        photoPostAbove = new PhotoPost("SiobhanRoch",
                "Programming Fundamentals Study Group - multiple groups hard at work on the morning of day 1, Sep 2021",
                "Programming Fundamentals Study Group 2021");
        //author 0 chars, caption 0 chars, filename 0 chars
        photoPostZero = new PhotoPost("", "", "");

        //9 - add all objects above except messagePostAbove, photoPostBelow, eventPostExact
        newsFeed.addPost(messagePostBelow);
        newsFeed.addPost(messagePostExact);
        newsFeed.addPost(eventPostBelow);
        newsFeed.addPost(photoPostExact);
        newsFeed.addPost(messagePostZero);
        newsFeed.addPost(photoPostAbove);
        newsFeed.addPost(eventPostAbove);
        newsFeed.addPost(eventPostZero);
        newsFeed.addPost(photoPostZero);
    }

    @AfterEach
    void tearDown(){
        messagePostBelow = messagePostExact = messagePostAbove = messagePostZero = null;
        eventPostBelow = eventPostExact = eventPostAbove = eventPostZero = null;
        photoPostBelow = photoPostExact = photoPostAbove = photoPostZero = null;
        newsFeed = emptyNewsFeed = null;
    }

    @Nested
    class GettersSetters{
        @Test
        void gettingPostListReturnsList() {
            List<Post> testPosts = new ArrayList<>();
            testPosts.add(messagePostBelow);
            testPosts.add(messagePostExact);
            testPosts.add(eventPostBelow);
            testPosts.add(photoPostExact);
            testPosts.add(messagePostZero);
            testPosts.add(photoPostAbove);
            testPosts.add(eventPostAbove);
            testPosts.add(eventPostZero);
            testPosts.add(photoPostZero);
            assertEquals(testPosts, newsFeed.getPosts());

            assertEquals(new ArrayList<Post>(),emptyNewsFeed.getPosts());
        }

        @Test
        void settingPostListReplacesList() {
            List<Post> testPosts = new ArrayList<>();
            testPosts.add(messagePostBelow);
            testPosts.add(messagePostExact);
            testPosts.add(eventPostBelow);

            assertEquals(9, newsFeed.numberOfPosts());
            newsFeed.setPosts(testPosts);
            assertEquals(testPosts, newsFeed.getPosts());
            assertEquals(3, newsFeed.numberOfPosts());

            emptyNewsFeed.setPosts(new ArrayList<Post>());
            assertEquals(0,emptyNewsFeed.numberOfPosts());
        }
    }

    @Nested
    class ArrayListCRUD {

        @Test
        void addingAPostAddsToArrayList() {
            assertEquals(9, newsFeed.numberOfPosts());

            //testing MessagePost object
            assertTrue(newsFeed.addPost(messagePostAbove));
            assertEquals(messagePostAbove, newsFeed.findPost(newsFeed.numberOfPosts() - 1));

            //testing PhotoPost object
            assertTrue(newsFeed.addPost(photoPostBelow));
            assertEquals(photoPostBelow, newsFeed.findPost(newsFeed.numberOfPosts() - 1));

            //testing EventPost object
            assertTrue(newsFeed.addPost(eventPostExact));
            assertEquals(eventPostExact, newsFeed.findPost(newsFeed.numberOfPosts() - 1));

            //testing empty arraylist
            assertEquals(0, emptyNewsFeed.numberOfPosts());
            assertTrue(emptyNewsFeed.addPost(eventPostExact));
            assertEquals(eventPostExact, emptyNewsFeed.findPost(emptyNewsFeed.numberOfPosts() - 1));
        }

        @Test
        void updatingAPostThatDoesNotExistReturnsFalse() {
            //testing arraylist with items
            assertFalse(newsFeed.updateMessagePost(9, "Updating Message", "Work"));
            assertFalse(newsFeed.updateEventPost(-1, "Updating Event", "No update", 1));
            assertFalse(newsFeed.updatePhotoPost(10, "Updating Photo", "No update", "No file"));

            //testing empty arraylist
            assertFalse(emptyNewsFeed.updateMessagePost(0, "Updating Note", "Work"));
        }

        @Test
        void updatingAMessagePostThatExistsReturnsTrueAndUpdates() {
            //check messagePost, index 1 exists and verify object
            MessagePost foundPost = (MessagePost) newsFeed.findPost(1);
            assertEquals(messagePostExact, foundPost);

            //update messagePost, index 1 exists and check the contents
            assertTrue(newsFeed.updateMessagePost(1, "NewAuthor", "Updated Message"));
            MessagePost updatedPost = (MessagePost) newsFeed.findPost(1);
            assertEquals("NewAuthor", updatedPost.getAuthor());
            assertEquals("Updated Message", updatedPost.getMessage());
        }

        @Test
        void updatingAnEventPostThatExistsReturnsTrueAndUpdates() {
            //check post, index 2 exists and verify object
            EventPost foundPost = (EventPost) newsFeed.findPost(2);
            assertEquals(eventPostBelow, foundPost);

            //update post, index 2 exists and check the contents
            assertTrue(newsFeed.updateEventPost(2, "New Author",
                    "Updated Event", 5));
            EventPost updatedPost = (EventPost) newsFeed.findPost(2);
            assertEquals("New Author", updatedPost.getAuthor());
            assertEquals("Updated Event", updatedPost.getEventName());
            assertEquals(5, updatedPost.getEventCost());
        }

        @Test
        void updatingAPhotoPostThatExistsReturnsTrueAndUpdates() {
            //check post, index 3 exists and verify object
            PhotoPost foundPost = (PhotoPost) newsFeed.findPost(3);
            assertEquals(photoPostExact, foundPost);

            //update post, index 3 exists and check the contents
            assertTrue(newsFeed.updatePhotoPost(3, "Updating Author",
                    "Updated Caption", "Update Filename"));
            PhotoPost updatedPost = (PhotoPost) newsFeed.findPost(3);
            assertEquals("SiobhanDro", updatedPost.getAuthor());
            assertEquals("Updated Caption", updatedPost.getCaption());
            assertEquals("Update Filename", updatedPost.getFilename());
        }

        @Test
        void deletingAPostThatDoesNotExistReturnsNull(){
            assertNull(emptyNewsFeed.deletePost(0));
            assertNull(newsFeed.deletePost(-1));
            assertNull(newsFeed.deletePost(newsFeed.numberOfPosts()));
        }

        @Test
        void deletingAPostThatExistsDeletesAndReturnsDeletedObject(){
            //deleting a post at the start of the arraylist
            assertEquals(9, newsFeed.numberOfPosts());
            assertEquals(messagePostBelow, newsFeed.deletePost(0));
            assertEquals(8, newsFeed.numberOfPosts());

            //deleting a post at the end of the arraylist
            assertEquals(8, newsFeed.numberOfPosts());
            assertEquals(photoPostZero, newsFeed.deletePost(7));
            assertEquals(7, newsFeed.numberOfPosts());
        }
    }

    @Nested
    class CountingMethods {

        @Test
        void numberOfPostsCalculatedCorrectly() {
            assertEquals(9, newsFeed.numberOfPosts());
            assertEquals(0, emptyNewsFeed.numberOfPosts());
        }

        @Test
        void numberOfEventPostsCalculatedCorrectly() {
            assertEquals(3, newsFeed.numberOfEventPosts());
            assertEquals(0, emptyNewsFeed.numberOfPosts());
        }

        @Test
        void numberOfMessagePostsCalculatedCorrectly() {
            assertEquals(3, newsFeed.numberOfMessagePosts());
            assertEquals(0, emptyNewsFeed.numberOfPosts());
        }

        @Test
        void numberOfPhotoostsCalculatedCorrectly() {
            assertEquals(3, newsFeed.numberOfPhotoPosts());
            assertEquals(0, emptyNewsFeed.numberOfPosts());
        }
    }

    @Nested
    class LikingUnliking {

        @Test
        void likingAnExistingPostIncreasesTheLikesByOne() {
            //check post, index 3 exists and verify the likes
            PhotoPost foundPost = (PhotoPost) newsFeed.findPost(3);
            assertEquals(0, foundPost.getLikes());
            newsFeed.likeAPost(3);
            assertEquals(1, foundPost.getLikes());

            //liking a post that doesn't exist doesn't cause an error
            assertEquals(null, newsFeed.findPost(newsFeed.numberOfPosts()));
            newsFeed.likeAPost(newsFeed.numberOfPosts());
        }

        @Test
        void unLikingAnExistingPostDecreasesTheLikesByOne() {
            //check post, index 3 exists and update likes to 2.
            PhotoPost foundPost = (PhotoPost) newsFeed.findPost(3);
            assertEquals(0, foundPost.getLikes());
            newsFeed.likeAPost(3);
            newsFeed.likeAPost(3);
            assertEquals(2, foundPost.getLikes());

            //unlike 3 times and verify likes (should not drop below zero)
            newsFeed.unLikeAPost(3);
            assertEquals(1, foundPost.getLikes());
            newsFeed.unLikeAPost(3);
            assertEquals(0, foundPost.getLikes());
            newsFeed.unLikeAPost(3);
            assertEquals(0, foundPost.getLikes());

            //unliking a post that doesn't exist doesn't cause an error
            assertEquals(null, newsFeed.findPost(newsFeed.numberOfPosts()));
            newsFeed.unLikeAPost(newsFeed.numberOfPosts());
        }

    }

    @Nested
    class ListingMethods {

        @Test
        void showReturnsNoPostsStoredWhenArrayListIsEmpty() {
            assertEquals(0, emptyNewsFeed.numberOfPosts());
            assertTrue(emptyNewsFeed.show().toLowerCase().contains("no posts"));
        }

        @Test
        void showReturnsPostsWhenArrayListHasPostsStored() {
            assertEquals(9, newsFeed.numberOfPosts());
            String posts = newsFeed.show();
            //checks for some objects in the string
            assertTrue(posts.contains("Programming fundamentals assignment due"));
            assertTrue(posts.contains("Objects and Classes Lecture starting now"));
            assertTrue(posts.contains("Programming Hackathon : Room IT101"));
            assertTrue(posts.contains("Programming Fundamentals Quiz Night"));
            assertTrue(posts.contains("Programming Fundamentals Study Group"));
            assertTrue(posts.contains("Prog Fund Quiz 2021-Applied Winning Team"));
        }

        @Test
        void showMessagesReturnsNoPostsStoredWhenArrayListIsEmpty() {
            assertEquals(0, emptyNewsFeed.numberOfMessagePosts());
            assertTrue(emptyNewsFeed.showMessagePosts().toLowerCase().contains("no message posts"));
        }

        @Test
        void showMessagesReturnsPostsWhenArrayListHasPostStored() {
            assertEquals(3, newsFeed.numberOfMessagePosts());
            String posts = newsFeed.showMessagePosts();
            //checks for the objects in the string
            assertTrue(posts.contains("Programming fundamentals assignment due"));
            assertTrue(posts.contains("Objects and Classes Lecture starting now"));
            assertFalse(posts.contains("Programming Hackathon : Room IT101"));
            assertFalse(posts.contains("Programming Fundamentals Quiz Night"));
            assertFalse(posts.contains("Programming Fundamentals Study Group"));
            assertFalse(posts.contains("Prog Fund Quiz 2021-Applied Winning Team"));
        }

        @Test
        void showPhotosReturnsNoPostsStoredWhenArrayListIsEmpty() {
            assertEquals(0, emptyNewsFeed.numberOfPhotoPosts());
            assertTrue(emptyNewsFeed.showPhotoPosts().toLowerCase().contains("no photo posts"));
        }

        @Test
        void showPhotosReturnsPostsWhenArrayListHasPostStored() {
            assertEquals(3, newsFeed.numberOfPhotoPosts());
            String posts = newsFeed.showPhotoPosts();
            //checks for the objects in the string
            assertFalse(posts.contains("Programming fundamentals assignment due"));
            assertFalse(posts.contains("Objects and Classes Lecture starting now"));
            assertFalse(posts.contains("Programming Hackathon : Room IT101"));
            assertTrue(posts.contains("Programming Fundamentals Study Group"));
            assertTrue(posts.contains("Prog Fund Quiz 2021-Applied Winning Team"));
        }

        @Test
        void showEventReturnsNoPostsStoredWhenArrayListIsEmpty() {
            assertEquals(0, emptyNewsFeed.numberOfEventPosts());
            assertTrue(emptyNewsFeed.showEventPosts().toLowerCase().contains("no event posts"));
        }

        @Test
        void showEventReturnsPostsWhenArrayListHasPostStored() {
            assertEquals(3, newsFeed.numberOfEventPosts());
            String posts = newsFeed.showEventPosts();
            //checks for the objects in the string
            assertFalse(posts.contains("Programming fundamentals assignment due"));
            assertFalse(posts.contains("Objects and Classes Lecture starting now"));
            assertFalse(posts.contains("Programming Fundamentals Quiz Night"));
            assertTrue(posts.contains("Programming Hackathon : Room IT101"));
            assertTrue(posts.contains("Programming Fundamentals Study"));
        }
    }

    @Nested
    class FindingMethods {

        @Test
        void findPostReturnsPostWhenIndexIsValid() {
            assertEquals(9, newsFeed.numberOfPosts());
            assertEquals(messagePostBelow, newsFeed.findPost(0));
            assertEquals(photoPostZero, newsFeed.findPost(8));
        }

        @Test
        void findPostReturnsNullWhenIndexIsInValid() {
            assertEquals(0, emptyNewsFeed.numberOfPosts());
            assertNull(emptyNewsFeed.findPost(0));

            assertEquals(9, newsFeed.numberOfPosts());
            assertNull(newsFeed.findPost(-1));
            assertNull(newsFeed.findPost(9));
        }
    }

    @Nested
    class HelperMethods {
        @Test
        void isValidIndexReturnsTrueForValidIndex() {
            assertTrue(newsFeed.isValidIndex(0));
            assertTrue(newsFeed.isValidIndex(newsFeed.numberOfPosts() -1));
        }

        @Test
        void isValidMessageIndexReturnsTrueForValidIndex() {
            assertTrue(newsFeed.isValidIndex(0));
            assertTrue(newsFeed.isValidIndex(4));
        }

        @Test
        void isValidPhotoIndexReturnsTrueForValidIndex() {
            assertTrue(newsFeed.isValidIndex(3));
            assertTrue(newsFeed.isValidIndex(8));
        }

        @Test
        void isValidEventIndexReturnsTrueForValidIndex() {
            assertTrue(newsFeed.isValidIndex(2));
            assertTrue(newsFeed.isValidIndex(7));
        }

        @Test
        void isValidIndexReturnsFalseForInValidIndex() {
            assertFalse(emptyNewsFeed.isValidIndex(0));
            assertFalse(emptyNewsFeed.isValidIndex(1));

            assertFalse(newsFeed.isValidIndex(-1));
            assertFalse(newsFeed.isValidIndex(newsFeed.numberOfPosts()));
        }


        @Test
        void isValidMessageIndexReturnsFalseForInValidIndex() {
            assertFalse(emptyNewsFeed.isValidMessagePostIndex(0));
            assertFalse(emptyNewsFeed.isValidMessagePostIndex(1));

            assertFalse(newsFeed.isValidMessagePostIndex(-1));
            assertFalse(newsFeed.isValidMessagePostIndex(3));  //photopost
        }

        @Test
        void isValidPhotoIndexReturnsFalseForInValidIndex() {
            assertFalse(emptyNewsFeed.isValidPhotoPostIndex(0));
            assertFalse(emptyNewsFeed.isValidPhotoPostIndex(1));

            assertFalse(newsFeed.isValidPhotoPostIndex(-1));
            assertFalse(newsFeed.isValidPhotoPostIndex(1));  //messagepost
        }

        @Test
        void isValidEventIndexReturnsFalseForInValidIndex() {
            assertFalse(emptyNewsFeed.isValidEventPostIndex(0));
            assertFalse(emptyNewsFeed.isValidEventPostIndex(1));

            assertFalse(newsFeed.isValidEventPostIndex(-1));
            assertFalse(newsFeed.isValidEventPostIndex(1));  //messagepost
        }
    }

}
