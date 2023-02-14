package com.codeup.adlister.models;

public class Category {

    private long id;
    private String name;

    public Category() {
    }

    //Using two constructors as best practice to mitigate need
    public Category(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(String name) {
        this.name = name;
    }

    public long getId(){return id;}

    public void setId(long id){this.id = id;}

    public String getName(){return name;}

    public void setName(){this.name = name;}

    @Override
    public String toString() {
        return this.name;
    }

}
