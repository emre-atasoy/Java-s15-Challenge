package com.workintech.library.model;

import java.time.LocalDate;

public class BorrowRecord {
    private final int id;
    private final int bookId;
    private final int memberId;
    private final LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnedDate;
    private double fee;


    public BorrowRecord(LocalDate dueDate, int id, int bookId, int memberId, LocalDate borrowDate) {
        this.dueDate = dueDate;
        this.id = id;
        this.bookId = bookId;
        this.memberId = memberId;
        this.borrowDate = borrowDate;
    }

    public int getId(){
        return id;
    }

    public int getBookId() {
        return bookId;
    }

    public int getMemberId(){
        return memberId;
    }

    public LocalDate getBorrowDate(){
        return borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate; }


    public LocalDate getReturnedDate() { return returnedDate;
    }

    public void setReturnedDate(LocalDate returnedDate) {
        this.returnedDate = returnedDate; }


    public double getFee() {
        return fee; }


    public void setFee(double fee) {
        this.fee = fee; }


    public String toString() {
        return String.format("BorrowRecord{id=%d, bookId=%d, memberId=%d, borrow=%s, due=%s, returned=%s, fee=%.2f}",
                id, bookId, memberId, borrowDate, dueDate, returnedDate, fee);
    }

}
