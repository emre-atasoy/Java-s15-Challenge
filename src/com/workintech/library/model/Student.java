package com.workintech.library.model;

public class Student extends Member {

    public Student(int id,String name){
        super(id,name);
        this.maxBooksAllowed = 5;
    }
}
