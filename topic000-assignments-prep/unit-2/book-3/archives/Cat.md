```plantuml
@startuml

class Pet {
    +String name
    +int age
    +char sex
    +Owner owner
    +int id
    +boolean[] getDaysAttending()
    +double calculateWeeklyFee()
}

class Cat {
    -boolean indoorCat
    -String favouriteToy

    +Cat(String name, int age, char sex, Owner owner, int id, boolean indoorCat, String favouriteToy)
    +boolean isIndoorCat()
    +void setIndoorCat(boolean indoorCat)
    +String getFavouriteToy()
    +void setFavouriteToy(String favouriteToy)
    +double calculateWeeklyFee()
    +String toString()
}

Cat --|> Pet

@enduml