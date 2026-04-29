 



public class Parrot extends Bird {
    private String vocabularySize;

    public Parrot(String name, int age, Owner owner, int id, double wingSpan, boolean canFly, int vocabularySize) {
        super(name, age, owner, id, wingSpan, canFly);
        this.vocabularySize = BirdUtility.getVocabularyLevel(vocabularySize);
    }

    @Override
    public double calculateWeeklyFee() {

        return 10 * super.numOfDaysAttending();
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
