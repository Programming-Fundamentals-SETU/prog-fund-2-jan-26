package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import utils.CategoryUtility;

import static org.junit.jupiter.api.Assertions.*;

public class EducationAppTest {

    private EducationApp edAppBelowBoundary, edAppOnBoundary, edAppAboveBoundary, edAppInvalidData;
    private Developer developerLego = new Developer("Lego", "www.lego.com");
    private Developer developerSphero = new Developer("Sphero", "www.sphero.com");

    @BeforeEach
    void setUp() {
        //Validation: appSize(1-1000), appVersion(>=1.0), ageRating (0-18), appCost(>=0), level(1-10).
        edAppBelowBoundary = new EducationApp(developerLego, "WeDo", 1, 1.0, 0, false,
                "Noncommercial", 0, CategoryUtility.getDefaultSubjectCategory(), 1);

        edAppOnBoundary = new EducationApp(developerLego, "Spike", 1000, 2.0, 18, true,
                "Proprietary", 1.99, "Technology", 10);

        edAppAboveBoundary = new EducationApp(developerLego, "EV3", 1001, 3.5, 19, true,
                "Trade secret", 2.99, "Business", 11);

        edAppInvalidData = new EducationApp(developerLego, "", -1, 0, -1, false,
                "", -1.00, "", 0);
    }

    @AfterEach
    void tearDown() {
        edAppBelowBoundary = edAppOnBoundary = edAppAboveBoundary = edAppInvalidData = null;
        developerLego = developerSphero = null;
    }

    @Nested
    class Getters {

        @Test
        void getDeveloper() {
            assertEquals(developerLego, edAppBelowBoundary.getDeveloper());
            assertEquals(developerLego, edAppOnBoundary.getDeveloper());
            assertEquals(developerLego, edAppAboveBoundary.getDeveloper());
            assertEquals(developerLego, edAppInvalidData.getDeveloper());
        }

        @Test
        void getAppName() {
            assertEquals("WeDo", edAppBelowBoundary.getAppName());
            assertEquals("Spike", edAppOnBoundary.getAppName());
            assertEquals("EV3", edAppAboveBoundary.getAppName());
            assertEquals("", edAppInvalidData.getAppName());
        }

        @Test
        void getAppSize() {
            assertEquals(1, edAppBelowBoundary.getAppSize());
            assertEquals(1000, edAppOnBoundary.getAppSize());
            assertEquals(0, edAppAboveBoundary.getAppSize());
            assertEquals(0, edAppInvalidData.getAppSize());
        }

        @Test
        void getAppVersion() {
            assertEquals(1.0, edAppBelowBoundary.getAppVersion());
            assertEquals(2.0, edAppOnBoundary.getAppVersion());
            assertEquals(3.5, edAppAboveBoundary.getAppVersion());
            assertEquals(1.0, edAppInvalidData.getAppVersion());
        }

        @Test
        void getAppAgeRating() {
            assertEquals(0, edAppBelowBoundary.getAgeRating());
            assertEquals(18, edAppOnBoundary.getAgeRating());
            assertEquals(0, edAppAboveBoundary.getAgeRating());
            assertEquals(0, edAppInvalidData.getAgeRating());
        }

        @Test
        void isInAppPurchase() {
            assertFalse(edAppBelowBoundary.isInAppPurchase());
            assertTrue(edAppOnBoundary.isInAppPurchase());
            assertTrue(edAppAboveBoundary.isInAppPurchase());
            assertFalse(edAppInvalidData.isInAppPurchase());
        }

        @Test
        void getLicense() {
            assertEquals("Noncommercial", edAppBelowBoundary.getLicense());
            assertEquals("Proprietary", edAppOnBoundary.getLicense());
            assertEquals("Trade secret", edAppAboveBoundary.getLicense());
            assertEquals("Proprietary", edAppInvalidData.getLicense());
        }

        @Test
        void getAppCost() {
            assertEquals(0, edAppBelowBoundary.getAppCost());
            assertEquals(1.99, edAppOnBoundary.getAppCost());
            assertEquals(2.99, edAppAboveBoundary.getAppCost());
            assertEquals(0, edAppInvalidData.getAppCost());
        }

        @Test
        void getCategory() {
            assertEquals("General", edAppBelowBoundary.getCategory());
            assertEquals("Technology", edAppOnBoundary.getCategory());
            assertEquals("Business", edAppAboveBoundary.getCategory());
            assertEquals("General", edAppInvalidData.getCategory());
        }

