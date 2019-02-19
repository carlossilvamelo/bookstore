package com.api.bookstore.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.api.bookstore.models.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
