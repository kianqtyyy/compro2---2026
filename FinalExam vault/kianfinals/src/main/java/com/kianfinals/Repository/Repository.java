package com.kianfinals.Repository;

import java.util.List;

public interface Repository<T> {

    void add(T item);

    List<T> getAll();

    void remove(T item);
}