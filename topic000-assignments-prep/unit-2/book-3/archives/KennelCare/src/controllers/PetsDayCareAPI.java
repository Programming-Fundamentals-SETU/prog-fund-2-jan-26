package controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.*;
import models.Pet;
import utils.BirdUtility;
import utils.ISerializer;

import java.io.*;
import java.util.ArrayList;


public class PetsDayCareAPI implements ISerializer {
    private ArrayList<Pet> pets;
    private String name;  // 10 chars
    private int maxNumberOfPets = 10;  // must be >= 10 <= 100 - default to 10
    private File file;

    //-------------------------------------
    //  Constructor
    //-------------------------------------
    public PetsDayCareAPI(String name, int maxNumberOfPets, File file) {
        pets = new ArrayList<>();
        initName(name);
        setMaxNumberOfPets(maxNumberOfPets);
    }

    //-------------------------------------
    //  Setters/Getters
    //-------------------------------------
    private void initName(String name) {
        this.name = (name.length() <= 10 ? name : name.substring(0, 10));
    }

    public void setMaxNumberOfPets(int maxNumberOfPets) {
        if (maxNumberOfPets >= 10 && maxNumberOfPets <= 100) {
            this.maxNumberOfPets = maxNumberOfPets;
        }
    }

    public void setName(String name) {
        if (name.length() <= 10) {
            this.name = name;
        }
    }

    public void setPetsArray(ArrayList<Pet> pets) {
        this.pets = pets;
    }

    public ArrayList<Pet> getPetsArray() {
        return pets;
    }

    public String getName() {
        return name;
    }
    public int getMaxNumberOfPets() {
        return maxNumberOfPets;
    }

    //-------------------------------------
    //  Pet ARRAYLIST CRUD
    //-------------------------------------
    public boolean addPet(Pet dog) {
        //check are all owners registered
        if (pets.size() < maxNumberOfPets) return  pets.add(dog);
        else return false;
    }
    public Pet removePet(int index) {
        if (isValidPetIndex(index))
            return pets.remove(index);
        else
            return null;
    }
    public Pet updatePet(int index, Pet updatedPet) {
        if (isValidPetIndex(index)) {
            pets.set(index, updatedPet);
            return pets.get(index);
        } else
            return null;
    }

    //-------------------------------------
    //  Pet ARRAYLIST - Utility methods
    //-------------------------------------

    public Pet getPet(int index) {
        if (isValidPetIndex(index))
            return pets.get(index);
        else
            return null;
    }
    public Pet getPet(String name) {
        if (numberOfPets() > 0) {
            for (Pet thePet : pets) {
                if (thePet.getName().equalsIgnoreCase(name))
                    return thePet;
            }

        }
        return null;
    }

    public boolean isValidPetIndex(int index) {
        return index >= 0 && index < pets.size();
    }


    //------------------------------------
    // LISTING METHODS - Basic and Advanced
    //------------------------------------
    public String listAllPets() {
        if (pets.isEmpty()) {
            return "There are no dogs registered at the moment";
        } else {
            String allPetsStr = "";
            for (int i = 0; i < pets.size(); i++)
                allPetsStr += i + ":\t" + pets.get(i);

            return allPetsStr;
        }
    }

    public String listAllDangerousDogs() {
        if (pets.isEmpty()) {
            return "There are no dogs registered at the moment";
        } else {
            String allPetsStr = "";
            for (int i = 0; i < pets.size(); i++)
                if (pets.get(i) instanceof Dog && ((Dog)(pets.get(i))).isDangerousBreed())
                    allPetsStr += i + ":\t" + pets.get(i);
            if (allPetsStr.isEmpty())
                return "No Dangerous Breeds at the moment";
            return allPetsStr;
        }
    }

    public String listAllPetsByOwner(String o) {
        if (pets.isEmpty()) {
            return "There are no pets registered at the moment";
        } else {
            String allPetsStr = "";
            for (int i = 0; i < pets.size(); i++)
                if (pets.get(i).getOwner().equals(o))
                    allPetsStr += i + ":\t" + pets.get(i);
            if (allPetsStr.isEmpty())
                return "No Pets have that owner";
            return allPetsStr;
        }
    }

