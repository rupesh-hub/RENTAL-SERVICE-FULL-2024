package com.rentme.app.role.repository;

import com.rentme.app.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(name="Role.findByName")
    Optional<Role> findByRole(@Param("role") String role);

    @Query(name="Role.findByRoleId")
    Optional<Role> findByRoleId(@Param("roleId") String roleId);

}
