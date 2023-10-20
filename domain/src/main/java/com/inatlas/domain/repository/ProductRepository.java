package com.inatlas.domain.repository;

import com.inatlas.domain.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

  List<Product> findAll();

  int count();

  Optional<Product> findById(int choice);

  Optional<Product> findByName(String name);
}
