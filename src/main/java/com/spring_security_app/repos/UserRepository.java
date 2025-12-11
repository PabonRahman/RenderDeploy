package com.spring_security_app.repos;

import com.spring_security_app.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String> {

    boolean existsByUserNameIgnoreCase(String userName);

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByUserName(String username);

    boolean existsByEmail(String email);
    // Fetch all products with pagination and sorting
    Page<User> findAll(Pageable pageable);


    Page<User> findByUserNameContainingIgnoreCaseOrUserFirstNameContainingIgnoreCaseOrUserLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
            String userName, String firstName, String lastName, String email, Pageable pageable);

}
