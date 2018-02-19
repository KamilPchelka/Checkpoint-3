package com.codecool.kamilpchelka.checkpoint3.models;

public class Author extends Person {
    private int id;

    public Author(String name, String surname, String city, String country) {
        super(name, surname, city, country);
    }

    public Author(String name, String surname, String city, String country, int id) {
        super(name, surname, city, country);
        this.id = id;
    }

    public Author() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                '}';
    }
}
