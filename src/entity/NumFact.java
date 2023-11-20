package entity;

public class NumFact implements Fact {
    private String category;
    private long value;

    public void Fact(String category, long value) {
        this.category = category;
        this.value = value;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public String getCategory(){
        return category;
    }

    public long getValue(){
        return value;
    }
}
