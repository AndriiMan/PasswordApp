package com.example.passwordapp.repository;

import com.example.passwordapp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDataBaseRepository extends CrudRepository<User, Long> {
    //Iterable<User> findUserByName(Iterable<String> iterable);
    List<User> findUserByName(String name);
}