        @Test
        void getLevel() {
            assertEquals(1, edAppBelowBoundary.getLevel());
            assertEquals(10, edAppOnBoundary.getLevel());
            assertEquals(0, edAppAboveBoundary.getLevel());
            assertEquals(0, edAppInvalidData.getLevel());
        }

    }

    @Nested
    class Setters {

        @Test
        void setDeveloper() {
            //no validation in models
            assertEquals(developerLego, edAppBelowBoundary.getDeveloper());
            edAppBelowBoundary.setDeveloper(developerSphero);
            assertEquals(developerSphero, edAppBelowBoundary.getDeveloper());
        }

        @Test
        void setAppName() {
            //no validation in models
            assertEquals("WeDo", edAppBelowBoundary.getAppName());
            edAppBelowBoundary.setAppName("Mindstorms");
            assertEquals("Mindstorms", edAppBelowBoundary.getAppName());
        }

        @Test
        void setAppSize() {
            //Validation: appSize(1-1000)
            assertEquals(1, edAppBelowBoundary.getAppSize());

            edAppBelowBoundary.setAppSize(1000);
            assertEquals(1000, edAppBelowBoundary.getAppSize()); //update

            edAppBelowBoundary.setAppSize(1001);
            assertEquals(1000, edAppBelowBoundary.getAppSize()); //no update

            edAppBelowBoundary.setAppSize(2);
            assertEquals(2, edAppBelowBoundary.getAppSize()); //update

            edAppBelowBoundary.setAppSize(0);
            assertEquals(2, edAppBelowBoundary.getAppSize()); //no update
        }

        @Test
        void setAppVersion() {
            //Validation: appVersion(>=1.0)
            assertEquals(1.0, edAppBelowBoundary.getAppVersion());

            edAppBelowBoundary.setAppVersion(2.0);
            assertEquals(2.0, edAppBelowBoundary.getAppVersion()); //update

            edAppBelowBoundary.setAppVersion(0.0);
            assertEquals(2.0, edAppBelowBoundary.getAppVersion()); //no update

            edAppBelowBoundary.setAppVersion(1.0);
            assertEquals(1.0, edAppBelowBoundary.getAppVersion()); //update
        }

        @Test
        void setAgeRating() {
            //Validation: ageRating (0-18)
            assertEquals(0, edAppBelowBoundary.getAgeRating());

            edAppBelowBoundary.setAgeRating(18);
            assertEquals(18, edAppBelowBoundary.getAgeRating()); //update

            edAppBelowBoundary.setAgeRating(19);
            assertEquals(18, edAppBelowBoundary.getAgeRating()); //no update

            edAppBelowBoundary.setAgeRating(0);
            assertEquals(0, edAppBelowBoundary.getAgeRating()); //update
        }

        @Test
        void setInAppPurchase() {
            //no validation in models
            assertFalse(edAppBelowBoundary.isInAppPurchase());
            edAppBelowBoundary.setInAppPurchase(true);
            assertTrue(edAppBelowBoundary.isInAppPurchase());
            edAppBelowBoundary.setInAppPurchase(false);
            assertFalse(edAppBelowBoundary.isInAppPurchase());
        }

        @Test
        void setAppCost() {
            //Validation: appCost(>=0)
            assertEquals(0.0, edAppBelowBoundary.getAppCost());

            edAppBelowBoundary.setAppCost(1.0);
            assertEquals(1.0, edAppBelowBoundary.getAppCost()); //update

            edAppBelowBoundary.setAppCost(-1);
            assertEquals(1.0, edAppBelowBoundary.getAppCost()); //no update

            edAppBelowBoundary.setAppCost(0.0);
            assertEquals(0.0, edAppBelowBoundary.getAppCost()); //update
        }

        @Test
        void setLevel() {
            //Validation: level(1-10)
            assertEquals(1, edAppBelowBoundary.getLevel());

            edAppBelowBoundary.setLevel(10);
            assertEquals(10, edAppBelowBoundary.getLevel()); //update

            edAppBelowBoundary.setLevel(11);
            assertEquals(10, edAppBelowBoundary.getLevel()); //no update

            edAppBelowBoundary.setLevel(1);
            assertEquals(1, edAppBelowBoundary.getLevel()); //update
        }

        @Test
        void setCategory() {
            //Validation: against CategoryUtility class
            assertEquals("General", edAppBelowBoundary.getCategory());

            edAppBelowBoundary.setCategory("Maths");
            assertEquals("Maths", edAppBelowBoundary.getCategory()); //update

            edAppBelowBoundary.setCategory("Nonsense");
            assertEquals("Maths", edAppBelowBoundary.getCategory()); //no update
        }

