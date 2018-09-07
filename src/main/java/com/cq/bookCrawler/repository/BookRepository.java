package com.cq.bookCrawler.repository;

import com.cq.bookCrawler.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: Celine Q
 * @create: 2018-09-07 19:54
 **/
public interface BookRepository extends JpaRepository<Book, Integer> {
}

