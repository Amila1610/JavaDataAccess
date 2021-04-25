package one.dao;

import one.exception.DaoException;

import java.util.List;

public interface Dao <E>{

    public List<E> readAll() throws DaoException;

    public void writeAll(List<E> elements) throws DaoException;
}
