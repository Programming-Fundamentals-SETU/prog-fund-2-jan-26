public class SpotWithValidation {

    private  int x = 0; //these are used as the
    private int y = 0; // x and y should be between 0 and 200. Default to 0.
    private String description; // should be max length 20. Default to first 20 chars
    public SpotWithValidation(int x, int y, String description) {
        setX(x);
        setY(y);
        if (description.length() <=20)
            this.description = description;
        else this.description = description.substring(0,20);
    }


    public int getY() {
        return y;
    }

    public void setY(int y) {
        if (y >=0 && y <=200)
            this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if (x >=0 && x <=200)
            this.x = x;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description.length() <= 20)
            this.description = description;
    }
    public String toString() {
        return  "Value of x : " + x +
                "  Value of y : " + y +
                "  Value of description is : "+ description + "\n";
    }
}