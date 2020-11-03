package org.example.designpattern.iterator;

import java.util.Iterator;
import java.util.function.Consumer;

public class BookShelfIterator implements Iterator {

    private BookShelf bookShelf;

    private int index;

    public BookShelfIterator(BookShelf bookShelf) {
        this.bookShelf = bookShelf;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        if(this.index < this.bookShelf.getLength()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object next() {
        return this.bookShelf.getBookAt(this.index++);
    }

    @Override
    public void remove() {

    }

    @Override
    public void forEachRemaining(Consumer action) {

    }
}
