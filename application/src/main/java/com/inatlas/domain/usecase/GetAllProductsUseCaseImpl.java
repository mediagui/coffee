package com.inatlas.domain.usecase;

import com.inatlas.domain.entity.Product;
import com.inatlas.domain.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GetAllProductsUseCaseImpl implements GetAllProductsUseCase {

  private final ProductRepository productRepository;

  public GetAllProductsUseCaseImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public Optional<List<Product>> getAllProducts() {
    return productRepository.findAll();
  }
}
