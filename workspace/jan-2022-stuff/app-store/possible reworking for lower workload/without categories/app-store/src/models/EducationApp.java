package models;

import utils.Utilities;

public class EducationApp extends App {

    private int level = 0; //valid levels are 1 to 10 inclusive (see https://www.qqi.ie/Articles/Pages/National-Framework-of-Qualifications-(NFQ).aspx)

    public EducationApp(Developer developer, String appName, double appSize, double appVersion, double appCost, int level) {
      super(developer, appName, appSize, appVersion, appCost);
        setLevel(level);
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
        //      appCost is > .99
        //      rating of >= 3.5
        //      level > 3
        return     (getAppCost() > 0.99)
                && (calculateRating() >= 3.5)
                && (level >= 3);
    }

    public String appSummary(){
        return  super.appSummary() + ".  Education App - level " + level;
    }

    @Override
    public String toString(){
        return super.toString() +
                ", Level: " + level +
                "\n\t Ratings (" + calculateRating() + "):\n"
                + listRatings();
    }
}
