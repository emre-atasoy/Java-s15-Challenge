package com.workintech.library.ui;

import com.workintech.library.model.*;
import com.workintech.library.repository.InMemoryRepository;
import com.workintech.library.service.LibraryService;
import com.workintech.library.util.IdGenerator;

import java.io.Console;
import java.util.Scanner;


//Konsol üzerinden kullanıcı etkileşimini sağlar.
// LibraryService sınıfını çağırarak tüm işlemleri yönlendirir.

public class ConsoleApp {

    private final LibraryService service;
    private final InMemoryRepository repo;
    private final Scanner sc = new Scanner(System.in);


    public ConsoleApp() {
        repo = new InMemoryRepository();
        service = new LibraryService(repo);
        seedData();
    }

    private void seedData() {
        Student student = new Student(IdGenerator.nextId(), "Emre ATASOY");
        Faculty faculty = new Faculty(IdGenerator.nextId(), "Şebnem KIRBAS");
        repo.addMember(student);
        repo.addMember(faculty);

        Book book1 = service.addBook("Java Programming", "John Doe", Category.TECHNOLOGY, 99.0, "3rd");
        Book book2 = service.addBook("Clean Code", "Robert C. Martin", Category.TECHNOLOGY, 80.0, "1st");
        Book book3 = service.addBook("World History", "Jane Smith", Category.GENERAL, 50.0, "2nd");

        System.out.println("Seed data added. Sample member IDs: " + student.getId() + ", " + faculty.getId());
        System.out.println("Sample book IDs: " + book1.getId() + ", " + book2.getId() + ", " + book3.getId());
    }


    public void start() {
        while(true){
            System.out.println("=== Library App ===");
            System.out.println("1) List all books");
            System.out.println("2) Add book");
            System.out.println("3) Remove book");
            System.out.println("4) Update book");
            System.out.println("5) Search by title");
            System.out.println("6) Search by author");
            System.out.println("7) List by category");
            System.out.println("8) Borrow book");
            System.out.println("9) Return book");
            System.out.println("10) Show invoices");
            System.out.println("0) Exit");
            System.out.print("Choose: ");
            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException nfe){
                System.out.println("Invalid number");
                continue;
            }

            try {
                switch (choice){
                    case 1: listAll(); break;
                    case 2: addBookInteractive(); break;
                    case 3: removeBookInteractive(); break;
                    case 4: updateBookInteractive(); break;
                    case 5: searchTitle(); break;
                    case 6: searchAuthor(); break;
                    case 7: listByCategory(); break;
                    case 8: borrowInteractive(); break;
                    case 9: returnInteractive(); break;
                    case 10: showInvoices(); break;
                    case 0: System.out.println("Bye"); return;
                    default: System.out.println("Invalid option");
                }

            }catch (Exception ex){
                System.out.println("Error: " + ex.getMessage());
            }


        }
    }

    private void listAll(){
        service.listAllBooks().forEach(System.out::println);
    }

    private void addBookInteractive(){
        System.out.println("Title: "); String title = sc.nextLine();
        System.out.println("Author: "); String author = sc.nextLine();
        System.out.println("Category (GENERAL,SCIENCE,TECHNOLOGY,FICTION,NONFICTION,MAGAZINE,STUDYBOOK): ");
        Category cat = Category.valueOf(sc.nextLine().toUpperCase());
        System.out.println("Price: "); double price = Double.parseDouble(sc.nextLine());
        System.out.println("Edition "); String edition = sc.nextLine();
        Book book = service.addBook(title, author, cat, price, edition);
        System.out.println("Added: " + book);
    }

    private void removeBookInteractive(){
        System.out.println("Book id: "); int id = Integer.parseInt(sc.nextLine());
        boolean ok = service.removeBook(id);
        System.out.println(ok ? "Removed" : "Cannot remove(not found or borrowed)");
    }

    private void updateBookInteractive(){
        System.out.println("Book id: "); int id = Integer.parseInt(sc.nextLine());
        System.out.println("New title (blank to skip): "); String title = sc.nextLine();
        System.out.println("New author (blank to skip): "); String author = sc.nextLine();
        System.out.println("New price (blank to skip): "); String p = sc.nextLine();
        Double price = p.isEmpty() ? null : Double.parseDouble(p);
        boolean ok = service.uptadeBook(id,title,author,price);
        System.out.println(ok ? "Updated" : "Not found");

    }

    private void searchTitle(){
        System.out.println("Title fragment: "); String title = sc.nextLine();
        service.findByTitle(title).forEach(System.out::println);
    }

    private void searchAuthor(){
        System.out.println("Author fragment: "); String author = sc.nextLine();
        service.findByAuthor(author).forEach(System.out::println);
    }

    private void listByCategory(){
        System.out.println("Category: "); Category category = Category.valueOf(sc.nextLine().toUpperCase());
        service.findByCategory(category);
    }

    private void borrowInteractive(){
        System.out.println("Member id: "); int memberId= Integer.parseInt(sc.nextLine());
        System.out.println("Book id "); int bookId = Integer.parseInt(sc.nextLine());
        BorrowRecord record = service.borrowRecord(memberId,bookId);
        System.out.println("Borrowe: " +record);
    }

    private void returnInteractive(){
        System.out.print("Member id: "); int memberId = Integer.parseInt(sc.nextLine());
        System.out.print("Book id: "); int bookId = Integer.parseInt(sc.nextLine());
        double fee = service.returnBook(memberId,bookId);
        System.out.println("Returned. Fee: " + fee);
    }

    private void showInvoices() {
        service.getInvoices().forEach(System.out::println);
    }

}
