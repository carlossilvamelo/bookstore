package com.api.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.bookstore.models.Credential;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, Long>{
}
