package entity;

import java.util.List;

public class Player {
    private String name;
    private int score;
    private List<Role> roles;

    public Player(String name, List<Role> roles) {
        this.name = name;
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