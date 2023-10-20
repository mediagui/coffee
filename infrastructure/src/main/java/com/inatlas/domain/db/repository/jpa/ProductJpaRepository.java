/**
 * Repository interface for accessing and managing ProductDB entities in a database.
 * It extends the JpaRepository interface, which provides basic CRUD operations.
 * The interface also includes a custom query method to find a product by its name.
 */
package com.inatlas.domain.db.repository.jpa;

import com.inatlas.domain.db.entity.ProductDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductDB,Integer> {

  /**
   * Custom query method to find a product by its name.
   *
   * @param name the name of the product
   * @return an optional containing the product with the given name, or empty if not found
   */
  @Query("from ProductDB p where p.name = ?1")
  Optional<ProductDB> findByName(String name);
}