        @Test
        void setLicense() {
            //Validation: against LicenseUtility class
            assertEquals("Noncommercial", edAppBelowBoundary.getLicense());

            edAppBelowBoundary.setLicense("Trade Secret");
            assertEquals("Trade Secret", edAppBelowBoundary.getLicense()); //update

            edAppBelowBoundary.setLicense("Nonsense");
            assertEquals("Trade Secret", edAppBelowBoundary.getLicense()); //no update
        }

    }

    @Nested
    class ObjectStateMethods {

        @Test
        void appSummaryReturnsCorrectString() {
            EducationApp edApp = setupEducationAppWithRatingAndDownloads(5, 3, 4);
            String stringContents = edApp.appSummary();

            assertTrue(stringContents.contains("level " + edApp.getLevel()));
            assertTrue(stringContents.contains(edApp.getAppName() + "(V" + edApp.getAppVersion()));
            assertTrue(stringContents.contains(edApp.getDeveloper().toString()));
            assertTrue(stringContents.contains("€" + edApp.getAppCost()));
            assertTrue(stringContents.contains("Downloads: " + edApp.getNumberOfDownloads()));
            assertTrue(stringContents.contains("Rating: " + edApp.calculateRating()));
        }

        @Test
        void toStringReturnsCorrectString() {
            EducationApp edApp = setupEducationAppWithRatingAndDownloads(5, 3, 4);
            String stringContents = edApp.toString();

            assertTrue(stringContents.contains(edApp.getAppName()));
            assertTrue(stringContents.contains("(Version " + edApp.getAppVersion()));
            assertTrue(stringContents.contains(edApp.getDeveloper().toString()));
            assertTrue(stringContents.contains("Age Rating " + edApp.getAgeRating()));
            assertTrue(stringContents.contains(edApp.getAppSize() + "MB"));
            assertTrue(stringContents.contains("In App Purchase: N"));
            assertTrue(stringContents.contains("Noncommercial"));
            assertTrue(stringContents.contains("Cost: " + edApp.getAppCost()));
            assertTrue(stringContents.contains("Downloads: " + edApp.getNumberOfDownloads()));
            assertTrue(stringContents.contains("Level: " + edApp.getLevel()));
            assertTrue(stringContents.contains("Maths"));
            assertTrue(stringContents.contains("Ratings (" + edApp.calculateRating()));

            //contains list of ratings too
            assertTrue(stringContents.contains("John Doe"));
            assertTrue(stringContents.contains("Very Good"));
            assertTrue(stringContents.contains("Jane Doe"));
            assertTrue(stringContents.contains("Excellent"));
        }

    }

    @Nested
    class RecommendedApp {

        // Algorithm - app is recommended if the following applies:
        //  inAppPurchase is false;  appCost is > .99;  numberofDownloads >= 5; rating of >= 3.5; category is not General

        @Test
        void appIsNotRecommendedWhenInAppPurchasesIsTrue() {
            //setting all conditions to true with downloads of 5, ratings of 3 and 4 (i.e. 3.5)
            EducationApp edApp = setupEducationAppWithRatingAndDownloads(5, 3, 4);
            edApp.setInAppPurchase(true);    //setting inAppPurchases to true

            //verifying all conditions are true
            assertTrue(edApp.isInAppPurchase());
            assertEquals(1.0, edApp.getAppCost(), 0.01);
            assertEquals(5, edApp.getNumberOfDownloads());
            assertEquals(3.5, edApp.calculateRating(), 0.01);
            assertNotEquals(CategoryUtility.getDefaultSubjectCategory(), edApp.getCategory());

            //verifying recommended app returns true
            assertFalse(edApp.isRecommendedApp());
        }

        @Test
        void appIsNotRecommendedWhenInAppCostIs99c() {
            //setting all conditions to true with downloads of 5, ratings of 3 and 4 (i.e. 3.5)
            EducationApp edApp = setupEducationAppWithRatingAndDownloads(5, 3, 4);
            edApp.setAppCost(0.99);   //setting appCost to 0.99

            //verifying all conditions are true
            assertFalse(edApp.isInAppPurchase());
            assertEquals(0.99, edApp.getAppCost(), 0.01);
            assertEquals(5, edApp.getNumberOfDownloads());
            assertEquals(3.5, edApp.calculateRating(), 0.01);
            assertNotEquals(CategoryUtility.getDefaultSubjectCategory(), edApp.getCategory());

            //verifying recommended app returns true
            assertFalse(edApp.isRecommendedApp());
        }

