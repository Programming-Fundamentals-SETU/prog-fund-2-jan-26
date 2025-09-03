package models;

import utils.CategoryUtility;
import utils.Utilities;

public class EducationApp extends App {

    private String subjectCategory = CategoryUtility.getDefaultSubjectCategory();
    private int level = 0; //valid levels are 1 to 10 inclusive (see https://www.qqi.ie/Articles/Pages/National-Framework-of-Qualifications-(NFQ).aspx)

    public EducationApp(Developer developer, String appName, double appSize, double appVersion, int ageRating, boolean inAppPurchase, String license, double appCost, String subjectCategory, int level) {
        super(developer, appName, appSize, appVersion, ageRating, inAppPurchase, license, appCost);
        setCategory(subjectCategory);
        setLevel(level);
    }

    @Override
    public String getCategory() {
        return subjectCategory;
    }

    @Override
    public void setCategory(String category) {
        if (CategoryUtility.isValidSubjectCategory(category)){
            this.subjectCategory = category;
        }
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if (Utilities.validRange(level, 1, 10)){
            this.level = level;
        }
    }

    @Override
    public boolean isRecommendedApp() {
        // Algorithm - app is recommended if the following applies:
        //      inAppPurchase is false;
        //      appCost is > .99
        //      numberofDownloads >= 5
        //      rating of >= 3.5
        //      category is not the default of General
        return  !(isInAppPurchase())
                && (getAppCost() > 0.99)
                && (getNumberOfDownloads() >= 5)
                && (calculateRating() >= 3.5)
                && !(subjectCategory.equals(CategoryUtility.getDefaultSubjectCategory()));
    }

    public String appSummary(){
        return  super.appSummary() + ".  Education App - level " + level;
    }

    @Override
    public String toString(){
        return super.toString() +
                ", Level: " + level +
                ", Subject: " + subjectCategory +
                "\n\t Ratings (" + calculateRating() + "):\n"
                + listRatings();
    }
}
