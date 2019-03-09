package com.gauravpathak.repository;

import com.gauravpathak.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote,Long> {

    List<Quote> findByUsername(String username);

    void deleteByUsername(String username);
}
