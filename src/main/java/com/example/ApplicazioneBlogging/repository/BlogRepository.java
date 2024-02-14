package com.example.ApplicazioneBlogging.repository;

import com.example.ApplicazioneBlogging.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer>, PagingAndSortingRepository<Blog, Integer> {
}
