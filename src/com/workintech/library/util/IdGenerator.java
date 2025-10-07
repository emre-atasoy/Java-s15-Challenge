package com.workintech.library.util;

import java.util.concurrent.atomic.AtomicInteger;


// LibraryService ve InMemoryRepository tarafından çağrılır.

// Benzersiz id


public class IdGenerator {

    private static final AtomicInteger Counter = new AtomicInteger(1000);


    private IdGenerator(){}

    public static int nextId(){
        return Counter.getAndIncrement();

    }
}
