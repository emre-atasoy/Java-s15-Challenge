package com.workintech.library.model;

import java.util.HashSet;
import java.util.Set;


//Metotlar: borrowBook(), returnBook(), canBorrowMore().

//Bu sınıf abstract, çünkü somut bir üye tipi değildir; sadece temel davranışları içerir.

//Student ve Faculty sınıfları bu sınıftan extends edilir.

public abstract class Member {
    private final int id;
    private String name;
    private String address;
    private String phone;
    protected int maxBooksAllowed;
    private final Set<Integer> borrowedBookIds = new HashSet<>();

    public Member(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public Set<Integer> getBorrowedBookIds() {
        return borrowedBookIds;
    }

    public boolean canBorrowMore() {
        return borrowedBookIds.size() < maxBooksAllowed;

    }

    public void borrowBook(int bookId){
        if(!canBorrowMore()) throw new IllegalStateException("Borrow limit reached");
        borrowedBookIds.add(bookId);
    }

    public void returnBook(int bookId){
        borrowedBookIds.remove(bookId);
    }

    @Override
    public String toString() { return String.format("Member{name='%s', id=%d}", name, id); }

}
