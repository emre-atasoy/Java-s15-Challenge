package com.workintech.library.model;

import java.time.LocalDate;
import java.util.Objects;

public class Book {

    private final int id;
    private String title;
    private String author;
    private Category category;
    private double price;
    private boolean borrowed;
    private LocalDate dateOfPurchase;
    private String edition;


    public Book(LocalDate dateOfPurchase, String edition, double price, Category category, String author, String title, int id) {
        this.dateOfPurchase = dateOfPurchase;
        this.edition = edition;
        this.price = price;
        this.category = category;
        this.author = author;
        this.title = title;
        this.id = id;
        this.borrowed = false;
    }

    public int getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public Category getCategory(){
        return category;
    }

    public void setCategory(Category category){
        this.category = category;

    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public boolean isBorrowed(){
        return borrowed;
    }

    public void setBorrowed(boolean borrowed){
        this.borrowed =borrowed;
    }

    public LocalDate getDateOfPurchase(){
        return dateOfPurchase;
    }

    public void setDateOfPurchase(LocalDate dateOfPurchase){
        this.dateOfPurchase = dateOfPurchase;
    }

    public String getEdition(){
        return edition;
    }

    public void setEdition(String edition){
        this.edition = edition;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(!(o instanceof Book)) return false;

        Book book = (Book) o;
        return id == book.id;
    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Book{id=%d, title='%s', author='%s', category=%s, price=%.2f, borrowed=%s}",
                id, title, author, category, price, borrowed);
    }
}
