package com.biblioteca.bibliotecaSpringBoot.repositories;


import com.biblioteca.bibliotecaSpringBoot.models.Ebook;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorieEbook extends MongoRepository<Ebook, String> {
    List<Ebook> findBytipoDeEbook(final String tipo);
    List<Ebook> findBycategoriaDeLibro(final String categoria);
}
