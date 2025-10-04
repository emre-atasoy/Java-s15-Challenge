package com.workintech.library.model;

public class Faculty extends Member {


    public Faculty(int id, String name) {
        super(id, name);
        this.maxBooksAllowed = 7;
    }
}
