package org.example.designpattern.iterator;

import java.util.Iterator;

public class BookShelf implements Aggregate {

    private Book[] books;

    private int last = 0;

    public BookShelf(int maxSize) {
        this.books = new Book[maxSize];
    }

    public int getLength() {
        return this.last;
    }

    public Book getBookAt(int index) {
        return this.books[index];
    }

    @Override
    public Iterator iterator() {
        return new BookShelfIterator(this);
    }

}
