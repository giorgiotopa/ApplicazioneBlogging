package com.example.ApplicazioneBlogging.repository;

import com.example.ApplicazioneBlogging.model.Autore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoreRepository extends JpaRepository<Autore, Integer>, PagingAndSortingRepository<Autore, Integer> {
}
