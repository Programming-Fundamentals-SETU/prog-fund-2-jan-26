 

public abstract class Mammal extends Pet {
    private char sex;
    private boolean vaccinated;
    private double weight;
    private boolean neutered; //

    public Mammal(String name, int age, Owner owner, int id, char sex, boolean vaccinated, double weight, boolean neutered) {
        super(name, age, owner, id);
        this.sex = sex;
        this.vaccinated = vaccinated;
        this.weight = weight;
        this.neutered = neutered;
    }

    public boolean isNeutered() {
        return neutered;
    }

    public void setNeutered(boolean neutered) {
        this.neutered = neutered;
    }

    public void setSex(char sex) {
        if (sex == 'm' || sex == 'M')
            this.sex = 'M';
        else if (sex == 'f' || sex == 'F')
            this.sex = 'F';
    }

    public char getSex() {
        return sex;
    }

    public boolean isVaccinated() {
        return vaccinated;
    }

    public void setVaccinated(boolean vaccinated) {
        this.vaccinated = vaccinated;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return  super.toString() +
                ",  sex: " + sex +
                ", vaccinated: " + (vaccinated ? "Yes" : "No") +
                ", neutered: " + (neutered ?  "Yes" : "No") +
                ", weight: " + weight;

    }
}
