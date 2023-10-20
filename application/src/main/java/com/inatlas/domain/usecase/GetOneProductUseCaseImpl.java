package com.inatlas.domain.usecase;

import com.inatlas.domain.entity.Product;
import com.inatlas.domain.repository.ProductRepository;
import org.springframework.stereotype.Component;

@Component
public class GetAProductUseCaseImpl implements GetAProductUseCase {

  private ProductRepository productRepository;

  public GetAProductUseCaseImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public Optional<Product> getTheProduct(int id) {
    return productRepository.findById(id);
  }
}