        @Test
        void appIsNotRecommendedWhenNumDownloadsLessThan5() {

            //setting all conditions to true with downloads of 4, ratings of 3 and 4 (i.e. 3.5)
            EducationApp edApp = setupEducationAppWithRatingAndDownloads(4, 3, 4);

            //verifying all conditions
            assertFalse(edApp.isInAppPurchase());
            assertEquals(1.0, edApp.getAppCost(), 0.01);
            assertEquals(4, edApp.getNumberOfDownloads());
            assertEquals(3.5, edApp.calculateRating(), 0.01);
            assertNotEquals(CategoryUtility.getDefaultSubjectCategory(), edApp.getCategory());

            //verifying recommended app returns true
            assertFalse(edApp.isRecommendedApp());
        }

        @Test
        void appIsNotRecommendedWhenRatingIsLessThan3AndAHalf() {
            //setting all conditions to true with downloads of 5, ratings of 3 and 3 (i.e. 3.0)
            EducationApp edApp = setupEducationAppWithRatingAndDownloads(5, 3, 3);

            //verifying all conditions
            assertFalse(edApp.isInAppPurchase());
            assertEquals(1.0, edApp.getAppCost(), 0.01);
            assertEquals(5, edApp.getNumberOfDownloads());
            assertEquals(3.0, edApp.calculateRating(), 0.01);
            assertNotEquals(CategoryUtility.getDefaultSubjectCategory(), edApp.getCategory());

            //verifying recommended app returns true
            assertFalse(edApp.isRecommendedApp());
        }

        @Test
        void appIsNotRecommendedWhenCategoryIsGeneral() {

            //setting all conditions to true with downloads of 5, ratings of 3 and 4 (i.e. 3.5)
            EducationApp edApp = setupEducationAppWithRatingAndDownloads(5, 3, 4);
            edApp.setCategory(CategoryUtility.getDefaultSubjectCategory());   //setting to General

            //verifying all conditions
            assertFalse(edApp.isInAppPurchase());
            assertEquals(1.0, edApp.getAppCost(), 0.01);
            assertEquals(5, edApp.getNumberOfDownloads());
            assertEquals(3.5, edApp.calculateRating(), 0.01);
            assertEquals(CategoryUtility.getDefaultSubjectCategory(), edApp.getCategory());

            //verifying recommended app returns true
            assertFalse(edApp.isRecommendedApp());
        }

        @Test
        void appIsNotRecommendedWhenNoRatingsExist() {
            //setting all conditions to true with downloads of 5 but no ratings
            EducationApp edApp = new EducationApp(developerLego, "WeDo", 1,
                    1.0, 0, false, "Noncommercial",
                    1.00, "Maths", 1);
            edApp.setNumberOfDownloads(5);

            //verifying all conditions
            assertFalse(edApp.isInAppPurchase());
            assertEquals(1.0, edApp.getAppCost(), 0.01);
            assertEquals(5, edApp.getNumberOfDownloads());
            assertNotEquals(CategoryUtility.getDefaultSubjectCategory(), edApp.getCategory());

            assertEquals(0, edApp.getRatings().size());
            assertEquals(0, edApp.calculateRating(), 0.01);

            //verifying recommended app returns true
            assertFalse(edApp.isRecommendedApp());
        }

        @Test
        void appIsRecommendedWhenAllOfTheConditionsAreTrue() {
            //setting all conditions to true with downloads of 5, ratings of 3 and 4 (i.e. 3.5)
            EducationApp edApp = setupEducationAppWithRatingAndDownloads(5, 3, 4);

            //verifying all conditions are true
            assertFalse(edApp.isInAppPurchase());
            assertEquals(1.0, edApp.getAppCost(), 0.01);
            assertEquals(5, edApp.getNumberOfDownloads());
            assertEquals(3.5, edApp.calculateRating(), 0.01);
            assertNotEquals(CategoryUtility.getDefaultSubjectCategory(), edApp.getCategory());

            //verifying recommended app returns true
            assertTrue(edApp.isRecommendedApp());
        }

    }

    EducationApp setupEducationAppWithRatingAndDownloads(int numberDownloads, int rating1, int rating2) {
        //setting all conditions to true
        EducationApp edApp = new EducationApp(developerLego, "WeDo", 1,
                1.0, 0, false, "Noncommercial",
                1.00, "Maths", 1);
        edApp.setNumberOfDownloads(numberDownloads);
        edApp.addRating(new Rating(rating1, "John Doe", "Very Good"));
        edApp.addRating(new Rating(rating2, "Jane Doe", "Excellent"));
        return edApp;
    }
}
