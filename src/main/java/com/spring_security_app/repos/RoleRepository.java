package com.spring_security_app.repos;

import com.spring_security_app.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, String> {

    boolean existsByRoleNameIgnoreCase(String roleName);

}
