package com.lexisnexis.risk.bot.service.repository;

import com.lexisnexis.risk.bot.dao.annotation.ReadData;
import com.lexisnexis.risk.bot.dao.annotation.WriteData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public class BaseRepositoryService<T, ID, R extends JpaRepository<T, ID>> {

    @Autowired
    protected R repository;

    @ReadData
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @ReadData
    public List<T> findAll() {
        return repository.findAll();
    }

    @WriteData
    public T save(T object) {
        return repository.save(object);
    }

    @WriteData
    public void delete(T object) {
        repository.delete(object);
    }

    @WriteData
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

}
