package models;

public class ProductivityApp extends App {

    public ProductivityApp(Developer developer, String appName, double appSize, double appVersion, double appCost) {
        super(developer, appName, appSize, appVersion, appCost);
    }

    @Override
    public boolean isRecommendedApp() {
        // Algorithm - app is recommended if the following applies:
        //      appCost is >= 1.99
        //      rating of > 3.0
        return  (getAppCost() >= 1.99)
                && (calculateRating() > 3.0);
    }

    @Override
    public String toString(){
        return super.toString() +
                "\n\t Ratings (" + calculateRating() + "):\n"
                + listRatings();
    }
}
