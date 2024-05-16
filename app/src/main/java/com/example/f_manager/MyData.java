package com.example.f_manager;

public class MyData {
    private int id;
    private String name;
    private int age;

    public MyData(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
