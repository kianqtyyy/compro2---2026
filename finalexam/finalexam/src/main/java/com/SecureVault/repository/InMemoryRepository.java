package com.SecureVault.repository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRepository<T>
        implements Repository<T> {

    private final List<T> items = new ArrayList<>();

    @Override
    public void add(T item) {
        items.add(item);
    }

    @Override
    public List<T> getAll() {
        return items;
    }

    @Override
    public void remove(T item) {
        items.remove(item);
    }
}
