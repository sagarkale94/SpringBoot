package co.in.sagarkale.globalexceptionhandling.entity;

public class User {
    private int id;
    private String name;
    private int age;
    private boolean isActive;

    public User(int id, String name, int age, boolean isActive) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public boolean isActive() {
        return isActive;
    }
}
