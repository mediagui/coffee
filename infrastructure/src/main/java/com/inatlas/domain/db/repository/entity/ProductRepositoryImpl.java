/**
 * This is an implementation of the ProductRepository interface.
 * It provides methods to access and manipulate Product entities in the database.
 */
package com.inatlas.domain.db.repository.entity;

import com.inatlas.domain.db.mapper.ProductMapper;
import com.inatlas.domain.db.repository.jpa.ProductJpaRepository;
import com.inatlas.domain.entity.Product;
import com.inatlas.domain.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
  private final ProductJpaRepository productJpaRepository;
  private final ProductMapper productMapper;
  
  public ProductRepositoryImpl(ProductJpaRepository productJpaRepository,
                               ProductMapper productMapper) {
    this.productJpaRepository = productJpaRepository;
    this.productMapper = productMapper;
  }
  
  /**
   * Retrieves all products from the database.
   * 
   * @return A list of Product entities.
   */
  @Override
  public List<Product> findAll() {
    return productMapper.toDomainList(productJpaRepository.findAll());
  }
  
  /**
   * Retrieves the total number of products in the database.
   * 
   * @return The count of products.
   */
  @Override
  public int count() {
    return (int) productJpaRepository.count();
  }
  
  /**
   * Retrieves a product by its ID.
   * 
   * @param choice The ID of the product to retrieve.
   * @return An Optional containing the Product entity, or an empty Optional if not found.
   * @throws RuntimeException if the product is not found.
   */
  @Override
  public Optional<Product> findById(int choice) {
    return Optional.ofNullable(productJpaRepository.findById(choice)
            .map(productMapper::toDomain)
            .orElseThrow(() -> new RuntimeException("Product not found")));
  }
  
  /**
   * Retrieves a product by its name.
   * 
   * @param name The name of the product to retrieve.
   * @return An Optional containing the Product entity, or an empty Optional if not found.
   */
  @Override
  public Optional<Product> findByName(String name) {
    return productJpaRepository.findByName(name)
            .map(productMapper::toDomain);
  }
}
