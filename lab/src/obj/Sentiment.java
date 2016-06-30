package obj;

public class Sentiment {

    private String word;
    private Double value;

    public Sentiment(){}
    public Sentiment(String word, Double value){
        this.word = word;
        this.value = value;
    }

    public String getWord() {
        return word;
    }
    public Double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return word + ", " + value;
    }
}
