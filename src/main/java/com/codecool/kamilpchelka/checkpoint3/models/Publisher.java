package com.codecool.kamilpchelka.checkpoint3.models;

public class Publisher extends Person {

    private int id;

    public Publisher(String name, String surname, String city, String country) {
        super(name, surname, city, country);
    }

    public Publisher(String name, String surname, String city, String country, int id) {
        super(name, surname, city, country);
        this.id = id;
    }

    public Publisher() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                '}';
    }
}
