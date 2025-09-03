package models;

import utils.Utilities;

public class GameApp extends App {

    private boolean isMultiplayer;

    public GameApp(Developer developer, String appName, double appSize, double appVersion, double appCost, boolean multiplayer) {
        super(developer, appName, appSize, appVersion, appCost);
        this.isMultiplayer = multiplayer;
    }

    public boolean isMultiplayer() {
        return isMultiplayer;
    }

    public void setMultiplayer(boolean multiplayer) {
        isMultiplayer = multiplayer;
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
        return  super.appSummary() + ".  Multiplayer: " + Utilities.booleanToYN(isMultiplayer);
    }

    @Override
    public String toString(){
        return super.toString() +
                "\n\t Ratings (" + calculateRating() + "):\n"
                + listRatings();
    }
}
