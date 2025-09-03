package models;

import utils.CategoryUtility;

public class ProductivityApp extends App {

    private String productivityCategory;

    public ProductivityApp(Developer developer, String appName, double appSize, double appVersion, int ageRating, boolean inAppPurchase, String license, double appCost, String productivityCategory) {
        super(developer, appName, appSize, appVersion, ageRating, inAppPurchase, license, appCost);
        setCategory(productivityCategory);
    }

    @Override
    public void setCategory(String category) {
        if (CategoryUtility.isValidProductivityCategory(category)){
            this.productivityCategory = category;
        }
        else {
            this.productivityCategory = "Utility";
        }
    }

    @Override
    public String getCategory() {
        return productivityCategory;
    }

    @Override
    public boolean isRecommendedApp() {
        // Algorithm - app is recommended if the following applies:
        //      inAppPurchase is false;
        //      appCost is > 1.99
        //      rating of > 3.0
        //      category is not Utility
        return false;
    }

    public String appSummary(){
        return  super.appSummary() + ".  Productivity App - " + productivityCategory;
    }

}
