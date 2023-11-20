package entity;

public class WordFact implements Fact{
    private String category;
    private String value;

    public void Fact(String category, String value) {
        this.category = category;
        this.value = value;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCategory(){
        return category;
    }

    public String getValue(){
        return value;
    }
}
