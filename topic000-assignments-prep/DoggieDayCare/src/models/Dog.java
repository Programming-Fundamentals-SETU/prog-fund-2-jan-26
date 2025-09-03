package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Dog {
    private static final float DANGEROUS_DAILY_RATE = 40;
    private static final float NONDANGEROUS_DAILY_RATE = 30;

    private int id = 1000; // 4 digits - default 1000
    private String name;  // <= 20 chars
    private String breed;  // no validation
    private boolean dangerousBreed;
    private int age =5; // >=0  <= 20 - default to 5
    private char sex = 'F'; //MUST BE M OR F / default to 'F'
    private boolean neutered; //
    private final ArrayList<Owner> owners;
    private boolean[] daysInKennel = {false, false, false, false, false};  //leave at 5 day (so easy to remember starts Monday)

    public Dog(int id, String name, String breed, boolean dangerousBreed, int age, char sex, boolean neutered, Owner primaryOwner) {
        setId(id);
        initName(name);
        this.breed = breed;
        this.dangerousBreed = dangerousBreed;
        setAge(age);
        setSex(sex);
        this.neutered = neutered;
        this.owners = new ArrayList<>();
        this.owners.add(primaryOwner); //keep setup simple?
    }

    public int getId(){ return id; }
    public String getName() {
        return name;
    }


    public String getBreed() {
        return breed;
    }

    public boolean isDangerousBreed() {
        return dangerousBreed;
    }

    public int getAge() {
        return age;
    }

    public char getSex() {
        return sex;
    }

    public boolean isNeutered() {
        return neutered;
    }

    public boolean[] getDaysInKennel() {
        return daysInKennel;
    }

    public boolean isInKennel(int day) {
        if (day >= 0 && day < 5) return daysInKennel[day];
        else return false;
    }

    public boolean addOwner(Owner owner) {
        return this.owners.add(owner);
    }
    public int getNumOwners() {
        return owners.size();
    }
    public ArrayList<Owner> getOwners() {return owners;}
    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setDangerousBreed(boolean dangerousBreed) {
        this.dangerousBreed = dangerousBreed;
    }

    public void setAge(int age) {
        if (age >= 0 && age <= 20) this.age = age;
    }

    public void setSex(char sex) {
        if (sex == 'm' || sex =='M' )
            this.sex = 'M';
        else if (sex == 'f' || sex == 'F')
            this.sex = 'F';
    }

    public void setNeutered(boolean neutered) {
        this.neutered = neutered;
    }
    public void initName(String name) {
        this.name = (name.length()<= 20? name : name.substring(0,20));
    }
    public void setName(String name) {
        if (name.length() <=20)  this.name = name;
    }

    public void setId(int id) {
        if (id >= 1000 && id <= 9999)
            this.id = id;
    }

    public void setDaysInKennel(boolean[] daysInKennel) {
        this.daysInKennel = daysInKennel;
    }
    public void setDaysInKennel(int i, boolean inKennel) {
        if (i >= 0 && i < 5){
            this.daysInKennel[i] = inKennel;
        }
    }
    public int numOfDaysInKennel() {
        int total = 0;
        for (int i= 0; i < daysInKennel.length; i++) {
            if (daysInKennel[i]) {total++;}
        }
        return total;
    }
    private String listOwners() {
        if (owners.size() == 1)
            return "Owner: " + owners.get(0).toString();
        else{
            String ownerStr = "List of owners \n";
            for (int i = 0; i < owners.size(); i++) {
                ownerStr += "i:  " + owners.get(i).toString();
            }
            return ownerStr;
        }
    }


    public double getWeeklyBill(){
        double dailyRate = (isDangerousBreed()? DANGEROUS_DAILY_RATE : NONDANGEROUS_DAILY_RATE);
        double total = 0.0;
        for (int i= 0; i < daysInKennel.length; i++) {
            if (daysInKennel[i]) {total = total + dailyRate;}
        }
        return total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        return getId() == dog.getId() && isDangerousBreed() == dog.isDangerousBreed() && getAge() == dog.getAge() && getSex() == dog.getSex() && isNeutered() == dog.isNeutered() && Objects.equals(getName(), dog.getName()) && Objects.equals(getBreed(), dog.getBreed()) && Objects.equals(getOwners(), dog.getOwners()) && Objects.deepEquals(getDaysInKennel(), dog.getDaysInKennel());
    }



    //should print male neutered or female not neutered
    //should print days that the dog is booked into kennels  // NOT DONE YET
    @Override
    public String toString() {
        return "Dog:" + name + ", Breed: " + breed + ", Dangerous: "
                + (dangerousBreed? "Yes" : "No") + ", Age: " + age + ", Sex: " + sex + ", Neutered: " + (neutered ? "is" : "is not")+ " neutered" + "\n" + "Dog is in the kennel for: "+ numOfDaysInKennel() + " days, and the bill is: " + getWeeklyBill() + "\n" + listOwners();
    }
}

