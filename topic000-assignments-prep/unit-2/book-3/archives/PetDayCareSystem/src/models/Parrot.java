package models;

import utils.BirdUtility;

public class Parrot extends Bird {
    private String vocabularySize;

    public Parrot(String name, int age, Owner owner, int id, double wingSpan, boolean canFly, int vocabularySize) {
        super(name, age, owner, id, wingSpan, canFly);
        this.vocabularySize = BirdUtility.getVocabularyLevel(vocabularySize);
    }

    @Override
    public double calculateWeeklyFee() {
        double fee = 0;
        boolean[] days = getDaysAttending();
        for (int i = 0; i < days.length; i++) {
            if (days[i]) {
                fee += 10;  // base rate for parrot

            }
        }
        return fee;
    }

    public String getVocabularySize() {
        return vocabularySize;
    }

    public void setVocabularySize(int vocabularySize) {
        this.vocabularySize = BirdUtility.getVocabularyLevel(vocabularySize);

    }

    @Override
    public String toString() {
        return "Parrot{" + super.toString()+
                "vocabularySize='" + vocabularySize + '\'' +
                '}';
    }
}
