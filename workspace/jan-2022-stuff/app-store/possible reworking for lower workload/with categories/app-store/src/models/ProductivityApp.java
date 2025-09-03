package models;

import utils.CategoryUtility;

public class ProductivityApp extends App {

    private String productivityCategory = CategoryUtility.getDefaultProductivityCategory();

    public ProductivityApp(Developer developer, String appName, double appSize, double appVersion, double appCost, String productivityCategory) {
        super(developer, appName, appSize, appVersion, appCost);
        setCategory(productivityCategory);
    }

    @Override
    public void setCategory(String category) {
        if (CategoryUtility.isValidProductivityCategory(category)){
            this.productivityCategory = category;
        }
    }

    @Override
    public String getCategory() {
        return productivityCategory;
    }

    @Override
    public boolean isRecommendedApp() {
        // Algorithm - app is recommended if the following applies:
        //      appCost is >= 1.99
        //      rating of > 3.0
        //      category is not the default of Utility
        return  (getAppCost() >= 1.99)
                && (calculateRating() > 3.0)
                && !(productivityCategory.equals(CategoryUtility.getDefaultProductivityCategory()));
    }

    public String appSummary(){
        return  super.appSummary() + ".  Productivity App - " + productivityCategory;
    }

    @Override
    public String toString(){
        return super.toString() +
                ", Category: " + productivityCategory +
                "\n\t Ratings (" + calculateRating() + "):\n"
                + listRatings();
    }
}
