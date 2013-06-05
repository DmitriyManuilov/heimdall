package ru.heimdall.eye.dao;

public interface DaoFactory {

    <T> BaseDaoImpl<T> getDao (Class<T> type);

}
