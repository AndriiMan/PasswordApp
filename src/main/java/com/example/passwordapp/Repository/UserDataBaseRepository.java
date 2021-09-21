package com.example.passwordapp.Repository;

import com.example.passwordapp.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataBaseRepository extends CrudRepository<User, Long> {
}
