package com.rentme.app.security.repository;

import com.rentme.app.security.entity.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScopeRepository extends JpaRepository<Scope, Long> {

    Optional<Scope> findByScope(String scope);

}
