package com.example.megha.repository;


import com.example.megha.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findUserById(Long id);
}
