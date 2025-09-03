package models;

import utils.CategoryUtility;
import utils.ValidationUtility;

public class EducationApp extends App {

    private String subjectCategory;
    private int level; //valid levels are 1 to 10 inclusive (see https://www.qqi.ie/Articles/Pages/National-Framework-of-Qualifications-(NFQ).aspx)

    public EducationApp(Developer developer, String appName, double appSize, double appVersion, int ageRating, boolean inAppPurchase, String license, double appCost, String subjectCategory, int level) {
        super(developer, appName, appSize, appVersion, ageRating, inAppPurchase, license, appCost);
        setCategory(subjectCategory);
        if (ValidationUtility.isValidRange(1, 10, level)){
            this.level = level;
        }
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
        else {
            this.subjectCategory = "General";
        }
    }

    @Override
    public boolean isRecommendedApp() {
        //todo
        // Algorithm - app is recommended if the following applies:
        //      inAppPurchase is false;
        //      appCost is > .99
        //      numberofDownloads >= 5
        //      rating of >= 3.5
        //      category is not General
        return false;
    }

    public String appSummary(){
        return  super.appSummary() + ".  Education App - level " + level;
    }


}
