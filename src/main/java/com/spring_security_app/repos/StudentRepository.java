package com.spring_security_app.repos;

import com.spring_security_app.entity.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentModel,Integer> {

}
