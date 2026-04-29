 

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Dog extends Mammal {
    private static final float DANGEROUS_DAILY_RATE = 40;
    private static final float NONDANGEROUS_DAILY_RATE = 30;


    private String breed;  // no validation
    private boolean dangerousBreed;

    public Dog(String name, int age, Owner owner, int id, char sex, boolean vaccinated, double weight, boolean neutered, String breed, boolean dangerousBreed) {
        super(name, age, owner, id, sex, vaccinated, weight, neutered);
        this.breed = breed;
        this.dangerousBreed = dangerousBreed;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public boolean isDangerousBreed() {
        return dangerousBreed;
    }

    public void setDangerousBreed(boolean dangerousBreed) {
        this.dangerousBreed = dangerousBreed;
    }


    public double calculateWeeklyFee(){
        double dailyRate = (isDangerousBreed()? DANGEROUS_DAILY_RATE : NONDANGEROUS_DAILY_RATE);
        double total = 0.0;
        for (int i= 0; i <  getDaysAttending().length; i++) {
            if ( getDaysAttending()[i]) {total = total + dailyRate;}
        }
        return total;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        return dangerousBreed == dog.dangerousBreed && Objects.equals(breed, dog.breed);
    }



    //should print male neutered or female not neutered
    //should print days that the dog is booked into kennels  // NOT DONE YET
    @Override
    public String toString() {
        return "[Dog]\n" + super.toString() +
                "\nBreed: " + breed +
                ", dangerous: " + (dangerousBreed? "Yes":"No") +
                "\nWeekly Fee: €" + calculateWeeklyFee();
    }
}

