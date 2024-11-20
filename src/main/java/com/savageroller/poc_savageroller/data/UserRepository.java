package com.savageroller.poc_savageroller.data;

import com.savageroller.poc_savageroller.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);
}
