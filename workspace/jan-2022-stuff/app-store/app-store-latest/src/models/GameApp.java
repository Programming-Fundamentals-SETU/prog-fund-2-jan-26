package models;

import utils.CategoryUtility;

public class GameApp extends App {

    private String gameGenre;
    private boolean isMultiplayer;

    public GameApp(Developer developer, String appName, double appSize, double appVersion, int ageRating, boolean inAppPurchase, String license, double appCost, String gameGenre, boolean multiplayer) {
        super(developer, appName, appSize, appVersion, ageRating, inAppPurchase, license, appCost);
        setCategory(gameGenre);
        this.isMultiplayer = multiplayer;
    }

    public boolean isMultiplayer() {
        return isMultiplayer;
    }

    public void setMultiplayer(boolean multiplayer) {
        isMultiplayer = multiplayer;
    }

    @Override
    public String getCategory() {
        return gameGenre;
    }

    @Override
    public void setCategory(String category) {
        if (CategoryUtility.isValidGameCategory(category)){
            this.gameGenre = category;
        }
        else {
            this.gameGenre = "Uncategorised";
        }
    }


    @Override
    public boolean isRecommendedApp() {
        // Algorithm - app is recommended if the following applies:
        //      inAppPurchase is true;
        //      numberofDownloads >= 10
        //      rating of >= 4.0
        return false;
    }


    public String appSummary(){
        return  super.appSummary() + ".  Game App - genre " + gameGenre;
    }

}
