package models;

public class Bat extends Mammal {
    private int no_Wings= 2;
    public Bat(String name, int age, Owner owner, int id, char sex, boolean vaccinated, double weight, boolean neutered,int no_Wings) {
        super(name, age, owner, id, sex, vaccinated, weight, neutered);
        setNo_Wings(no_Wings);
    }
           public int getNo_Wings() {
        return no_Wings;
    }
    public void setNo_Wings(int no_Wings) {if (no_Wings >=0 && no_Wings <=2) {
                this.no_Wings = no_Wings;
    }
    }

    @Override
    public double calculateWeeklyFee() {
        return 0;
    }
public String toString(){return "Bat";}
}
