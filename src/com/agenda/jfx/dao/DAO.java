package com.agenda.jfx.dao;

import java.util.List;

/**
 *
 * @author tayron
 */
public interface DAO<T, K> {
    void add(T a) throws Exception;
    void update(T a) throws Exception;
    void delete(K id) throws Exception;
    T getOne(K id) throws Exception;
    List<T> getAll() throws Exception;
}
