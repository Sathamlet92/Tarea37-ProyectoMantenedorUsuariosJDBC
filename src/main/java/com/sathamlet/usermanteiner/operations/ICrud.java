package com.sathamlet.usermanteiner.operations;

import java.util.List;

public interface ICrud<T> {
    List<T> listing();
    T getById(int id);
    boolean save(T t);
    boolean delete(int id);

}
