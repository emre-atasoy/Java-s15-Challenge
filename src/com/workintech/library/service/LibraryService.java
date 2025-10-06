package com.workintech.library.service;

import com.workintech.library.model.*;
import com.workintech.library.repository.InMemoryRepository;
import com.workintech.library.util.IdGenerator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class LibraryService {

    private final InMemoryRepository repo;

    public LibraryService(InMemoryRepository repo){
        this.repo = repo;
    }


  //* Yeni bir kitap oluşturur ve repository'e ekler.


    public Book addBook(String title, String author, Category cat, double price, String edition) {
        int id = IdGenerator.nextId();
        Book book = new Book(LocalDate.now(), edition, price, cat, author, title, id);
        repo.addBook(book);
        return book;
    }

    // Kitap ID'sine göre bir kitabı siler (eğer ödünçte değilse).

    public boolean removeBook(int bookId){
        Book book = repo.getBook(bookId);
        if(book == null) return false;
        if(book.isBorrowed()) return false;
        repo.removeBook(bookId);
        return true;
    }


   //* Mevcut bir kitabın bilgilerini (isim, yazar, fiyat) günceller.


    public boolean uptadeBook(int bookId,String newTitle,String newAuthor,Double newPrice){
        Book book = repo.getBook(bookId);
        if(book == null) return false;
        if(newTitle != null && !newTitle.isEmpty()) book.setTitle((newTitle));
        if (newAuthor != null && !newAuthor.isEmpty()) book.setAuthor(newAuthor);
        if (newPrice != null) book.setPrice(newPrice);
        return true;
    }



    //* Başlığa göre kitap araması yapar.


    public List<Book> findByTitle(String title){
        return repo.findBooksByTitle(title);
    }


    // Yazara göre arama.

    public List<Book> findByAuthor(String author) {
       return repo.findBooksByAuthor(author);
    }

    // Categorye göre arama.

    public List<Book> findByCategory(Category category) {
        return repo.getAllBooks().stream()
                .filter(b -> b.getCategory() == category)
                .collect(Collectors.toList());
    }




    //  * Tüm kitapları liste olarak döndürür.
    // repo.getAllBooks().stream().collect(Collectors.toList()) tüm kitapları List<Book> olarak dönüştürmemizi sağlıyor********
    public List<Book> listAllBooks() {
        return repo.getAllBooks().stream().collect(Collectors.toList());
    }


 // * Bir üyeye kitap ödünç verir.
 // * - Üye ve kitap kontrol edilir.
 // * - Üye limitini aşmadıysa ödünç verilir.
 // * - 14 günlük iade süresi atanır.
 // * - Depozito faturası (%10) oluşturulur.


    public BorrowRecord borrowRecord(int memberId,int bookId){
        Member member = repo.getMember(memberId);
        Book book = repo.getBook(bookId);

        if(member == null) throw new IllegalArgumentException("Member not found");
        if(book == null) throw new IllegalArgumentException("Book not found");
        if(book.isBorrowed()) throw  new IllegalStateException("Book already borrowed");
        if(!member.canBorrowMore()) new IllegalStateException("Member reached limit");

        book.setBorrowed(true);
        member.borrowBook(bookId);


        int recId = IdGenerator.nextId();
        LocalDate borrowDate = LocalDate.now();
        LocalDate due = borrowDate.plusDays(14);
        BorrowRecord rec =new BorrowRecord(due,recId,bookId,memberId,borrowDate);
        repo.addBorrowRecord(rec);

        Invoice inv = new Invoice(IdGenerator.nextId(),memberId,bookId,book.getPrice()* 0.1,"CHARGE");
        repo.addInvoice(inv);
        System.out.println("Invoice created: " + inv);
        return rec;

    }

    // * Üye bir kitabı iade eder.
    // * - Gecikme kontrolü yapılır.
    // * - Gecikme varsa ceza faturası oluşturulur.
    // * - Zamanında iade edilirse depozito iadesi faturası oluşturulur.

    public double returnBook(int memberId,int bookId){
        Member member = repo.getMember(memberId);
        Book book = repo.getBook(bookId);

        if(member == null || book == null) throw new IllegalArgumentException("Invalid ids");

        BorrowRecord found = repo.getAllBorrowRecords().stream()
                .filter(r -> r.getBookId() == bookId && r.getMemberId() == memberId && r.getReturnedDate()== null)
                .findFirst().orElse(null);

        if (found == null) throw new IllegalStateException("No active borrow record");

        found.setReturnedDate(LocalDate.now());
        long daysLate = ChronoUnit.DAYS.between(found.getDueDate(),found.getReturnedDate());
        double fee = 0.0;

        if(daysLate > 0) {
            fee = daysLate * 1.0;
            found.setFee(fee);
            Invoice inv = new Invoice(IdGenerator.nextId(),memberId,bookId,fee,"CHARGE");
            repo.addInvoice(inv);
            System.out.println("Late fee invoice created: " + inv);

        } else {
            Invoice inv = new Invoice(IdGenerator.nextId(),memberId,bookId,book.getPrice() * 0.1,"REFUND");
            repo.addInvoice(inv);
            System.out.println("Refund invoice created: " + inv);
        }

        book.setBorrowed(false);
        member.returnBook(bookId);
        return fee;
    }

    // * Tüm faturaları (invoice) liste olarak döndürür.


    public List<Invoice> getInvoices() {
        return repo.getAllInvoices().stream().collect(Collectors.toList());
    }









}
