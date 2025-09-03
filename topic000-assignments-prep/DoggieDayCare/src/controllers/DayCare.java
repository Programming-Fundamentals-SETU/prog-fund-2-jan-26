package controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.Dog;
import models.Owner;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class DayCare {
    private ArrayList<Dog> dogsArray;
    private String name;  // 10 chars
    private int maxNumberOfDogs = 10;  // must be >= 10 <= 100 - default to 10

    //-------------------------------------
    //  Constructor
    //-------------------------------------
    public DayCare(String name, int maxNumberOfDogs) {
        dogsArray = new ArrayList<>();
        initName(name);
        setMaxNumberOfDogs(maxNumberOfDogs);
    }

    //-------------------------------------
    //  Setters/Getters
    //-------------------------------------
    private void initName(String name) {
        this.name = (name.length() <= 10 ? name : name.substring(0, 10));
    }

    public void setMaxNumberOfDogs(int maxNumberOfDogs) {
        if (maxNumberOfDogs >= 10 && maxNumberOfDogs <= 100) {
            this.maxNumberOfDogs = maxNumberOfDogs;
        }
    }

    public void setName(String name) {
        if (name.length() <= 10) {
            this.name = name;
        }
    }

    public void setDogsArray(ArrayList<Dog> dogsArray) {
        this.dogsArray = dogsArray;
    }

    public ArrayList<Dog> getDogsArray() {
        return dogsArray;
    }

    public String getName() {
        return name;
    }
    public int getMaxNumberOfDogs() {
        return maxNumberOfDogs;
    }

    //-------------------------------------
    //  Dog ARRAYLIST CRUD
    //-------------------------------------
    public boolean addDog(Dog dog) {
        //check are all owners registered
     if (dogsArray.size() < maxNumberOfDogs) return  dogsArray.add(dog);
        else return false;
    }
    public Dog removeDog(int index) {
        if (isValidDogIndex(index))
            return dogsArray.remove(index);
        else
            return null;
    }
    public Dog updateDog(int index, Dog updatedDog) {
        if (isValidDogIndex(index)) {
            dogsArray.set(index, updatedDog);
            return dogsArray.get(index);
        } else
            return null;
    }

    //-------------------------------------
    //  Dog ARRAYLIST - Utility methods
    //-------------------------------------

    public Dog getDog(int index) {
        if (isValidDogIndex(index))
            return dogsArray.get(index);
        else
            return null;
    }
    public Dog getDog(String name) {
        if (numberOfDogs() > 0) {
            for (Dog theDog : dogsArray) {
                if (theDog.getName().equalsIgnoreCase(name))
                    return theDog;
            }

        }
        return null;
    }

    private boolean isValidDogIndex(int index) {
        return index >= 0 && index < dogsArray.size();
    }


    //------------------------------------
    // LISTING METHODS - Basic and Advanced
    //------------------------------------
    public String listAllDogs() {
        if (dogsArray.isEmpty()) {
            return "There are no dogs registered at the moment";
        } else {
            String allDogsStr = "";
            for (int i = 0; i < dogsArray.size(); i++)
                allDogsStr += i + ":\t" + dogsArray.get(i);

            return allDogsStr;
        }
    }

    public String listAllDangerousDogs() {
        if (dogsArray.isEmpty()) {
            return "There are no dogs registered at the moment";
        } else {
            String allDogsStr = "";
            for (int i = 0; i < dogsArray.size(); i++)
                if (dogsArray.get(i).isDangerousBreed())
                    allDogsStr += i + ":\t" + dogsArray.get(i);
            if (allDogsStr.isEmpty())
                return "No Dangerous Breeds at the moment";
            return allDogsStr;
        }
    }

    public String listAllDogsByOwner(Owner o) {
        if (dogsArray.isEmpty()) {
            return "There are no dogs registered at the moment";
        } else {
            String allDogsStr = "";
            for (int i = 0; i < dogsArray.size(); i++)
                if (dogsArray.get(i).getOwners().contains(o))
                    allDogsStr += i + ":\t" + dogsArray.get(i);
            if (allDogsStr.isEmpty())
                return "No Dogs have that owner";
            return allDogsStr;
        }
    }

    public String listAllDogsThatStayMoreThanDays(int numDays) {
        if (dogsArray.isEmpty()) {
            return "There are no dogs registered at the moment";
        } else {
            String allDogsStr = "";
            for (int i = 0; i < dogsArray.size(); i++)
                if (dogsArray.get(i).numOfDaysInKennel() > numDays)
                    allDogsStr += i + ":\t" + dogsArray.get(i);
            if (allDogsStr.isEmpty())
                return "No Dogs stay longer than " + numDays + " days at the moment";
            return allDogsStr;
        }
    }


    //-------------------------------------
    //  Counting Methods
    //-------------------------------------

    public int numberOfDogs() {
        return dogsArray.size();
    }


    public int numberOfDangerousDogs() {
        int countDanger = 0;
        for (Dog theDog : dogsArray) {
            if (theDog.isDangerousBreed())
                countDanger++;
        }
        return countDanger;
    }

    public double getWeeklyIncome() {
        double weeklyIncome = 0;
        for (Dog theDog : dogsArray) {
            weeklyIncome += theDog.getWeeklyBill();
        }
        return weeklyIncome;
    }

    public float getAverageNumDaysPerWeek() {
        int averageNumDays = 0;
        for (Dog theDog : dogsArray) {
            averageNumDays += theDog.numOfDaysInKennel();
        }
        return (float)averageNumDays / numberOfDogs();
    }

    //------------------------------
    //  FINDING METHODS
    //-------------------------------


    public Dog findDogByOwnerAndBreedAndAge(String name, String breed, int age) {
        if (numberOfDogs() > 0) {
            for (Dog theDog : dogsArray) {
                if (theDog.getName().equalsIgnoreCase(name) && theDog.getAge() == age && theDog.getBreed().equalsIgnoreCase(breed))
                    return theDog;
            }

        }
        return null;
    }

    //------------------------------
    //  SEARCHING METHODS
    //-------------------------------

    public String getDogsByOwnersName(String ownerName) {
        if (dogsArray.isEmpty()) {return "There are no dogs registered at the moment";}
        else {
            String allDogsByOwner = "";
            for (int i = 0; i < dogsArray.size(); i++){
                if (dogsArray.get(i).toString().contains(ownerName))   allDogsByOwner += i + ":\t" + dogsArray.get(i) + "\n";
            }
            if (allDogsByOwner.isEmpty()) {return "No Dogs have that owner";}
            else return allDogsByOwner;
        }
    }

    //---------------------------------
    //  Methods for Persistence
    // --------------------------------

   @SuppressWarnings("unchecked")
    public void load() throws Exception {
        //list of classes that you wish to include in the serialisation, separated by a comma
        Class<?>[] classes = new Class[] { Dog.class, Owner.class };

        //setting up the xstream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        //doing the actual serialisation to an XML file
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("kennel.xml"));
        dogsArray = (ArrayList<Dog>) is.readObject();
        is.close();
    }

    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("kennel.xml"));
        out.writeObject(dogsArray);
        out.close();
    }



}



