package com.workintech.library.model;

import java.util.Objects;

public class Librarian {
    private String name;
    private String password;

    public Librarian(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() { return name; }
    public boolean verifyPassword(String pw) { return password.equals(pw); }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Librarian)) return false;

        Librarian librarian = (Librarian) o;
        return Objects.equals(name,librarian.name) && Objects.equals(password,librarian.password);
    }

    @Override
    public int hashCode(){
        return Objects.hash(name,password);
    }

    @Override
    public String toString() {
        return "Librarian{name='" + name + "', password='" + password + "'}";
    }
}
