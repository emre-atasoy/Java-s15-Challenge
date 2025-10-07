package com.workintech.library.repository;

import com.workintech.library.model.Book;
import com.workintech.library.model.BorrowRecord;
import com.workintech.library.model.Invoice;
import com.workintech.library.model.Member;

import java.util.*;

//Tüm verilerin tutulduğu geçici bellek deposudur.

//Map ve List yapıları ile kitap, üye, kayıt, fatura gibi verileri saklar.

//LibraryService bu sınıfı kullanarak verilere ulaşır (composition ilişkisi).

public class InMemoryRepository {


    private final Map<Integer, Book> books = new HashMap<>();
    private final Map<Integer, Member> members = new HashMap<>();
    private final Map<Integer, BorrowRecord> borrowRecords = new HashMap<>();
    private final Map<Integer, Invoice> invoices = new HashMap<>();

    // Book ops
    public void addBook(Book b) { books.put(b.getId(), b); }
    public Book getBook(int id) { return books.get(id); }
    public Book removeBook(int id) { return books.remove(id); }
    public Collection<Book> getAllBooks() { return books.values(); }

    public List<Book> findBooksByTitle(String title) {
        List<Book> res = new ArrayList<>();
        for (Book b : books.values()) if (b.getTitle().toLowerCase().contains(title.toLowerCase())) res.add(b);
        return res;
    }

    public List<Book> findBooksByAuthor(String author) {
        List<Book> res = new ArrayList<>();
        for (Book b : books.values()) if (b.getAuthor().toLowerCase().contains(author.toLowerCase())) res.add(b);
        return res;
    }


    // Member islemleri

    public void addMember(Member member) {
        members.put(member.getId(), member);
    }

    public Member getMember(int id) {
        return members.get(id);
    }

    public Collection<Member> getAllMembers(){
        return members.values();
    }


    // BorrowRecord islemleri
    public void addBorrowRecord(BorrowRecord r) { borrowRecords.put(r.getId(), r); }
    public BorrowRecord getBorrowRecord(int id) { return borrowRecords.get(id); }
    public Collection<BorrowRecord> getAllBorrowRecords() { return borrowRecords.values(); }

    // Invoice islemleri
    public void addInvoice(Invoice inv) { invoices.put(inv.getId(), inv); }
    public Invoice getInvoice(int id) { return invoices.get(id); }
    public Collection<Invoice> getAllInvoices() { return invoices.values(); }



}
