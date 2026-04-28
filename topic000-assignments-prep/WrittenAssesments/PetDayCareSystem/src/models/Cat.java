package models;

public class Cat extends Mammal {
    private boolean indoorCat;
    private String favouriteToy;

    public Cat(String name, int age, Owner owner, int id, char sex, boolean vaccinated, double weight, boolean neutered, boolean indoorCat, String favouriteToy) {
        super(name, age, owner, id, sex, vaccinated, weight, neutered);
        this.indoorCat = indoorCat;
        this.favouriteToy = favouriteToy;
    }

    public boolean isIndoorCat() {
        return indoorCat;
    }

    public void setIndoorCat(boolean indoorCat) {
        this.indoorCat = indoorCat;
    }

    public String getFavouriteToy() {
        return favouriteToy;
    }

    public void setFavouriteToy(String favouriteToy) {
        this.favouriteToy = favouriteToy;
    }

    @Override
    public double calculateWeeklyFee() {
        double fee = 0;
        boolean[] days = getDaysAttending();
        for (int i = 0; i < days.length; i++) {
            if (days[i]) {
                fee += 20;  // base rate for cat
                if (indoorCat) {
                    fee -= 5;  // discount for indoor cat
                }
            }
        }
        return fee;
    }

    @Override
    public String toString() {
        return "[Cat]\n" + super.toString() +
                "\nIndoor: " + indoorCat +
                " | Favourite Toy: " + favouriteToy +
                "\nWeekly Fee: €" + calculateWeeklyFee();
    }
}
