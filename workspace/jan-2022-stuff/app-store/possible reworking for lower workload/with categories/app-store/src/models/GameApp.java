package models;

import utils.CategoryUtility;

public class GameApp extends App {

    private String gameGenre = CategoryUtility.getDefaultGameCategory();
    private boolean isMultiplayer;

    public GameApp(Developer developer, String appName, double appSize, double appVersion, double appCost, String gameGenre, boolean multiplayer) {
        super(developer, appName, appSize, appVersion, appCost);
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
    }

    @Override
    public boolean isRecommendedApp() {
        // Algorithm - app is recommended if the following applies:
        //      isMultiplayer is false;
        //      rating of >= 4.0
        return  (!isMultiplayer) &&
                (calculateRating() >= 4.0);
    }

    public String appSummary(){
        return  super.appSummary() + ".  Game App - genre " + gameGenre;
    }

    @Override
    public String toString(){
        return super.toString() +
                ", Genre: " + gameGenre +
                "\n\t Ratings (" + calculateRating() + "):\n"
                + listRatings();
    }
}
