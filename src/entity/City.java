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

}
