package controllers;


import models.Dog;

import java.util.ArrayList;

public class DayCare {
    private ArrayList<Dog> dogsArray;
    private String name;  // 10 chars
    private int maxNumberOfDogs;  // must be >= 10 <= 100

    //-------------------------------------
    //  Constructor
    //-------------------------------------
    //TODO array list of dogs, should be empty at the start.

    //TODO constructor (String name, int numDogs).
    //     Default name is "".
    //     When creating the DayCare, truncate the name to 10 characters.
    //     When updating an existing DayCare, only update the name if it is 10 characters or less.
    //     number of dogs must be must be >= 10 <= 100 default to 10

    //-------------------------------------
    //  Setters/Getters
    //-------------------------------------
    //TODO Add a getter and setter for each field, that adheres to the above validation rules

    //-------------------------------------
    //  ARRAYLIST CRUD
    //-------------------------------------
    //TODO Add a method, addDog(Dog). The return type is boolean.
    //     This method will add the dog object, passed as a parameter to the arraylist of dogs.
    //     If the add was successful, return true, otherwise, return false.


    //TODO Add a method, updateDog(int, Dog).  The return type is boolean.
    //     This method takes in, as the first parameter, the index of the dogs object that you want to update.
    //     If the index is invalid (i.e. there is no dog object at that location), return false.
    //     The other parameter is a  Dog object - that is being updated
    //     i.e. it holds the new values of  dog object
    //     If the update was successful, then return true.


    //TODO Add a method, deleteDog(int).  The return type is Dog.
    //     This method takes in the index of the dog object that you want to delete.
    //     If the index is invalid (i.e. there is no dog object at that location), return null.
    //     If the index is valid, remove the object at that index location.  Return the object you just deleted.


    //-------------------------------------
    //  ARRAYLIST - Utility methods
    //-------------------------------------
//TODO Add a method isValidIndex(int) which returns an boolean -
    //      - returns true if the index is valid for the dogs arrayList (in range)
    //      - returns false otherwise
    //      As this method is used inside this class, it should be private


    //TODO  Add a method  getDog(int) which returns a Dog object:
    //       - if the supplied index is valid, the Dog object at that location is returned
    //       - if the supplied index is invalid, null is returned

    //TODO  Add a method  getDog(String) which returns a Dog object:
    //       - if the supplied name is found, the first Dog object with that name is returned
    //       - if the supplied name is not found, null is returned


//TODO  Add a method  getDogById(int) which returns a Dog object:
    //       - if the supplied id is found, the Dog object with that id is returned
    //       - if the supplied id is not found, null is returned




    //------------------------------------
    // LISTING METHODS - Basic and ADvanced
    //------------------------------------

    //TODO Add a method, listAllDogs().  The return type is String.
    //     This method returns a list of the dogs stored in the array list.
    //     Each dog should be on a new line and should be preceded by the index number e.g.
    //        0: dog 1 Details
    //        1: dog 2 Details
    //    If there are no dogs stored in the array list, return a string that contains "There are no dogs registered at the moment".

    //TODO Add a method, listAllDangerousDogs().  The return type is String.
    //     This method returns a list of the dogs stored in the array list that are dangerous.
    //     Each dog should be on a new line and should be preceded by the index number e.g.
    //        0: dog 1 Details
    //        1: dog 2 Details
    //    If there are no dogs stored in the array list, return a string that contains "There are no dogs registered at the moment".
    //    If there are  dogs stored in the array list, but no dangerous then
    //    return a string that contains "No Dangerous Breeds at the moment".

    //TODO Add a method, listAllDogsByOwner(Owner).  The return type is String.
    //     This method returns a list of the dogs stored in the array with that Owner.
    //     Each dog should be on a new line and should be preceded by the index number e.g.
    //        0: dog 1 Details
    //        1: dog 2 Details
    //    If there are no dogs stored in the array list, return a string that contains "There are no dogs registered at the moment".
    //    If there are  dogs stored in the array list, but none have that owner
    //    return a string that contains "No Dogs have that owner";


    //TODO Add a method, listAllDogsThatStayMoreThanDays(int).  The return type is String.
    //     This method returns a list of the dogs stored in the array that stay the inputed day or longer.
    //     Each dog should be on a new line and should be preceded by the index number e.g.
    //        0: dog 1 Details
    //        1: dog 2 Details
    //    If there are no dogs stored in the array list, return a string that contains "There are no dogs registered at the moment".
    //    If there are  dogs stored in the array list, but none for more than the input number of days
    //    return a string that contains "No Dogs stay longer than 2 days at the moment";;




    //-------------------------------------
    //  Counting Methods
    //-------------------------------------

    //TODO Add a method, numberOfDogs().  The return type is int.
    //     This method returns the number of dogs objects currently stored in the array list.

    //TODO Add a method, numberOfDangerousDogs().  The return type is int.
    //     This method returns the number of dangerous dogs objects currently stored in the array list.

    //TODO Add a method, getWeeklyIncome().  The return type is double.
    //     This method returns the amount received from all the dogs per week.

    //TODO Add a method, getAverageNumDaysPerWeek().  The return type is int.
    //     This method returns the average number of days dogs stay in the Day Care.


    //------------------------------
    //  FINDING METHODS
    //-------------------------------

    //TODO Add a method, findDogByName(String).  The return type is Dog.
    //    This method returns the first dog with the name that was passed as a parameter.
    //    However, if the name is not found, null is returned.

    //TODO Add a method, findDogByOwnerAndBreedAndAge(String , String , int ).  The return type is Dog.
    //    This method returns the first dog with the name, breeed and age that was passed as a parameter.
    //    However, if the name is not found, null is returned.



    //------------------------------
    //  SEARCHING METHODS
    //-------------------------------

    //TODO Add a method, searchDogsByName(String).  The return type is String.
    //    This method returns a list of the dogs whose name contains the string passed as a parameter.
    //    Each matching dog should be on a new line and should be preceded by the index number e.g.
    //        1: dog 2 Details
    //        4: dog 5 Details
    //    If there are no dogs stored in the array list, return a string that contains "No dogs".
    //    If there are no dogs whose name contains the supplied string, the return string should
    //    have "No dogs found with that name".


    //TODO Add a method, searchDogsByOwnersName(String).  The return type is String.
    //    This method returns a list of dogs whose owner name contains the string passed
    //    as a parameter.
    //    Each dog should be on a new line and should contain the dog name and breed e.g.
    //        Buddy (Golden Retriver)
    //        Jack (Jack Russel)
    //    If there are no dogs stored in the array list, return a string that contains "No dogs".
    //    If there are no dogs whose owner name contains the supplied string, the return string should
    //    have "No dogs found for this owner.



    //---------------------------------
    //  Methods for Persistence
    // --------------------------------

    //TODO Add a method, load().  The return type is void.
    //    This method uses the XStream component to deserialise the playList object and their associated owners from
    //    an XML file into the Dogs array list.


    //TODO Add a method, save().  The return type is void.
    //    This method uses the XStream component to serialise the playList object and their associated owners to
    //    an XML file.

}



