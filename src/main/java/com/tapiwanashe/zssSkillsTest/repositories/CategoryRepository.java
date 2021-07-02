package com.tapiwanashe.zssSkillsTest.repositories;

import com.tapiwanashe.zssSkillsTest.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository  extends JpaRepository<Category, Long> {
}
