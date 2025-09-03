package controllers;

import models.Developer;
import persistence.PersistenceManager;
import persistence.Serializer;

import java.util.ArrayList;
import java.util.List;

import static utils.ValidationUtility.isValidIndex;

public class DeveloperAPI implements PersistenceManager {

    private List<Developer> developers = new ArrayList<>();
    private Serializer serializer;

    public DeveloperAPI(Serializer serializer)  {
        this.serializer = serializer;
    }

    //---------------------
    // Create methods
    //---------------------
    public boolean addDeveloper(Developer developer) {
        if (isValidDeveloper(developer.getDeveloperName())){
            return false;
        }
        return developers.add(developer);
    }

    //---------------------
    // Read methods
    //---------------------
    public Developer getDeveloperByIndex(int index){
        if (isValidIndex(developers, index)){
            return developers.get(index);
        }
        else{
            return null;
        }
    }

    public Developer getDeveloperByName (String developerName){
        int index = retrieveDeveloperIndex(developerName);
        if (index != -1){
            return developers.get(index);
        }
        return null;
    }


    public String listDevelopers(){
        String listDevelopers = "";
        for (Developer developer : developers){
            listDevelopers += developers.indexOf(developer) + ": " + developer;
        }
        if (listDevelopers.equals("")){
            return "No developers";
        }
        else {
            return listDevelopers;
        }
    }

    //---------------------
    // Update methods
    //---------------------
    public boolean updateDeveloperWebsite(String developerName, String developerWebsite){
        if (isValidDeveloper(developerName)){
            Developer developerToUpdate = getDeveloperByName(developerName);
            developerToUpdate.setDeveloperWebsite(developerWebsite);
            return true;
        }
        return false;
    }

    //---------------------
    // Delete methods
    //---------------------
    public boolean removeDeveloper(Developer developer){
        if (developers.contains(developer)) {
            return developers.remove(developer);
        }
        return false;
    }

    public Developer removeDeveloper(String developerName){
        int index = retrieveDeveloperIndex(developerName);
        if (index != -1) {
            return developers.remove(index);
        }
        return null;
    }

    //---------------------
    // Validation Methods
    //---------------------
    public boolean isValidDeveloper(String developerName){
        for (Developer developer : developers){
            if (developer.getDeveloperName().equalsIgnoreCase(developerName)){
                return true;
            }
        }
        return false;
    }

    public int retrieveDeveloperIndex(String developerName){
        for (Developer developer : developers){
            if (isValidDeveloper(developerName)){
                return developers.indexOf(developer);
            }
        }
        return -1;
    }

    //---------------------
    // Persistence Methods
    //---------------------

    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        developers = (ArrayList<Developer>) serializer.read();
    }

    public void store() throws Exception {
        serializer.write(developers);
    }

}
