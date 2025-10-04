package com.workintech.library.model;

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
    public String toString() {
        return "Librarian{name='" + name + "'}";
    }
}
