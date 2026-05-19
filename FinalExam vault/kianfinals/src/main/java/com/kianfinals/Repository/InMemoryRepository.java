package com.kianfinals.Repository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRepository<T>
        implements Repository<T> {

    private final List<T> items = new ArrayList<>();

    public void add(T item) {
        items.add(item);
    }
    public List<T> getAll() {
        return items;
    }
    public void remove(T item) {
        items.remove(item);
    }
}