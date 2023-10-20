package com.inatlas.domain.usecase;

import com.inatlas.domain.entity.Product;
import com.inatlas.domain.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllProductsUseCaseImpl implements GetAllProductsUseCase {

  private ProductRepository productRepository;

  public GetAllProductsUseCaseImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }
}
