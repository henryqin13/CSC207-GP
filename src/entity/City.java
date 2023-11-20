package entity;

import java.util.HashMap;

public class City {
    private String name;
    private HashMap<String, Fact> facts;

    public City(String name, HashMap<String, Fact> facts){
        this.name = name;
        this.facts = facts;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setFacts(HashMap<String, Fact> facts){
        this.facts = facts;
    }

    public HashMap<String, Fact> getFacts(){
        return this.facts;
    }
}