    public String listAllPetsThatStayMoreThanDays(int numDays) {
        if (pets.isEmpty()) {
            return "There are no dogs registered at the moment";
        } else {
            String allPetsStr = "";
            for (int i = 0; i < pets.size(); i++)
                if (pets.get(i).numOfDaysAttending() > numDays)
                    allPetsStr += i + ":\t" + pets.get(i);
            if (allPetsStr.isEmpty())
                return "No Pets stay longer than " + numDays + " days at the moment";
            return allPetsStr;
        }
    }


    //-------------------------------------
    //  Counting Methods
    //-------------------------------------

    public int numberOfPets() {
        return pets.size();
    }
    public int numberOfDogs() {
        int count = 0;
        for (Pet p:pets){
            if( p instanceof Dog)
                count++;
        }
        return count;
    }
    public int numberOfCats() {
        int count = 0;
        for (Pet p:pets){
            if( p instanceof Cat)
                count++;
        }
        return count;
    }
    public int numberOfParrots() {
        int count = 0;
        for (Pet p:pets){
            if( p instanceof Parrot)
                count++;
        }
        return count;
    }


    public int numberOfDangerousDogs() {
        int countDanger = 0;
        for (Pet thePet : pets) {
            if (thePet instanceof Dog && ((Dog)(thePet)).isDangerousBreed())
                countDanger++;
        }
        return countDanger;
    }

    public int numberOfIndoorCats() {
        int countIndoor = 0;
        for (Pet thePet : pets) {
            if (thePet instanceof Cat && ((Cat)(thePet)).isIndoorCat())
                countIndoor++;
        }
        return countIndoor;
    }
    public int numberOfParrotsByVocabularySize(int vocabSize) {
        int countParrot = 0;
        for (Pet thePet : pets) {
            if (thePet instanceof Parrot && ((Parrot)(thePet)).getVocabularySize().equals(BirdUtility.getVocabularyLevel(vocabSize)))
                countParrot++;
        }
        return countParrot;
    }

    public double getWeeklyIncome() {
        double weeklyIncome = 0;
        for (Pet thePet : pets) {
            weeklyIncome += thePet.calculateWeeklyFee();
        }
        return weeklyIncome;
    }

    public double getAverageNumDaysPerWeek() {
        int averageNumDays = 0;
        for (Pet thePet : pets) {
            averageNumDays += thePet.numOfDaysAttending();
        }
        return (double)averageNumDays / numberOfPets();
    }

    //------------------------------
    //  FINDING METHODS
    //-------------------------------


    public Pet findDogByOwnerAndBreedAndAge(String name, String breed, int age) {
        if (numberOfPets() > 0) {
            for (Pet thePet : pets) {
                if (thePet instanceof Dog) {
                    Dog dog = (Dog) (thePet);

                    if (dog.getName().equalsIgnoreCase(name) && dog.getAge() == age && dog.getBreed().equalsIgnoreCase(breed))
                        return thePet;
                }
            }

        }
        return null;
    }

    //------------------------------
    //  SEARCHING METHODS
    //-------------------------------

    public String getPetsByOwnersName(String ownerName) {
        if (pets.isEmpty()) {return "There are no pets registered at the moment";}
        else {
            String allPetsByOwner = "";
            for (int i = 0; i < pets.size(); i++){
                if (pets.get(i).toString().contains(ownerName))   allPetsByOwner += i + ":\t" + pets.get(i) + "\n";
            }
            if (allPetsByOwner.isEmpty()) {return "No Pets have that owner";}
            else return allPetsByOwner;
        }
    }

    //---------------------------------
    //  Methods for Persistence
    // --------------------------------

    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        //list of classes that you wish to include in the serialisation, separated by a comma
        Class<?>[] classes = new Class[] { Pet.class, Mammal.class, Bird.class, Parrot.class, Dog.class, Cat.class};

        //setting up the xstream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        //doing the actual serialisation to an XML file
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("kennel.xml"));
        pets = (ArrayList<Pet>) is.readObject();
        is.close();
    }

    @Override
    public String fileName() {
        return "";
    }

    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("kennel.xml"));
        out.writeObject(pets);
        out.close();
    }


    public String listOwners() {
        return "something";
    }
}



