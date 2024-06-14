package com.rentme.app.security.repository;

import com.rentme.app.security.entity.RedirectUri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RedirectUriRepository extends JpaRepository<RedirectUri, Long> {

    Optional<RedirectUri> findByUri(String uri);

}
