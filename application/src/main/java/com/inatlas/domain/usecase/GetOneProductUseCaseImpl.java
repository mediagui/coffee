package com.inatlas.domain.usecase;

import com.inatlas.domain.entity.Product;
import com.inatlas.domain.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetOneProductUseCaseImpl implements GetOneProductUseCase {

  private final ProductRepository productRepository;

  public GetOneProductUseCaseImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public Optional<Product> getTheProduct(int id) {
    return productRepository.findById(id);
  }
}
