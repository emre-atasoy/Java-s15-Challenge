package com.workintech.library.model;


import java.time.LocalDate;

public class Invoice {

    private final int id;
    private final int memberId;
    private final int bookId;
    private final LocalDate date;
    private final double amount;
    private final String type;  // CHARGE = TAHSILAT , REFUND = IADE ****


    //Kullanıcılara kitap kiralama ücreti veya iade sonrası para iadesi oluşturmak için kullanılır.
    //LibraryService bu nesneyi oluşturur ve InMemoryRepository içinde saklanır.

    public Invoice(int id, int memberId, int bookId, double amount, String type) {
        this.id = id;
        this.memberId = memberId;
        this.bookId = bookId;
        this.date = LocalDate.now();
        this.amount = amount;
        this.type = type;
    }

    public int getId() { return id; }
    public int getMemberId() { return memberId; }
    public int getBookId() { return bookId; }
    public LocalDate getDate() { return date; }
    public double getAmount() { return amount; }
    public String getType() { return type; }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Invoice)) return false;

        Invoice invoice = (Invoice) o;

        return this.id == invoice.id;
    }

    @Override
    public int hashCode(){
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        return String.format("Invoice{id=%d, memberId=%d, bookId=%d, date=%s, amount=%.2f, type=%s}",
                id, memberId, bookId, date, amount, type);
    }

}
