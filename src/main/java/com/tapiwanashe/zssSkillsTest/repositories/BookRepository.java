package com.tapiwanashe.zssSkillsTest.repositories;

import com.tapiwanashe.zssSkillsTest.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository  extends JpaRepository<Book, Long> {
}
