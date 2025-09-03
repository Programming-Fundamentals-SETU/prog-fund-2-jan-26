package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import utils.CategoryUtility;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {



    private EducationApp appBelowBoundary, appOnBoundary, appAboveBoundary, appInvalidData;
    private Developer developerLego = new Developer("Lego", "http://www.lego.com");
    private Developer developerSphero = new Developer("Sphero", "http://www.sphero.com");

    @BeforeEach
    void setUp() {
        //Validation: appSize(1-1000), appVersion(>=1.0), ageRating (0-18), appCost(>=0), level(1-10).
        appBelowBoundary = new EducationApp(developerLego, "WeDo", 1, 1.0, 0, false,
                "Noncommercial", 0, CategoryUtility.getDefaultSubjectCategory(), 1);

        appOnBoundary = new EducationApp(developerLego, "Spike", 1000, 2.0, 18, true,
                "Proprietary", 1.99, "Technology", 10);

        appAboveBoundary = new EducationApp(developerLego, "EV3", 1001, 3.5, 19, true,
                "Trade secret", 2.99, "Business", 11);

        appInvalidData = new EducationApp(developerLego, "", -1, 0, -1, false,
                "", -1.00, "", 0);
    }

    @AfterEach
    void tearDown() {
        appBelowBoundary = appOnBoundary = appAboveBoundary = appInvalidData = null;
        developerLego = developerSphero = null;
    }

    @Nested
    class Getters {

        @Test
        void getDeveloper() {
            assertEquals(developerLego, appBelowBoundary.getDeveloper());
            assertEquals(developerLego, appOnBoundary.getDeveloper());
            assertEquals(developerLego, appAboveBoundary.getDeveloper());
            assertEquals(developerLego, appInvalidData.getDeveloper());
        }

        @Test
        void getAppName() {
            assertEquals("WeDo", appBelowBoundary.getAppName());
            assertEquals("Spike", appOnBoundary.getAppName());
            assertEquals("EV3", appAboveBoundary.getAppName());
            assertEquals("", appInvalidData.getAppName());
        }

        @Test
        void getAppSize() {
            assertEquals(1, appBelowBoundary.getAppSize());
            assertEquals(1000, appOnBoundary.getAppSize());
            assertEquals(0, appAboveBoundary.getAppSize());
            assertEquals(0, appInvalidData.getAppSize());
        }

        @Test
        void getAppVersion() {
            assertEquals(1.0, appBelowBoundary.getAppVersion());
            assertEquals(2.0, appOnBoundary.getAppVersion());
            assertEquals(3.5, appAboveBoundary.getAppVersion());
            assertEquals(1.0, appInvalidData.getAppVersion());
        }

        @Test
        void getAppAgeRating() {
            assertEquals(0, appBelowBoundary.getAgeRating());
            assertEquals(18, appOnBoundary.getAgeRating());
            assertEquals(0, appAboveBoundary.getAgeRating());
            assertEquals(0, appInvalidData.getAgeRating());
        }

        @Test
        void isInAppPurchase() {
            assertFalse(appBelowBoundary.isInAppPurchase());
            assertTrue(appOnBoundary.isInAppPurchase());
            assertTrue(appAboveBoundary.isInAppPurchase());
            assertFalse(appInvalidData.isInAppPurchase());
        }

        @Test
        void getLicense() {
            assertEquals("Noncommercial", appBelowBoundary.getLicense());
            assertEquals("Proprietary", appOnBoundary.getLicense());
            assertEquals("Trade secret", appAboveBoundary.getLicense());
            assertEquals("Proprietary", appInvalidData.getLicense());
        }

        @Test
        void getAppCost() {
            assertEquals(0, appBelowBoundary.getAppCost());
            assertEquals(1.99, appOnBoundary.getAppCost());
            assertEquals(2.99, appAboveBoundary.getAppCost());
            assertEquals(0, appInvalidData.getAppCost());
        }


        @Test
        void getLevel() {
            assertEquals(1, appBelowBoundary.getLevel());
            assertEquals(10, appOnBoundary.getLevel());
            assertEquals(0, appAboveBoundary.getLevel());
            assertEquals(0, appInvalidData.getLevel());
        }

    }

    @Nested
    class Setters {

        @Test
        void setDeveloper() {
            //no validation in models
            assertEquals(developerLego, appBelowBoundary.getDeveloper());
            appBelowBoundary.setDeveloper(developerSphero);
            assertEquals(developerSphero, appBelowBoundary.getDeveloper());
        }

        @Test
        void setAppName() {
            //no validation in models
            assertEquals("WeDo", appBelowBoundary.getAppName());
            appBelowBoundary.setAppName("Mindstorms");
            assertEquals("Mindstorms", appBelowBoundary.getAppName());
        }

        @Test
        void setAppSize() {
            //Validation: appSize(1-1000)
            assertEquals(1, appBelowBoundary.getAppSize());

            appBelowBoundary.setAppSize(1000);
            assertEquals(1000, appBelowBoundary.getAppSize()); //update

            appBelowBoundary.setAppSize(1001);
            assertEquals(1000, appBelowBoundary.getAppSize()); //no update

            appBelowBoundary.setAppSize(2);
            assertEquals(2, appBelowBoundary.getAppSize()); //update

            appBelowBoundary.setAppSize(0);
            assertEquals(2, appBelowBoundary.getAppSize()); //no update
        }

        @Test
        void setAppVersion() {
            //Validation: appVersion(>=1.0)
            assertEquals(1.0, appBelowBoundary.getAppVersion());

            appBelowBoundary.setAppVersion(2.0);
            assertEquals(2.0, appBelowBoundary.getAppVersion()); //update

            appBelowBoundary.setAppVersion(0.0);
            assertEquals(2.0, appBelowBoundary.getAppVersion()); //no update

            appBelowBoundary.setAppVersion(1.0);
            assertEquals(1.0, appBelowBoundary.getAppVersion()); //update
        }

        @Test
        void setAgeRating() {
            //Validation: ageRating (0-18)
            assertEquals(0, appBelowBoundary.getAgeRating());

            appBelowBoundary.setAgeRating(18);
            assertEquals(18, appBelowBoundary.getAgeRating()); //update

            appBelowBoundary.setAgeRating(19);
            assertEquals(18, appBelowBoundary.getAgeRating()); //no update

            appBelowBoundary.setAgeRating(0);
            assertEquals(0, appBelowBoundary.getAgeRating()); //update
        }

        @Test
        void setInAppPurchase() {
            //no validation in models
            assertFalse(appBelowBoundary.isInAppPurchase());
            appBelowBoundary.setInAppPurchase(true);
            assertTrue(appBelowBoundary.isInAppPurchase());
            appBelowBoundary.setInAppPurchase(false);
            assertFalse(appBelowBoundary.isInAppPurchase());
        }

        @Test
        void setAppCost() {
            //Validation: appCost(>=0)
            assertEquals(0.0, appBelowBoundary.getAppCost());

            appBelowBoundary.setAppCost(1.0);
            assertEquals(1.0, appBelowBoundary.getAppCost()); //update

            appBelowBoundary.setAppCost(-1);
            assertEquals(1.0, appBelowBoundary.getAppCost()); //no update

            appBelowBoundary.setAppCost(0.0);
            assertEquals(0.0, appBelowBoundary.getAppCost()); //update
        }

        @Test
        void setLevel() {
            //Validation: level(1-10)
            assertEquals(1, appBelowBoundary.getLevel());

            appBelowBoundary.setLevel(10);
            assertEquals(10, appBelowBoundary.getLevel()); //update

            appBelowBoundary.setLevel(11);
            assertEquals(10, appBelowBoundary.getLevel()); //no update

            appBelowBoundary.setLevel(1);
            assertEquals(1, appBelowBoundary.getLevel()); //update
        }



        @Test
        void setLicense() {
            //Validation: against LicenseUtility class
            assertEquals("Noncommercial", appBelowBoundary.getLicense());

            appBelowBoundary.setLicense("Trade Secret");
            assertEquals("Trade Secret", appBelowBoundary.getLicense()); //update

            appBelowBoundary.setLicense("Nonsense");
            assertEquals("Trade Secret", appBelowBoundary.getLicense()); //no update
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
            assertTrue(stringContents.contains("Great App"));
            assertTrue(stringContents.contains("Jane Doe"));
            assertTrue(stringContents.contains("Excellent"));
        }

    }



    EducationApp setupEducationAppWithRatingAndDownloads(int numberDownloads, int rating1, int rating2) {
        //setting all conditions to true
        EducationApp edApp = new EducationApp(developerLego, "WeDo", 1,
                1.0, 0, false, "Noncommercial",
                1.00, "Maths", 1);
        edApp.setNumberOfDownloads(numberDownloads);
        edApp.addRating(new Rating(rating1, "John Doe", "Great App"));
        edApp.addRating(new Rating(rating2, "Jane Doe", "Excellent"));
        return edApp;
    }
}
