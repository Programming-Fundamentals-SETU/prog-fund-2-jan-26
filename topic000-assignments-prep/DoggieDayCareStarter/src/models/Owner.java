package models;

import java.util.Objects;

public class Owner {
    //TODO The id (int id)  in the system is entered by the user.
    //     Default value is 100.
    //     When creating the Owner, must be an id between 100 and 999
    //     When updating an existing Owner, only update the  id if between 100 and 999
    private  int id = 100;// 3 digits - default 100

    //TODO The  name (String name)  in the system is entered by the user.
    //     Default value is "".
    //     When creating the Owner, truncate the name to 30 characters.
    //     When updating an existing Owner, only update the name if it is 30 characters or less.
    private String name;

    //TODO The  phoneNumber (String phoneNumber)  in the system is entered by the user.
    //     Default value is "UnKnown".
    //     When creating the Owner, only add numbers 
    //     When updating an existing Owner, only update if String only contains numbers.
    private String phoneNumber = "UnKnown";
    //TODO Add the constructor, Owner(int , String , String )  that adheres to the above validation rules
    public Owner(int id, String name, String phoneNumber) {
       //TODO
    }

    //TODO Add a getter and setter for each field, that adheres to the above validation rules


    //TODO Add a generated equals method.


    //TODO The toString should return the string in this format:
    //      123, Micheal Taylor, 0871234567  is a dog owner



}

