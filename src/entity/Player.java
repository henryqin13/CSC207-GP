package entity;

import java.util.List;

public class Player {
    private String name;
    private final String password;
    private int score;
    private List<Role> roles;

    public Player(String name, String password, List<Role> roles) {
        this.name = name;
        this.password = password;
        this.score = 0;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPassword(){
        return this.password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String toString() {
        return "Player{ " + "name='" + name + '\'' + ", score=" + score + ", roles=" + roles + '}';
    }
}