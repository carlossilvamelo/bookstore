package com.api.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.bookstore.models.BookOrder;

@Repository
public interface BookOrderRepository extends JpaRepository<BookOrder, Long>{

}
