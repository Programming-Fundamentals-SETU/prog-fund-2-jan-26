package models;

import utils.ValidationUtility;

public class Rating {

    private int numberOfStars;  //between 1 and 5 inclusive
    private String raterName;   //name of rater - max num chars
    private String ratingComment;  //comment (optional)

    public Rating(int numberOfStars, String raterName, String ratingComment) {
        if (ValidationUtility.isValidRange(1, 5, numberOfStars))
            this.numberOfStars = numberOfStars;
        else
            this.numberOfStars = 0;

        this.raterName = raterName;
        this.ratingComment = ratingComment;
    }

    public int getNumberOfStars() {
        return numberOfStars;
    }

    public String getRaterName() {
        return raterName;
    }

    public String getRatingComment() {
        return ratingComment;
    }

    @Override
    public String toString() {
        String ratingHeader = numberOfStars + " stars (by " + raterName + ").";
        if (ratingComment.isEmpty())
            return ratingHeader;
        else
            return ratingHeader + "\n + \t\"" + ratingComment + '\"';
    }

}
