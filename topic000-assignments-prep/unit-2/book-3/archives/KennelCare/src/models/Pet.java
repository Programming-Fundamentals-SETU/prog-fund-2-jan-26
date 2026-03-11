package models;

public abstract class Pet {
    private String name;
    private int age;
    private Owner owner;
    private boolean[] daysAttending;  // 0=Mon ... 6=Sun
    private int id = 1000; // 4 digits - default 1000

    public Pet(String name, int age,  Owner owner, int id) {
        initName(name);
        this.age = age;
        this.owner = owner;
        this.daysAttending = new boolean[7];
        setId(id);
    }

    public String getName() {
        return name;
    }
    public Owner getOwner() {
        return owner;
    }

    public int getAge() {
        return age;
    }



    public int getId() {
        return id;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public void setDaysAttending(boolean[] daysAttending) {
        this.daysAttending = daysAttending;
    }

    public boolean[] getDaysAttending() {
        return daysAttending;
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
    public void setAge(int age) {
        if (age >= 0 && age <= 20) this.age = age;
    }

    public void checkIn(int dayIndex) {
        if (dayIndex >= 0 && dayIndex < 7) {
            daysAttending[dayIndex] = true;
        }
    }
    public void checkOut(int dayIndex) {
        if (dayIndex >= 0 && dayIndex < 7) {
            daysAttending[dayIndex] = false;
        }
    }

    public int numOfDaysInKennel(){
        int days = 0;
        for (boolean d:daysAttending)
            if(d) days++;
        return days;
    }

    /** Calculate weekly fee for this pet. Subclasses override if needed. */
    public abstract double calculateWeeklyFee() ;

    @Override
    public String toString() {
        String days = "";
        String[] names = { "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" };
        for (int i = 0; i < daysAttending.length; i++) {
            if (daysAttending[i]) {
                days += names[i] + " ";
            }
        }
        if (days.equals("")) {
            days = "None";
        }
        return "Name: " + name + " | Age: " + age  +
                "\n" + owner.toString() +
                "\nDays attending: " + days;
    }
}

